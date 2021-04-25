package br.edu.ufersa.ropke.model.DAO;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import br.edu.ufersa.ropke.model.VO.PessoaVO;

public class PessoaDAO extends OperacaoDAO {
	public static void cadastrar(PessoaVO pessoa, File arquivo) {
		try {
			// Verifica se o objeto já existe
			boolean pessoaExiste = false;
			if (arquivo.exists() && arquivo.isFile() && arquivo.canRead()) {
				FileInputStream arquivoLeitura = new FileInputStream(arquivo);

				while (arquivoLeitura.available() > 0 && !pessoaExiste) {
					// Classe responsável por recuperar os objetos do arquivo
					ObjectInputStream objetoLeitura = new ObjectInputStream(arquivoLeitura);

					PessoaVO pessoaLeitura = (PessoaVO) objetoLeitura.readObject();

					// Compara os pessoas pelo cpf deles
					if (pessoaLeitura.getCpf().equals(pessoa.getCpf())) {
						pessoaExiste = true;
					}
				}

				arquivoLeitura.close();
			}

			// Se o objeto já existe
			if (pessoaExiste) {
				System.out.println("Essa pessoa ja foi cadastrada");
				// Se não existe, prosseguir com a inserção
			} else {
				FileOutputStream arquivoGravador = new FileOutputStream(arquivo, true);
				// Classe responsável por inserir os objetos
				ObjectOutputStream objetoGravador = new ObjectOutputStream(arquivoGravador);

				// Grava o objeto pessoa no arquivo
				objetoGravador.writeObject(pessoa);
				objetoGravador.flush();
				arquivoGravador.close();
				objetoGravador.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void alterar(PessoaVO pessoa, File arquivo) {
		try {
			// Verifica se o objeto existe
			boolean pessoaExiste = false;
			ArrayList<PessoaVO> pessoas = new ArrayList<PessoaVO>();
			// Procura pelo objeto enquanto salva os outros em um vetor de objetos
			if (arquivo.exists() && arquivo.isFile() && arquivo.canRead()) {
				FileInputStream arquivoLeitura = new FileInputStream(arquivo);

				while (arquivoLeitura.available() > 0) {
					// Classe responsável por recuperar os objetos do arquivo
					ObjectInputStream objetoLeitura = new ObjectInputStream(arquivoLeitura);

					PessoaVO pessoaLeitura = (PessoaVO) objetoLeitura.readObject();

					// Compara os pessoas pelo CPF deles
					if (pessoaLeitura.getCpf().equals(pessoa.getCpf())) {
						pessoaExiste = true;
						// Quando for o objeto a ser alterado, insere do parâmetro do método
						pessoas.add(pessoa);
					} else {
						// Quando não for o objeto a ser alterado, insere do arquivo
						pessoas.add(pessoaLeitura);
					}
				}

				arquivoLeitura.close();
			}

			// Se o objeto a ser alterado não existe
			if (!pessoaExiste) {
				System.out.println("Essa pessoa nao existe");
				// Se existe, prosseguir com a alteração
			} else {
				FileOutputStream arquivoGravador = new FileOutputStream(arquivo);
				int tamanhoVetorPessoas = pessoas.size();

				for (int i = 0; i < tamanhoVetorPessoas; i++) {
					// Classe responsável por inserir os objetos
					ObjectOutputStream objetoGravador = new ObjectOutputStream(arquivoGravador);

					// Grava o objeto pessoa no arquivo
					objetoGravador.writeObject(pessoas.get(i));
					objetoGravador.flush();
				}

				arquivoGravador.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void deletar(PessoaVO pessoa, File arquivo) {
		try {
			// Verifica se o objeto existe
			boolean pessoaExiste = false;
			ArrayList<PessoaVO> pessoas = new ArrayList<PessoaVO>();
			// Procura pelo objeto enquanto salva os outros em um vetor de objetos
			if (arquivo.exists() && arquivo.isFile() && arquivo.canRead()) {
				FileInputStream arquivoLeitura = new FileInputStream(arquivo);

				while (arquivoLeitura.available() > 0) {
					// Classe responsável por recuperar os objetos do arquivo
					ObjectInputStream objetoLeitura = new ObjectInputStream(arquivoLeitura);

					PessoaVO pessoaLeitura = (PessoaVO) objetoLeitura.readObject();

					// Compara os pessoas pelo CPF deles
					if (pessoaLeitura.getCpf().equals(pessoa.getCpf())) {
						pessoaExiste = true;
					} else {
						// Quando não for o pessoa a ser alterado, insere do arquivo
						pessoas.add(pessoaLeitura);
					}
				}

				arquivoLeitura.close();
			}

			// Se o objeto a ser alterado não existe
			if (!pessoaExiste) {
				System.out.println("Essa pessoa nao existe");
				// Se existe, prosseguir com a alteração
			} else {
				FileOutputStream arquivoGravador = new FileOutputStream(arquivo);
				int tamanhoVetorPessoas = pessoas.size();

				for (int i = 0; i < tamanhoVetorPessoas; i++) {
					// Classe responsável por inserir os objetos
					ObjectOutputStream objetoGravador = new ObjectOutputStream(arquivoGravador);

					// Grava o objeto pessoa no arquivo
					objetoGravador.writeObject(pessoas.get(i));
					objetoGravador.flush();
				}

				arquivoGravador.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void pesquisar(File arquivo) {
		try {
			if (arquivo.exists() && arquivo.isFile() && arquivo.canRead()) {
				FileInputStream arquivoLeitura = new FileInputStream(arquivo);

				int indicePessoa = 1;
				while (arquivoLeitura.available() > 0) {
					// Classe responsável por recuperar os objetos do arquivo
					ObjectInputStream objetoLeitura = new ObjectInputStream(arquivoLeitura);

					PessoaVO pessoa = (PessoaVO) objetoLeitura.readObject();
					System.out.println("\nPessoa " + indicePessoa + '\n');
					System.out.println(pessoa.toString());
					System.out.println("-----------------------------------------");
					indicePessoa++;
				}

				arquivoLeitura.close();
			} else {
				System.out.println("Sem pessoas");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
