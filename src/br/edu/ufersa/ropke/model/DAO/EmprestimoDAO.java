package br.edu.ufersa.ropke.model.DAO;

import java.io.*;

import br.edu.ufersa.ropke.model.VO.Emprestimo;

public class EmprestimoDAO {
	public void cadastrar(Emprestimo emprestimo) {
		try {
			// Gera o arquivo para armazenar o objeto
			File arquivo = new File("br/edu/ufersa/ropke/model/DAO/arquivos/emprestimos.dat");
			FileOutputStream arquivoGravador = new FileOutputStream(arquivo, true);
			// Classe responsável por inserir os objetos
			ObjectOutputStream objetoGravador = new ObjectOutputStream(arquivoGravador);

			// Grava o objeto emprestimo no arquivo
			objetoGravador.writeObject(emprestimo);
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
			File arquivo = new File("br/edu/ufersa/ropke/model/DAO/arquivos/emprestimos.dat");

			if (arquivo.exists() && arquivo.isFile() && arquivo.canRead()) {
				FileInputStream arquivoLeitura = new FileInputStream(arquivo);
				// Classe responsável por recuperar os objetos do arquivo
				ObjectInputStream objetoLeitura = new ObjectInputStream(arquivoLeitura);

				while (arquivoLeitura.available() > 0) {
					Emprestimo emprestimo = (Emprestimo)objetoLeitura.readObject();
					System.out.println(emprestimo.toString());
				}

				objetoLeitura.close();
				arquivoLeitura.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
