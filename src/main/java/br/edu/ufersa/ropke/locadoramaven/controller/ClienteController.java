package br.edu.ufersa.ropke.locadoramaven.controller;

import java.net.URL;
import java.util.ResourceBundle;

import br.edu.ufersa.ropke.locadoramaven.model.BO.ClienteBO;
import br.edu.ufersa.ropke.locadoramaven.model.VO.ClienteVO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class ClienteController extends ComumController implements Initializable {
	@FXML
	private TextField pesquisaCliente;
	@FXML
	private TableView<ClienteVO> tabelaClientes;
	@FXML
	private TableColumn<ClienteVO, String> colunaNome;
	@FXML
	private TableColumn<ClienteVO, String> colunaCpf;
	@FXML
	private TableColumn<ClienteVO, String> colunaEndereco;
	@FXML
	private TableColumn<ClienteVO, String> colunaEmail;
	@FXML
	private TableColumn<ClienteVO, String> colunaTelefone;

	ObservableList<ClienteVO> listaClientes = FXCollections.observableArrayList();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		loadClientes();

		ClienteBO discoBO = new ClienteBO();

		listaClientes.addAll(discoBO.listar());
		tabelaClientes.setItems(listaClientes);
		tabelaClientes.getItems().stream().forEach(doc -> System.out.println(doc.toString()));
	}

	private void loadClientes() {
		colunaNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
		colunaCpf.setCellValueFactory(new PropertyValueFactory<>("cpf"));
		colunaEndereco.setCellValueFactory(new PropertyValueFactory<>("endereco"));
		colunaEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
		colunaTelefone.setCellValueFactory(new PropertyValueFactory<>("telefone"));
	}
}
