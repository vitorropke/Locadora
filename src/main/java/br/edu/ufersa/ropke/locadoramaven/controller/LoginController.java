package br.edu.ufersa.ropke.locadoramaven.controller;

import br.edu.ufersa.ropke.locadoramaven.exception.IncorrectPasswordException;
import br.edu.ufersa.ropke.locadoramaven.exception.InvalidParameterException;
import br.edu.ufersa.ropke.locadoramaven.exception.UserNotFoundException;
import br.edu.ufersa.ropke.locadoramaven.model.BO.FuncionarioBO;
import br.edu.ufersa.ropke.locadoramaven.model.BO.GerenteBO;
import br.edu.ufersa.ropke.locadoramaven.model.VO.FuncionarioVO;
import br.edu.ufersa.ropke.locadoramaven.model.VO.GerenteVO;
import br.edu.ufersa.ropke.locadoramaven.view.View;
import br.edu.ufersa.ropke.locadoramaven.view.ViewSwitcher;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController {
	@FXML
	private Label usuarioNaoEncontrado;
	@FXML
	private Label senhaIncorreta;
	@FXML
	private TextField login;
	@FXML
	private PasswordField senha;

	@FXML
	public void entrar() {
		GerenteBO gerenteBO = new GerenteBO();
		GerenteVO gerenteVO = new GerenteVO();
		String stringLogin = login.getText();
		String stringSenha = senha.getText();

		gerenteVO.setLogin(stringLogin);
		gerenteVO.setSenha(stringSenha);

		// Busca pelos gerentes
		try {
			if (gerenteBO.autenticar(gerenteVO)) {
				usuarioNaoEncontrado.setVisible(false);
				senhaIncorreta.setVisible(false);
				ViewSwitcher.switchTo(View.PRINCIPAL_GERENTE);
			}
		} catch (UserNotFoundException | InvalidParameterException e) {
			FuncionarioBO funcionarioBO = new FuncionarioBO();
			FuncionarioVO funcionarioVO = new FuncionarioVO();

			funcionarioVO.setLogin(stringLogin);
			funcionarioVO.setSenha(stringSenha);

			// Se dar errado, busca pelos funcionários
			try {
				if (funcionarioBO.autenticar(funcionarioVO)) {
					usuarioNaoEncontrado.setVisible(false);
					senhaIncorreta.setVisible(false);
					ViewSwitcher.switchTo(View.PRINCIPAL_FUNCIONARIO);
				}
			} catch (UserNotFoundException | InvalidParameterException f) {
				usuarioNaoEncontrado.setVisible(true);
				senhaIncorreta.setVisible(false);
			} catch (IncorrectPasswordException f) {
				usuarioNaoEncontrado.setVisible(false);
				senhaIncorreta.setVisible(true);
			}
		} catch (IncorrectPasswordException e) {
			usuarioNaoEncontrado.setVisible(false);
			senhaIncorreta.setVisible(true);
		}
	}

	@FXML
	public void alterarSenha() {
		ViewSwitcher.switchTo(View.ALTERA_SENHA);
	}
}
