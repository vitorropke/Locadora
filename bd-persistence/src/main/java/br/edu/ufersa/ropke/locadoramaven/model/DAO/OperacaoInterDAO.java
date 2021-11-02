package br.edu.ufersa.ropke.locadoramaven.model.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface OperacaoInterDAO<VO> {
	public void cadastrar(VO entidade) throws SQLException;

	public void alterar(VO entidade) throws SQLException;

	public void deletar(VO entidade) throws SQLException;

	public ResultSet pesquisar(VO entidade) throws SQLException;

	public ResultSet listar() throws SQLException;
}
