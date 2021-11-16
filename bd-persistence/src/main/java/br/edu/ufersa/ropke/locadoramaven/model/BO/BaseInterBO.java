package br.edu.ufersa.ropke.locadoramaven.model.BO;

import java.util.List;

import br.edu.ufersa.ropke.locadoramaven.exception.FoundException;
import br.edu.ufersa.ropke.locadoramaven.exception.InvalidParameterException;
import br.edu.ufersa.ropke.locadoramaven.exception.NotFoundException;

public interface BaseInterBO<VO> {
	public void cadastrar(VO entidade) throws FoundException, InvalidParameterException;

	public void alterar(VO entidade) throws NotFoundException, InvalidParameterException;

	public void deletar(long id) throws NotFoundException, InvalidParameterException;

	public List<VO> listar();

	public VO pesquisarId(long id) throws NotFoundException, InvalidParameterException;
}
