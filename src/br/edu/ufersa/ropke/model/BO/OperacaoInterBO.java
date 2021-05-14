package br.edu.ufersa.ropke.model.BO;

import java.io.File;

public interface OperacaoInterBO<VO> {
	public void cadastrar(VO entidade, File arquivo);

	public void alterar(VO entidade, File arquivo);

	public void deletar(VO entidade, File arquivo);

	public void pesquisar(File arquivo);
	
	public VO pesquisar(VO entidade, File arquivo);
}
