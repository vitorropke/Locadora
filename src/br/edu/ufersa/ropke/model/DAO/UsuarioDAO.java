package br.edu.ufersa.ropke.model.DAO;

import java.io.File;

import br.edu.ufersa.ropke.model.VO.UsuarioVO;

public abstract class UsuarioDAO extends PessoaDAO {
	public static void cadastrar(UsuarioVO usuario, File arquivo) {
		PessoaDAO.cadastrar(usuario, arquivo);
	}

	public static void alterar(UsuarioVO usuario, File arquivo) {
		PessoaDAO.alterar(usuario, arquivo);
	}

	public static void deletar(UsuarioVO usuario, File arquivo) {
		PessoaDAO.deletar(usuario, arquivo);
	}

	public static void pesquisar(File arquivo) {
		PessoaDAO.pesquisar(arquivo);
	}

	public static UsuarioVO pesquisar(UsuarioVO usuario, File arquivo) {
		return (UsuarioVO) PessoaDAO.pesquisar(usuario, arquivo);
	}
}
