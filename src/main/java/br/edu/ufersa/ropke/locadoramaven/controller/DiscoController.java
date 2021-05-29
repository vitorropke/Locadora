package br.edu.ufersa.ropke.locadoramaven.controller;

import java.net.URL;
import java.util.ResourceBundle;

import br.edu.ufersa.ropke.locadoramaven.model.BO.DiscoBO;
import br.edu.ufersa.ropke.locadoramaven.model.VO.DiscoVO;
import br.edu.ufersa.ropke.locadoramaven.view.View;
import br.edu.ufersa.ropke.locadoramaven.view.ViewSwitcher;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class DiscoController extends ComumController implements Initializable {
	@FXML
	private TextField pesquisaDisco;
	@FXML
	private TableView<DiscoVO> tabelaDiscos;
	@FXML
	private TableColumn<DiscoVO, String> colunaTitulo;
	@FXML
	private TableColumn<DiscoVO, String> colunaBanda;
	@FXML
	private TableColumn<DiscoVO, String> colunaEstilo;
	@FXML
	private TableColumn<DiscoVO, Integer> colunaQuantidade;
	@FXML
	private TableColumn<DiscoVO, Float> colunaValorAluguel;

	ObservableList<DiscoVO> listaDeDiscos = FXCollections.observableArrayList();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		loadEmprestimo();

		DiscoBO discoBO = new DiscoBO();

		listaDeDiscos.addAll(discoBO.listar());
		tabelaDiscos.setItems(listaDeDiscos);
		tabelaDiscos.getItems().stream().forEach(doc -> System.out.println(doc.toString()));
	}

	private void loadEmprestimo() {
		colunaTitulo.setCellValueFactory(new PropertyValueFactory<>("titulo"));
		colunaBanda.setCellValueFactory(new PropertyValueFactory<>("banda"));
		colunaEstilo.setCellValueFactory(new PropertyValueFactory<>("estilo"));
		colunaQuantidade.setCellValueFactory(new PropertyValueFactory<>("numeroExemplares"));
		colunaValorAluguel.setCellValueFactory(new PropertyValueFactory<>("valorAluguel"));
	}
	
	@FXML
	public void irParaTelaPrincipal() {
		ViewSwitcher.switchTo(View.PRINCIPAL_GERENTE);
	}
	
	@FXML
	public void cadastrarDisco() {
		
	}
	
	
	
	@FXML
	public void acessarClientes() {

	}
	
	@FXML
	public void sair() {
		ViewSwitcher.switchTo(View.LOGIN);
	}
}
