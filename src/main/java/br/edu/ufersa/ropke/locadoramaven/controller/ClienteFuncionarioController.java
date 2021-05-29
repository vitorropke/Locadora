package br.edu.ufersa.ropke.locadoramaven.controller;

import br.edu.ufersa.ropke.locadoramaven.view.View;
import br.edu.ufersa.ropke.locadoramaven.view.ViewSwitcher;
import javafx.fxml.FXML;

public class ClienteFuncionarioController extends ClienteController {
	@FXML
	public void irParaTelaPrincipal() {
		ViewSwitcher.switchTo(View.PRINCIPAL_FUNCIONARIO);
	}

	@FXML
	public void cadastrarCliente() {
		ViewSwitcher.switchTo(View.CADASTRO_CLIENTE_FUNCIONARIO);
	}
}
