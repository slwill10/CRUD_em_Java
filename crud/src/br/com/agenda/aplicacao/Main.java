package br.com.agenda.aplicacao;

import java.awt.ContainerOrderFocusTraversalPolicy;

import br.com.agenda.dao.ContatoDAO;
import br.com.agenda.model.Contato;

public class Main {

	public static void main(String[] args) {
		Contato contato = new Contato();
		ContatoDAO contatoDAO = new ContatoDAO();
		contato.setNome("Pedro Alves Lima");
		contato.setIdade(72);
		contato.setSexo("M");
		// contatoDAO.save(contato);
		
		// Atuaizar o contato
		
		Contato c1 = new Contato();
		c1.setNome("Pedro Alves Orlando");
		c1.setIdade(80);
		c1.setSexo("F");
		c1.setId(4); //É o número que esta no banco de dados
		//contatoDAO.update(c1);
		
		// Deletar o contato pelo seu número de ID
		
		contatoDAO.deleteById(1);
		
		
		
		// visualizão do banco de dados TODOS 
		
		for(Contato c : contatoDAO.getContatos()) {
			System.out.println("contatos: " + c.getNome());
		}
		
		

	}

}
