package br.edu.ufersa.ropke.locadoramaven.controller;

import java.util.ArrayList;
import java.util.List;

import br.edu.ufersa.ropke.locadoramaven.exception.FoundException;
import br.edu.ufersa.ropke.locadoramaven.exception.InvalidParameterException;
import br.edu.ufersa.ropke.locadoramaven.model.BO.ClienteBO;
import br.edu.ufersa.ropke.locadoramaven.model.VO.ClienteVO;
import br.edu.ufersa.ropke.locadoramaven.model.VO.EnderecoVO;
import br.edu.ufersa.ropke.locadoramaven.model.VO.TelefoneVO;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class CadastroClienteController {
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

	public boolean cadastrarClienteSuper() {
		ClienteBO clienteBO = new ClienteBO();

		String stringNome = nome.getText();
		String stringCpf = cpf.getText();

		try {
			List<EnderecoVO> enderecos = new ArrayList<EnderecoVO>();
			List<String> emails = new ArrayList<String>();
			List<TelefoneVO> telefones = new ArrayList<TelefoneVO>();

			List<String> camposEndereco = new ArrayList<String>();
			String campoAtual = "";
			for (char caractereAtual : endereco.getText().toCharArray()) {
				if (caractereAtual != ';') {
					campoAtual += caractereAtual;
				} else {
					camposEndereco.add(campoAtual);
					campoAtual = "";
				}
			}
			enderecos.add(new EnderecoVO(camposEndereco.get(0), camposEndereco.get(1), camposEndereco.get(2),
					camposEndereco.get(3), camposEndereco.get(4), camposEndereco.get(5), camposEndereco.get(6),
					camposEndereco.get(7)));

			List<String> camposEmails = new ArrayList<String>();
			campoAtual = "";
			for (char caractereAtual : email.getText().toCharArray()) {
				if (caractereAtual != ';') {
					campoAtual += caractereAtual;
				} else {
					camposEmails.add(campoAtual);
					campoAtual = "";
				}
			}
			emails.addAll(camposEmails);

			List<String> camposTelefone = new ArrayList<String>();
			campoAtual = "";
			for (char caractereAtual : telefone.getText().toCharArray()) {
				if (caractereAtual != ';') {
					campoAtual += caractereAtual;
				} else {
					camposTelefone.add(campoAtual);
					campoAtual = "";
				}
			}
			telefones.add(new TelefoneVO(camposTelefone.get(0), camposTelefone.get(1)));

			// nome, cpf, endereco, 'email', 'telefone'
			ClienteVO clienteVO = new ClienteVO(stringNome, stringCpf, enderecos, emails, telefones);

			clienteBO.cadastrar(clienteVO);

			clienteJaCadastrado.setVisible(false);
			dadosIncompletos.setVisible(false);

			return true;
		} catch (FoundException e) {
			clienteJaCadastrado.setVisible(true);
			dadosIncompletos.setVisible(false);
		} catch (InvalidParameterException e) {
			clienteJaCadastrado.setVisible(false);
			dadosIncompletos.setVisible(true);
		}

		return false;
	}
}
