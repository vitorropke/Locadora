package br.edu.ufersa.ropke.locadoramaven.controller;

import br.edu.ufersa.ropke.locadoramaven.view.View;
import br.edu.ufersa.ropke.locadoramaven.view.ViewSwitcher;
import javafx.fxml.FXML;

public class CadastroClienteFuncionarioController extends CadastroClienteController {
	@FXML
	public void cadastrar() {
		if (cadastrarClienteSuper()) {
			ViewSwitcher.switchTo(View.CLIENTE_FUNCIONARIO);
		}
	}

	@FXML
	public void cancelar() {
		ViewSwitcher.switchTo(View.CLIENTE_FUNCIONARIO);
	}
}
