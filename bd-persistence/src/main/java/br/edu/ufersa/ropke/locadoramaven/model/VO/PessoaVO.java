package br.edu.ufersa.ropke.locadoramaven.model.VO;

import java.util.ArrayList;
import java.util.List;

public abstract class PessoaVO {
	private long id;
	private String nome;
	private String cpf;
	private List<Endereco> enderecos = new ArrayList<Endereco>();
	private List<String> emails = new ArrayList<String>();
	private List<Telefone> telefones = new ArrayList<Telefone>();

	public PessoaVO(String nome, String cpf, List<Endereco> enderecos, List<String> emails, List<Telefone> telefones) {
		setNome(nome);
		setCpf(cpf);
		setEnderecos(enderecos);
		setEmails(emails);
		setTelefones(telefones);
	}

	@Override
	public String toString() {
		String pessoa;

		pessoa = "\nID pessoa:\t\t" + id;
		pessoa += "\nNome:\t\t\t" + nome;

		pessoa += "\nCPF:\t\t\t" + cpf.substring(0, 3) + '.' + cpf.substring(3, 6) + '.' + cpf.substring(6, 9) + '-'
				+ cpf.substring(9);

		pessoa += "\n\n--------------------------------------------\n";
		pessoa += "\nEnderecos:";

		if (!enderecos.isEmpty()) {
			pessoa += '\n';
			for (Endereco enderecoAtual : enderecos) {
				pessoa += enderecoAtual;
			}
		} else {
			pessoa += "\t\tSem enderecos\n";
		}

		pessoa += "\n--------------------------------------------\n";
		pessoa += "\nE-mails:";

		if (!emails.isEmpty()) {
			pessoa += "\n\n";
			for (String emailAtual : emails) {
				pessoa += emailAtual + '\n';
			}
		} else {
			pessoa += "\t\tSem e-mails\n";
		}

		pessoa += "\n--------------------------------------------\n";
		pessoa += "\nTelefones:";

		if (!telefones.isEmpty()) {
			pessoa += '\n';
			for (Telefone telefoneAtual : telefones) {
				pessoa += telefoneAtual;
			}
		} else {
			pessoa += "\t\tSem telefones\n";
		}
		pessoa += '\n';

		return pessoa;
	}

	public long getIdPessoa() {
		return id;
	}

	public void setIdPessoa(long id) {
		if (id >= 0) {
			this.id = id;
		} else {
			System.out.println("ID negativo");
		}
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		if ((nome != null) && !nome.isBlank()) {
			this.nome = nome.trim();
		} else {
			System.out.println("Nome vazio");
		}
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		if (Validador.isCpf(cpf)) {
			this.cpf = cpf.replaceAll("\\D", "");
		} else {
			System.out.println("CPF invalido");
		}
	}

	public List<Endereco> getEnderecos() {
		return enderecos;
	}

	public void setEnderecos(List<Endereco> enderecos) {
		if ((enderecos != null) && !enderecos.isEmpty()) {
			int indice = 0;
			// Procura por endereços vazios ou inválidos
			for (Endereco enderecoAtual : enderecos) {
				if ((enderecoAtual == null) || (enderecoAtual.getLogradouro() == null)
						|| (enderecoAtual.getNumero() == null) || (enderecoAtual.getComplemento() == null)
						|| (enderecoAtual.getBairro() == null) || (enderecoAtual.getCidade() == null)
						|| (enderecoAtual.getEstado() == null) || (enderecoAtual.getCep() == null)) {
					System.out.println("Endereco " + indice + " vazio ou invalido");
					return;
				}
				indice++;
			}

			this.enderecos = enderecos;
		} else {
			System.out.println("Enderecos vazios");
		}
	}

	public List<String> getEmails() {
		return emails;
	}

	public void setEmails(List<String> emails) {
		if ((emails != null) && !emails.isEmpty()) {
			int indice = 0;
			// Procura por e-mails vazios ou inválidos
			for (String emailAtual : emails) {
				if (!Validador.isEmail(emailAtual)) {
					System.out.println("E-mail " + indice + " invalido");
					return;
				} else {
					// Remove espaços antes e depois do e-mail
					emails.set(indice, emailAtual.trim());
				}
				indice++;
			}

			this.emails = emails;
		} else {
			System.out.println("E-mails vazios");
		}
	}

	public List<Telefone> getTelefones() {
		return telefones;
	}

	public void setTelefones(List<Telefone> telefones) {
		if ((telefones != null) && !telefones.isEmpty()) {
			int indice = 0;
			// Procura por telefones vazios ou inválidos
			for (Telefone telefoneAtual : telefones) {
				if ((telefoneAtual == null) || (telefoneAtual.getDdd() == null)
						|| (telefoneAtual.getTelefone() == null)) {
					System.out.println("Telefone " + indice + " vazio ou invalido");
					return;
				}
				indice++;
			}

			this.telefones = telefones;
		} else {
			System.out.println("Telefones vazios");
		}
	}
}
