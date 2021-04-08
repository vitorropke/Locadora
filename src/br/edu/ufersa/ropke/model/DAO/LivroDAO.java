package br.edu.ufersa.ropke.model.DAO;

import java.io.*;

import br.edu.ufersa.ropke.model.VO.Livro;

public class LivroDAO {

	public void cadastrar(Livro livro) {
		try {
			// Gera o arquivo para armazenar o objeto
			File arquivo = new File("br/edu/ufersa/ropke/model/DAO/arquivos/livros.dat");
			FileOutputStream arquivoGravador = new FileOutputStream(arquivo, true);
			// Classe responsável por inserir os objetos
			ObjectOutputStream objetoGravador = new ObjectOutputStream(arquivoGravador);

			// Grava o objeto cliente no arquivo
			objetoGravador.writeObject(livro);
			objetoGravador.flush();
			arquivoGravador.close();
			objetoGravador.close();
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
				// Classe responsável por recuperar os objetos do arquivo
				ObjectInputStream objetoLeitura = new ObjectInputStream(arquivoLeitura);

				while (arquivoLeitura.available() > 0) {
					Livro livro = (Livro)objetoLeitura.readObject();
					System.out.println(livro.toString());
				}

				objetoLeitura.close();
				arquivoLeitura.close();
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
