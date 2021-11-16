package br.edu.ufersa.ropke.locadoramaven.model.BO;

import br.edu.ufersa.ropke.locadoramaven.model.VO.PessoaVO;

public abstract class PessoaBO<VO extends PessoaVO> implements PessoaInterBO<VO> {
	public boolean isInvalid(VO pessoa) {
		if ((pessoa != null) && (pessoa.getNome() != null) && !pessoa.getEnderecos().isEmpty()) {
			return false;
		} else {
			return true;
		}
	}
}
