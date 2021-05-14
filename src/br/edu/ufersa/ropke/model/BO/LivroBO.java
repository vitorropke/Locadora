package br.edu.ufersa.ropke.model.BO;

import java.io.File;

import br.edu.ufersa.ropke.model.DAO.LivroDAO;
import br.edu.ufersa.ropke.model.VO.EmprestavelVO;
import br.edu.ufersa.ropke.model.VO.LivroVO;

public class LivroBO extends EmprestavelBO<LivroVO> {
	private static final File arquivo = LivroDAO.getArquivo();
	private static LivroDAO livroDAO = new LivroDAO();

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

	public LivroVO[] pesquisarTitulo(String titulo) {
		EmprestavelVO[] emprestaveis = super.pesquisarTitulo(titulo, arquivo);
		int tamanhoVetorEmprestaveis = emprestaveis.length;

		LivroVO[] livros = new LivroVO[tamanhoVetorEmprestaveis];

		// cast de cada posição do vetor emprestável para livro
		for (int i = 0; i < tamanhoVetorEmprestaveis; i++) {
			livros[i] = (LivroVO) emprestaveis[i];
		}

		return livros;
	}

	public LivroVO[] pesquisarAnoLancamento(int anoLancamento) {
		EmprestavelVO[] emprestaveis = super.pesquisarAnoLancamento(anoLancamento, arquivo);
		int tamanhoVetorEmprestaveis = emprestaveis.length;

		LivroVO[] livros = new LivroVO[tamanhoVetorEmprestaveis];

		// cast de cada posição do vetor emprestável para livro
		for (int i = 0; i < tamanhoVetorEmprestaveis; i++) {
			livros[i] = (LivroVO) emprestaveis[i];
		}

		return livros;
	}

	public LivroVO[] pesquisarGenero(String titulo) {
		if (titulo != null && titulo != "") {
			return livroDAO.pesquisarGenero(titulo);
		} else {
			LivroVO[] semLivros = new LivroVO[0];
			return semLivros;
		}
	}
}
