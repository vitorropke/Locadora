package br.edu.ufersa.ropke.model.BO;

import java.io.File;

import br.edu.ufersa.ropke.model.DAO.LivroDAO;
import br.edu.ufersa.ropke.model.VO.EmprestavelVO;
import br.edu.ufersa.ropke.model.VO.LivroVO;

public class LivroBO extends EmprestavelBO {
	private static final File arquivo = LivroDAO.getArquivo();

	public static void cadastrar(LivroVO livro) {
		EmprestavelBO.cadastrar(livro, arquivo);
	}

	public static void alterar(LivroVO livro) {
		EmprestavelBO.alterar(livro, arquivo);
	}

	public static void deletar(LivroVO livro) {
		EmprestavelBO.deletar(livro, arquivo);
	}

	public static void pesquisar() {
		EmprestavelBO.pesquisar(arquivo);
	}

	public static void pesquisar(LivroVO livro) {
		EmprestavelBO.pesquisar(livro, arquivo);
	}

	public static LivroVO pesquisarTitulo(String titulo) {
		return (LivroVO) EmprestavelBO.pesquisarTitulo(titulo, arquivo);
	}

	public static LivroVO[] pesquisarAnoLancamento(int anoLancamento) {
		EmprestavelVO[] emprestaveis = EmprestavelBO.pesquisarAnoLancamento(anoLancamento, arquivo);
		int tamanhoVetorEmprestaveis = emprestaveis.length;

		LivroVO[] livros = new LivroVO[tamanhoVetorEmprestaveis];

		// cast de cada posição do vetor emprestável para livro
		for (int i = 0; i < tamanhoVetorEmprestaveis; i++) {
			livros[i] = (LivroVO) emprestaveis[i];
		}

		return livros;
	}

	public static LivroVO[] pesquisarGenero(String titulo) {
		if (titulo != null && titulo != "") {
			return LivroDAO.pesquisarGenero(titulo);
		} else {
			LivroVO[] semLivros = new LivroVO[0];
			return semLivros;
		}
	}
}
