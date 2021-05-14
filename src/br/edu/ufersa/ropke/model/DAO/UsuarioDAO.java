package br.edu.ufersa.ropke.model.DAO;

import java.io.File;

import br.edu.ufersa.ropke.model.VO.UsuarioVO;

public abstract class UsuarioDAO<VO extends UsuarioVO> extends PessoaDAO<VO> {
	@Override
	public void cadastrar(VO usuario, File arquivo) {
		super.cadastrar(usuario, arquivo);
	}

	@Override
	public void alterar(VO usuario, File arquivo) {
		super.alterar(usuario, arquivo);
	}

	@Override
	public void deletar(VO usuario, File arquivo) {
		super.deletar(usuario, arquivo);
	}

	@Override
	public void pesquisar(File arquivo) {
		super.pesquisar(arquivo);
	}

	@Override
	public VO pesquisar(VO usuario, File arquivo) {
		return super.pesquisar(usuario, arquivo);
	}
}
