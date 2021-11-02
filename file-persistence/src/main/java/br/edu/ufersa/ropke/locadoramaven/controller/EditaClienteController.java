package br.edu.ufersa.ropke.locadoramaven.controller;

import java.util.ArrayList;

import br.edu.ufersa.ropke.locadoramaven.exception.InvalidParameterException;
import br.edu.ufersa.ropke.locadoramaven.exception.NotFoundException;
import br.edu.ufersa.ropke.locadoramaven.model.BO.ClienteBO;
import br.edu.ufersa.ropke.locadoramaven.model.VO.ClienteVO;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class EditaClienteController {
	@FXML
	private Label naoEncontrado;
	@FXML
	private Label dadosIncompletos;
	@FXML
	private Label dadosIncorretos;
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
	public void pesquisar() {
		String stringNome = nome.getText();
		String stringCpf = nome.getText();

		ClienteBO clienteBO = new ClienteBO();
		ArrayList<ClienteVO> clientes = clienteBO.pesquisarNome(stringNome);
		int quantidadeClientes = clientes.size();

		if (quantidadeClientes == 1) {
			ClienteVO clienteAtual = clientes.get(0);

			nome.setText(clienteAtual.getNome());
			cpf.setText(clienteAtual.getCpf());

			int numeroEnderecos = clienteAtual.getEndereco().size();
			int numeroEmails = clienteAtual.getEmail().size();
			int numeroTelefones = clienteAtual.getTelefone().size();
			String dado = "";

			for (int i = 0; i < numeroEnderecos; i++) {
				dado += clienteAtual.getEndereco().get(i) + ", ";
			}
			endereco.setText(dado);

			dado = "";
			for (int i = 0; i < numeroEmails; i++) {
				dado += clienteAtual.getEmail().get(i) + ", ";
			}
			email.setText(dado);

			dado = "";
			for (int i = 0; i < numeroTelefones; i++) {
				dado += clienteAtual.getTelefone().get(i) + ", ";
			}
			telefone.setText(dado);

			naoEncontrado.setVisible(false);
			dadosIncompletos.setVisible(false);
		} else {
			ClienteVO clienteAtual = clienteBO.pesquisarCpf(stringCpf);

			if (clienteAtual != null) {
				nome.setText(clienteAtual.getNome());
				cpf.setText(clienteAtual.getCpf());

				int numeroEnderecos = clienteAtual.getEndereco().size();
				int numeroEmails = clienteAtual.getEmail().size();
				int numeroTelefones = clienteAtual.getTelefone().size();
				String dado = "";

				for (int i = 0; i < numeroEnderecos; i++) {
					dado += clienteAtual.getEndereco().get(i) + ", ";
				}
				endereco.setText(dado);

				dado = "";
				for (int i = 0; i < numeroEmails; i++) {
					dado += clienteAtual.getEmail().get(i) + ", ";
				}
				email.setText(dado);

				dado = "";
				for (int i = 0; i < numeroTelefones; i++) {
					dado += clienteAtual.getTelefone().get(i) + ", ";
				}
				telefone.setText(dado);

				naoEncontrado.setVisible(false);
				dadosIncompletos.setVisible(false);
			} else {
				naoEncontrado.setVisible(true);
				dadosIncompletos.setVisible(false);
			}
		}
	}

	public boolean editarClienteSuper() {
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

			clienteBO.alterar(clienteVO);

			naoEncontrado.setVisible(false);
			dadosIncompletos.setVisible(false);

			return true;
		} catch (NumberFormatException e) {
			naoEncontrado.setVisible(false);
			dadosIncompletos.setVisible(false);
		} catch (NotFoundException e) {
			naoEncontrado.setVisible(true);
			dadosIncompletos.setVisible(false);
		} catch (InvalidParameterException e) {
			naoEncontrado.setVisible(false);
			dadosIncompletos.setVisible(true);
		}

		return false;
	}

	public boolean apagarClienteSuper() {
		String stringNome = nome.getText();
		String stringCpf = nome.getText();

		ClienteBO clienteBO = new ClienteBO();
		ArrayList<ClienteVO> clientes = clienteBO.pesquisarNome(stringNome);
		int quantidadeClientes = clientes.size();

		if (quantidadeClientes == 1) {
			ClienteVO clienteAtual = clientes.get(0);

			clienteBO.deletar(clienteAtual);

			naoEncontrado.setVisible(false);
			dadosIncompletos.setVisible(false);

			return true;
		} else {
			ClienteVO clienteAtual = clienteBO.pesquisarCpf(stringCpf);

			if (clienteAtual != null) {
				clienteBO.deletar(clienteAtual);

				naoEncontrado.setVisible(false);
				dadosIncompletos.setVisible(false);

				return true;
			} else {
				naoEncontrado.setVisible(true);
				dadosIncompletos.setVisible(false);
			}
		}
		return false;
	}
}
