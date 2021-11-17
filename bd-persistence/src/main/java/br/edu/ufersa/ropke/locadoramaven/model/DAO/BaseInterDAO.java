package br.edu.ufersa.ropke.locadoramaven.model.DAO;

import java.sql.ResultSet;

public interface BaseInterDAO<VO> {
	public void cadastrar(VO entidade);

	public void alterar(VO entidade);

	public void deletar(VO entidade);

	public ResultSet listar();

	public ResultSet pesquisarId(long id);
}
