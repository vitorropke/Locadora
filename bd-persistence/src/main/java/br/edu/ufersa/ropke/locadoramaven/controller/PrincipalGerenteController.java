package br.edu.ufersa.ropke.locadoramaven.controller;

import br.edu.ufersa.ropke.locadoramaven.view.View;
import br.edu.ufersa.ropke.locadoramaven.view.ViewSwitcher;
import javafx.fxml.FXML;

public class PrincipalGerenteController extends PrincipalController {
	@FXML
	public void acessarLivros() {
		ViewSwitcher.switchTo(View.LIVRO);
	}

	@FXML
	public void acessarDiscos() {
		ViewSwitcher.switchTo(View.DISCO);
	}

	@FXML
	public void acessarClientes() {
		ViewSwitcher.switchTo(View.CLIENTE_GERENTE);
	}

	@FXML
	public void cadastrarEmprestimo() {
		ViewSwitcher.switchTo(View.CADASTRO_EMPRESTIMO_GERENTE);
	}

	@FXML
	public void cadastrarDevolucao() {
		ViewSwitcher.switchTo(View.CADASTRO_DEVOLUCAO_GERENTE);
	}

	@FXML
	public void gerarRelatorio() {
		ViewSwitcher.switchTo(View.RELATORIO);
	}
}
