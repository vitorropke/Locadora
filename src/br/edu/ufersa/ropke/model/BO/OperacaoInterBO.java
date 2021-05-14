package br.edu.ufersa.ropke.model.BO;

import java.io.File;

import br.edu.ufersa.ropke.exception.FoundException;

public interface OperacaoInterBO<VO> {
	public void cadastrar(VO entidade, File arquivo) throws FoundException;

	public void alterar(VO entidade, File arquivo);

	public void deletar(VO entidade, File arquivo);

	public void pesquisar(File arquivo);

	public VO pesquisar(VO entidade, File arquivo);
}
