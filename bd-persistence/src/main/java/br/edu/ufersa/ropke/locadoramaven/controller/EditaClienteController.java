package br.edu.ufersa.ropke.locadoramaven.controller;

import java.util.ArrayList;
import java.util.List;

import br.edu.ufersa.ropke.locadoramaven.exception.InvalidParameterException;
import br.edu.ufersa.ropke.locadoramaven.exception.NotFoundException;
import br.edu.ufersa.ropke.locadoramaven.model.BO.ClienteBO;
import br.edu.ufersa.ropke.locadoramaven.model.VO.ClienteVO;
import br.edu.ufersa.ropke.locadoramaven.model.VO.EnderecoVO;
import br.edu.ufersa.ropke.locadoramaven.model.VO.TelefoneVO;
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

	private long idCliente;
	private long idPessoa;

	@FXML
	public void pesquisar() {
		String stringNome = nome.getText();
		String stringCpf = nome.getText();

		ClienteBO clienteBO = new ClienteBO();
		List<ClienteVO> clientes = clienteBO.pesquisarNome(stringNome);
		int quantidadeClientes = clientes.size();

		if (quantidadeClientes == 1) {
			ClienteVO clienteAtual = clientes.get(0);
			idCliente = clienteAtual.getId();
			idPessoa = clienteAtual.getIdPessoa();

			nome.setText(clienteAtual.getNome());
			cpf.setText(clienteAtual.getCpf());

			int numeroEnderecos = clienteAtual.getEnderecos().size();
			int numeroEmails = clienteAtual.getEmails().size();
			int numeroTelefones = clienteAtual.getTelefones().size();
			String dado = "";

			for (int i = 0; i < numeroEnderecos; i++) {
				dado += clienteAtual.getEnderecos().get(i) + ", ";
			}
			endereco.setText(dado);

			dado = "";
			for (int i = 0; i < numeroEmails; i++) {
				dado += clienteAtual.getEmails().get(i) + ", ";
			}
			email.setText(dado);

			dado = "";
			for (int i = 0; i < numeroTelefones; i++) {
				dado += clienteAtual.getTelefones().get(i) + ", ";
			}
			telefone.setText(dado);

			naoEncontrado.setVisible(false);
			dadosIncompletos.setVisible(false);
		} else {
			ClienteVO clienteAtual = clienteBO.pesquisarCpf(stringCpf);

			if (clienteAtual != null) {
				idCliente = clienteAtual.getId();
				idPessoa = clienteAtual.getIdPessoa();

				nome.setText(clienteAtual.getNome());
				cpf.setText(clienteAtual.getCpf());

				int numeroEnderecos = clienteAtual.getEnderecos().size();
				int numeroEmails = clienteAtual.getEmails().size();
				int numeroTelefones = clienteAtual.getTelefones().size();
				String dado = "";

				for (int i = 0; i < numeroEnderecos; i++) {
					dado += clienteAtual.getEnderecos().get(i) + ", ";
				}
				endereco.setText(dado);

				dado = "";
				for (int i = 0; i < numeroEmails; i++) {
					dado += clienteAtual.getEmails().get(i) + ", ";
				}
				email.setText(dado);

				dado = "";
				for (int i = 0; i < numeroTelefones; i++) {
					dado += clienteAtual.getTelefones().get(i) + ", ";
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
			clienteVO.setId(idCliente);
			clienteVO.setIdPessoa(idPessoa);

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
		List<ClienteVO> clientes = clienteBO.pesquisarNome(stringNome);
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
