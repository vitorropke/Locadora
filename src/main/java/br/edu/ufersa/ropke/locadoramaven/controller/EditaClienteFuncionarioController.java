package br.edu.ufersa.ropke.locadoramaven.controller;

import br.edu.ufersa.ropke.locadoramaven.view.View;
import br.edu.ufersa.ropke.locadoramaven.view.ViewSwitcher;
import javafx.fxml.FXML;

public class EditaClienteFuncionarioController extends EditaClienteController {
	@FXML
	public void editar() {
		if (editarClienteSuper()) {
			ViewSwitcher.switchTo(View.CLIENTE_FUNCIONARIO);
		}
	}

	@FXML
	public void apagar() {
		if (apagarClienteSuper()) {
			ViewSwitcher.switchTo(View.CLIENTE_FUNCIONARIO);
		}
	}

	@FXML
	public void cancelar() {
		ViewSwitcher.switchTo(View.CLIENTE_FUNCIONARIO);
	}
}
