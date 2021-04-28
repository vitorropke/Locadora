package br.edu.ufersa.ropke.model.DAO;

import java.io.File;

import br.edu.ufersa.ropke.model.VO.FuncionarioVO;

public class FuncionarioDAO extends UsuarioDAO {
	private static final File arquivo = new File("src/br/edu/ufersa/ropke/model/DAO/arquivos/funcionarios.dat");

	public static File getArquivo() {
		return arquivo;
	}

	public static void cadastrar(FuncionarioVO funcionario) {
		UsuarioDAO.cadastrar(funcionario, arquivo);
	}

	public static void alterar(FuncionarioVO funcionario) {
		UsuarioDAO.alterar(funcionario, arquivo);
	}

	public static void deletar(FuncionarioVO funcionario) {
		UsuarioDAO.deletar(funcionario, arquivo);
	}

	public static void pesquisar() {
		UsuarioDAO.pesquisar(arquivo);
	}
}
