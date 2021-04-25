package br.edu.ufersa.ropke.model.DAO;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import br.edu.ufersa.ropke.model.VO.EmprestavelVO;

public class EmprestavelDAO extends OperacaoDAO {
	public static void cadastrar(EmprestavelVO emprestavel, File arquivo) {
		try {
			// Verifica se o objeto já existe
			boolean emprestavelExiste = false;
			if (arquivo.exists() && arquivo.isFile() && arquivo.canRead()) {
				FileInputStream arquivoLeitura = new FileInputStream(arquivo);

				while (arquivoLeitura.available() > 0 && !emprestavelExiste) {
					// Classe responsável por recuperar os objetos do arquivo
					ObjectInputStream objetoLeitura = new ObjectInputStream(arquivoLeitura);

					EmprestavelVO emprestavelLeitura = (EmprestavelVO) objetoLeitura.readObject();

					// Compara os emprestaveis pelo título deles
					if (emprestavelLeitura.getTitulo().equals(emprestavel.getTitulo())) {
						emprestavelExiste = true;
					}
				}

				arquivoLeitura.close();
			}

			// Se o objeto já existe
			if (emprestavelExiste) {
				System.out.println("Esse emprestavel ja foi cadastrado");
				// Se não existe, prosseguir com a inserção
			} else {
				FileOutputStream arquivoGravador = new FileOutputStream(arquivo, true);
				// Classe responsável por inserir os objetos
				ObjectOutputStream objetoGravador = new ObjectOutputStream(arquivoGravador);

				// Grava o objeto emprestavel no arquivo
				objetoGravador.writeObject(emprestavel);
				objetoGravador.flush();
				arquivoGravador.close();
				objetoGravador.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void alterar(EmprestavelVO emprestavel, File arquivo) {
		try {
			// Verifica se o objeto já existe
			boolean emprestavelExiste = false;
			ArrayList<EmprestavelVO> emprestaveis = new ArrayList<EmprestavelVO>();
			// Procura pelo objeto enquanto salva os outros em um vetor de objetos
			if (arquivo.exists() && arquivo.isFile() && arquivo.canRead()) {
				FileInputStream arquivoLeitura = new FileInputStream(arquivo);

				while (arquivoLeitura.available() > 0) {
					// Classe responsável por recuperar os objetos do arquivo
					ObjectInputStream objetoLeitura = new ObjectInputStream(arquivoLeitura);

					EmprestavelVO emprestavelLeitura = (EmprestavelVO) objetoLeitura.readObject();

					// Compara os emprestaveis pelo título deles
					if (emprestavelLeitura.getTitulo().equals(emprestavel.getTitulo())) {
						emprestavelExiste = true;
						// Quando for o emprestavel a ser alterado, insere do parâmetro do método
						emprestaveis.add(emprestavel);
					} else {
						// Quando não for o emprestavel a ser alterado, insere do arquivo
						emprestaveis.add(emprestavelLeitura);
					}
				}

				arquivoLeitura.close();
			}

			// Se o objeto a ser alterado não existe
			if (!emprestavelExiste) {
				System.out.println("Esse emprestavel nao existe");
				// Se existe, prosseguir com a alteração
			} else {
				FileOutputStream arquivoGravador = new FileOutputStream(arquivo);
				int tamanhoVetorEmprestaveis = emprestaveis.size();

				for (int i = 0; i < tamanhoVetorEmprestaveis; i++) {
					// Classe responsável por inserir os objetos
					ObjectOutputStream objetoGravador = new ObjectOutputStream(arquivoGravador);

					// Grava o objeto emprestavel no arquivo
					objetoGravador.writeObject(emprestaveis.get(i));
					objetoGravador.flush();
				}

				arquivoGravador.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void deletar(EmprestavelVO emprestavel, File arquivo) {
		try {
			// Verifica se o objeto já existe
			boolean emprestavelExiste = false;
			ArrayList<EmprestavelVO> emprestaveis = new ArrayList<EmprestavelVO>();
			// Procura pelo objeto enquanto salva os outros em um vetor de objetos
			if (arquivo.exists() && arquivo.isFile() && arquivo.canRead()) {
				FileInputStream arquivoLeitura = new FileInputStream(arquivo);

				while (arquivoLeitura.available() > 0) {
					// Classe responsável por recuperar os objetos do arquivo
					ObjectInputStream objetoLeitura = new ObjectInputStream(arquivoLeitura);

					EmprestavelVO emprestavelLeitura = (EmprestavelVO) objetoLeitura.readObject();

					// Compara os emprestaveis pelo título deles
					if (emprestavelLeitura.getTitulo().equals(emprestavel.getTitulo())) {
						emprestavelExiste = true;
					} else {
						// Quando não for o emprestavel a ser alterado, insere do arquivo
						emprestaveis.add(emprestavelLeitura);
					}
				}

				arquivoLeitura.close();
			}

			// Se o objeto a ser alterado não existe
			if (!emprestavelExiste) {
				System.out.println("Esse emprestavel nao existe");
				// Se existe, prosseguir com a alteração
			} else {
				FileOutputStream arquivoGravador = new FileOutputStream(arquivo);
				int tamanhoVetorEmprestaveis = emprestaveis.size();

				for (int i = 0; i < tamanhoVetorEmprestaveis; i++) {
					// Classe responsável por inserir os objetos
					ObjectOutputStream objetoGravador = new ObjectOutputStream(arquivoGravador);

					// Grava o objeto emprestavel no arquivo
					objetoGravador.writeObject(emprestaveis.get(i));
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

				int indiceEmprestavel = 1;
				while (arquivoLeitura.available() > 0) {
					// Classe responsável por recuperar os objetos do arquivo
					ObjectInputStream objetoLeitura = new ObjectInputStream(arquivoLeitura);

					EmprestavelVO emprestavel = (EmprestavelVO) objetoLeitura.readObject();
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

	public static EmprestavelVO pesquisarTitulo(String titulo, File arquivo) {
		try {
			if (arquivo.exists() && arquivo.isFile() && arquivo.canRead()) {
				FileInputStream arquivoLeitura = new FileInputStream(arquivo);

				while (arquivoLeitura.available() > 0) {
					// Classe responsável por recuperar os objetos do arquivo
					ObjectInputStream objetoLeitura = new ObjectInputStream(arquivoLeitura);

					EmprestavelVO emprestavelLeitura = (EmprestavelVO) objetoLeitura.readObject();

					// Retorna o emprestavel quando obter o mesmo título
					if (emprestavelLeitura.getTitulo().equals(titulo)) {
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

		System.out.println("Esse emprestavel nao existe no sistema!");
		EmprestavelVO semEmprestavel = new EmprestavelVO();
		return semEmprestavel;
	}

	public static EmprestavelVO[] pesquisarAnoLancamento(int anoLancamento, File arquivo) {
		try {
			ArrayList<EmprestavelVO> emprestaveis = new ArrayList<EmprestavelVO>();
			
			// Procura pelo objeto enquanto salva os outros em um vetor de objetos
			if (arquivo.exists() && arquivo.isFile() && arquivo.canRead()) {
				FileInputStream arquivoLeitura = new FileInputStream(arquivo);

				while (arquivoLeitura.available() > 0) {
					// Classe responsável por recuperar os objetos do arquivo
					ObjectInputStream objetoLeitura = new ObjectInputStream(arquivoLeitura);

					EmprestavelVO livroLeitura = (EmprestavelVO) objetoLeitura.readObject();

					// Salva o livro no vetor quando o genero for igual ao parametro
					if (livroLeitura.getAnoLancamento() == anoLancamento) {
						emprestaveis.add(livroLeitura);
					}
				}

				arquivoLeitura.close();
			}

			// Verifica se existem emprestaveis nesse ano
			if (emprestaveis.size() != 0) {
				// ArrayList emprestaveis para vetor 'vetorEmprestaveis'
				EmprestavelVO[] vetorEmprestaveis = new EmprestavelVO[emprestaveis.size()];
				vetorEmprestaveis = emprestaveis.toArray(vetorEmprestaveis);
				return vetorEmprestaveis;
			} else {
				System.out.println("Sem emprestaveis nesse ano!");
				EmprestavelVO[] semEmprestaveis = new EmprestavelVO[0];
				return semEmprestaveis;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("Sem emprestaveis nesse ano!");
		EmprestavelVO[] semEmprestaveis = new EmprestavelVO[0];
		return semEmprestaveis;
	}
}
