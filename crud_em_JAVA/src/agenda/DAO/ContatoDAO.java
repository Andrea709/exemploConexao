package agenda.DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import conexao.Conexao;
import model.Contato;

public class ContatoDAO {
	/*
	 * CRUD
	 * c: create - OK -
	 * r: read - OK - 
	 * u: update - OK -
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
	
	
	public void update(Contato contato) {
		String sql = "UPDATE contato SET nome = ?, idade = ?, dataCadastro = ?" +
		"WHERE id = ?";
		
		Connection conn = null;
		PreparedStatement pstm = null;
		
		try {
			//criar conexão com o banco
			conn = Conexao.createConnectionToMySQL();
			
			//criar a classe para executar a query
			pstm = (PreparedStatement) conn.prepareStatement(sql);
			
			//adicionar os valores novos para atualizar 
			pstm.setString(1, contato.getNome());
			pstm.setInt(2, contato.getId());
			pstm.setDate(3,  new Date(contato.getDataCadastro().getTime()));
			
			//qual o id do registro que deseja atualizar:
			pstm.setInt(4, contato.getId());
			
			//executar a query
			pstm.execute();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
				if(pstm != null) {
					pstm.close();
				}
				
				if(conn != null) {
					conn.close();
				}
			}catch (Exception e){
				e.printStackTrace();
			}
		}
	}
	
	public void deleteById(int id) {
		
		String sql = "DELETE FROM contato WHERE id = ?";
		
		Connection conn = null;
		
		PreparedStatement pstm = null;
		
		try {
			conn = Conexao.createConnectionToMySQL();
			
			pstm = (PreparedStatement) conn.prepareStatement(sql);
			
			pstm.setInt(1, id);
			
			pstm.execute();
		}catch (Exception e){
			e.printStackTrace();
		}finally {
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
	
 	public List<Contato> getContatos(){
		String sql = "SELECT * FROM contato";
		
		List<Contato> contatos = new ArrayList<Contato>();
		
		Connection conn = null;
		PreparedStatement pstm = null;
		
		//diferente da listagem de contato, é uma classe que vai reuperar os dados do banco **select
		ResultSet rset = null;
		
		try {
			conn  = Conexao.createConnectionToMySQL();
			
			pstm = (PreparedStatement)conn.prepareStatement(sql);
			
			rset = pstm.executeQuery();
			
			while (rset.next()) {
				
				Contato contato = new Contato();
				
				//Recuper o id
				contato.setId(rset.getInt("id"));
				//Recuper o nome
				contato.setNome(rset.getString("nome"));
				//Recuperar a idade
				contato.setId(rset.getInt("idade"));
				//Recuperar a data de cadastro
				contato.setDataCadastro(rset.getDate("dataCadastro"));
				
				contatos.add(contato);
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				
				try{
					if(rset != null) {
						rset.close();
					}
					
					if(pstm != null) {
						pstm.close();
					}
					
					if(conn != null) {
						conn.close();
					}	
				}catch (Exception e) {
					e.printStackTrace();
				}
				

			}
	
			return contatos;		
		
	}
}
