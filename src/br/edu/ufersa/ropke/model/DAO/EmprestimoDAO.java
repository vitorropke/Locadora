package br.edu.ufersa.ropke.model.DAO;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import br.edu.ufersa.ropke.model.VO.EmprestimoVO;

public class EmprestimoDAO {
	public static void cadastrar(EmprestimoVO emprestimo) {
		try {
			// Gera o arquivo para armazenar o objeto
			File arquivo = new File("src/br/edu/ufersa/ropke/model/DAO/arquivos/emprestimos.dat");

			// Verifica se o objeto já existe
			boolean objetoExiste = false;
			if (arquivo.exists() && arquivo.isFile() && arquivo.canRead()) {
				FileInputStream arquivoLeitura = new FileInputStream(arquivo);

				while (arquivoLeitura.available() > 0 && !objetoExiste) {
					// Classe responsável por recuperar os objetos do arquivo
					ObjectInputStream objetoLeitura = new ObjectInputStream(arquivoLeitura);

					EmprestimoVO emprestimoLeitura = (EmprestimoVO) objetoLeitura.readObject();

					// Compara os emprestimos pelo id deles
					if (emprestimoLeitura.getIdEmprestimo() == (emprestimo.getIdEmprestimo())) {
						objetoExiste = true;
					}
				}

				arquivoLeitura.close();
			}

			// Se o objeto já existe
			if (objetoExiste) {
				System.out.println("Esse emprestimo ja foi cadastrado");
				// Se não existe, prosseguir com a inserção
			} else {
				FileOutputStream arquivoGravador = new FileOutputStream(arquivo, true);
				// Classe responsável por inserir os objetos
				ObjectOutputStream objetoGravador = new ObjectOutputStream(arquivoGravador);

				// Grava o objeto emprestimo no arquivo
				objetoGravador.writeObject(emprestimo);
				objetoGravador.flush();
				arquivoGravador.close();
				objetoGravador.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void alterar(EmprestimoVO emprestimo) {
		try {
			// Gera o arquivo para armazenar o objeto
			File arquivo = new File("src/br/edu/ufersa/ropke/model/DAO/arquivos/emprestimos.dat");

			boolean emprestimoExiste = false;
			ArrayList<EmprestimoVO> emprestimos = new ArrayList<EmprestimoVO>();
			// Procura pelo objeto enquanto salva os outros em um vetor de objetos
			if (arquivo.exists() && arquivo.isFile() && arquivo.canRead()) {
				FileInputStream arquivoLeitura = new FileInputStream(arquivo);

				while (arquivoLeitura.available() > 0) {
					// Classe responsável por recuperar os objetos do arquivo
					ObjectInputStream objetoLeitura = new ObjectInputStream(arquivoLeitura);

					EmprestimoVO emprestimoLeitura = (EmprestimoVO) objetoLeitura.readObject();

					// Compara os emprestimos pelo id deles
					if (emprestimoLeitura.getIdEmprestimo() == (emprestimo.getIdEmprestimo())) {
						emprestimoExiste = true;
						// Quando for o emprestimo a ser alterado, insere do parâmetro do método
						emprestimos.add(emprestimo);
					} else {
						// Quando não for o emprestimo a ser alterado, insere do arquivo
						emprestimos.add(emprestimoLeitura);
					}
				}

				arquivoLeitura.close();
			}

			// Se o objeto a ser alterado não existe
			if (!emprestimoExiste) {
				System.out.println("Esse emprestimo nao existe");
				// Se existe, prosseguir com a alteração
			} else {
				FileOutputStream arquivoGravador = new FileOutputStream(arquivo);
				int tamanhoVetorEmprestimos = emprestimos.size();

				for (int i = 0; i < tamanhoVetorEmprestimos; i++) {
					// Classe responsável por inserir os objetos
					ObjectOutputStream objetoGravador = new ObjectOutputStream(arquivoGravador);

					// Grava o objeto emprestimo no arquivo
					objetoGravador.writeObject(emprestimos.get(i));
					objetoGravador.flush();
				}

				arquivoGravador.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void deletar(EmprestimoVO emprestimo) {
		try {
			// Gera o arquivo para armazenar o objeto
			File arquivo = new File("src/br/edu/ufersa/ropke/model/DAO/arquivos/emprestimos.dat");

			boolean emprestimoExiste = false;
			ArrayList<EmprestimoVO> emprestimos = new ArrayList<EmprestimoVO>();
			// Procura pelo objeto enquanto salva os outros em um vetor de objetos
			if (arquivo.exists() && arquivo.isFile() && arquivo.canRead()) {
				FileInputStream arquivoLeitura = new FileInputStream(arquivo);

				while (arquivoLeitura.available() > 0) {
					// Classe responsável por recuperar os objetos do arquivo
					ObjectInputStream objetoLeitura = new ObjectInputStream(arquivoLeitura);

					EmprestimoVO emprestimoLeitura = (EmprestimoVO) objetoLeitura.readObject();

					// Compara os emprestimos pelo id deles
					if (emprestimoLeitura.getIdEmprestimo() == (emprestimo.getIdEmprestimo())) {
						emprestimoExiste = true;
					} else {
						// Quando não for o emprestimo a ser alterado, insere do arquivo
						emprestimos.add(emprestimoLeitura);
					}
				}

				arquivoLeitura.close();
			}

			// Se o objeto a ser alterado não existe
			if (!emprestimoExiste) {
				System.out.println("Esse emprestimo nao existe");
				// Se existe, prosseguir com a alteração
			} else {
				FileOutputStream arquivoGravador = new FileOutputStream(arquivo);
				int tamanhoVetorEmprestimos = emprestimos.size();

				for (int i = 0; i < tamanhoVetorEmprestimos; i++) {
					// Classe responsável por inserir os objetos
					ObjectOutputStream objetoGravador = new ObjectOutputStream(arquivoGravador);

					// Grava o objeto emprestimo no arquivo
					objetoGravador.writeObject(emprestimos.get(i));
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
			File arquivo = new File("src/br/edu/ufersa/ropke/model/DAO/arquivos/emprestimos.dat");

			if (arquivo.exists() && arquivo.isFile() && arquivo.canRead()) {
				FileInputStream arquivoLeitura = new FileInputStream(arquivo);

				int indiceEmprestimo = 1;
				while (arquivoLeitura.available() > 0) {
					// Classe responsável por recuperar os objetos do arquivo
					ObjectInputStream objetoLeitura = new ObjectInputStream(arquivoLeitura);

					EmprestimoVO emprestimo = (EmprestimoVO) objetoLeitura.readObject();
					System.out.println("\nEmprestimo " + indiceEmprestimo + '\n');
					System.out.println(emprestimo.toString());
					System.out.println("-----------------------------------------");
					indiceEmprestimo++;
				}

				arquivoLeitura.close();
			} else {
				System.out.println("Sem emprestimos");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
