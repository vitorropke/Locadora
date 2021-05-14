package br.edu.ufersa.ropke.model.BO;

import java.io.File;

import br.edu.ufersa.ropke.model.DAO.GerenteDAO;
import br.edu.ufersa.ropke.model.VO.GerenteVO;

public class GerenteBO extends UsuarioBO<GerenteVO> {
	private static final File arquivo = GerenteDAO.getArquivo();

	public void cadastrar(GerenteVO gerente) {
		super.cadastrar(gerente, arquivo);
	}

	public void alterar(GerenteVO gerente) {
		super.alterar(gerente, arquivo);
	}

	public void deletar(GerenteVO gerente) {
		super.deletar(gerente, arquivo);
	}

	public void pesquisar() {
		super.pesquisar(arquivo);
	}

	public boolean autenticar(GerenteVO gerente, String login, String senha) {
		return super.autenticar(gerente, arquivo, login, senha);
	}
}
