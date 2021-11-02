package br.edu.ufersa.ropke.locadoramaven.view;

public enum View {
	LOGIN("login.fxml"), ALTERA_SENHA("altera_senha.fxml"), PRINCIPAL_GERENTE("principal_gerente.fxml"),
	PRINCIPAL_FUNCIONARIO("principal_funcionario.fxml"), LIVRO("livro.fxml"), DISCO("disco.fxml"),
	CLIENTE_GERENTE("cliente_gerente.fxml"), CLIENTE_FUNCIONARIO("cliente_funcionario.fxml"),
	CADASTRO_LIVRO("cadastro_livro.fxml"), CADASTRO_DISCO("cadastro_disco.fxml"),
	CADASTRO_CLIENTE_GERENTE("cadastro_cliente_gerente.fxml"),
	CADASTRO_CLIENTE_FUNCIONARIO("cadastro_cliente_funcionario.fxml"),
	CADASTRO_EMPRESTIMO_GERENTE("cadastro_emprestimo_gerente.fxml"),
	CADASTRO_EMPRESTIMO_FUNCIONARIO("cadastro_emprestimo_funcionario.fxml"),
	CADASTRO_DEVOLUCAO_GERENTE("cadastro_devolucao_gerente.fxml"),
	CADASTRO_DEVOLUCAO_FUNCIONARIO("cadastro_devolucao_funcionario.fxml"), EDITA_LIVRO("edita_livro.fxml"),
	EDITA_DISCO("edita_disco.fxml"), EDITA_CLIENTE_GERENTE("edita_cliente_gerente.fxml"),
	EDITA_CLIENTE_FUNCIONARIO("edita_cliente_funcionario.fxml"), RELATORIO("relatorio.fxml"),
	RELATORIO_OBJETO("relatorio_objeto.fxml"), RELATORIO_CLIENTE("relatorio_cliente.fxml"),
	FATURAMENTO("faturamento.fxml");

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
