package br.edu.ufersa.ropke.locadoramaven.view;

public enum View {
	LOGIN("login.fxml"), PRINCIPAL("principal.fxml");

	private String nomeArquivo;

	View(String tela) {
		// TODO Auto-generated constructor stub
		setNomeArquivo(tela);
	}

	public String getNomeArquivo() {
		return nomeArquivo;
	}

	public void setNomeArquivo(String nomeArquivo) {
		this.nomeArquivo = nomeArquivo;
	}
}
