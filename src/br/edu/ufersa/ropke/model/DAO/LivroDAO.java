package br.edu.ufersa.ropke.model.DAO;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import br.edu.ufersa.ropke.model.VO.EmprestavelVO;
import br.edu.ufersa.ropke.model.VO.LivroVO;

public class LivroDAO extends EmprestavelDAO {
	private static final File arquivo = new File("src/br/edu/ufersa/ropke/model/DAO/arquivos/livros.dat");

	public static File getArquivo() {
		return arquivo;
	}

	public static void cadastrar(LivroVO livro) {
		EmprestavelDAO.cadastrar(livro, arquivo);
	}

	public static void alterar(LivroVO livro) {
		EmprestavelDAO.alterar(livro, arquivo);
	}

	public static void deletar(LivroVO livro) {
		EmprestavelDAO.deletar(livro, arquivo);
	}

	public static void pesquisar() {
		EmprestavelDAO.pesquisar(arquivo);
	}

	public static LivroVO pesquisar(LivroVO livro) {
		return (LivroVO) EmprestavelDAO.pesquisar(livro, arquivo);
	}

	public static LivroVO[] pesquisarTitulo(String titulo) {
		EmprestavelVO[] emprestaveis = EmprestavelDAO.pesquisarTitulo(titulo, arquivo);
		int tamanhoVetorEmprestaveis = emprestaveis.length;

		LivroVO[] livros = new LivroVO[tamanhoVetorEmprestaveis];

		// cast de cada posição do vetor emprestável para livro
		for (int i = 0; i < tamanhoVetorEmprestaveis; i++) {
			livros[i] = (LivroVO) emprestaveis[i];
		}

		return livros;
	}

	public static LivroVO[] pesquisarAnoLancamento(int anoLancamento) {
		EmprestavelVO[] emprestaveis = EmprestavelDAO.pesquisarAnoLancamento(anoLancamento, arquivo);
		int tamanhoVetorEmprestaveis = emprestaveis.length;

		LivroVO[] livros = new LivroVO[tamanhoVetorEmprestaveis];

		// cast de cada posição do vetor emprestável para livro
		for (int i = 0; i < tamanhoVetorEmprestaveis; i++) {
			livros[i] = (LivroVO) emprestaveis[i];
		}

		return livros;
	}

	public static LivroVO[] pesquisarGenero(String genero) {
		try {
			ArrayList<LivroVO> livros = new ArrayList<LivroVO>();

			// Procura pelo objeto enquanto salva os outros em um vetor de objetos
			if (arquivo.exists() && arquivo.isFile() && arquivo.canRead()) {
				FileInputStream arquivoLeitura = new FileInputStream(arquivo);
				ObjectInputStream objetoLeitura;
				LivroVO livroLeitura;

				while (arquivoLeitura.available() > 0) {
					// Classe responsável por recuperar os objetos do arquivo
					objetoLeitura = new ObjectInputStream(arquivoLeitura);

					livroLeitura = (LivroVO) objetoLeitura.readObject();

					// Salva o livro no vetor quando parte do nome do gênero coincidir com o
					// parâmetro
					if (livroLeitura.getGenero().contains(genero)) {
						livros.add(livroLeitura);
					}
				}

				arquivoLeitura.close();
			}

			int numeroLivros = livros.size();
			// Verifica se o vetor de livros não é vazio
			if (numeroLivros != 0) {
				// ArrayList livros para vetor 'vetorLivros'
				LivroVO[] vetorLivros = new LivroVO[numeroLivros];
				vetorLivros = livros.toArray(vetorLivros);
				return vetorLivros;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("Sem livros nesse genero");
		LivroVO[] semLivros = new LivroVO[0];
		return semLivros;
	}
}
