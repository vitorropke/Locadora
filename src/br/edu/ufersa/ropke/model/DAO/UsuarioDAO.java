package br.edu.ufersa.ropke.model.DAO;

import java.io.*;
import java.util.List;
import java.util.ArrayList;

import br.edu.ufersa.ropke.model.VO.Usuario;

public class UsuarioDAO {
	public static void cadastrar(Usuario usuario) {
		try {
			// Gera o arquivo para armazenar o objeto
			File arquivo = new File("br/edu/ufersa/ropke/model/DAO/arquivos/usuarios.dat");

			// Verifica se o objeto já existe
			boolean usuarioExiste = false;
			if (arquivo.exists() && arquivo.isFile() && arquivo.canRead()) {
				FileInputStream arquivoLeitura = new FileInputStream(arquivo);

				while (arquivoLeitura.available() > 0 && !usuarioExiste) {
					// Classe responsável por recuperar os objetos do arquivo
					ObjectInputStream objetoLeitura = new ObjectInputStream(arquivoLeitura);

					Usuario usuarioLeitura = (Usuario)objetoLeitura.readObject();

					// Compara os usuarios pelo login deles
					if (usuarioLeitura.getLogin().equals(usuario.getLogin())) {
						usuarioExiste = true;
					}
				}

				arquivoLeitura.close();
			}

			// Se o objeto já existe
			if (usuarioExiste) {
				System.out.println("Esse usuario ja foi cadastrado");
			// Se não existe, prosseguir com a inserção
			} else {
				FileOutputStream arquivoGravador = new FileOutputStream(arquivo, true);
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

	public static void alterar(Usuario usuario) {
		try {
			// Gera o arquivo para armazenar o objeto
			File arquivo = new File("br/edu/ufersa/ropke/model/DAO/arquivos/usuarios.dat");

			boolean usuarioExiste = false;
			ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
			// Procura pelo objeto enquanto salva os outros em um vetor de objetos
			if (arquivo.exists() && arquivo.isFile() && arquivo.canRead()) {
				FileInputStream arquivoLeitura = new FileInputStream(arquivo);

				while (arquivoLeitura.available() > 0) {
					// Classe responsável por recuperar os objetos do arquivo
					ObjectInputStream objetoLeitura = new ObjectInputStream(arquivoLeitura);

					Usuario usuarioLeitura = (Usuario)objetoLeitura.readObject();

					// Compara os usuarios pelo login deles
					if (usuarioLeitura.getLogin().equals(usuario.getLogin())) {
						usuarioExiste = true;
						// Quando for o usuario a ser alterado, insere do parâmetro do método
						usuarios.add(usuario);
					} else {
						// Quando não for o usuario a ser alterado, insere do arquivo
						usuarios.add(usuarioLeitura);
					}
				}

				arquivoLeitura.close();
			}

			// Se o objeto a ser alterado não existe
			if (!usuarioExiste) {
				System.out.println("Esse usuario nao existe");
			// Se existe, prosseguir com a alteração
			} else {
				FileOutputStream arquivoGravador = new FileOutputStream(arquivo);
				int tamanhoVetorUsuarios = usuarios.size();
				
				for (int i = 0; i < tamanhoVetorUsuarios; i++) {
					// Classe responsável por inserir os objetos
					ObjectOutputStream objetoGravador = new ObjectOutputStream(arquivoGravador);

					// Grava o objeto usuario no arquivo
					objetoGravador.writeObject(usuarios.get(i));
					objetoGravador.flush();
				}
				
				arquivoGravador.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void deletar(Usuario usuario) {
		try {
			// Gera o arquivo para armazenar o objeto
			File arquivo = new File("br/edu/ufersa/ropke/model/DAO/arquivos/usuarios.dat");

			boolean usuarioExiste = false;
			ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
			// Procura pelo objeto enquanto salva os outros em um vetor de objetos
			if (arquivo.exists() && arquivo.isFile() && arquivo.canRead()) {
				FileInputStream arquivoLeitura = new FileInputStream(arquivo);

				while (arquivoLeitura.available() > 0) {
					// Classe responsável por recuperar os objetos do arquivo
					ObjectInputStream objetoLeitura = new ObjectInputStream(arquivoLeitura);

					Usuario usuarioLeitura = (Usuario)objetoLeitura.readObject();

					// Compara os usuarios pelo login deles
					if (usuarioLeitura.getLogin().equals(usuario.getLogin())) {
						usuarioExiste = true;
					} else {
						// Quando não for o usuario a ser alterado, insere do arquivo
						usuarios.add(usuarioLeitura);
					}
				}

				arquivoLeitura.close();
			}

			// Se o objeto a ser alterado não existe
			if (!usuarioExiste) {
				System.out.println("Esse usuario nao existe");
			// Se existe, prosseguir com a alteração
			} else {
				FileOutputStream arquivoGravador = new FileOutputStream(arquivo);
				int tamanhoVetorUsuarios = usuarios.size();
				
				for (int i = 0; i < tamanhoVetorUsuarios; i++) {
					// Classe responsável por inserir os objetos
					ObjectOutputStream objetoGravador = new ObjectOutputStream(arquivoGravador);

					// Grava o objeto usuario no arquivo
					objetoGravador.writeObject(usuarios.get(i));
					objetoGravador.flush();
				}
				
				arquivoGravador.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void pesquisar() {
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
