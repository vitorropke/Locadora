package br.edu.ufersa.ropke.model.DAO;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import br.edu.ufersa.ropke.model.VO.EmprestavelVO;

public abstract class EmprestavelDAO<VO extends EmprestavelVO> extends OperacaoDAO<VO> {
	@Override
	public void cadastrar(VO emprestavel, File arquivo) {
		try {
			FileOutputStream arquivoGravador = new FileOutputStream(arquivo, true);
			// Classe responsável por inserir os objetos
			ObjectOutputStream objetoGravador = new ObjectOutputStream(arquivoGravador);

			// Grava o objeto emprestavel no arquivo
			objetoGravador.writeObject(emprestavel);
			objetoGravador.flush();
			arquivoGravador.close();
			objetoGravador.close();

			System.out.println("Objeto gravado com sucesso!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public void alterar(VO emprestavel, File arquivo) {
		try {
			ArrayList<EmprestavelVO> emprestaveis = new ArrayList<EmprestavelVO>();

			// Procura pelo objeto enquanto salva os outros em um vetor de objetos
			if (arquivo.exists() && arquivo.isFile() && arquivo.canRead()) {
				FileInputStream arquivoLeitura = new FileInputStream(arquivo);
				ObjectInputStream objetoLeitura;
				VO emprestavelLeitura;

				while (arquivoLeitura.available() > 0) {
					// Classe responsável por recuperar os objetos do arquivo
					objetoLeitura = new ObjectInputStream(arquivoLeitura);

					emprestavelLeitura = (VO) objetoLeitura.readObject();

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

			System.out.println("Objeto alterado com sucesso!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public void deletar(VO emprestavel, File arquivo) {
		try {
			ArrayList<VO> emprestaveis = new ArrayList<VO>();

			// Procura pelo objeto enquanto salva os outros em um vetor de objetos
			if (arquivo.exists() && arquivo.isFile() && arquivo.canRead()) {
				FileInputStream arquivoLeitura = new FileInputStream(arquivo);
				ObjectInputStream objetoLeitura;
				VO emprestavelLeitura;

				while (arquivoLeitura.available() > 0) {
					// Classe responsável por recuperar os objetos do arquivo
					objetoLeitura = new ObjectInputStream(arquivoLeitura);

					emprestavelLeitura = (VO) objetoLeitura.readObject();

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

			System.out.println("Objeto apagado com sucesso!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public void pesquisar(File arquivo) {
		try {
			if (arquivo.exists() && arquivo.isFile() && arquivo.canRead()) {
				FileInputStream arquivoLeitura = new FileInputStream(arquivo);
				ObjectInputStream objetoLeitura;
				VO emprestavel;

				int indiceEmprestavel = 1;
				while (arquivoLeitura.available() > 0) {
					// Classe responsável por recuperar os objetos do arquivo
					objetoLeitura = new ObjectInputStream(arquivoLeitura);

					emprestavel = (VO) objetoLeitura.readObject();
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

	@SuppressWarnings("unchecked")
	@Override
	public VO pesquisar(VO emprestavel, File arquivo) {
		try {
			if (arquivo.exists() && arquivo.isFile() && arquivo.canRead()) {
				FileInputStream arquivoLeitura = new FileInputStream(arquivo);
				ObjectInputStream objetoLeitura;
				VO emprestavelLeitura;

				while (arquivoLeitura.available() > 0) {
					// Classe responsável por recuperar os objetos do arquivo
					objetoLeitura = new ObjectInputStream(arquivoLeitura);

					emprestavelLeitura = (VO) objetoLeitura.readObject();

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

	public EmprestavelVO[] pesquisarTitulo(String titulo, File arquivo) {
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

			int numeroEmprestaveis = emprestaveis.size();
			// Verifica se o vetor de emprestáveis não é vazio
			if (numeroEmprestaveis != 0) {
				// ArrayList emprestaveis para vetor 'vetorEmprestaveis'
				EmprestavelVO[] vetorEmprestaveis = new EmprestavelVO[numeroEmprestaveis];
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

	public EmprestavelVO[] pesquisarAnoLancamento(int anoLancamento, File arquivo) {
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

			int numeroEmprestaveis = emprestaveis.size();
			// Verifica se o vetor de emprestáveis não é vazio
			if (numeroEmprestaveis != 0) {
				// ArrayList emprestaveis para vetor 'vetorEmprestaveis'
				EmprestavelVO[] vetorEmprestaveis = new EmprestavelVO[numeroEmprestaveis];
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
