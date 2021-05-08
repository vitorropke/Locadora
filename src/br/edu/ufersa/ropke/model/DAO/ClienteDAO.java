package br.edu.ufersa.ropke.model.DAO;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import br.edu.ufersa.ropke.model.VO.ClienteVO;

public class ClienteDAO extends PessoaDAO {
	private static final File arquivo = new File("src/br/edu/ufersa/ropke/model/DAO/arquivos/clientes.dat");

	public static File getArquivo() {
		return arquivo;
	}

	public static void cadastrar(ClienteVO cliente) {
		PessoaDAO.cadastrar(cliente, arquivo);
	}

	public static void alterar(ClienteVO cliente) {
		PessoaDAO.alterar(cliente, arquivo);
	}

	public static void deletar(ClienteVO cliente) {
		PessoaDAO.deletar(cliente, arquivo);
	}

	public static void pesquisar() {
		PessoaDAO.pesquisar(arquivo);
	}

	public static ClienteVO pesquisar(ClienteVO cliente) {
		return (ClienteVO) PessoaDAO.pesquisar(cliente, arquivo);
	}

	public static ClienteVO[] pesquisarNome(String nome) {
		try {
			ArrayList<ClienteVO> clientes = new ArrayList<ClienteVO>();

			if (arquivo.exists() && arquivo.isFile() && arquivo.canRead()) {
				FileInputStream arquivoLeitura = new FileInputStream(arquivo);
				ObjectInputStream objetoLeitura;
				ClienteVO clienteLeitura;

				while (arquivoLeitura.available() > 0) {
					// Classe responsável por recuperar os objetos do arquivo
					objetoLeitura = new ObjectInputStream(arquivoLeitura);

					clienteLeitura = (ClienteVO) objetoLeitura.readObject();

					// Salva o cliente no vetor quando o nome conter parte da string que vem do
					// parâmetro
					if (clienteLeitura.getNome().contains(nome)) {
						clientes.add(clienteLeitura);
					}
				}

				arquivoLeitura.close();
			}

			// Verifica se o vetor de clientes não é vazio
			if (clientes.size() != 0) {
				// ArrayList clientes para vetor 'vetorClientes'
				ClienteVO[] vetorClientes = new ClienteVO[clientes.size()];
				vetorClientes = clientes.toArray(vetorClientes);
				return vetorClientes;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("Sem clientes com esse nome!");
		ClienteVO[] semClientes = new ClienteVO[0];
		return semClientes;
	}

	public static ClienteVO pesquisarCpf(String cpf) {
		try {
			if (arquivo.exists() && arquivo.isFile() && arquivo.canRead()) {
				FileInputStream arquivoLeitura = new FileInputStream(arquivo);
				ObjectInputStream objetoLeitura;
				ClienteVO clienteLeitura;

				// Reduz o CPF, removendo tudo que não é dígito
				cpf = cpf.replaceAll("\\D+", "");

				while (arquivoLeitura.available() > 0) {
					// Classe responsável por recuperar os objetos do arquivo
					objetoLeitura = new ObjectInputStream(arquivoLeitura);

					clienteLeitura = (ClienteVO) objetoLeitura.readObject();

					// Retorna o cliente quando obter o mesmo cpf
					if (clienteLeitura.getCpf().equals(cpf)) {
						arquivoLeitura.close();
						objetoLeitura.close();
						return clienteLeitura;
					}
				}

				arquivoLeitura.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("O cliente nao existe no sistema!");
		return null;
	}
}
