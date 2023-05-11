package agenda_Aplicacao;

import java.sql.Date;

import agenda.DAO.ContatoDAO;
import model.Contato;

public class Main {
	public static void main(String[] args) {
		
		ContatoDAO contatoDao = new ContatoDAO();
		
		Contato contatos = new Contato();
		contatos.setNome("Maria Gabriela");
		contatos.setIdade(55);
		contatos.setDataCadastro(new Date(0));
		
		contatoDao.save(contatos);
		
		
	}
}
