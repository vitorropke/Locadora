package br.edu.ufersa.ropke.locadoramaven.model.DAO;

import java.sql.ResultSet;

import br.edu.ufersa.ropke.locadoramaven.model.VO.UsuarioVO;

public interface UsuarioInterDAO<VO extends UsuarioVO> extends BaseInterDAO<VO> {
	public ResultSet pesquisarLogin(String login);
}
