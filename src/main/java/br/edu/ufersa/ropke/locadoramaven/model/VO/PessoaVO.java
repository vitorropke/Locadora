package br.edu.ufersa.ropke.locadoramaven.model.VO;

import java.io.Serializable;
import java.util.ArrayList;

public abstract class PessoaVO implements Serializable {
	private static final long serialVersionUID = 1L;
	// Variáveis
	private String nome;
	private String cpf;
	private String[] endereco;
	private String[] email;
	private String[] telefone;

	// Construtores
	public PessoaVO() {
	}

	public PessoaVO(String nome, String cpf, String[] endereco) {
		setNome(nome);
		setCpf(cpf);
		setEndereco(endereco);
	}

	public PessoaVO(String nome, String cpf, String[] endereco, String[] email) {
		setNome(nome);
		setCpf(cpf);
		setEndereco(endereco);
		setEmail(email);
	}

	public PessoaVO(String nome, String cpf, String[] endereco, String[] email, String[] telefone) {
		setNome(nome);
		setCpf(cpf);
		setEndereco(endereco);
		setEmail(email);
		setTelefone(telefone);
	}

	// toString
	@Override
	public String toString() {
		String pessoa = "";

		pessoa = "\nNome:\t\t" + nome;
		pessoa += "\nCPF:\t\t" + cpf;
		pessoa += "\n----------------------------------------------";
		pessoa += "\nEnderecos:";

		if (endereco != null) {
			if (endereco.length == 0) {
				pessoa += "\tSem enderecos\n";
			} else {
				for (int x = 0; x < endereco.length; x++) {
					pessoa += "\t" + endereco[x] + "\n";
				}
			}
		} else {
			pessoa += "\tSem enderecos\n";
		}

		pessoa += "\n----------------------------------------------";
		pessoa += "\nEmails:";

		if (email != null) {
			if (email.length == 0) {
				pessoa += "\t\tSem emails\n";
			} else {
				for (int x = 0; x < email.length; x++) {
					pessoa += "\t\t" + email[x] + "\n";
				}
			}
		} else {
			pessoa += "\t\tSem emails\n";
		}

		pessoa += "\n----------------------------------------------";
		pessoa += "\nTelefones:";

		if (telefone != null) {
			if (telefone.length == 0) {
				pessoa += "\tSem telefones\n";
			} else {
				for (int x = 0; x < telefone.length; x++) {
					pessoa += "\t(";

					pessoa += telefone[x].substring(0, 2) + ") ";

					if (telefone[x].length() == 11) {
						pessoa += telefone[x].substring(2, 7);
						pessoa += '-' + telefone[x].substring(7, 11);
					} else {
						pessoa += telefone[x].substring(2, 6);
						pessoa += '-' + telefone[x].substring(6, 10);
					}

					pessoa += "\n";
				}
			}
		} else {
			pessoa += "\tSem telefones\n";
		}

		return pessoa;
	}

	// Getters e setters
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		if ((nome != null) && (nome != "")) {
			this.nome = nome;
		} else {
			System.out.println("Nome nao pode ser vazio!");
		}
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		if ((cpf != null) && (cpf != "")) {
			if ((cpf.length() >= 11) && (cpf.length() <= 14)) {
				// CPF deve ter tamanho entre 11 e 14
				if (cpf.length() > 11) {
					// Se o CPF tem mais de 11 dígitos
					cpf = cpf.replaceAll("\\D+", "");
					// Reduz o CPF, removendo tudo que não é dígito

					if (Validador.isCpf(cpf)) {
						// Verifica o CPF
						this.cpf = cpf;
					} else {
						System.out.println("CPF invalido!");
					}
				} else {
					// Se o CPF não tem mais de 11 dígitos vai para o verificador
					if (Validador.isCpf(cpf)) {
						// Verifica o CPF
						this.cpf = cpf;
					} else {
						System.out.println("CPF invalido!");
					}
				}
			} else {
				System.out.println("CPF invalido!");
			}
		} else {
			System.out.println("CPF nao pode ser vazio!");
		}
	}

	public String[] getEndereco() {
		return endereco;
	}

	public void setEndereco(String[] endereco) {
		if (endereco != null) {
			ArrayList<String> enderecosValidos = new ArrayList<String>();

			// Insere somente endereços válidos
			for (int x = 0; x < endereco.length; x++) {
				if ((endereco[x] != null) && (endereco[x] != "")) {
					enderecosValidos.add(endereco[x]);
				} else {
					System.out.println("Endereco nao pode ser vazio!");
				}
			}

			// Verifica se existem endereços válidos
			if (enderecosValidos.size() != 0) {
				// ArrayList para vetor
				String[] enderecos = new String[enderecosValidos.size()];
				enderecos = enderecosValidos.toArray(enderecos);
				this.endereco = enderecos;
			} else {
				System.out.println("Sem enderecos validos!");
				String[] semEnderecos = new String[0];
				this.endereco = semEnderecos;
			}
		} else {
			System.out.println("Enderecos nao podem ser vazios!");
		}
	}

	public String[] getEmail() {
		return email;
	}

	public void setEmail(String[] email) {
		if (email != null) {
			ArrayList<String> emailsValidos = new ArrayList<String>();

			// Insere somente emails válidos
			for (int x = 0; x < email.length; x++) {
				if ((email[x] != null) && (email[x] != "")) {
					if (Validador.isEmail(email[x])) {
						emailsValidos.add(email[x]);
					} else {
						System.out.println("Email invalido!");
					}
				} else {
					System.out.println("Email nao pode ser vazio!");
				}
			}

			// Verifica se existem emails válidos
			if (emailsValidos.size() != 0) {
				// ArrayList para vetor
				String[] emails = new String[emailsValidos.size()];
				emails = emailsValidos.toArray(emails);
				this.email = emails;
			} else {
				System.out.println("Sem emails validos!");
				String[] semEmails = new String[0];
				this.email = semEmails;
			}
		} else {
			System.out.println("Emails nao podem ser vazios!");
		}
	}

	public String[] getTelefone() {
		return telefone;
	}

	public void setTelefone(String[] telefone) {
		if (telefone != null) {
			ArrayList<String> telefonesValidos = new ArrayList<String>();

			// Insere somente telefones válidos
			for (int x = 0; x < telefone.length; x++) {
				if ((telefone[x] != null) && (telefone[x] != "")) {
					if ((telefone[x].length() >= 10) && (telefone[x].length() <= 16)) {
						telefone[x] = telefone[x].replaceAll("\\D+", "");
						// Reduz o telefone, removendo tudo que não é dígito

						if (telefone[x].indexOf('0') == 0) {
							// Se o telefone possui o primeiro digito sendo 0
							telefone[x] = telefone[x].substring(1);
							// Remove-se esse 0 no início
						}

						if ((telefone[x].length() == 10) || (telefone[x].length() == 11)) {
							// Se o telefone possui 10 ou 11 dígitos
							telefonesValidos.add(telefone[x]);
						} else {
							System.out.println("Telefone invalido!");
						}
					} else {
						System.out.println("Telefone invalido!");
					}
				} else {
					System.out.println("Telefone nao pode ser vazio!");
				}
			}

			// Verifica se existem telefones válidos
			if (telefonesValidos.size() != 0) {
				// ArrayList para vetor
				String[] telefones = new String[telefonesValidos.size()];
				telefones = telefonesValidos.toArray(telefones);
				this.telefone = telefones;
			} else {
				System.out.println("Sem telefones validos!");
				String[] semtelefones = new String[0];
				this.telefone = semtelefones;
			}
		} else {
			System.out.println("Telefones nao podem ser vazios!");
		}
	}
}
