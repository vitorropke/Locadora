package br.edu.ufersa.ropke.locadoramaven.model.VO;

public class ClienteVO extends PessoaVO {
	private static final long serialVersionUID = 1L;

	public ClienteVO() {
		// TODO Auto-generated constructor stub
	}

	public ClienteVO(String nome, String cpf, String[] endereco) {
		super(nome, cpf, endereco);
		// TODO Auto-generated constructor stub
	}

	public ClienteVO(String nome, String cpf, String[] endereco, String[] email) {
		super(nome, cpf, endereco, email);
		// TODO Auto-generated constructor stub
	}

	public ClienteVO(String nome, String cpf, String[] endereco, String[] email, String[] telefone) {
		super(nome, cpf, endereco, email, telefone);
		// TODO Auto-generated constructor stub
	}
}
