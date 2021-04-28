package br.edu.ufersa.ropke.model.BO;

import java.io.File;

import br.edu.ufersa.ropke.model.DAO.GerenteDAO;
import br.edu.ufersa.ropke.model.VO.GerenteVO;

public class GerenteBO extends UsuarioBO {
	private static final File arquivo = GerenteDAO.getArquivo();

	public static void cadastrar(GerenteVO gerente) {
		UsuarioBO.cadastrar(gerente, arquivo);
	}

	public static void alterar(GerenteVO gerente) {
		UsuarioBO.alterar(gerente, arquivo);
	}

	public static void deletar(GerenteVO gerente) {
		UsuarioBO.deletar(gerente, arquivo);
	}

	public static boolean autenticar(GerenteVO gerente, File arquivo, String login, String senha) {
		return UsuarioBO.autenticar(gerente, arquivo, login, senha);
	}
}
