package br.edu.ufersa.ropke.locadoramaven.model.BO;

import java.util.List;

import br.edu.ufersa.ropke.locadoramaven.exception.InvalidParameterException;
import br.edu.ufersa.ropke.locadoramaven.exception.NotFoundException;
import br.edu.ufersa.ropke.locadoramaven.model.VO.PessoaVO;

public interface PessoaInterBO<VO extends PessoaVO> extends BaseInterBO<VO> {
	public List<VO> pesquisarNome(String nome) throws InvalidParameterException;

	public VO pesquisarCpf(String cpf) throws NotFoundException, InvalidParameterException;
}
