package br.edu.ufersa.ropke.model.BO;

import java.io.File;

import br.edu.ufersa.ropke.model.DAO.ClienteDAO;
import br.edu.ufersa.ropke.model.VO.ClienteVO;

public class ClienteBO extends PessoaBO {
	private static final File arquivo = ClienteDAO.getArquivo();

	public static void cadastrar(ClienteVO cliente) {
		PessoaBO.cadastrar(cliente, arquivo);
	}

	public static void alterar(ClienteVO cliente) {
		PessoaBO.alterar(cliente, arquivo);
	}

	public static void deletar(ClienteVO cliente) {
		PessoaBO.deletar(cliente, arquivo);
	}

	public static void pesquisar() {
		PessoaBO.pesquisar(arquivo);
	}

	public static ClienteVO pesquisar(ClienteVO cliente) {
		return (ClienteVO) PessoaBO.pesquisar(cliente, arquivo);
	}

	public static ClienteVO[] pesquisarNome(String nome) {
		if (nome != null && nome != "") {
			return ClienteDAO.pesquisarNome(nome);
		} else {
			ClienteVO[] semClientes = new ClienteVO[0];
			return semClientes;
		}
	}

	public static ClienteVO pesquisarCpf(String cpf) {
		if (cpf != null && cpf != "") {
			return ClienteDAO.pesquisarCpf(cpf);
		} else {
			return null;
		}
	}
}
