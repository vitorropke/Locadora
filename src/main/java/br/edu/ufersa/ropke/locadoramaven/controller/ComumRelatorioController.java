package br.edu.ufersa.ropke.locadoramaven.controller;

import java.net.URL;
import java.util.ResourceBundle;

import br.edu.ufersa.ropke.locadoramaven.view.View;
import br.edu.ufersa.ropke.locadoramaven.view.ViewSwitcher;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

public class ComumRelatorioController implements Initializable {
	@FXML
	public void inicio() {
		ViewSwitcher.switchTo(View.PRINCIPAL_GERENTE);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
	}
}
