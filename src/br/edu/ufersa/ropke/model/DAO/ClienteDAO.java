package br.edu.ufersa.ropke.model.DAO;

import java.io.*;
import java.util.List;
import java.util.ArrayList;

import br.edu.ufersa.ropke.model.VO.Cliente;

public class ClienteDAO {
	public static void cadastrar(Cliente cliente) {
		try {
			// Gera o arquivo para armazenar o objeto
			File arquivo = new File("br/edu/ufersa/ropke/model/DAO/arquivos/clientes.dat");

			// Verifica se o objeto já existe
			boolean clienteExiste = false;
			if (arquivo.exists() && arquivo.isFile() && arquivo.canRead()) {
				FileInputStream arquivoLeitura = new FileInputStream(arquivo);

				while (arquivoLeitura.available() > 0 && !clienteExiste) {
					// Classe responsável por recuperar os objetos do arquivo
					ObjectInputStream objetoLeitura = new ObjectInputStream(arquivoLeitura);

					Cliente clienteLeitura = (Cliente)objetoLeitura.readObject();

					// Compara os clientes pelo cpf deles
					if (clienteLeitura.getCpf().equals(cliente.getCpf())) {
						clienteExiste = true;
					}
				}

				arquivoLeitura.close();
			}

			// Se o objeto já existe
			if (clienteExiste) {
				System.out.println("Esse cliente ja foi cadastrado");
			// Se não existe, prosseguir com a inserção
			} else {
				FileOutputStream arquivoGravador = new FileOutputStream(arquivo, true);
				// Classe responsável por inserir os objetos
				ObjectOutputStream objetoGravador = new ObjectOutputStream(arquivoGravador);

				// Grava o objeto cliente no arquivo
				objetoGravador.writeObject(cliente);
				objetoGravador.flush();
				arquivoGravador.close();
				objetoGravador.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void alterar(Cliente cliente) {
		try {
			// Gera o arquivo para armazenar o objeto
			File arquivo = new File("br/edu/ufersa/ropke/model/DAO/arquivos/clientes.dat");

			boolean clienteExiste = false;
			ArrayList<Cliente> clientes = new ArrayList<Cliente>();
			// Procura pelo objeto enquanto salva os outros em um vetor de objetos
			if (arquivo.exists() && arquivo.isFile() && arquivo.canRead()) {
				FileInputStream arquivoLeitura = new FileInputStream(arquivo);

				while (arquivoLeitura.available() > 0) {
					// Classe responsável por recuperar os objetos do arquivo
					ObjectInputStream objetoLeitura = new ObjectInputStream(arquivoLeitura);

					Cliente clienteLeitura = (Cliente)objetoLeitura.readObject();

					// Compara os clientes pelo CPF deles
					if (clienteLeitura.getCpf().equals(cliente.getCpf())) {
						clienteExiste = true;
						// Quando for o cliente a ser alterado, insere do parâmetro do método
						clientes.add(cliente);
					} else {
						// Quando não for o cliente a ser alterado, insere do arquivo
						clientes.add(clienteLeitura);
					}
				}

				arquivoLeitura.close();
			}

			// Se o objeto a ser alterado não existe
			if (!clienteExiste) {
				System.out.println("Esse cliente nao existe");
			// Se existe, prosseguir com a alteração
			} else {
				FileOutputStream arquivoGravador = new FileOutputStream(arquivo);
				int tamanhoVetorClientes = clientes.size();
				
				for (int i = 0; i < tamanhoVetorClientes; i++) {
					// Classe responsável por inserir os objetos
					ObjectOutputStream objetoGravador = new ObjectOutputStream(arquivoGravador);

					// Grava o objeto cliente no arquivo
					objetoGravador.writeObject(clientes.get(i));
					objetoGravador.flush();
				}
				
				arquivoGravador.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void deletar(Cliente cliente) {
		try {
			// Gera o arquivo para armazenar o objeto
			File arquivo = new File("br/edu/ufersa/ropke/model/DAO/arquivos/clientes.dat");

			boolean clienteExiste = false;
			ArrayList<Cliente> clientes = new ArrayList<Cliente>();
			// Procura pelo objeto enquanto salva os outros em um vetor de objetos
			if (arquivo.exists() && arquivo.isFile() && arquivo.canRead()) {
				FileInputStream arquivoLeitura = new FileInputStream(arquivo);

				while (arquivoLeitura.available() > 0) {
					// Classe responsável por recuperar os objetos do arquivo
					ObjectInputStream objetoLeitura = new ObjectInputStream(arquivoLeitura);

					Cliente clienteLeitura = (Cliente)objetoLeitura.readObject();

					// Compara os clientes pelo CPF deles
					if (clienteLeitura.getCpf().equals(cliente.getCpf())) {
						clienteExiste = true;
					} else {
						// Quando não for o cliente a ser alterado, insere do arquivo
						clientes.add(clienteLeitura);
					}
				}

				arquivoLeitura.close();
			}

			// Se o objeto a ser alterado não existe
			if (!clienteExiste) {
				System.out.println("Esse cliente nao existe");
			// Se existe, prosseguir com a alteração
			} else {
				FileOutputStream arquivoGravador = new FileOutputStream(arquivo);
				int tamanhoVetorClientes = clientes.size();
				
				for (int i = 0; i < tamanhoVetorClientes; i++) {
					// Classe responsável por inserir os objetos
					ObjectOutputStream objetoGravador = new ObjectOutputStream(arquivoGravador);

					// Grava o objeto cliente no arquivo
					objetoGravador.writeObject(clientes.get(i));
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
			File arquivo = new File("br/edu/ufersa/ropke/model/DAO/arquivos/clientes.dat");

			if (arquivo.exists() && arquivo.isFile() && arquivo.canRead()) {
				FileInputStream arquivoLeitura = new FileInputStream(arquivo);

				int indiceCliente = 1;
				while (arquivoLeitura.available() > 0) {
					// Classe responsável por recuperar os objetos do arquivo
					ObjectInputStream objetoLeitura = new ObjectInputStream(arquivoLeitura);
					
					Cliente cliente = (Cliente)objetoLeitura.readObject();
					System.out.println("\nCliente " + indiceCliente + '\n');
					System.out.println(cliente.toString());
					System.out.println("-----------------------------------------");
					indiceCliente++;
				}

				arquivoLeitura.close();
			} else {
				System.out.println("Sem clientes");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
