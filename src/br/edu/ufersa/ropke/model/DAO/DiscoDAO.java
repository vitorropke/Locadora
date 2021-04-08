package br.edu.ufersa.ropke.model.DAO;

import java.io.*;

import br.edu.ufersa.ropke.model.VO.Disco;

public class DiscoDAO {
	public void cadastrar(Disco disco) {
		try {
			// Gera o arquivo para armazenar o objeto
			File arquivo = new File("br/edu/ufersa/ropke/model/DAO/arquivos/discos.dat");
			FileOutputStream arquivoGravador = new FileOutputStream(arquivo, true);

			// Verifica se o objeto já existe
			boolean objetoExiste = false;
			if (arquivo.exists() && arquivo.isFile() && arquivo.canRead()) {
				FileInputStream arquivoLeitura = new FileInputStream(arquivo);

				while (arquivoLeitura.available() > 0 && !objetoExiste) {
					// Classe responsável por recuperar os objetos do arquivo
					ObjectInputStream objetoLeitura = new ObjectInputStream(arquivoLeitura);

					Disco discoLeitura = (Disco)objetoLeitura.readObject();

					// Compara os discos pelo título deles
					if (discoLeitura.getTitulo().equals(disco.getTitulo())) {
						objetoExiste = true;
					}
				}

				arquivoLeitura.close();
			}

			// Se o objeto já existe
			if (objetoExiste) {
				System.out.println("Esse disco ja foi cadastrado");
				arquivoGravador.close();
			// Se não existe, prosseguir com a inserção
			} else {
				// Classe responsável por inserir os objetos
				ObjectOutputStream objetoGravador = new ObjectOutputStream(arquivoGravador);

				// Grava o objeto disco no arquivo
				objetoGravador.writeObject(disco);
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
			File arquivo = new File("br/edu/ufersa/ropke/model/DAO/arquivos/discos.dat");

			if (arquivo.exists() && arquivo.isFile() && arquivo.canRead()) {
				FileInputStream arquivoLeitura = new FileInputStream(arquivo);

				int indiceDisco = 1;
				while (arquivoLeitura.available() > 0) {
					// Classe responsável por recuperar os objetos do arquivo
					ObjectInputStream objetoLeitura = new ObjectInputStream(arquivoLeitura);
					
					Disco disco = (Disco)objetoLeitura.readObject();
					System.out.println("\nDisco " + indiceDisco + '\n');
					System.out.println(disco.toString());
					System.out.println("-----------------------------------------");
					indiceDisco++;
				}

				arquivoLeitura.close();
			} else {
				System.out.println("Sem discos");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
