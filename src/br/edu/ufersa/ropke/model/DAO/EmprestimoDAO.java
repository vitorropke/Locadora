package br.edu.ufersa.ropke.model.DAO;

import java.io.*;

import br.edu.ufersa.ropke.model.VO.Emprestimo;

public class EmprestimoDAO {
	public void cadastrar(Emprestimo emprestimo) {
		try {
			// Gera o arquivo para armazenar o objeto
			File arquivo = new File("br/edu/ufersa/ropke/model/DAO/arquivos/emprestimos.dat");
			FileOutputStream arquivoGravador = new FileOutputStream(arquivo, true);

			// Verifica se o objeto já existe
			boolean objetoExiste = false;
			if (arquivo.exists() && arquivo.isFile() && arquivo.canRead()) {
				FileInputStream arquivoLeitura = new FileInputStream(arquivo);

				while (arquivoLeitura.available() > 0 && !objetoExiste) {
					// Classe responsável por recuperar os objetos do arquivo
					ObjectInputStream objetoLeitura = new ObjectInputStream(arquivoLeitura);

					Emprestimo emprestimoLeitura = (Emprestimo)objetoLeitura.readObject();

					// Compara os emprestimos pelo id deles
					if (emprestimoLeitura.getIdEmprestimo() == (emprestimo.getIdEmprestimo())) {
						objetoExiste = true;
					}
				}

				arquivoLeitura.close();
			}

			// Se o objeto já existe
			if (objetoExiste) {
				System.out.println("Esse emprestimo ja foi cadastrado");
				arquivoGravador.close();
			// Se não existe, prosseguir com a inserção
			} else {
				// Classe responsável por inserir os objetos
				ObjectOutputStream objetoGravador = new ObjectOutputStream(arquivoGravador);

				// Grava o objeto emprestimo no arquivo
				objetoGravador.writeObject(emprestimo);
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
			File arquivo = new File("br/edu/ufersa/ropke/model/DAO/arquivos/emprestimos.dat");

			if (arquivo.exists() && arquivo.isFile() && arquivo.canRead()) {
				FileInputStream arquivoLeitura = new FileInputStream(arquivo);

				int indiceEmprestimo = 1;
				while (arquivoLeitura.available() > 0) {
					// Classe responsável por recuperar os objetos do arquivo
					ObjectInputStream objetoLeitura = new ObjectInputStream(arquivoLeitura);
					
					Emprestimo emprestimo = (Emprestimo)objetoLeitura.readObject();
					System.out.println("\nEmprestimo " + indiceEmprestimo + '\n');
					System.out.println(emprestimo.toString());
					System.out.println("-----------------------------------------");
					indiceEmprestimo++;
				}

				arquivoLeitura.close();
			} else {
				System.out.println("Sem emprestimos");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
