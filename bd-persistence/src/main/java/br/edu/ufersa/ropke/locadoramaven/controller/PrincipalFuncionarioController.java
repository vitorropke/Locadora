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
		ViewSwitcher.switchTo(View.CADASTRO_EMPRESTIMO_FUNCIONARIO);
	}

	@FXML
	public void cadastrarDevolucao() {
		ViewSwitcher.switchTo(View.CADASTRO_DEVOLUCAO_FUNCIONARIO);
	}
}
