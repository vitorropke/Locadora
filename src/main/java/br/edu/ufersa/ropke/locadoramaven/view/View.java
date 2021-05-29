package br.edu.ufersa.ropke.locadoramaven.view;

public enum View {
	LOGIN("login.fxml"), ALTERA_SENHA("altera_senha.fxml"), PRINCIPAL_GERENTE("principal_gerente.fxml"),
	PRINCIPAL_FUNCIONARIO("principal_funcionario.fxml"), LIVRO("livro.fxml"), DISCO("disco.fxml"),
	CLIENTE_GERENTE("cliente_gerente.fxml"), CLIENTE_FUNCIONARIO("cliente_funcionario.fxml"),
	CADASTRO_LIVRO("cadastro_livro.fxml"), CADASTRO_DISCO("cadastro_disco.fxml"),
	CADASTRO_CLIENTE_GERENTE("cadastro_cliente_gerente.fxml"),
	CADASTRO_CLIENTE_FUNCIONARIO("cadastro_cliente_funcionario.fxml");

	private String nomeArquivo;

	View(String tela) {
		setNomeArquivo(tela);
	}

	public String getNomeArquivo() {
		return nomeArquivo;
	}

	public void setNomeArquivo(String nomeArquivo) {
		this.nomeArquivo = nomeArquivo;
	}
}
