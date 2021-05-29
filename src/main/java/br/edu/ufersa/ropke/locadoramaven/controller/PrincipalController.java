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
	private TableColumn<EmprestimoVO, String> colunaTitulo;
	@FXML
	private TableColumn<EmprestimoVO, Integer> colunaQuantidade;
	@FXML
	private TableColumn<EmprestimoVO, Calendar> colunaDataEmprestimo;
	@FXML
	private TableColumn<EmprestimoVO, Calendar> colunaDataDevolucao;

	ObservableList<EmprestimoVO> listaEmprestimos = FXCollections.observableArrayList();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		loadEmprestimos();

		EmprestimoBO emprestimoBO = new EmprestimoBO();

		listaEmprestimos.addAll(emprestimoBO.listar());
		tabelaEmprestimos.setItems(listaEmprestimos);
		tabelaEmprestimos.getItems().stream().forEach(doc -> System.out.println(doc.toString()));
	}

	private void loadEmprestimos() {
		colunaCliente.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getCliente().getNome()));
		colunaTitulo.setCellValueFactory(
				param -> new SimpleStringProperty(param.getValue().getEmprestavel().get(0).getTitulo()));
		colunaTitulo.setCellValueFactory(new PropertyValueFactory<>("emprestavel"));
		colunaQuantidade.setCellValueFactory(new PropertyValueFactory<>("quantidadeEmprestavel"));
		colunaDataEmprestimo.setCellValueFactory(new PropertyValueFactory<>("dataEmprestimo"));
		// https://stackoverflow.com/questions/26224822/javafx-show-calendar-value-in-tableview
		// Formata o horário para o formato "'dia' de 'mes' de 'ano'". Ex: 21 de mai. de
		// 2020
		final DateFormat dateFormat = DateFormat.getDateInstance();
		colunaDataEmprestimo.setCellFactory(col -> new TableCell<EmprestimoVO, Calendar>() {
			@Override
			protected void updateItem(Calendar date, boolean empty) {
				super.updateItem(date, empty);
				if (date == null) {
					setText(null);
				} else {
					setText(dateFormat.format(date.getTime()));
				}
			}
		});
		colunaDataDevolucao.setCellValueFactory(new PropertyValueFactory<>("dataDevolucao"));
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
