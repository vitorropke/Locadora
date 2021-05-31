package br.edu.ufersa.ropke.locadoramaven.controller;

import br.edu.ufersa.ropke.locadoramaven.view.View;
import br.edu.ufersa.ropke.locadoramaven.view.ViewSwitcher;
import javafx.fxml.FXML;

public class CadastroEmprestimoFuncionarioController extends CadastroEmprestimoController {
	@FXML
	public void cadastrarEmprestimo() {
		if (cadastrarEmprestimoSuper()) {
			ViewSwitcher.switchTo(View.PRINCIPAL_FUNCIONARIO);
		}
	}

	@FXML
	public void cancelarCadastro() {
		ViewSwitcher.switchTo(View.PRINCIPAL_FUNCIONARIO);
	}
}
