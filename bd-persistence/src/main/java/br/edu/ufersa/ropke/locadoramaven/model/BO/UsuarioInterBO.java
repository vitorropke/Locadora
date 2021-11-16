package br.edu.ufersa.ropke.locadoramaven.model.BO;

import br.edu.ufersa.ropke.locadoramaven.exception.AuthenticationException;
import br.edu.ufersa.ropke.locadoramaven.exception.InvalidParameterException;
import br.edu.ufersa.ropke.locadoramaven.exception.NotFoundException;
import br.edu.ufersa.ropke.locadoramaven.model.VO.UsuarioVO;

public interface UsuarioInterBO<VO extends UsuarioVO> extends PessoaInterBO<VO> {
	public VO pesquisarLogin(String login) throws NotFoundException, InvalidParameterException;

	public boolean autenticar(String login, String password) throws AuthenticationException, InvalidParameterException;
}
