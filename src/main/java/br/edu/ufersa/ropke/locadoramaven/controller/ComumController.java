package br.edu.ufersa.ropke.locadoramaven.controller;

import br.edu.ufersa.ropke.locadoramaven.view.View;
import br.edu.ufersa.ropke.locadoramaven.view.ViewSwitcher;
import javafx.fxml.FXML;

public abstract class ComumController {
	@FXML
	public void irParaTelaPrincipal() {
		ViewSwitcher.switchTo(View.PRINCIPAL_GERENTE);
	}

	@FXML
	public void acessarLivros() {
		ViewSwitcher.switchTo(View.LIVRO);
	}

	@FXML
	public void acessarDiscos() {
		ViewSwitcher.switchTo(View.DISCO);
	}

	@FXML
	public void acessarClientes() {

	}

	@FXML
	public void sair() {
		ViewSwitcher.switchTo(View.LOGIN);
	}
}
