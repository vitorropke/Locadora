package br.edu.ufersa.ropke.locadoramaven.model.BO;

import java.io.File;

import br.edu.ufersa.ropke.locadoramaven.model.DAO.ClienteDAO;
import br.edu.ufersa.ropke.locadoramaven.model.VO.ClienteVO;

public class ClienteBO extends PessoaBO<ClienteVO> {
	private static final File arquivo = ClienteDAO.getArquivo();
	private static ClienteDAO clienteDAO = new ClienteDAO();

	public void cadastrar(ClienteVO cliente) {
		super.cadastrar(cliente, arquivo);
	}

	public void alterar(ClienteVO cliente) {
		super.alterar(cliente, arquivo);
	}

	public void deletar(ClienteVO cliente) {
		super.deletar(cliente, arquivo);
	}

	public void pesquisar() {
		super.pesquisar(arquivo);
	}

	public ClienteVO pesquisar(ClienteVO cliente) {
		return super.pesquisar(cliente, arquivo);
	}

	public ClienteVO[] pesquisarNome(String nome) {
		if (nome != null && nome != "") {
			return clienteDAO.pesquisarNome(nome);
		} else {
			ClienteVO[] semClientes = new ClienteVO[0];
			return semClientes;
		}
	}

	public static ClienteVO pesquisarCpf(String cpf) {
		if (cpf != null && cpf != "") {
			return clienteDAO.pesquisarCpf(cpf);
		} else {
			return null;
		}
	}
}
