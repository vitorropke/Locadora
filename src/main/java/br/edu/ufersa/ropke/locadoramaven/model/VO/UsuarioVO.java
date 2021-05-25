package br.edu.ufersa.ropke.locadoramaven.model.VO;

public abstract class UsuarioVO extends PessoaVO {
	private static final long serialVersionUID = 1L;
	
	// Variáveis
	private static boolean tipoUsuario; // false = funcionário, true = gerente
	private String login;
	private String senha;

	// Construtores
	public UsuarioVO() {
	}

	public UsuarioVO(String login, String senha, boolean tipoUsuario, String nome, String cpf, String[] endereco) {
		super(nome, cpf, endereco);
		// TODO Auto-generated constructor stub
		setLogin(login);
		setSenha(senha);
		setTipoUsuario(tipoUsuario);
	}

	public UsuarioVO(String login, String senha, boolean tipoUsuario, String nome, String cpf, String[] endereco,
			String[] email) {
		super(nome, cpf, endereco, email);
		// TODO Auto-generated constructor stub
		setLogin(login);
		setSenha(senha);
		setTipoUsuario(tipoUsuario);
	}

	public UsuarioVO(String login, String senha, boolean tipoUsuario, String nome, String cpf, String[] endereco,
			String[] email, String[] telefone) {
		super(nome, cpf, endereco, email, telefone);
		// TODO Auto-generated constructor stub
		setLogin(login);
		setSenha(senha);
		setTipoUsuario(tipoUsuario);
	}

	// toString
	@Override
	public String toString() {
		String usuario = "";

		usuario = "\nLogin:\t\t" + login;
		usuario += "\nSenha:\t\t" + senha;
		usuario += "\nTipo:\t\t" + tipoUsuario;
		usuario += super.toString() + "\n";

		return usuario;
	}

	// Getters e setters
	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		if ((login != null) && (login != "")) {
			this.login = login;
		} else {
			System.out.println("Login nao pode ser vazio!");
		}
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		if ((senha != null) && (senha != "")) {
			this.senha = senha;
		} else {
			System.out.println("Senha nao pode ser vazia!");
		}
	}

	public static boolean isTipoUsuario() {
		return tipoUsuario;
	}

	public static void setTipoUsuario(boolean tipoUsuario) {
		UsuarioVO.tipoUsuario = tipoUsuario;
	}
}
