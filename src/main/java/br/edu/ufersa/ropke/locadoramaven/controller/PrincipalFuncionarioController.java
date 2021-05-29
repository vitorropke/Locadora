package br.edu.ufersa.ropke.locadoramaven.controller;

import br.edu.ufersa.ropke.locadoramaven.view.View;
import br.edu.ufersa.ropke.locadoramaven.view.ViewSwitcher;
import javafx.fxml.FXML;

public class PrincipalFuncionarioController extends PrincipalController {
	@FXML
	public void acessarClientes() {
		ViewSwitcher.switchTo(View.CLIENTE_FUNCIONARIO);
	}
	
	@FXML
	public void cadastrarEmprestimo() {

	}

	@FXML
	public void cadastrarDevolucao() {

	}

	@FXML
	public void gerarRelatorio() {

	}
}
