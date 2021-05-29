package br.edu.ufersa.ropke.locadoramaven.view;

public enum View {
	LOGIN("login.fxml"), ALTERA_SENHA("altera_senha.fxml"), PRINCIPAL_GERENTE("principal_gerente.fxml"),
	PRINCIPAL_FUNCIONARIO("principal_funcionario.fxml"), LIVRO("livro.fxml"), DISCO("disco.fxml"),
	CLIENTE_GERENTE("cliente_gerente.fxml"), CLIENTE_FUNCIONARIO("cliente_funcionario.fxml");

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
