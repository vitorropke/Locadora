package br.edu.ufersa.ropke.model.BO;

import java.io.File;

import br.edu.ufersa.ropke.model.DAO.FuncionarioDAO;
import br.edu.ufersa.ropke.model.VO.FuncionarioVO;

public class FuncionarioBO extends UsuarioBO<FuncionarioVO> {
	private static final File arquivo = FuncionarioDAO.getArquivo();

	public void cadastrar(FuncionarioVO funcionario) {
		super.cadastrar(funcionario, arquivo);
	}

	public void alterar(FuncionarioVO funcionario) {
		super.alterar(funcionario, arquivo);
	}

	public void deletar(FuncionarioVO funcionario) {
		super.deletar(funcionario, arquivo);
	}

	public void pesquisar() {
		super.pesquisar(arquivo);
	}

	public boolean autenticar(FuncionarioVO funcionario, File arquivo, String login, String senha) {
		return super.autenticar(funcionario, arquivo, login, senha);
	}
}
