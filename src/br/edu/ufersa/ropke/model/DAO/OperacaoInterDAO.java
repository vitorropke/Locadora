package br.edu.ufersa.ropke.model.DAO;

import java.io.File;

public interface OperacaoInterDAO<VO> {
	public void cadastrar(VO entidade, File arquivo);

	public void alterar(VO entidade, File arquivo);

	public void deletar(VO entidade, File arquivo);

	public void pesquisar(File arquivo);

	public VO pesquisar(VO entidade, File arquivo);
}