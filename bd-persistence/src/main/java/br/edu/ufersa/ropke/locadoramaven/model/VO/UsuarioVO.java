package br.edu.ufersa.ropke.locadoramaven.model.VO;

import java.util.List;

public abstract class UsuarioVO extends PessoaVO {
	private long id;
	private String login;
	private String senha;

	public UsuarioVO(String login, String senha, String nome, String cpf, List<EnderecoVO> enderecos, List<String> emails,
			List<TelefoneVO> telefones) {
		super(nome, cpf, enderecos, emails, telefones);
		setLogin(login);
		setSenha(senha);
	}

	@Override
	public String toString() {
		String usuario;

		usuario = "\nID usuario:\t\t" + id;
		usuario += "\nLogin:\t\t\t" + login;
		usuario += "\nSenha:\t\t\t" + "*****" + '\n';
		usuario += super.toString();

		return usuario;
	}

	public long getIdUsuario() {
		return id;
	}

	public void setIdUsuario(long id) {
		if (id >= 0) {
			this.id = id;
		} else {
			System.out.println("ID negativo");
		}
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		if ((login != null) && !login.isBlank()) {
			this.login = login;
		} else {
			System.out.println("Login vazio");
		}
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		if ((senha != null) && !senha.isBlank()) {
			this.senha = senha;
		} else {
			System.out.println("Senha vazia");
		}
	}
}
