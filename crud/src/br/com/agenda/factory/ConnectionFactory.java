package br.com.agenda.factory;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionFactory {
	
	// nome do usuário do mySQL: 
	
	private static final String USERNAME = 	"root";
	
	// senha do banco: 
	
	private static final String PASSWORD = 	"";
	
	// caminho para o banco de dados, porta e o nome do banco de dados:
	
	private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/agenda";
	
	/*
	 * conexão com o banco de dados:
	*/
	
	public static Connection creatConnectionToMySQL() throws Exception {
		// Faz com que a classe seja carregada pela JVM
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		Connection connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
		
		return connection;
	}
	
	public static void main(String[] args) throws Exception {
		
		// Recuperar uma conexão com o banco de dados
		
		Connection con = creatConnectionToMySQL();
		
		// Testar se a conexão é nula 
		
		if(con!=null) {
			System.out.println("conexão obetida com sucesso!");
			con.close();
		}
}
}

	
