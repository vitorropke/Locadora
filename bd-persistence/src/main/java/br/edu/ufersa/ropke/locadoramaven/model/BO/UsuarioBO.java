package br.edu.ufersa.ropke.locadoramaven.model.BO;

import br.edu.ufersa.ropke.locadoramaven.model.VO.UsuarioVO;

public abstract class UsuarioBO<VO extends UsuarioVO> extends PessoaBO<VO> implements UsuarioInterBO<VO> {
	@Override
	public boolean isInvalid(VO usuario) {
		if (!super.isInvalid(usuario) && (usuario.getLogin() != null) && (usuario.getSenha() != null)) {
			return false;
		} else {
			return true;
		}
	}
}
