package br.edu.ufersa.ropke.model.DAO;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import br.edu.ufersa.ropke.model.VO.EmprestimoVO;

public class EmprestimoDAO extends OperacaoDAO {
	private static final File arquivo = new File("src/br/edu/ufersa/ropke/model/DAO/arquivos/emprestimos.dat");

	public static File getArquivo() {
		return arquivo;
	}

	public static void cadastrar(EmprestimoVO emprestimo) {
		try {
			FileOutputStream arquivoGravador = new FileOutputStream(arquivo, true);
			// Classe responsável por inserir os objetos
			ObjectOutputStream objetoGravador = new ObjectOutputStream(arquivoGravador);

			// Grava o objeto emprestimo no arquivo
			objetoGravador.writeObject(emprestimo);
			objetoGravador.flush();
			arquivoGravador.close();
			objetoGravador.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void alterar(EmprestimoVO emprestimo) {
		try {
			ArrayList<EmprestimoVO> emprestimos = new ArrayList<EmprestimoVO>();

			// Procura pelo objeto enquanto salva os outros em um vetor de objetos
			if (arquivo.exists() && arquivo.isFile() && arquivo.canRead()) {
				FileInputStream arquivoLeitura = new FileInputStream(arquivo);
				ObjectInputStream objetoLeitura;
				EmprestimoVO emprestimoLeitura;

				while (arquivoLeitura.available() > 0) {
					// Classe responsável por recuperar os objetos do arquivo
					objetoLeitura = new ObjectInputStream(arquivoLeitura);

					emprestimoLeitura = (EmprestimoVO) objetoLeitura.readObject();

					// Compara os emprestimos pelo id deles
					if (emprestimoLeitura.getIdEmprestimo() == (emprestimo.getIdEmprestimo())) {
						// Quando for o objeto a ser alterado, insere no vetor, o objeto que vem do
						// parâmetro do método 'alterar'
						emprestimos.add(emprestimo);
					} else {
						// Quando não for o emprestimo a ser alterado, insere do arquivo
						emprestimos.add(emprestimoLeitura);
					}
				}

				arquivoLeitura.close();
			}

			FileOutputStream arquivoGravador = new FileOutputStream(arquivo);
			ObjectOutputStream objetoGravador;
			int tamanhoVetorEmprestimos = emprestimos.size();

			for (int i = 0; i < tamanhoVetorEmprestimos; i++) {
				// Classe responsável por inserir os objetos
				objetoGravador = new ObjectOutputStream(arquivoGravador);

				// Grava o objeto emprestimo no arquivo
				objetoGravador.writeObject(emprestimos.get(i));
				objetoGravador.flush();
			}

			arquivoGravador.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void deletar(EmprestimoVO emprestimo) {
		try {
			ArrayList<EmprestimoVO> emprestimos = new ArrayList<EmprestimoVO>();

			// Procura pelo objeto enquanto salva os outros em um vetor de objetos
			if (arquivo.exists() && arquivo.isFile() && arquivo.canRead()) {
				FileInputStream arquivoLeitura = new FileInputStream(arquivo);
				ObjectInputStream objetoLeitura;
				EmprestimoVO emprestimoLeitura;

				while (arquivoLeitura.available() > 0) {
					// Classe responsável por recuperar os objetos do arquivo
					objetoLeitura = new ObjectInputStream(arquivoLeitura);

					emprestimoLeitura = (EmprestimoVO) objetoLeitura.readObject();

					// Compara os emprestimos pelo id deles
					if (emprestimoLeitura.getIdEmprestimo() != (emprestimo.getIdEmprestimo())) {
						// Quando não encontrar o empréstimo, insere no vetor
						// Quando encontrar a empréstimo, não insere no vetor
						emprestimos.add(emprestimoLeitura);
					}
				}

				arquivoLeitura.close();
			}

			FileOutputStream arquivoGravador = new FileOutputStream(arquivo);
			ObjectOutputStream objetoGravador;
			int tamanhoVetorEmprestimos = emprestimos.size();

			for (int i = 0; i < tamanhoVetorEmprestimos; i++) {
				// Classe responsável por inserir os objetos
				objetoGravador = new ObjectOutputStream(arquivoGravador);

				// Grava o objeto emprestimo no arquivo
				objetoGravador.writeObject(emprestimos.get(i));
				objetoGravador.flush();
			}

			arquivoGravador.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void pesquisar() {
		try {
			if (arquivo.exists() && arquivo.isFile() && arquivo.canRead()) {
				FileInputStream arquivoLeitura = new FileInputStream(arquivo);
				ObjectInputStream objetoLeitura;
				EmprestimoVO emprestimo;

				int indiceEmprestimo = 1;
				while (arquivoLeitura.available() > 0) {
					// Classe responsável por recuperar os objetos do arquivo
					objetoLeitura = new ObjectInputStream(arquivoLeitura);

					emprestimo = (EmprestimoVO) objetoLeitura.readObject();
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
	
	public static EmprestimoVO pesquisar(EmprestimoVO emprestimo) {
		try {
			if (arquivo.exists() && arquivo.isFile() && arquivo.canRead()) {
				FileInputStream arquivoLeitura = new FileInputStream(arquivo);
				ObjectInputStream objetoLeitura;
				EmprestimoVO emprestimoLeitura;

				while (arquivoLeitura.available() > 0) {
					// Classe responsável por recuperar os objetos do arquivo
					objetoLeitura = new ObjectInputStream(arquivoLeitura);
					
					emprestimoLeitura = (EmprestimoVO) objetoLeitura.readObject();

					// Compara os emprestimos pelo id deles
					if (emprestimoLeitura.getIdEmprestimo() == (emprestimo.getIdEmprestimo())) {
						arquivoLeitura.close();
						objetoLeitura.close();
						return emprestimoLeitura;
					}
				}

				arquivoLeitura.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
}
