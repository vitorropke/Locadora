package br.edu.ufersa.ropke.locadoramaven.controller;

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

public class AlteraSenhaController {
	@FXML
	private Label usuarioNaoEncontrado;
	@FXML
	private Label senhasNaoConferem;
	@FXML
	private TextField login;
	@FXML
	private PasswordField novaSenha;
	@FXML
	private PasswordField confirmaNovaSenha;

	@FXML
	public void alterarSenha() {
		GerenteBO gerenteBO = new GerenteBO();
		GerenteVO gerenteVO = new GerenteVO();
		FuncionarioBO funcionarioBO = new FuncionarioBO();
		FuncionarioVO funcionarioVO = new FuncionarioVO();

		String stringLogin = login.getText();
		String stringNovaSenha = novaSenha.getText();
		String stringConfirmaNovaSenha = confirmaNovaSenha.getText();

		// Define o login
		gerenteVO.setLogin(stringLogin);
		gerenteVO.setSenha("senha aleatoria");
		funcionarioVO.setLogin(stringLogin);
		funcionarioVO.setSenha("senha aleatoria");

		// Procura pelo objeto
		gerenteVO = gerenteBO.pesquisarLogin(gerenteVO);
		funcionarioVO = funcionarioBO.pesquisarLogin(funcionarioVO);

		// Busca pelo gerente ou funcionário
		if (gerenteVO != null) {
			// Verifica se as senhas são iguais
			if (stringNovaSenha.equals(stringConfirmaNovaSenha)) {
				// Define a nova senha para o gerente
				gerenteVO.setSenha(stringConfirmaNovaSenha);
				// Altera a senha no arquivo
				gerenteBO.alterar(gerenteVO);

				// Muda a tela
				usuarioNaoEncontrado.setVisible(false);
				senhasNaoConferem.setVisible(false);
				ViewSwitcher.switchTo(View.LOGIN);
			} else {
				usuarioNaoEncontrado.setVisible(false);
				senhasNaoConferem.setVisible(true);
			}
		} else if (funcionarioVO != null) {
			// Verifica se as senhas são iguais
			if (stringNovaSenha.equals(stringConfirmaNovaSenha)) {
				// Define a nova senha para o funcionário
				funcionarioVO.setSenha(stringConfirmaNovaSenha);
				// Altera a senha no arquivo
				funcionarioBO.alterar(funcionarioVO);

				// Muda a tela
				usuarioNaoEncontrado.setVisible(false);
				senhasNaoConferem.setVisible(false);
				ViewSwitcher.switchTo(View.LOGIN);
			} else {
				usuarioNaoEncontrado.setVisible(false);
				senhasNaoConferem.setVisible(true);
			}
		} else {
			usuarioNaoEncontrado.setVisible(true);
			senhasNaoConferem.setVisible(false);
		}
	}
}
