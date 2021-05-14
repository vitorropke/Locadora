package br.edu.ufersa.ropke.model.DAO;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import br.edu.ufersa.ropke.model.VO.PessoaVO;

public class PessoaDAO<VO extends PessoaVO> extends OperacaoDAO<VO> {
	@Override
	public void cadastrar(VO pessoa, File arquivo) {
		try {
			FileOutputStream arquivoGravador = new FileOutputStream(arquivo, true);
			// Classe responsável por inserir os objetos
			ObjectOutputStream objetoGravador = new ObjectOutputStream(arquivoGravador);

			// Grava o objeto pessoa no arquivo
			objetoGravador.writeObject(pessoa);
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
	public void alterar(VO pessoa, File arquivo) {
		try {
			ArrayList<PessoaVO> pessoas = new ArrayList<PessoaVO>();

			// Procura pelo objeto enquanto salva os outros em um vetor de objetos
			// O arquivo será reescrito com o objeto modificado
			if (arquivo.exists() && arquivo.isFile() && arquivo.canRead()) {
				FileInputStream arquivoLeitura = new FileInputStream(arquivo);
				ObjectInputStream objetoLeitura;
				VO pessoaLeitura;

				while (arquivoLeitura.available() > 0) {
					// Classe responsável por recuperar os objetos do arquivo
					objetoLeitura = new ObjectInputStream(arquivoLeitura);

					pessoaLeitura = (VO) objetoLeitura.readObject();

					// Compara as pessoas pelo CPF delas
					if (pessoaLeitura.getCpf().equals(pessoa.getCpf())) {
						// Quando for o objeto a ser alterado, insere no vetor, o objeto que vem do
						// parâmetro do método 'alterar'
						pessoas.add(pessoa);
					} else {
						// Quando não for o objeto a ser alterado, insere do arquivo
						pessoas.add(pessoaLeitura);
					}
				}

				arquivoLeitura.close();
			}

			// Escrita com o objeto modificado
			FileOutputStream arquivoGravador = new FileOutputStream(arquivo);
			ObjectOutputStream objetoGravador;
			int tamanhoVetorPessoas = pessoas.size();

			for (int i = 0; i < tamanhoVetorPessoas; i++) {
				// Classe responsável por inserir os objetos
				objetoGravador = new ObjectOutputStream(arquivoGravador);

				// Grava o objeto pessoa no arquivo
				objetoGravador.writeObject(pessoas.get(i));
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
	public void deletar(VO pessoa, File arquivo) {
		try {
			ArrayList<PessoaVO> pessoas = new ArrayList<PessoaVO>();

			// Procura pelo objeto enquanto salva os outros em um vetor de objetos
			if (arquivo.exists() && arquivo.isFile() && arquivo.canRead()) {
				FileInputStream arquivoLeitura = new FileInputStream(arquivo);
				ObjectInputStream objetoLeitura;
				VO pessoaLeitura;

				while (arquivoLeitura.available() > 0) {
					// Classe responsável por recuperar os objetos do arquivo
					objetoLeitura = new ObjectInputStream(arquivoLeitura);

					pessoaLeitura = (VO) objetoLeitura.readObject();

					// Compara as pessoas pelo CPF delas
					if (!pessoaLeitura.getCpf().equals(pessoa.getCpf())) {
						// Quando não encontrar a pessoa, insere no vetor
						// Quando encontrar a pessoa, não insere no vetor
						pessoas.add(pessoaLeitura);
					}
				}

				arquivoLeitura.close();
			}

			// Escrita com o objeto removido
			FileOutputStream arquivoGravador = new FileOutputStream(arquivo);
			ObjectOutputStream objetoGravador;
			int tamanhoVetorPessoas = pessoas.size();

			for (int i = 0; i < tamanhoVetorPessoas; i++) {
				// Classe responsável por inserir os objetos
				objetoGravador = new ObjectOutputStream(arquivoGravador);

				// Grava o objeto pessoa no arquivo
				objetoGravador.writeObject(pessoas.get(i));
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
				VO pessoa;
				int indicePessoa = 1;

				while (arquivoLeitura.available() > 0) {
					// Classe responsável por recuperar os objetos do arquivo
					objetoLeitura = new ObjectInputStream(arquivoLeitura);

					pessoa = (VO) objetoLeitura.readObject();
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

	@SuppressWarnings("unchecked")
	@Override
	public VO pesquisar(VO pessoa, File arquivo) {
		try {
			if (arquivo.exists() && arquivo.isFile() && arquivo.canRead()) {
				FileInputStream arquivoLeitura = new FileInputStream(arquivo);
				String cpf;

				// Reduz o CPF, removendo tudo que não é dígito
				cpf = pessoa.getCpf().replaceAll("\\D+", "");

				ObjectInputStream objetoLeitura;
				VO pessoaLeitura;

				while (arquivoLeitura.available() > 0) {
					// Classe responsável por recuperar os objetos do arquivo
					objetoLeitura = new ObjectInputStream(arquivoLeitura);

					pessoaLeitura = (VO) objetoLeitura.readObject();

					// Retorna a pessoa quando obter o mesmo cpf
					if (pessoaLeitura.getCpf().equals(cpf)) {
						arquivoLeitura.close();
						objetoLeitura.close();
						return pessoaLeitura;
					}
				}

				arquivoLeitura.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("Pessoa nao encontrada");
		return null;
	}
}