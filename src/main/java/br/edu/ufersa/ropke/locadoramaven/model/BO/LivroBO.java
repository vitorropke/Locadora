package br.edu.ufersa.ropke.locadoramaven.model.BO;

import java.io.File;
import java.util.ArrayList;

import br.edu.ufersa.ropke.locadoramaven.model.DAO.LivroDAO;
import br.edu.ufersa.ropke.locadoramaven.model.VO.LivroVO;

public class LivroBO extends EmprestavelBO<LivroVO> {
	private final File arquivo = LivroDAO.getArquivo();
	private LivroDAO livroDAO = new LivroDAO();

	public void cadastrar(LivroVO livro) {
		super.cadastrar(livro, arquivo);
	}

	public void alterar(LivroVO livro) {
		super.alterar(livro, arquivo);
	}

	public void deletar(LivroVO livro) {
		super.deletar(livro, arquivo);
	}

	public void pesquisar() {
		super.pesquisar(arquivo);
	}

	public LivroVO pesquisar(LivroVO livro) {
		return super.pesquisar(livro, arquivo);
	}

	public ArrayList<LivroVO> listar() {
		return super.listar(arquivo);
	}

	public ArrayList<LivroVO> pesquisarTitulo(String titulo) {
		return super.pesquisarTitulo(titulo, arquivo);
	}

	public ArrayList<LivroVO> pesquisarAnoLancamento(int anoLancamento) {
		return super.pesquisarAnoLancamento(anoLancamento, arquivo);
	}

	public ArrayList<LivroVO> pesquisarGenero(String titulo) {
		if ((titulo != null) && (!titulo.isBlank())) {
			return livroDAO.pesquisarGenero(titulo);
		} else {
			return new ArrayList<LivroVO>();
		}
	}
}
