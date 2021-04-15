package br.edu.ufersa.ropke.model.DAO;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import br.edu.ufersa.ropke.model.VO.LivroVO;

public class LivroDAO {
	public static void cadastrar(LivroVO livro) {
		try {
			// Gera o arquivo para armazenar o objeto
			File arquivo = new File("src/br/edu/ufersa/ropke/model/DAO/arquivos/livros.dat");
			
			// Verifica se o objeto já existe
			boolean livroExiste = false;
			if (arquivo.exists() && arquivo.isFile() && arquivo.canRead()) {
				FileInputStream arquivoLeitura = new FileInputStream(arquivo);

				while (arquivoLeitura.available() > 0 && !livroExiste) {
					// Classe responsável por recuperar os objetos do arquivo
					ObjectInputStream objetoLeitura = new ObjectInputStream(arquivoLeitura);

					LivroVO livroLeitura = (LivroVO) objetoLeitura.readObject();
					
					// Compara os livros pelo título deles
					if (livroLeitura.getTitulo().equals(livro.getTitulo())) {
						livroExiste = true;
					}
				}

				arquivoLeitura.close();
			}

			// Se o objeto já existe
			if (livroExiste) {
				System.out.println("Esse livro ja foi cadastrado");
				// Se não existe, prosseguir com a inserção
			} else {
				FileOutputStream arquivoGravador = new FileOutputStream(arquivo, true);
				// Classe responsável por inserir os objetos
				ObjectOutputStream objetoGravador = new ObjectOutputStream(arquivoGravador);
				
				// Grava o objeto livro no arquivo
				objetoGravador.writeObject(livro);
				objetoGravador.flush();
				arquivoGravador.close();
				objetoGravador.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void alterar(LivroVO livro) {
		try {
			// Gera o arquivo para armazenar o objeto
			File arquivo = new File("src/br/edu/ufersa/ropke/model/DAO/arquivos/livros.dat");

			boolean livroExiste = false;
			ArrayList<LivroVO> livros = new ArrayList<LivroVO>();
			// Procura pelo objeto enquanto salva os outros em um vetor de objetos
			if (arquivo.exists() && arquivo.isFile() && arquivo.canRead()) {
				FileInputStream arquivoLeitura = new FileInputStream(arquivo);

				while (arquivoLeitura.available() > 0) {
					// Classe responsável por recuperar os objetos do arquivo
					ObjectInputStream objetoLeitura = new ObjectInputStream(arquivoLeitura);

					LivroVO livroLeitura = (LivroVO) objetoLeitura.readObject();

					// Compara os livros pelo título deles
					if (livroLeitura.getTitulo().equals(livro.getTitulo())) {
						livroExiste = true;
						// Quando for o livro a ser alterado, insere do parâmetro do método
						livros.add(livro);
					} else {
						// Quando não for o livro a ser alterado, insere do arquivo
						livros.add(livroLeitura);
					}
				}

				arquivoLeitura.close();
			}

			// Se o objeto a ser alterado não existe
			if (!livroExiste) {
				System.out.println("Esse livro nao existe");
				// Se existe, prosseguir com a alteração
			} else {
				FileOutputStream arquivoGravador = new FileOutputStream(arquivo);
				int tamanhoVetorLivros = livros.size();

				for (int i = 0; i < tamanhoVetorLivros; i++) {
					// Classe responsável por inserir os objetos
					ObjectOutputStream objetoGravador = new ObjectOutputStream(arquivoGravador);

					// Grava o objeto livro no arquivo
					objetoGravador.writeObject(livros.get(i));
					objetoGravador.flush();
				}

				arquivoGravador.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void deletar(LivroVO livro) {
		try {
			// Gera o arquivo para armazenar o objeto
			File arquivo = new File("src/br/edu/ufersa/ropke/model/DAO/arquivos/livros.dat");

			boolean livroExiste = false;
			ArrayList<LivroVO> livros = new ArrayList<LivroVO>();
			// Procura pelo objeto enquanto salva os outros em um vetor de objetos
			if (arquivo.exists() && arquivo.isFile() && arquivo.canRead()) {
				FileInputStream arquivoLeitura = new FileInputStream(arquivo);

				while (arquivoLeitura.available() > 0) {
					// Classe responsável por recuperar os objetos do arquivo
					ObjectInputStream objetoLeitura = new ObjectInputStream(arquivoLeitura);

					LivroVO livroLeitura = (LivroVO) objetoLeitura.readObject();

					// Compara os livros pelo título deles
					if (livroLeitura.getTitulo().equals(livro.getTitulo())) {
						livroExiste = true;
					} else {
						// Quando não for o livro a ser alterado, insere do arquivo
						livros.add(livroLeitura);
					}
				}

				arquivoLeitura.close();
			}

			// Se o objeto a ser alterado não existe
			if (!livroExiste) {
				System.out.println("Esse livro nao existe");
				// Se existe, prosseguir com a alteração
			} else {
				FileOutputStream arquivoGravador = new FileOutputStream(arquivo);
				int tamanhoVetorLivros = livros.size();

				for (int i = 0; i < tamanhoVetorLivros; i++) {
					// Classe responsável por inserir os objetos
					ObjectOutputStream objetoGravador = new ObjectOutputStream(arquivoGravador);

					// Grava o objeto livro no arquivo
					objetoGravador.writeObject(livros.get(i));
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
			File arquivo = new File("src/br/edu/ufersa/ropke/model/DAO/arquivos/livros.dat");

			if (arquivo.exists() && arquivo.isFile() && arquivo.canRead()) {
				FileInputStream arquivoLeitura = new FileInputStream(arquivo);

				int indiceLivro = 1;
				while (arquivoLeitura.available() > 0) {
					// Classe responsável por recuperar os objetos do arquivo
					ObjectInputStream objetoLeitura = new ObjectInputStream(arquivoLeitura);

					LivroVO livro = (LivroVO) objetoLeitura.readObject();
					System.out.println("\nLivro " + indiceLivro + '\n');
					System.out.println(livro.toString());
					System.out.println("-----------------------------------------");
					indiceLivro++;
				}

				arquivoLeitura.close();
			} else {
				System.out.println("Sem livros!");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static LivroVO pesquisarTitulo(String titulo) {
		try {
			// Gera o arquivo para armazenar o objeto
			File arquivo = new File("src/br/edu/ufersa/ropke/model/DAO/arquivos/livros.dat");

			if (arquivo.exists() && arquivo.isFile() && arquivo.canRead()) {
				FileInputStream arquivoLeitura = new FileInputStream(arquivo);

				while (arquivoLeitura.available() > 0) {
					// Classe responsável por recuperar os objetos do arquivo
					ObjectInputStream objetoLeitura = new ObjectInputStream(arquivoLeitura);

					LivroVO livroLeitura = (LivroVO) objetoLeitura.readObject();

					// Retorna o livro quando obter o mesmo título
					if (livroLeitura.getTitulo().equals(titulo)) {
						arquivoLeitura.close();
						objetoLeitura.close();
						return livroLeitura;
					}
				}

				arquivoLeitura.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("Esse livro nao existe no sistema!");
		LivroVO semLivro = new LivroVO();
		return semLivro;
	}

	public static LivroVO[] pesquisarGenero(String genero) {
		try {
			// Gera o arquivo para armazenar o objeto
			File arquivo = new File("src/br/edu/ufersa/ropke/model/DAO/arquivos/livros.dat");

			ArrayList<LivroVO> livros = new ArrayList<LivroVO>();
			// Procura pelo objeto enquanto salva os outros em um vetor de objetos
			if (arquivo.exists() && arquivo.isFile() && arquivo.canRead()) {
				FileInputStream arquivoLeitura = new FileInputStream(arquivo);

				while (arquivoLeitura.available() > 0) {
					// Classe responsável por recuperar os objetos do arquivo
					ObjectInputStream objetoLeitura = new ObjectInputStream(arquivoLeitura);

					LivroVO livroLeitura = (LivroVO) objetoLeitura.readObject();

					// Salva o livro no vetor quando o genero for igual ao parametro
					if (livroLeitura.getGenero().equals(genero)) {
						livros.add(livroLeitura);
					}
				}

				arquivoLeitura.close();
			}

			// Verifica se existem livros desse genero
			if (livros.size() != 0) {
				// ArrayList livros para vetor 'vetorLivros'
				LivroVO[] vetorLivros = new LivroVO[livros.size()];
				vetorLivros = livros.toArray(vetorLivros);
				return vetorLivros;
			} else {
				System.out.println("Sem livros com esse genero!");
				LivroVO[] semLivros = new LivroVO[0];
				return semLivros;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("Sem livros com esse genero!");
		LivroVO[] semLivros = new LivroVO[0];
		return semLivros;
	}

	public static LivroVO[] pesquisarAno(int anoLancamento) {
		try {
			// Gera o arquivo para armazenar o objeto
			File arquivo = new File("src/br/edu/ufersa/ropke/model/DAO/arquivos/livros.dat");

			ArrayList<LivroVO> livros = new ArrayList<LivroVO>();
			// Procura pelo objeto enquanto salva os outros em um vetor de objetos
			if (arquivo.exists() && arquivo.isFile() && arquivo.canRead()) {
				FileInputStream arquivoLeitura = new FileInputStream(arquivo);

				while (arquivoLeitura.available() > 0) {
					// Classe responsável por recuperar os objetos do arquivo
					ObjectInputStream objetoLeitura = new ObjectInputStream(arquivoLeitura);

					LivroVO livroLeitura = (LivroVO) objetoLeitura.readObject();

					// Salva o livro no vetor quando o genero for igual ao parametro
					if (livroLeitura.getAnoLancamento() == anoLancamento) {
						livros.add(livroLeitura);
					}
				}

				arquivoLeitura.close();
			}

			// Verifica se existem livros nesse ano
			if (livros.size() != 0) {
				// ArrayList livros para vetor 'vetorLivros'
				LivroVO[] vetorLivros = new LivroVO[livros.size()];
				vetorLivros = livros.toArray(vetorLivros);
				return vetorLivros;
			} else {
				System.out.println("Sem livros nesse ano!");
				LivroVO[] semLivros = new LivroVO[0];
				return semLivros;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("Sem livros nesse ano!");
		LivroVO[] semLivros = new LivroVO[0];
		return semLivros;
	}
}
