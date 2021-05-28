package br.edu.ufersa.ropke.locadoramaven.controller;

import br.edu.ufersa.ropke.locadoramaven.view.View;
import br.edu.ufersa.ropke.locadoramaven.view.ViewSwitcher;
import javafx.fxml.FXML;

public class DiscoController {
	@FXML
	public void irParaTelaPrincipal() {
		ViewSwitcher.switchTo(View.PRINCIPAL_GERENTE);
	}
	
	@FXML
	public void cadastrarDisco() {
		
	}
	
	
	
	@FXML
	public void acessarClientes() {

	}
	
	@FXML
	public void sair() {
		ViewSwitcher.switchTo(View.LOGIN);
	}
}
