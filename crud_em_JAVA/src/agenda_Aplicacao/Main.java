package agenda_Aplicacao;

import java.sql.Date;

import agenda.DAO.ContatoDAO;
import model.Contato;

public class Main {
	public static void main(String[] args) {
		
		ContatoDAO contatoDao = new ContatoDAO();
		
		Contato contatos = new Contato();
		contatos.setNome("Joseph Joestar");
		contatos.setIdade(22);
		contatos.setDataCadastro(new Date(0));
		
		//SALVAR CONTATO
		contatoDao.save(contatos);
		
		//atualizar contato
		Contato c1 = new Contato();
		c1.setNome("José Bezerra");
		c1.setIdade(37);
		c1.setDataCadastro(new Date(0));
		c1.setId(2); //numero que está no banco de dados
		
		
		//chamar o contato dao
		//ATUALIZAR CONTATO
		contatoDao.update(c1);
		
		
		//deletar o contato pelo numero de id
		//DELETAR CONTATO
		contatoDao.deleteById(6);
		
		//visualização do registro do banco de dados = TODOS os registros
		for(Contato c  :contatoDao.getContatos()) {
			System.out.println("contato: " + c.getNome());
		}
		
		
		
	}
}
