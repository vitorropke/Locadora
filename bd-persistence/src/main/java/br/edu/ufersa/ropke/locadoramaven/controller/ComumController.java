package br.edu.ufersa.ropke.locadoramaven.controller;

import br.edu.ufersa.ropke.locadoramaven.view.View;
import br.edu.ufersa.ropke.locadoramaven.view.ViewSwitcher;
import javafx.fxml.FXML;

public class ComumController {
	@FXML
	public void sair() {
		ViewSwitcher.switchTo(View.LOGIN);
	}
}
