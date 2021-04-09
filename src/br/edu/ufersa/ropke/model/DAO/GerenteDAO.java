package br.edu.ufersa.ropke.model.DAO;

import java.io.*;
import java.util.List;
import java.util.ArrayList;

import br.edu.ufersa.ropke.model.VO.Gerente;

public class GerenteDAO {
	public static void cadastrar(Gerente gerente) {
		try {
			// Gera o arquivo para armazenar o objeto
			File arquivo = new File("br/edu/ufersa/ropke/model/DAO/arquivos/gerentes.dat");

			// Verifica se o objeto já existe
			boolean gerenteExiste = false;
			if (arquivo.exists() && arquivo.isFile() && arquivo.canRead()) {
				FileInputStream arquivoLeitura = new FileInputStream(arquivo);

				while (arquivoLeitura.available() > 0 && !gerenteExiste) {
					// Classe responsável por recuperar os objetos do arquivo
					ObjectInputStream objetoLeitura = new ObjectInputStream(arquivoLeitura);

					Gerente gerenteLeitura = (Gerente)objetoLeitura.readObject();

					// Compara os gerentes pelo cpf deles
					if (gerenteLeitura.getCpf().equals(gerente.getCpf())) {
						gerenteExiste = true;
					}
				}

				arquivoLeitura.close();
			}

			// Se o objeto já existe
			if (gerenteExiste) {
				System.out.println("Esse gerente ja foi cadastrado");
			// Se não existe, prosseguir com a inserção
			} else {
				FileOutputStream arquivoGravador = new FileOutputStream(arquivo, true);
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

	public static void alterar(Gerente gerente) {
		try {
			// Gera o arquivo para armazenar o objeto
			File arquivo = new File("br/edu/ufersa/ropke/model/DAO/arquivos/gerentes.dat");

			boolean gerenteExiste = false;
			ArrayList<Gerente> gerentes = new ArrayList<Gerente>();
			// Procura pelo objeto enquanto salva os outros em um vetor de objetos
			if (arquivo.exists() && arquivo.isFile() && arquivo.canRead()) {
				FileInputStream arquivoLeitura = new FileInputStream(arquivo);

				while (arquivoLeitura.available() > 0) {
					// Classe responsável por recuperar os objetos do arquivo
					ObjectInputStream objetoLeitura = new ObjectInputStream(arquivoLeitura);

					Gerente gerenteLeitura = (Gerente)objetoLeitura.readObject();

					// Compara os gerentes pelo CPF deles
					if (gerenteLeitura.getCpf().equals(gerente.getCpf())) {
						gerenteExiste = true;
						// Quando for o gerente a ser alterado, insere do parâmetro do método
						gerentes.add(gerente);
					} else {
						// Quando não for o gerente a ser alterado, insere do arquivo
						gerentes.add(gerenteLeitura);
					}
				}

				arquivoLeitura.close();
			}

			// Se o objeto a ser alterado não existe
			if (!gerenteExiste) {
				System.out.println("Esse gerente nao existe");
			// Se existe, prosseguir com a alteração
			} else {
				FileOutputStream arquivoGravador = new FileOutputStream(arquivo);
				int tamanhoVetorGerentes = gerentes.size();
				
				for (int i = 0; i < tamanhoVetorGerentes; i++) {
					// Classe responsável por inserir os objetos
					ObjectOutputStream objetoGravador = new ObjectOutputStream(arquivoGravador);

					// Grava o objeto gerente no arquivo
					objetoGravador.writeObject(gerentes.get(i));
					objetoGravador.flush();
				}
				
				arquivoGravador.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void deletar(Gerente gerente) {
		try {
			// Gera o arquivo para armazenar o objeto
			File arquivo = new File("br/edu/ufersa/ropke/model/DAO/arquivos/gerentes.dat");

			boolean gerenteExiste = false;
			ArrayList<Gerente> gerentes = new ArrayList<Gerente>();
			// Procura pelo objeto enquanto salva os outros em um vetor de objetos
			if (arquivo.exists() && arquivo.isFile() && arquivo.canRead()) {
				FileInputStream arquivoLeitura = new FileInputStream(arquivo);

				while (arquivoLeitura.available() > 0) {
					// Classe responsável por recuperar os objetos do arquivo
					ObjectInputStream objetoLeitura = new ObjectInputStream(arquivoLeitura);

					Gerente gerenteLeitura = (Gerente)objetoLeitura.readObject();

					// Compara os gerentes pelo CPF deles
					if (gerenteLeitura.getCpf().equals(gerente.getCpf())) {
						gerenteExiste = true;
					} else {
						// Quando não for o gerente a ser alterado, insere do arquivo
						gerentes.add(gerenteLeitura);
					}
				}

				arquivoLeitura.close();
			}

			// Se o objeto a ser alterado não existe
			if (!gerenteExiste) {
				System.out.println("Esse gerente nao existe");
			// Se existe, prosseguir com a alteração
			} else {
				FileOutputStream arquivoGravador = new FileOutputStream(arquivo);
				int tamanhoVetorGerentes = gerentes.size();
				
				for (int i = 0; i < tamanhoVetorGerentes; i++) {
					// Classe responsável por inserir os objetos
					ObjectOutputStream objetoGravador = new ObjectOutputStream(arquivoGravador);

					// Grava o objeto gerente no arquivo
					objetoGravador.writeObject(gerentes.get(i));
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
