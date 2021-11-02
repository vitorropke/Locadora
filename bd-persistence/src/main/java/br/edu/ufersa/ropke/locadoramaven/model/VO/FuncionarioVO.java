package br.edu.ufersa.ropke.locadoramaven.model.VO;

import java.util.List;

public class FuncionarioVO extends UsuarioVO {
	private long id;

	public FuncionarioVO(String login, String senha, String nome, String cpf, List<Endereco> enderecos,
			List<String> emails, List<Telefone> telefones) {
		super(login, senha, nome, cpf, enderecos, emails, telefones);
	}

	@Override
	public String toString() {
		String funcionario;

		funcionario = "\nID funcionario:\t\t" + id + '\n';
		funcionario += super.toString();

		return funcionario;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		if (id >= 0) {
			this.id = id;
		} else {
			System.out.println("ID negativo");
		}
	}
}
