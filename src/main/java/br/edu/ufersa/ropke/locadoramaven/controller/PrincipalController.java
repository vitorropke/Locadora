package br.edu.ufersa.ropke.locadoramaven.controller;

import java.net.URL;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.ResourceBundle;

import br.edu.ufersa.ropke.locadoramaven.model.BO.EmprestimoBO;
import br.edu.ufersa.ropke.locadoramaven.model.VO.EmprestimoVO;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class PrincipalController extends ComumController implements Initializable {
	@FXML
	private TextField pesquisaEmprestimo;
	@FXML
	private TableView<EmprestimoVO> tabelaEmprestimos;
	@FXML
	private TableColumn<EmprestimoVO, String> colunaCliente;
	@FXML
	private TableColumn<EmprestimoVO, String> colunaObjeto;
	@FXML
	private TableColumn<EmprestimoVO, String> colunaTitulo;
	@FXML
	private TableColumn<EmprestimoVO, Integer> colunaQuantidade;
	@FXML
	private TableColumn<EmprestimoVO, Calendar> colunaDataDevolucao;

	ObservableList<EmprestimoVO> listaDeEmprestimos = FXCollections.observableArrayList();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		loadEmprestimo();

		EmprestimoBO emprestimoBO = new EmprestimoBO();

		listaDeEmprestimos.addAll(emprestimoBO.listar());
		tabelaEmprestimos.setItems(listaDeEmprestimos);
		tabelaEmprestimos.getItems().stream().forEach(doc -> System.out.println(doc.toString()));
	}

	private void loadEmprestimo() {
		colunaCliente.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getCliente().getNome()));
		/*
		 * colunaCliente.setCellValueFactory(new
		 * Callback<TableColumn.CellDataFeatures<EmprestimoVO,String>,
		 * ObservableValue<String>>() {
		 * 
		 * @Override public ObservableValue<String> call(CellDataFeatures<EmprestimoVO,
		 * String> param) { return new
		 * SimpleStringProperty(param.getValue().getCliente().getNome()); } });
		 */
		// colunaCliente.setCellValueFactory(new PropertyValueFactory<EmprestimoVO,
		// ClienteVO>("nome"));
		colunaObjeto.setCellValueFactory(new PropertyValueFactory<>("objeto"));
		colunaTitulo.setCellValueFactory(new PropertyValueFactory<>("titulo"));
		// colunaTitulo.setCellValueFactory(param -> new
		// SimpleStringProperty(param.getValue().getDisco()));
		colunaQuantidade.setCellValueFactory(new PropertyValueFactory<>("quantidade"));

		colunaDataDevolucao.setCellValueFactory(new PropertyValueFactory<>("dataEmprestimo"));
		// Formata o horário para o formato "'dia' de 'mes' de 'ano'". Ex: 21 de mai. de
		// 2020
		DateFormat dateFormat = DateFormat.getDateInstance();
		colunaDataDevolucao.setCellFactory(col -> new TableCell<EmprestimoVO, Calendar>() {
			@Override
			protected void updateItem(Calendar date, boolean empty) {
				super.updateItem(date, empty);
				if (empty) {
					setText(null);
				} else {
					setText(dateFormat.format(date.getTime()));
				}
			}
		});
	}

	@FXML
	public void cadastrarEmprestimo() {

	}

	@FXML
	public void cadastrarDevolucao() {

	}

	@FXML
	public void gerarRelatorio() {

	}
}
