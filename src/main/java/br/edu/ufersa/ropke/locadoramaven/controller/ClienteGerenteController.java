package br.edu.ufersa.ropke.locadoramaven.controller;

import br.edu.ufersa.ropke.locadoramaven.view.View;
import br.edu.ufersa.ropke.locadoramaven.view.ViewSwitcher;
import javafx.fxml.FXML;

public class ClienteGerenteController extends ClienteController {
	@FXML
	public void irParaTelaPrincipal() {
		ViewSwitcher.switchTo(View.PRINCIPAL_GERENTE);
	}

	@FXML
	public void cadastrarCliente() {
		ViewSwitcher.switchTo(View.CADASTRO_CLIENTE_GERENTE);
	}

	@FXML
	public void acessarLivros() {
		ViewSwitcher.switchTo(View.LIVRO);
	}

	@FXML
	public void acessarDiscos() {
		ViewSwitcher.switchTo(View.DISCO);
	}
}
