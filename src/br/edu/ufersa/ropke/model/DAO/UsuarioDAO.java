package br.edu.ufersa.ropke.model.DAO;

import java.io.*;

import br.edu.ufersa.ropke.model.VO.Usuario;

public class UsuarioDAO {
	public void cadastrar(Usuario usuario) {
		try {
			// Gera o arquivo para armazenar o objeto
			File arquivo = new File("br/edu/ufersa/ropke/model/DAO/arquivos/usuarios.dat");
			FileOutputStream arquivoGravador = new FileOutputStream(arquivo, true);

			// Verifica se o objeto já existe
			boolean objetoExiste = false;
			if (arquivo.exists() && arquivo.isFile() && arquivo.canRead()) {
				FileInputStream arquivoLeitura = new FileInputStream(arquivo);

				while (arquivoLeitura.available() > 0 && !objetoExiste) {
					// Classe responsável por recuperar os objetos do arquivo
					ObjectInputStream objetoLeitura = new ObjectInputStream(arquivoLeitura);

					Usuario usuarioLeitura = (Usuario)objetoLeitura.readObject();

					// Compara os usuarios pelo login deles
					if (usuarioLeitura.getLogin().equals(usuario.getLogin())) {
						objetoExiste = true;
					}
				}

				arquivoLeitura.close();
			}

			// Se o objeto já existe
			if (objetoExiste) {
				System.out.println("Esse usuario ja foi cadastrado");
				arquivoGravador.close();
			// Se não existe, prosseguir com a inserção
			} else {
				// Classe responsável por inserir os objetos
				ObjectOutputStream objetoGravador = new ObjectOutputStream(arquivoGravador);

				// Grava o objeto usuario no arquivo
				objetoGravador.writeObject(usuario);
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
			File arquivo = new File("br/edu/ufersa/ropke/model/DAO/arquivos/usuarios.dat");

			if (arquivo.exists() && arquivo.isFile() && arquivo.canRead()) {
				FileInputStream arquivoLeitura = new FileInputStream(arquivo);

				int indiceUsuario = 1;
				while (arquivoLeitura.available() > 0) {
					// Classe responsável por recuperar os objetos do arquivo
					ObjectInputStream objetoLeitura = new ObjectInputStream(arquivoLeitura);
					
					Usuario usuario = (Usuario)objetoLeitura.readObject();
					System.out.println("\nUsuario " + indiceUsuario + '\n');
					System.out.println(usuario.toString());
					System.out.println("-----------------------------------------");
					indiceUsuario++;
				}

				arquivoLeitura.close();
			} else {
				System.out.println("Sem usuarios");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
