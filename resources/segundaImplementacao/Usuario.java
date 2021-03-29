class Usuario {
	// Variáveis
	private String	login;
	private String	senha;
	private String	nome;
	private String	cpf;
	private String	endereco;
	private String	email;
	private String	telefone;

	// Construtores
	public Usuario(String login, String senha, String nome, String cpf,
			String endereco) {
		setLogin(login);
		setSenha(senha);
		setNome(nome);
		setCpf(cpf);
		setEndereco(endereco);
	}
	public Usuario(String login, String senha, String nome, String cpf,
			String endereco, String email) {
		setLogin(login);
		setSenha(senha);
		setNome(nome);
		setCpf(cpf);
		setEndereco(endereco);
		setEmail(email);
	}
	public Usuario(String login, String senha, String nome, String cpf,
			String endereco, String email, String telefone) {
		setLogin(login);
		setSenha(senha);
		setNome(nome);
		setCpf(cpf);
		setEndereco(endereco);
		setEmail(email);
		setTelefone(telefone);
	}

	public String toString() {
		String usuario = "";

		usuario = "Login:    " + login;
		usuario += "\nSenha:    " + senha;
		usuario += "\nNome:     " + nome;
		usuario += "\nCPF:      " + cpf;
		usuario += "\nEndereco: " + endereco;
		usuario += "\nEmail:    " + email;
		usuario += "\nTelefone: " + telefone;
		
		return usuario;
	}

	// Getters e Setters
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
					cpf = cpf.replaceAll("\\D+","");
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

	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		if ((endereco != null) && (endereco != "")) {
			this.endereco = endereco;
		} else {
			System.out.println("Endereco nao pode ser vazio!");
		}
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		if ((email != null) && (email != "")) {
			if (Validador.isEmail(email)) {
				this.email = email;
			} else {
				System.out.println("Email invalido!");
			}
		} else {
			System.out.println("Email nao pode ser vazio!");
		}
	}

	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		if ((telefone != null) && (telefone != "")) {
			if ((telefone.length() >= 10) && (telefone.length() <= 16)) {
				telefone = telefone.replaceAll("\\D+","");
				// Reduz o telefone, removendo tudo que não é dígito

				if (telefone.indexOf('0') == 0) {
					// Se o telefone possui o primeiro digito sendo 0
					telefone = telefone.substring(1);
					// Remove-se esse 0 no início

					if((telefone.length() == 10) || (telefone.length() == 11)) {
						// Se o telefone possui 10 ou 11 dígitos após a redução
						this.telefone = telefone;
					} else {
						System.out.println("Telefone invalido!");
					}
				} else {
					// Se o telefone não possui o primeiro digito sendo 0
					if((telefone.length() == 10) || (telefone.length() == 11)) {
						// Se o telefone possui 10 ou 11 dígitos após a redução
						this.telefone = telefone;
					} else {
						System.out.println("Telefone invalido!");
					}
				}
			} else {
				System.out.println("Telefone invalido!");
			}
		} else {
			System.out.println("Telefone nao pode ser vazio!");
		}
	}

	// Métodos
	/*
	public void cadastrar(Usuario usuario) {
		// adiciona o usuário ao "banco de dados"
	}

	public void alterar(Usuario usuario) {
		// altera os atributos do usuário no "banco de dados"
	}

	public void deletar(Usuario usuario) {
		// remove o usuário do "banco de dados"
		usuario.login = null;
		usuario.senha = null;
		usuario.nome = null;
		usuario.cpf = null;
		usuario.endereco = null;
		usuario.email = null;
		usuario.telefone = null;
	}

	public void login() {
		// autoriza o acesso do usuário ao sistema
	}
	*/
}
