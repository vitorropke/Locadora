package br.edu.ufersa.ropke.locadoramaven.model.DAO;

import java.sql.ResultSet;

import br.edu.ufersa.ropke.locadoramaven.model.VO.EmprestavelVO;

public interface EmprestavelInterDAO<VO extends EmprestavelVO> extends BaseInterDAO<VO> {
	public ResultSet pesquisarTitulo(String titulo);

	public ResultSet pesquisarAnoLancamento(int anoLancamento);
}
