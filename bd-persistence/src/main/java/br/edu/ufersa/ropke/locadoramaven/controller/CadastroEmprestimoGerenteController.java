package br.edu.ufersa.ropke.locadoramaven.controller;

import br.edu.ufersa.ropke.locadoramaven.view.View;
import br.edu.ufersa.ropke.locadoramaven.view.ViewSwitcher;
import javafx.fxml.FXML;

public class CadastroEmprestimoGerenteController extends CadastroEmprestimoController {
	@FXML
	public void cadastrar() {
		if (cadastrarEmprestimoSuper()) {
			ViewSwitcher.switchTo(View.PRINCIPAL_GERENTE);
		}
	}

	@FXML
	public void cancelar() {
		ViewSwitcher.switchTo(View.PRINCIPAL_GERENTE);
	}
}
