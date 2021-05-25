package br.edu.ufersa.ropke.locadoramaven.model.VO;

public class FuncionarioVO extends UsuarioVO {
	private static final long serialVersionUID = 1L;

	public FuncionarioVO() {
		// TODO Auto-generated constructor stub
		super.setTipoUsuario(false);
	}

	public FuncionarioVO(String login, String senha, String nome, String cpf, String[] endereco) {
		super(login, senha, false, nome, cpf, endereco);
		// TODO Auto-generated constructor stub
	}

	public FuncionarioVO(String login, String senha, String nome, String cpf, String[] endereco, String[] email) {
		super(login, senha, false, nome, cpf, endereco, email);
		// TODO Auto-generated constructor stub
	}

	public FuncionarioVO(String login, String senha, String nome, String cpf, String[] endereco, String[] email,
			String[] telefone) {
		super(login, senha, false, nome, cpf, endereco, email, telefone);
		// TODO Auto-generated constructor stub
	}
}
