package br.edu.ufersa.ropke.model.BO;

import java.io.File;

import br.edu.ufersa.ropke.model.DAO.FuncionarioDAO;
import br.edu.ufersa.ropke.model.VO.FuncionarioVO;

public class FuncionarioBO extends UsuarioBO {
	private static final File arquivo = FuncionarioDAO.getArquivo();

	public static void cadastrar(FuncionarioVO funcionario) {
		UsuarioBO.cadastrar(funcionario, arquivo);
	}

	public static void alterar(FuncionarioVO funcionario) {
		UsuarioBO.alterar(funcionario, arquivo);
	}

	public static void deletar(FuncionarioVO funcionario) {
		UsuarioBO.deletar(funcionario, arquivo);
	}

	public static void pesquisar() {
		UsuarioBO.pesquisar(arquivo);
	}

	public static boolean autenticar(FuncionarioVO funcionario, File arquivo, String login, String senha) {
		return UsuarioBO.autenticar(funcionario, arquivo, login, senha);
	}
}
