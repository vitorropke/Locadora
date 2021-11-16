package br.edu.ufersa.ropke.locadoramaven.model.BO;

import br.edu.ufersa.ropke.locadoramaven.model.VO.EmprestavelVO;

public abstract class EmprestavelBO<VO extends EmprestavelVO> implements EmprestavelInterBO<VO> {
	public boolean isInvalid(VO emprestavel) {
		if ((emprestavel != null) && (emprestavel.getTitulo() != null)) {
			return false;
		} else {
			return true;
		}
	}
}
