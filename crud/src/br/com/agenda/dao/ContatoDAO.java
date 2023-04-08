package br.com.agenda.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.mysql.cj.protocol.Resultset;

import br.com.agenda.factory.ConnectionFactory;
import br.com.agenda.model.Contato;

public class ContatoDAO {
	
	//CRUD
	// c: CREATE -- ok -- INSERT
	//r: READ
	//u: UPDATE
	//d: DELETE
	
	public void save(Contato contato) {

		
		String sql = "INSERT INTO contatos(nome, idade, sexo) VALUES (?, ?, ?)";
		
		Connection conn = null;
		PreparedStatement pstm = null;
		
		try {
			// conexão com o banco de dados:
			conn = ConnectionFactory.creatConnectionToMySQL();
			
			// Criamos uma prepareStatement para executar uma query:
			pstm = conn.prepareStatement(sql);
			// Adicionar os valores que são esperados pela query
			pstm.setString(1, contato.getNome());
			pstm.setInt(2, contato.getIdade());
			pstm.setString(3, contato.getSexo());
			
			
			// Executar a query
			
			System.out.println("contato salvo com sucesso!");
			
			pstm.execute();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			// fechar as conexões
			try {
				if(pstm != null) {
					pstm.close();
				}
				if(conn != null) {
					conn.close();
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public void update(Contato contato) {
		String sql = "UPDATE contatos SET nome = ?, idade = ?, sexo = ? "  + "WHERE id = ?";
		
		Connection conn = null;
		PreparedStatement pstm = null;
		
		try {
			// Criar conexão com o banco
			conn = ConnectionFactory.creatConnectionToMySQL();
			
			// Criar a classe para executar a query
			pstm = conn.prepareStatement(sql);
			
			// Adicionar os valores que vão ser inseridos
			pstm.setString(1, contato.getNome());
			pstm.setInt(2, contato.getIdade());
			pstm.setString(3, contato.getSexo());
			
			// Qual o ID do registro que deseja atualizar
			pstm.setInt(4, contato.getId());
			
			// executar a query
			pstm.execute();	
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstm!=null) {
					pstm.close();
				}
				
				if(conn != null) {
					conn.close();
				}
				
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public void deleteById(int id) {
		
		String sql = "DELETE FROM contatos WHERE id = ?";
		
		Connection conn = null;
		PreparedStatement pstm = null;
		
		try {
			conn = ConnectionFactory.creatConnectionToMySQL();
			
			pstm = conn.prepareStatement(sql);
			
			pstm.setInt(1, id);
			
			pstm.execute();
		}catch (Exception e){
			e.printStackTrace();
		}finally {
			try {
				if(pstm!= null) {
					pstm.close();
				}
				if(conn != null) {
					conn.close();
				}
				}catch(Exception e) {
					e.printStackTrace();
			}
		}
	}
	

	public List<Contato> getContatos(){
		String sql = "SELECT * FROM contatos";
		
		List<Contato> contatos = new ArrayList<Contato>();
		
		Connection conn = null;
		PreparedStatement pstm = null;
		
		// Classe que vai recuperar os dados do banco: SELECT
		ResultSet rset = null;
		
		try {
			conn = ConnectionFactory.creatConnectionToMySQL();
			
			pstm = conn.prepareStatement(sql);
			
			rset = (ResultSet) pstm.executeQuery();
			
			while (rset.next()) {
				Contato contato = new Contato();
				
				
				// Recuperar o ID				
				contato.setId(rset.getInt("id"));
				// Recuperar o nome
				contato.setNome(rset.getString("nome"));
				// Recuperar a idade
				contato.setIdade(rset.getInt("idade"));
				// recuperar o sexo
				contato.setSexo(rset.getString("sexo"));
				
				contatos.add(contato);
				}
			} catch (Exception e) {
					e.printStackTrace();
			}finally {
				try {
				if(rset!= null) {
					rset.close();
				}
				
				if(pstm != null) {
					pstm.close();
				}
				
				if(conn != null) {
					conn.close();
				}
			
				
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
	
			return contatos;
	}
		
	
}
