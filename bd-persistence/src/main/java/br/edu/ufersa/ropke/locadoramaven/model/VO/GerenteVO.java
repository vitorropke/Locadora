package br.edu.ufersa.ropke.locadoramaven.model.VO;

import java.util.List;

public class GerenteVO extends UsuarioVO {
	private long id;

	public GerenteVO(String login, String senha, String nome, String cpf, List<EnderecoVO> enderecos, List<String> emails,
			List<TelefoneVO> telefones) {
		super(login, senha, nome, cpf, enderecos, emails, telefones);
	}

	@Override
	public String toString() {
		String gerente;

		gerente = "\nID gerente:\t\t" + id + '\n';
		gerente += super.toString();

		return gerente;
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
