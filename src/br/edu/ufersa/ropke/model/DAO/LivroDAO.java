package br.edu.ufersa.ropke.model.DAO;

import java.io.*;

import br.edu.ufersa.ropke.model.VO.Livro;

public class LivroDAO {

	public void cadastrar(Livro livro) {
		try {
			// Gera o arquivo para armazenar o objeto
			File arquivo = new File("br/edu/ufersa/ropke/model/DAO/arquivos/livros.dat");
			FileOutputStream arquivoGravador = new FileOutputStream(arquivo, true);

			// Verifica se o objeto já existe
			boolean objetoExiste = false;
			if (arquivo.exists() && arquivo.isFile() && arquivo.canRead()) {
				FileInputStream arquivoLeitura = new FileInputStream(arquivo);

				while (arquivoLeitura.available() > 0 && !objetoExiste) {
					// Classe responsável por recuperar os objetos do arquivo
					ObjectInputStream objetoLeitura = new ObjectInputStream(arquivoLeitura);

					Livro livroLeitura = (Livro)objetoLeitura.readObject();

					// Compara os livros pelo título deles
					if (livroLeitura.getTitulo().equals(livro.getTitulo())) {
						objetoExiste = true;
					}
				}

				arquivoLeitura.close();
			}

			// Se o objeto já existe
			if (objetoExiste) {
				System.out.println("Esse livro ja foi cadastrado");
				arquivoGravador.close();
			// Se não existe, prosseguir com a inserção
			} else {
				// Classe responsável por inserir os objetos
				ObjectOutputStream objetoGravador = new ObjectOutputStream(arquivoGravador);

				// Grava o objeto livro no arquivo
				objetoGravador.writeObject(livro);
				objetoGravador.flush();
				arquivoGravador.close();
				objetoGravador.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void pesquisar() {
		try {
			// Carrega o arquivo
			File arquivo = new File("br/edu/ufersa/ropke/model/DAO/arquivos/livros.dat");

			if (arquivo.exists() && arquivo.isFile() && arquivo.canRead()) {
				FileInputStream arquivoLeitura = new FileInputStream(arquivo);

				int indiceLivro = 1;
				while (arquivoLeitura.available() > 0) {
					// Classe responsável por recuperar os objetos do arquivo
					ObjectInputStream objetoLeitura = new ObjectInputStream(arquivoLeitura);

					Livro livro = (Livro)objetoLeitura.readObject();
					System.out.println("\nLivro " + indiceLivro + '\n');
					System.out.println(livro.toString());
					System.out.println("-----------------------------------------");
					indiceLivro++;
				}

				arquivoLeitura.close();
			} else {
				System.out.println("Sem livros");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/*
	public void alterar(Livro livro) {
		// altera os atributos do livro no "banco de dados"
	}
	
	public void deletar(Livro livro) {
		// apaga o livro do "banco de dados"
		livro.titulo = null;
		livro.genero = null;
		livro.numeroPaginas = null;
		livro.numeroExemplares = null;
		livro.numeroEmprestimos = null;
		livro.numeroDiasAlugado = null;
		livro.anoLancamento = null;
		livro.valorAluguel = null;
	}
	
	public Livro pesquisarTitulo(String titulo) {
		return Livro;
	}
	
	public Livro[] pesquisarGenero(String genero) {
		return Livro;
	}
	
	public Livro[] pesquisarAno(Calendar anoLancamento) {
		return Livro;
	}
	*/
}
