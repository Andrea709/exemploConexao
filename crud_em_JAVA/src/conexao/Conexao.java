package conexao;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexao {
	
	//nome do usuário do mysql
	public static final String USERNAME = "root";
	
	//senha do banco
	private static final String PASSWORD = "aluno";
	
	//caminho do banco de dados
	private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/agenda"; 
	
	/*
	 * Conexão com o banco de dados
	 */

	
	public static void main(String[]args) throws Exception {
		
		//rcuperar uma conexão com o banco de dados
		Connection con = createConnectionToMySQL();
		
		//tstar se a conexão é nula
		if(con != null) {
			System.out.println("Conexão obtida com sucesso");
			con.close();
		}
	}
	
	public static Connection createConnectionToMySQL() throws Exception {
		//faz com que a classe seja carregada pela JVM
		Class.forName("com.mysql.cj.jdbc.Driver");
		//Cria a conexão em si
		Connection connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
		
		return connection;
	}
	
}
