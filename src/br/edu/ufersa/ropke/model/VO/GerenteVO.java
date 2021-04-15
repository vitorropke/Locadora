package br.edu.ufersa.ropke.model.VO;

public class GerenteVO extends UsuarioVO {
	private static final long serialVersionUID = 1L;

	public GerenteVO() {
		// TODO Auto-generated constructor stub
		super.setTipoUsuario(true);
	}

	public GerenteVO(String login, String senha, String nome, String cpf, String[] endereco) {
		super(login, senha, true, nome, cpf, endereco);
		// TODO Auto-generated constructor stub
	}

	public GerenteVO(String login, String senha, String nome, String cpf, String[] endereco, String[] email) {
		super(login, senha, true, nome, cpf, endereco, email);
		// TODO Auto-generated constructor stub
	}

	public GerenteVO(String login, String senha, String nome, String cpf, String[] endereco, String[] email,
			String[] telefone) {
		super(login, senha, true, nome, cpf, endereco, email, telefone);
		// TODO Auto-generated constructor stub
	}
}
