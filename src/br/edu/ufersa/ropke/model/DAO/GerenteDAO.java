package br.edu.ufersa.ropke.model.DAO;

import java.io.*;

import br.edu.ufersa.ropke.model.VO.Gerente;

public class GerenteDAO {
	public void cadastrar(Gerente gerente) {
		try {
			// Gera o arquivo para armazenar o objeto
			File arquivo = new File("br/edu/ufersa/ropke/model/DAO/arquivos/gerentes.dat");
			FileOutputStream arquivoGravador = new FileOutputStream(arquivo, true);

			// Verifica se o objeto já existe
			boolean objetoExiste = false;
			if (arquivo.exists() && arquivo.isFile() && arquivo.canRead()) {
				FileInputStream arquivoLeitura = new FileInputStream(arquivo);

				while (arquivoLeitura.available() > 0 && !objetoExiste) {
					// Classe responsável por recuperar os objetos do arquivo
					ObjectInputStream objetoLeitura = new ObjectInputStream(arquivoLeitura);

					Gerente gerenteLeitura = (Gerente)objetoLeitura.readObject();

					// Compara os gerentes pelo cpf deles
					if (gerenteLeitura.getCpf().equals(gerente.getCpf())) {
						objetoExiste = true;
					}
				}

				arquivoLeitura.close();
			}

			// Se o objeto já existe
			if (objetoExiste) {
				System.out.println("Esse gerente ja foi cadastrado");
				arquivoGravador.close();
			// Se não existe, prosseguir com a inserção
			} else {
				// Classe responsável por inserir os objetos
				ObjectOutputStream objetoGravador = new ObjectOutputStream(arquivoGravador);

				// Grava o objeto gerente no arquivo
				objetoGravador.writeObject(gerente);
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
			File arquivo = new File("br/edu/ufersa/ropke/model/DAO/arquivos/gerentes.dat");

			if (arquivo.exists() && arquivo.isFile() && arquivo.canRead()) {
				FileInputStream arquivoLeitura = new FileInputStream(arquivo);

				int indiceGerente = 1;
				while (arquivoLeitura.available() > 0) {
					// Classe responsável por recuperar os objetos do arquivo
					ObjectInputStream objetoLeitura = new ObjectInputStream(arquivoLeitura);
					
					Gerente gerente = (Gerente)objetoLeitura.readObject();
					System.out.println("\nGerente " + indiceGerente + '\n');
					System.out.println(gerente.toString());
					System.out.println("-----------------------------------------");
					indiceGerente++;
				}

				arquivoLeitura.close();
			} else {
				System.out.println("Sem gerentes");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
