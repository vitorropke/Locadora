package br.edu.ufersa.ropke.locadoramaven.controller;

import java.util.ArrayList;

import br.edu.ufersa.ropke.locadoramaven.exception.FoundException;
import br.edu.ufersa.ropke.locadoramaven.exception.InvalidParameterException;
import br.edu.ufersa.ropke.locadoramaven.model.BO.ClienteBO;
import br.edu.ufersa.ropke.locadoramaven.model.VO.ClienteVO;
import br.edu.ufersa.ropke.locadoramaven.view.View;
import br.edu.ufersa.ropke.locadoramaven.view.ViewSwitcher;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class CadastroClienteGerenteController {
	@FXML
	private Label clienteJaCadastrado;
	@FXML
	private Label dadosIncompletos;
	@FXML
	private TextField nome;
	@FXML
	private TextField cpf;
	@FXML
	private TextField endereco;
	@FXML
	private TextField email;
	@FXML
	private TextField telefone;

	@FXML
	public void cadastrarCliente() {
		ClienteBO clienteBO = new ClienteBO();

		String stringNome = nome.getText();
		String stringCpf = cpf.getText();
		String stringEndereco = endereco.getText();
		String stringEmail = email.getText();
		String stringTelefone = telefone.getText();

		try {
			ArrayList<String> enderecos = new ArrayList<String>();
			ArrayList<String> emails = new ArrayList<String>();
			ArrayList<String> telefones = new ArrayList<String>();

			enderecos.add(stringEndereco);
			emails.add(stringEmail);
			telefones.add(stringTelefone);

			// nome, cpf, endereco, 'email', 'telefone'
			ClienteVO clienteVO = new ClienteVO(stringNome, stringCpf, enderecos, emails, telefones);

			clienteBO.cadastrar(clienteVO);

			clienteJaCadastrado.setVisible(false);
			dadosIncompletos.setVisible(false);
			ViewSwitcher.switchTo(View.CLIENTE_GERENTE);
		} catch (FoundException e) {
			clienteJaCadastrado.setVisible(true);
			dadosIncompletos.setVisible(false);
		} catch (InvalidParameterException e) {
			clienteJaCadastrado.setVisible(false);
			dadosIncompletos.setVisible(true);
		}
	}

	@FXML
	public void cancelarCadastro() {
		ViewSwitcher.switchTo(View.CLIENTE_GERENTE);
	}
}
