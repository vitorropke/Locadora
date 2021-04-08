package br.edu.ufersa.ropke.model.DAO;

import java.io.*;

import br.edu.ufersa.ropke.model.VO.Disco;

public class DiscoDAO {
	public void cadastrar(Disco disco) {
		try {
			// Gera o arquivo para armazenar o objeto
			File arquivo = new File("br/edu/ufersa/ropke/model/DAO/arquivos/discos.dat");
			FileOutputStream arquivoGravador = new FileOutputStream(arquivo, true);
			// Classe responsável por inserir os objetos
			ObjectOutputStream objetoGravador = new ObjectOutputStream(arquivoGravador);

			// Grava o objeto disco no arquivo
			objetoGravador.writeObject(disco);
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
			File arquivo = new File("br/edu/ufersa/ropke/model/DAO/arquivos/discos.dat");

			if (arquivo.exists() && arquivo.isFile() && arquivo.canRead()) {
				FileInputStream arquivoLeitura = new FileInputStream(arquivo);
				// Classe responsável por recuperar os objetos do arquivo
				ObjectInputStream objetoLeitura = new ObjectInputStream(arquivoLeitura);

				while (arquivoLeitura.available() > 0) {
					Disco disco = (Disco)objetoLeitura.readObject();
					System.out.println(disco.toString());
				}

				objetoLeitura.close();
				arquivoLeitura.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
