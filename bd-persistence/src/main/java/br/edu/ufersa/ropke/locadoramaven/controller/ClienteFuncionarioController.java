package br.edu.ufersa.ropke.locadoramaven.controller;

import br.edu.ufersa.ropke.locadoramaven.view.View;
import br.edu.ufersa.ropke.locadoramaven.view.ViewSwitcher;
import javafx.fxml.FXML;

public class ClienteFuncionarioController extends ClienteController {
	@FXML
	public void inicio() {
		ViewSwitcher.switchTo(View.PRINCIPAL_FUNCIONARIO);
	}

	@FXML
	public void cadastrar() {
		ViewSwitcher.switchTo(View.CADASTRO_CLIENTE_FUNCIONARIO);
	}
	
	@FXML
	public void editar() {
		ViewSwitcher.switchTo(View.EDITA_CLIENTE_FUNCIONARIO);
	}
}
