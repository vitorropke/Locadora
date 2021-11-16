package br.edu.ufersa.ropke.locadoramaven.model.BO;

import java.util.List;

import br.edu.ufersa.ropke.locadoramaven.exception.InvalidParameterException;
import br.edu.ufersa.ropke.locadoramaven.model.VO.EmprestavelVO;

public interface EmprestavelInterBO<VO extends EmprestavelVO> extends BaseInterBO<VO> {
	public List<VO> pesquisarTitulo(String titulo) throws InvalidParameterException;

	public List<VO> pesquisarAnoLancamento(int anoLancamento);
}
