package br.edu.ufersa.ropke.locadoramaven.controller;

import br.edu.ufersa.ropke.locadoramaven.view.View;
import br.edu.ufersa.ropke.locadoramaven.view.ViewSwitcher;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController {
	@FXML
	private TextField login;
	@FXML
	private PasswordField senha;

	@FXML
	public void entrar() {
		ViewSwitcher.switchTo(View.PRINCIPAL);
	}

	@FXML
	public void alterarSenha() {
		
	}

}
