package agenda.DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;

import conexao.Conexao;
import model.Contato;

public class ContatoDAO {
	/*
	 * CRUD
	 * c: create - OK
	 * r: read
	 * u: update
	 * d: delete
	 */
	
	public void save(Contato contatos) {
		String sql = "INSERT INTO  contato(nome, idade, dataCadastro) VALUES (?, ?,?)";
		
		Connection conn = null;
		PreparedStatement pstm = null;
		
		try {
			
			//criar uma conexão com o banco de dados
			conn = Conexao.createConnectionToMySQL();
			//cria uma prepared statement para executar uma query
			pstm = (PreparedStatement) conn.prepareStatement(sql);
			
			//adicionar valores esperados pea query
			pstm.setString(1, contatos.getNome());
			pstm.setInt(2, contatos.getIdade());
			pstm.setDate(3, new Date(contatos.getDataCadastro().getTime()));
			
			//executar a query
			pstm.execute();
			
			System.out.println("Cadastrado com sucesso:D");
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			
			//fechar as conexões
			try {
				if(pstm != null) {
					pstm.close();
				}
				if(conn != null) {
					conn.close();
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
		
	}
}
