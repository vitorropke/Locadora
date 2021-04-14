package br.edu.ufersa.ropke.model.DAO;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import br.edu.ufersa.ropke.model.VO.DiscoVO;

public class DiscoDAO {
	public static void cadastrar(DiscoVO disco) {
		try {
			// Gera o arquivo para armazenar o objeto
			File arquivo = new File("src/br/edu/ufersa/ropke/model/DAO/arquivos/discos.dat");

			// Verifica se o objeto já existe
			boolean discoExiste = false;
			if (arquivo.exists() && arquivo.isFile() && arquivo.canRead()) {
				FileInputStream arquivoLeitura = new FileInputStream(arquivo);

				while (arquivoLeitura.available() > 0 && !discoExiste) {
					// Classe responsável por recuperar os objetos do arquivo
					ObjectInputStream objetoLeitura = new ObjectInputStream(arquivoLeitura);

					DiscoVO discoLeitura = (DiscoVO) objetoLeitura.readObject();

					// Compara os discos pelo título deles
					if (discoLeitura.getTitulo().equals(disco.getTitulo())) {
						discoExiste = true;
					}
				}

				arquivoLeitura.close();
			}

			// Se o objeto já existe
			if (discoExiste) {
				System.out.println("Esse disco ja foi cadastrado");
				// Se não existe, prosseguir com a inserção
			} else {
				FileOutputStream arquivoGravador = new FileOutputStream(arquivo, true);
				// Classe responsável por inserir os objetos
				ObjectOutputStream objetoGravador = new ObjectOutputStream(arquivoGravador);

				// Grava o objeto disco no arquivo
				objetoGravador.writeObject(disco);
				objetoGravador.flush();
				arquivoGravador.close();
				objetoGravador.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void alterar(DiscoVO disco) {
		try {
			// Gera o arquivo para armazenar o objeto
			File arquivo = new File("src/br/edu/ufersa/ropke/model/DAO/arquivos/discos.dat");

			boolean discoExiste = false;
			ArrayList<DiscoVO> discos = new ArrayList<DiscoVO>();
			// Procura pelo objeto enquanto salva os outros em um vetor de objetos
			if (arquivo.exists() && arquivo.isFile() && arquivo.canRead()) {
				FileInputStream arquivoLeitura = new FileInputStream(arquivo);

				while (arquivoLeitura.available() > 0) {
					// Classe responsável por recuperar os objetos do arquivo
					ObjectInputStream objetoLeitura = new ObjectInputStream(arquivoLeitura);

					DiscoVO discoLeitura = (DiscoVO) objetoLeitura.readObject();

					// Compara os discos pelo título deles
					if (discoLeitura.getTitulo().equals(disco.getTitulo())) {
						discoExiste = true;
						// Quando for o disco a ser alterado, insere do parâmetro do método
						discos.add(disco);
					} else {
						// Quando não for o disco a ser alterado, insere do arquivo
						discos.add(discoLeitura);
					}
				}

				arquivoLeitura.close();
			}

			// Se o objeto a ser alterado não existe
			if (!discoExiste) {
				System.out.println("Esse disco nao existe");
				// Se existe, prosseguir com a alteração
			} else {
				FileOutputStream arquivoGravador = new FileOutputStream(arquivo);
				int tamanhoVetorDiscos = discos.size();

				for (int i = 0; i < tamanhoVetorDiscos; i++) {
					// Classe responsável por inserir os objetos
					ObjectOutputStream objetoGravador = new ObjectOutputStream(arquivoGravador);

					// Grava o objeto disco no arquivo
					objetoGravador.writeObject(discos.get(i));
					objetoGravador.flush();
				}

				arquivoGravador.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void deletar(DiscoVO disco) {
		try {
			// Gera o arquivo para armazenar o objeto
			File arquivo = new File("src/br/edu/ufersa/ropke/model/DAO/arquivos/discos.dat");

			boolean discoExiste = false;
			ArrayList<DiscoVO> discos = new ArrayList<DiscoVO>();
			// Procura pelo objeto enquanto salva os outros em um vetor de objetos
			if (arquivo.exists() && arquivo.isFile() && arquivo.canRead()) {
				FileInputStream arquivoLeitura = new FileInputStream(arquivo);

				while (arquivoLeitura.available() > 0) {
					// Classe responsável por recuperar os objetos do arquivo
					ObjectInputStream objetoLeitura = new ObjectInputStream(arquivoLeitura);

					DiscoVO discoLeitura = (DiscoVO) objetoLeitura.readObject();

					// Compara os discos pelo título deles
					if (discoLeitura.getTitulo().equals(disco.getTitulo())) {
						discoExiste = true;
					} else {
						// Quando não for o disco a ser alterado, insere do arquivo
						discos.add(discoLeitura);
					}
				}

				arquivoLeitura.close();
			}

			// Se o objeto a ser alterado não existe
			if (!discoExiste) {
				System.out.println("Esse disco nao existe");
				// Se existe, prosseguir com a alteração
			} else {
				FileOutputStream arquivoGravador = new FileOutputStream(arquivo);
				int tamanhoVetorDiscos = discos.size();

				for (int i = 0; i < tamanhoVetorDiscos; i++) {
					// Classe responsável por inserir os objetos
					ObjectOutputStream objetoGravador = new ObjectOutputStream(arquivoGravador);

					// Grava o objeto disco no arquivo
					objetoGravador.writeObject(discos.get(i));
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
			File arquivo = new File("src/br/edu/ufersa/ropke/model/DAO/arquivos/discos.dat");

			if (arquivo.exists() && arquivo.isFile() && arquivo.canRead()) {
				FileInputStream arquivoLeitura = new FileInputStream(arquivo);

				int indiceDisco = 1;
				while (arquivoLeitura.available() > 0) {
					// Classe responsável por recuperar os objetos do arquivo
					ObjectInputStream objetoLeitura = new ObjectInputStream(arquivoLeitura);

					DiscoVO disco = (DiscoVO) objetoLeitura.readObject();
					System.out.println("\nDisco " + indiceDisco + '\n');
					System.out.println(disco.toString());
					System.out.println("-----------------------------------------");
					indiceDisco++;
				}

				arquivoLeitura.close();
			} else {
				System.out.println("Sem discos!");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static DiscoVO pesquisarTitulo(String titulo) {
		try {
			// Gera o arquivo para armazenar o objeto
			File arquivo = new File("src/br/edu/ufersa/ropke/model/DAO/arquivos/discos.dat");

			if (arquivo.exists() && arquivo.isFile() && arquivo.canRead()) {
				FileInputStream arquivoLeitura = new FileInputStream(arquivo);

				while (arquivoLeitura.available() > 0) {
					// Classe responsável por recuperar os objetos do arquivo
					ObjectInputStream objetoLeitura = new ObjectInputStream(arquivoLeitura);

					DiscoVO discoLeitura = (DiscoVO) objetoLeitura.readObject();

					// Retorna o disco quando obter o mesmo título
					if (discoLeitura.getTitulo().equals(titulo)) {
						arquivoLeitura.close();
						objetoLeitura.close();
						return discoLeitura;
					}
				}

				arquivoLeitura.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("Esse disco nao existe no sistema!");
		DiscoVO semDisco = new DiscoVO();
		return semDisco;
	}

	public static DiscoVO[] pesquisarBanda(String banda) {
		try {
			// Gera o arquivo para armazenar o objeto
			File arquivo = new File("src/br/edu/ufersa/ropke/model/DAO/arquivos/discos.dat");

			ArrayList<DiscoVO> discos = new ArrayList<DiscoVO>();
			// Procura pelo objeto enquanto salva os outros em um vetor de objetos
			if (arquivo.exists() && arquivo.isFile() && arquivo.canRead()) {
				FileInputStream arquivoLeitura = new FileInputStream(arquivo);

				while (arquivoLeitura.available() > 0) {
					// Classe responsável por recuperar os objetos do arquivo
					ObjectInputStream objetoLeitura = new ObjectInputStream(arquivoLeitura);

					DiscoVO discoLeitura = (DiscoVO) objetoLeitura.readObject();

					// Salva o disco no vetor quando a banda for igual ao parametro
					if (discoLeitura.getBanda().equals(banda)) {
						discos.add(discoLeitura);
					}
				}

				arquivoLeitura.close();
			}

			// Verifica se existem discos desse genero
			if (discos.size() != 0) {
				// ArrayList discos para vetor 'vetorDiscos'
				DiscoVO[] vetorDiscos = new DiscoVO[discos.size()];
				vetorDiscos = discos.toArray(vetorDiscos);
				return vetorDiscos;
			} else {
				System.out.println("Sem discos com essa banda!");
				DiscoVO[] semDiscos = new DiscoVO[0];
				return semDiscos;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("Sem discos com essa banda!");
		DiscoVO[] semDiscos = new DiscoVO[0];
		return semDiscos;
	}

	public static DiscoVO[] pesquisarEstilo(String estilo) {
		try {
			// Gera o arquivo para armazenar o objeto
			File arquivo = new File("src/br/edu/ufersa/ropke/model/DAO/arquivos/discos.dat");

			ArrayList<DiscoVO> discos = new ArrayList<DiscoVO>();
			// Procura pelo objeto enquanto salva os outros em um vetor de objetos
			if (arquivo.exists() && arquivo.isFile() && arquivo.canRead()) {
				FileInputStream arquivoLeitura = new FileInputStream(arquivo);

				while (arquivoLeitura.available() > 0) {
					// Classe responsável por recuperar os objetos do arquivo
					ObjectInputStream objetoLeitura = new ObjectInputStream(arquivoLeitura);

					DiscoVO discoLeitura = (DiscoVO) objetoLeitura.readObject();

					// Salva o disco no vetor quando a banda for igual ao parametro
					if (discoLeitura.getEstilo().equals(estilo)) {
						discos.add(discoLeitura);
					}
				}

				arquivoLeitura.close();
			}

			// Verifica se existem discos desse genero
			if (discos.size() != 0) {
				// ArrayList discos para vetor 'vetorDiscos'
				DiscoVO[] vetorDiscos = new DiscoVO[discos.size()];
				vetorDiscos = discos.toArray(vetorDiscos);
				return vetorDiscos;
			} else {
				System.out.println("Sem discos com essa banda!");
				DiscoVO[] semDiscos = new DiscoVO[0];
				return semDiscos;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("Sem discos com essa banda!");
		DiscoVO[] semDiscos = new DiscoVO[0];
		return semDiscos;
	}

	public static DiscoVO[] pesquisarAno(int anoLancamento) {
		try {
			// Gera o arquivo para armazenar o objeto
			File arquivo = new File("src/br/edu/ufersa/ropke/model/DAO/arquivos/discos.dat");

			ArrayList<DiscoVO> discos = new ArrayList<DiscoVO>();
			// Procura pelo objeto enquanto salva os outros em um vetor de objetos
			if (arquivo.exists() && arquivo.isFile() && arquivo.canRead()) {
				FileInputStream arquivoLeitura = new FileInputStream(arquivo);

				while (arquivoLeitura.available() > 0) {
					// Classe responsável por recuperar os objetos do arquivo
					ObjectInputStream objetoLeitura = new ObjectInputStream(arquivoLeitura);

					DiscoVO discoLeitura = (DiscoVO) objetoLeitura.readObject();

					// Salva o disco no vetor quando a banda for igual ao parametro
					if (discoLeitura.getAnoLancamento() == anoLancamento) {
						discos.add(discoLeitura);
					}
				}

				arquivoLeitura.close();
			}

			// Verifica se existem discos desse genero
			if (discos.size() != 0) {
				// ArrayList discos para vetor 'vetorDiscos'
				DiscoVO[] vetorDiscos = new DiscoVO[discos.size()];
				vetorDiscos = discos.toArray(vetorDiscos);
				return vetorDiscos;
			} else {
				System.out.println("Sem discos nesse ano!");
				DiscoVO[] semDiscos = new DiscoVO[0];
				return semDiscos;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("Sem discos nesse ano!");
		DiscoVO[] semDiscos = new DiscoVO[0];
		return semDiscos;
	}
}
