package br.edu.ufersa.ropke.model.DAO;

import java.io.*;

import br.edu.ufersa.ropke.model.VO.Funcionario;

public class FuncionarioDAO {
	public void cadastrar(Funcionario funcionario) {
		try {
			// Gera o arquivo para armazenar o objeto
			File arquivo = new File("br/edu/ufersa/ropke/model/DAO/arquivos/funcionarios.dat");
			FileOutputStream arquivoGravador = new FileOutputStream(arquivo, true);
			// Classe responsável por inserir os objetos
			ObjectOutputStream objetoGravador = new ObjectOutputStream(arquivoGravador);

			// Grava o objeto funcionario no arquivo
			objetoGravador.writeObject(funcionario);
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
			File arquivo = new File("br/edu/ufersa/ropke/model/DAO/arquivos/funcionarios.dat");

			if (arquivo.exists() && arquivo.isFile() && arquivo.canRead()) {
				FileInputStream arquivoLeitura = new FileInputStream(arquivo);
				// Classe responsável por recuperar os objetos do arquivo
				ObjectInputStream objetoLeitura = new ObjectInputStream(arquivoLeitura);

				while (arquivoLeitura.available() > 0) {
					Funcionario funcionario = (Funcionario)objetoLeitura.readObject();
					System.out.println(funcionario.toString());
				}

				objetoLeitura.close();
				arquivoLeitura.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}