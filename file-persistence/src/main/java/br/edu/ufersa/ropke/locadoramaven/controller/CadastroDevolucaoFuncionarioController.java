package br.edu.ufersa.ropke.locadoramaven.controller;

import br.edu.ufersa.ropke.locadoramaven.view.View;
import br.edu.ufersa.ropke.locadoramaven.view.ViewSwitcher;
import javafx.fxml.FXML;

public class CadastroDevolucaoFuncionarioController extends CadastroDevolucaoController {
	@FXML
	public void cadastrar() {
		if (cadastrarDevolucaoSuper()) {
			ViewSwitcher.switchTo(View.PRINCIPAL_FUNCIONARIO);
		}
	}

	@FXML
	public void cancelar() {
		ViewSwitcher.switchTo(View.PRINCIPAL_FUNCIONARIO);
	}
}
