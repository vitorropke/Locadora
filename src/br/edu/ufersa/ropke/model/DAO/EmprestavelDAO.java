package br.edu.ufersa.ropke.model.DAO;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import br.edu.ufersa.ropke.model.VO.EmprestavelVO;

public abstract class EmprestavelDAO extends OperacaoDAO {
	public static void cadastrar(EmprestavelVO emprestavel, File arquivo) {
		try {
			FileOutputStream arquivoGravador = new FileOutputStream(arquivo, true);
			// Classe responsável por inserir os objetos
			ObjectOutputStream objetoGravador = new ObjectOutputStream(arquivoGravador);

			// Grava o objeto emprestavel no arquivo
			objetoGravador.writeObject(emprestavel);
			objetoGravador.flush();
			arquivoGravador.close();
			objetoGravador.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void alterar(EmprestavelVO emprestavel, File arquivo) {
		try {
			ArrayList<EmprestavelVO> emprestaveis = new ArrayList<EmprestavelVO>();

			// Procura pelo objeto enquanto salva os outros em um vetor de objetos
			if (arquivo.exists() && arquivo.isFile() && arquivo.canRead()) {
				FileInputStream arquivoLeitura = new FileInputStream(arquivo);
				ObjectInputStream objetoLeitura;
				EmprestavelVO emprestavelLeitura;

				while (arquivoLeitura.available() > 0) {
					// Classe responsável por recuperar os objetos do arquivo
					objetoLeitura = new ObjectInputStream(arquivoLeitura);

					emprestavelLeitura = (EmprestavelVO) objetoLeitura.readObject();

					// Compara os emprestaveis pelo título deles
					if (emprestavelLeitura.getTitulo().equals(emprestavel.getTitulo())) {
						// Quando for o emprestavel a ser alterado, insere no vetor, o objeto que vem do
						// parâmetro do método 'alterar'
						emprestaveis.add(emprestavel);
					} else {
						// Quando não for o emprestavel a ser alterado, insere do arquivo
						emprestaveis.add(emprestavelLeitura);
					}
				}

				arquivoLeitura.close();
			}

			// Escrita com o objeto modificado
			FileOutputStream arquivoGravador = new FileOutputStream(arquivo);
			ObjectOutputStream objetoGravador;
			int tamanhoVetorEmprestaveis = emprestaveis.size();

			for (int i = 0; i < tamanhoVetorEmprestaveis; i++) {
				// Classe responsável por inserir os objetos
				objetoGravador = new ObjectOutputStream(arquivoGravador);

				// Grava o objeto emprestavel no arquivo
				objetoGravador.writeObject(emprestaveis.get(i));
				objetoGravador.flush();
			}

			arquivoGravador.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void deletar(EmprestavelVO emprestavel, File arquivo) {
		try {
			ArrayList<EmprestavelVO> emprestaveis = new ArrayList<EmprestavelVO>();

			// Procura pelo objeto enquanto salva os outros em um vetor de objetos
			if (arquivo.exists() && arquivo.isFile() && arquivo.canRead()) {
				FileInputStream arquivoLeitura = new FileInputStream(arquivo);
				ObjectInputStream objetoLeitura;
				EmprestavelVO emprestavelLeitura;

				while (arquivoLeitura.available() > 0) {
					// Classe responsável por recuperar os objetos do arquivo
					objetoLeitura = new ObjectInputStream(arquivoLeitura);

					emprestavelLeitura = (EmprestavelVO) objetoLeitura.readObject();

					// Compara os emprestaveis pelo título deles
					if (!emprestavelLeitura.getTitulo().equals(emprestavel.getTitulo())) {
						// Quando não encontrar o emprestável, insere no vetor
						// Quando encontrar o emprestável, não insere no vetor
						emprestaveis.add(emprestavelLeitura);
					}
				}

				arquivoLeitura.close();
			}

			// Escrita com o objeto removido
			FileOutputStream arquivoGravador = new FileOutputStream(arquivo);
			ObjectOutputStream objetoGravador;
			int tamanhoVetorEmprestaveis = emprestaveis.size();

			for (int i = 0; i < tamanhoVetorEmprestaveis; i++) {
				// Classe responsável por inserir os objetos
				objetoGravador = new ObjectOutputStream(arquivoGravador);

				// Grava o objeto emprestavel no arquivo
				objetoGravador.writeObject(emprestaveis.get(i));
				objetoGravador.flush();
			}

			arquivoGravador.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void pesquisar(File arquivo) {
		try {
			if (arquivo.exists() && arquivo.isFile() && arquivo.canRead()) {
				FileInputStream arquivoLeitura = new FileInputStream(arquivo);
				ObjectInputStream objetoLeitura;
				EmprestavelVO emprestavel;

				int indiceEmprestavel = 1;
				while (arquivoLeitura.available() > 0) {
					// Classe responsável por recuperar os objetos do arquivo
					objetoLeitura = new ObjectInputStream(arquivoLeitura);

					emprestavel = (EmprestavelVO) objetoLeitura.readObject();
					System.out.println("\nEmprestavel " + indiceEmprestavel + '\n');
					System.out.println(emprestavel.toString());
					System.out.println("-----------------------------------------");
					indiceEmprestavel++;
				}

				arquivoLeitura.close();
			} else {
				System.out.println("Sem emprestaveis!");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static EmprestavelVO pesquisar(EmprestavelVO emprestavel, File arquivo) {
		try {
			if (arquivo.exists() && arquivo.isFile() && arquivo.canRead()) {
				FileInputStream arquivoLeitura = new FileInputStream(arquivo);
				ObjectInputStream objetoLeitura;
				EmprestavelVO emprestavelLeitura;

				while (arquivoLeitura.available() > 0) {
					// Classe responsável por recuperar os objetos do arquivo
					objetoLeitura = new ObjectInputStream(arquivoLeitura);

					emprestavelLeitura = (EmprestavelVO) objetoLeitura.readObject();

					// Retorna o emprestavel quando obter o mesmo título
					if (emprestavelLeitura.getTitulo().equals(emprestavel.getTitulo())) {
						arquivoLeitura.close();
						objetoLeitura.close();
						return emprestavelLeitura;
					}
				}

				arquivoLeitura.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("Emprestavel nao encontrado");
		return null;
	}

	public static EmprestavelVO[] pesquisarTitulo(String titulo, File arquivo) {
		try {
			ArrayList<EmprestavelVO> emprestaveis = new ArrayList<EmprestavelVO>();

			// Procura pelo objeto enquanto salva os outros em um vetor de objetos
			if (arquivo.exists() && arquivo.isFile() && arquivo.canRead()) {
				FileInputStream arquivoLeitura = new FileInputStream(arquivo);
				ObjectInputStream objetoLeitura;
				EmprestavelVO emprestavelLeitura;

				while (arquivoLeitura.available() > 0) {
					// Classe responsável por recuperar os objetos do arquivo
					objetoLeitura = new ObjectInputStream(arquivoLeitura);

					emprestavelLeitura = (EmprestavelVO) objetoLeitura.readObject();

					// Salva o emprestável no vetor quando parte do nome do título coincidir com o
					// parâmetro
					if (emprestavelLeitura.getTitulo().contains(titulo)) {
						emprestaveis.add(emprestavelLeitura);
					}
				}

				arquivoLeitura.close();
			}

			// Verifica se o vetor de emprestáveis não é vazio
			if (emprestaveis.size() != 0) {
				// ArrayList emprestaveis para vetor 'vetorEmprestaveis'
				EmprestavelVO[] vetorEmprestaveis = new EmprestavelVO[emprestaveis.size()];
				vetorEmprestaveis = emprestaveis.toArray(vetorEmprestaveis);
				return vetorEmprestaveis;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("Sem emprestaveis com esse titulo");
		EmprestavelVO[] semEmprestaveis = new EmprestavelVO[0];
		return semEmprestaveis;
	}

	public static EmprestavelVO[] pesquisarAnoLancamento(int anoLancamento, File arquivo) {
		try {
			ArrayList<EmprestavelVO> emprestaveis = new ArrayList<EmprestavelVO>();

			// Procura pelo objeto enquanto salva os outros em um vetor de objetos
			if (arquivo.exists() && arquivo.isFile() && arquivo.canRead()) {
				FileInputStream arquivoLeitura = new FileInputStream(arquivo);
				ObjectInputStream objetoLeitura;
				EmprestavelVO emprestavelLeitura;

				while (arquivoLeitura.available() > 0) {
					// Classe responsável por recuperar os objetos do arquivo
					objetoLeitura = new ObjectInputStream(arquivoLeitura);

					emprestavelLeitura = (EmprestavelVO) objetoLeitura.readObject();

					// Salva o emprestável no vetor quando o ano for igual ao parametro
					if (emprestavelLeitura.getAnoLancamento() == anoLancamento) {
						emprestaveis.add(emprestavelLeitura);
					}
				}

				arquivoLeitura.close();
			}

			// Verifica se o vetor de emprestáveis não é vazio
			if (emprestaveis.size() != 0) {
				// ArrayList emprestaveis para vetor 'vetorEmprestaveis'
				EmprestavelVO[] vetorEmprestaveis = new EmprestavelVO[emprestaveis.size()];
				vetorEmprestaveis = emprestaveis.toArray(vetorEmprestaveis);
				return vetorEmprestaveis;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("Sem emprestaveis nesse ano");
		EmprestavelVO[] semEmprestaveis = new EmprestavelVO[0];
		return semEmprestaveis;
	}
}
