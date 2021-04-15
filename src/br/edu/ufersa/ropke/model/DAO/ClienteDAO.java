package br.edu.ufersa.ropke.model.DAO;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import br.edu.ufersa.ropke.model.VO.ClienteVO;

public class ClienteDAO {
	public static void cadastrar(ClienteVO cliente) {
		try {
			// Gera o arquivo para armazenar o objeto
			File arquivo = new File("src/br/edu/ufersa/ropke/model/DAO/arquivos/clientes.dat");

			// Verifica se o objeto já existe
			boolean clienteExiste = false;
			if (arquivo.exists() && arquivo.isFile() && arquivo.canRead()) {
				FileInputStream arquivoLeitura = new FileInputStream(arquivo);

				while (arquivoLeitura.available() > 0 && !clienteExiste) {
					// Classe responsável por recuperar os objetos do arquivo
					ObjectInputStream objetoLeitura = new ObjectInputStream(arquivoLeitura);

					ClienteVO clienteLeitura = (ClienteVO) objetoLeitura.readObject();

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

	public static void alterar(ClienteVO cliente) {
		try {
			// Gera o arquivo para armazenar o objeto
			File arquivo = new File("src/br/edu/ufersa/ropke/model/DAO/arquivos/clientes.dat");

			boolean clienteExiste = false;
			ArrayList<ClienteVO> clientes = new ArrayList<ClienteVO>();
			// Procura pelo objeto enquanto salva os outros em um vetor de objetos
			if (arquivo.exists() && arquivo.isFile() && arquivo.canRead()) {
				FileInputStream arquivoLeitura = new FileInputStream(arquivo);

				while (arquivoLeitura.available() > 0) {
					// Classe responsável por recuperar os objetos do arquivo
					ObjectInputStream objetoLeitura = new ObjectInputStream(arquivoLeitura);

					ClienteVO clienteLeitura = (ClienteVO) objetoLeitura.readObject();

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

	public static void deletar(ClienteVO cliente) {
		try {
			// Gera o arquivo para armazenar o objeto
			File arquivo = new File("src/br/edu/ufersa/ropke/model/DAO/arquivos/clientes.dat");

			boolean clienteExiste = false;
			ArrayList<ClienteVO> clientes = new ArrayList<ClienteVO>();
			// Procura pelo objeto enquanto salva os outros em um vetor de objetos
			if (arquivo.exists() && arquivo.isFile() && arquivo.canRead()) {
				FileInputStream arquivoLeitura = new FileInputStream(arquivo);

				while (arquivoLeitura.available() > 0) {
					// Classe responsável por recuperar os objetos do arquivo
					ObjectInputStream objetoLeitura = new ObjectInputStream(arquivoLeitura);

					ClienteVO clienteLeitura = (ClienteVO) objetoLeitura.readObject();

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
			File arquivo = new File("src/br/edu/ufersa/ropke/model/DAO/arquivos/clientes.dat");

			if (arquivo.exists() && arquivo.isFile() && arquivo.canRead()) {
				FileInputStream arquivoLeitura = new FileInputStream(arquivo);

				int indiceCliente = 1;
				while (arquivoLeitura.available() > 0) {
					// Classe responsável por recuperar os objetos do arquivo
					ObjectInputStream objetoLeitura = new ObjectInputStream(arquivoLeitura);

					ClienteVO cliente = (ClienteVO) objetoLeitura.readObject();
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

	public static ClienteVO[] pesquisarNome(String nome) {
		try {
			// Carrega o arquivo
			File arquivo = new File("src/br/edu/ufersa/ropke/model/DAO/arquivos/clientes.dat");

			ArrayList<ClienteVO> clientes = new ArrayList<ClienteVO>();
			if (arquivo.exists() && arquivo.isFile() && arquivo.canRead()) {
				FileInputStream arquivoLeitura = new FileInputStream(arquivo);

				while (arquivoLeitura.available() > 0) {
					// Classe responsável por recuperar os objetos do arquivo
					ObjectInputStream objetoLeitura = new ObjectInputStream(arquivoLeitura);

					ClienteVO clienteLeitura = (ClienteVO) objetoLeitura.readObject();

					// Salva o cliente no vetor quando o nome for igual ao parametro
					if (clienteLeitura.getNome() == nome) {
						clientes.add(clienteLeitura);
					}
				}

				arquivoLeitura.close();
			}

			// Verifica se existem discos desse genero
			if (clientes.size() != 0) {
				// ArrayList discos para vetor 'vetorDiscos'
				ClienteVO[] vetorClientes = new ClienteVO[clientes.size()];
				vetorClientes = clientes.toArray(vetorClientes);
				return vetorClientes;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("Sem clientes com esse nome!");
		ClienteVO[] semClientes = new ClienteVO[0];
		return semClientes;
	}

	public static ClienteVO pesquisarCpf(String cpf) {
		try {
			// Gera o arquivo para armazenar o objeto
			File arquivo = new File("src/br/edu/ufersa/ropke/model/DAO/arquivos/clientes.dat");

			if (arquivo.exists() && arquivo.isFile() && arquivo.canRead()) {
				FileInputStream arquivoLeitura = new FileInputStream(arquivo);

				cpf = cpf.replaceAll("\\D+", "");
				// Reduz o CPF, removendo tudo que não é dígito
				while (arquivoLeitura.available() > 0) {
					// Classe responsável por recuperar os objetos do arquivo
					ObjectInputStream objetoLeitura = new ObjectInputStream(arquivoLeitura);

					ClienteVO clienteLeitura = (ClienteVO) objetoLeitura.readObject();

					// Retorna o cliente quando obter o mesmo cpf
					if (clienteLeitura.getCpf().equals(cpf)) {
						arquivoLeitura.close();
						objetoLeitura.close();
						return clienteLeitura;
					}
				}

				arquivoLeitura.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("Esse cliente nao existe no sistema!");
		ClienteVO semCliente = new ClienteVO();
		return semCliente;
	}
}
