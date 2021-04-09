package br.edu.ufersa.ropke.model.DAO;

import java.io.*;
import java.util.List;
import java.util.ArrayList;

import br.edu.ufersa.ropke.model.VO.Funcionario;

public class FuncionarioDAO {
	public static void cadastrar(Funcionario funcionario) {
		try {
			// Gera o arquivo para armazenar o objeto
			File arquivo = new File("br/edu/ufersa/ropke/model/DAO/arquivos/funcionarios.dat");

			// Verifica se o objeto já existe
			boolean funcionarioExiste = false;
			if (arquivo.exists() && arquivo.isFile() && arquivo.canRead()) {
				FileInputStream arquivoLeitura = new FileInputStream(arquivo);

				while (arquivoLeitura.available() > 0 && !funcionarioExiste) {
					// Classe responsável por recuperar os objetos do arquivo
					ObjectInputStream objetoLeitura = new ObjectInputStream(arquivoLeitura);

					Funcionario funcionarioLeitura = (Funcionario)objetoLeitura.readObject();

					// Compara os funcionarios pelo cpf deles
					if (funcionarioLeitura.getCpf().equals(funcionario.getCpf())) {
						funcionarioExiste = true;
					}
				}

				arquivoLeitura.close();
			}

			// Se o objeto já existe
			if (funcionarioExiste) {
				System.out.println("Esse funcionario ja foi cadastrado");
			// Se não existe, prosseguir com a inserção
			} else {
				FileOutputStream arquivoGravador = new FileOutputStream(arquivo, true);
				// Classe responsável por inserir os objetos
				ObjectOutputStream objetoGravador = new ObjectOutputStream(arquivoGravador);

				// Grava o objeto funcionario no arquivo
				objetoGravador.writeObject(funcionario);
				objetoGravador.flush();
				arquivoGravador.close();
				objetoGravador.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void alterar(Funcionario funcionario) {
		try {
			// Gera o arquivo para armazenar o objeto
			File arquivo = new File("br/edu/ufersa/ropke/model/DAO/arquivos/funcionarios.dat");

			boolean funcionarioExiste = false;
			ArrayList<Funcionario> funcionarios = new ArrayList<Funcionario>();
			// Procura pelo objeto enquanto salva os outros em um vetor de objetos
			if (arquivo.exists() && arquivo.isFile() && arquivo.canRead()) {
				FileInputStream arquivoLeitura = new FileInputStream(arquivo);

				while (arquivoLeitura.available() > 0) {
					// Classe responsável por recuperar os objetos do arquivo
					ObjectInputStream objetoLeitura = new ObjectInputStream(arquivoLeitura);

					Funcionario funcionarioLeitura = (Funcionario)objetoLeitura.readObject();

					// Compara os funcionarios pelo CPF deles
					if (funcionarioLeitura.getCpf().equals(funcionario.getCpf())) {
						funcionarioExiste = true;
						// Quando for o funcionario a ser alterado, insere do parâmetro do método
						funcionarios.add(funcionario);
					} else {
						// Quando não for o funcionario a ser alterado, insere do arquivo
						funcionarios.add(funcionarioLeitura);
					}
				}

				arquivoLeitura.close();
			}

			// Se o objeto a ser alterado não existe
			if (!funcionarioExiste) {
				System.out.println("Esse funcionario nao existe");
			// Se existe, prosseguir com a alteração
			} else {
				FileOutputStream arquivoGravador = new FileOutputStream(arquivo);
				int tamanhoVetorFuncionarios = funcionarios.size();
				
				for (int i = 0; i < tamanhoVetorFuncionarios; i++) {
					// Classe responsável por inserir os objetos
					ObjectOutputStream objetoGravador = new ObjectOutputStream(arquivoGravador);

					// Grava o objeto funcionario no arquivo
					objetoGravador.writeObject(funcionarios.get(i));
					objetoGravador.flush();
				}
				
				arquivoGravador.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void deletar(Funcionario funcionario) {
		try {
			// Gera o arquivo para armazenar o objeto
			File arquivo = new File("br/edu/ufersa/ropke/model/DAO/arquivos/funcionarios.dat");

			boolean funcionarioExiste = false;
			ArrayList<Funcionario> funcionarios = new ArrayList<Funcionario>();
			// Procura pelo objeto enquanto salva os outros em um vetor de objetos
			if (arquivo.exists() && arquivo.isFile() && arquivo.canRead()) {
				FileInputStream arquivoLeitura = new FileInputStream(arquivo);

				while (arquivoLeitura.available() > 0) {
					// Classe responsável por recuperar os objetos do arquivo
					ObjectInputStream objetoLeitura = new ObjectInputStream(arquivoLeitura);

					Funcionario funcionarioLeitura = (Funcionario)objetoLeitura.readObject();

					// Compara os funcionarios pelo CPF deles
					if (funcionarioLeitura.getCpf().equals(funcionario.getCpf())) {
						funcionarioExiste = true;
					} else {
						// Quando não for o funcionario a ser alterado, insere do arquivo
						funcionarios.add(funcionarioLeitura);
					}
				}

				arquivoLeitura.close();
			}

			// Se o objeto a ser alterado não existe
			if (!funcionarioExiste) {
				System.out.println("Esse funcionario nao existe");
			// Se existe, prosseguir com a alteração
			} else {
				FileOutputStream arquivoGravador = new FileOutputStream(arquivo);
				int tamanhoVetorFuncionarios = funcionarios.size();
				
				for (int i = 0; i < tamanhoVetorFuncionarios; i++) {
					// Classe responsável por inserir os objetos
					ObjectOutputStream objetoGravador = new ObjectOutputStream(arquivoGravador);

					// Grava o objeto funcionario no arquivo
					objetoGravador.writeObject(funcionarios.get(i));
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
			File arquivo = new File("br/edu/ufersa/ropke/model/DAO/arquivos/funcionarios.dat");

			if (arquivo.exists() && arquivo.isFile() && arquivo.canRead()) {
				FileInputStream arquivoLeitura = new FileInputStream(arquivo);

				int indiceFuncionario = 1;
				while (arquivoLeitura.available() > 0) {
					// Classe responsável por recuperar os objetos do arquivo
					ObjectInputStream objetoLeitura = new ObjectInputStream(arquivoLeitura);
					
					Funcionario funcionario = (Funcionario)objetoLeitura.readObject();
					System.out.println("\nFuncionario " + indiceFuncionario + '\n');
					System.out.println(funcionario.toString());
					System.out.println("-----------------------------------------");
					indiceFuncionario++;
				}

				arquivoLeitura.close();
			} else {
				System.out.println("Sem funcionarios");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
