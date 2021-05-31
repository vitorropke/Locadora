package br.edu.ufersa.ropke.locadoramaven.controller;

import java.net.URL;
import java.text.DateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.ResourceBundle;

import br.edu.ufersa.ropke.locadoramaven.model.BO.ClienteBO;
import br.edu.ufersa.ropke.locadoramaven.model.BO.EmprestimoBO;
import br.edu.ufersa.ropke.locadoramaven.model.VO.ClienteVO;
import br.edu.ufersa.ropke.locadoramaven.model.VO.EmprestimoVO;
import br.edu.ufersa.ropke.locadoramaven.view.View;
import br.edu.ufersa.ropke.locadoramaven.view.ViewSwitcher;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class RelatorioClienteController extends ComumRelatorioController {
	@FXML
	private TextField cliente;
	@FXML
	private DatePicker dataInicio;
	@FXML
	private DatePicker dataFim;
	@FXML
	private TableView<EmprestimoVO> tabelaEmprestimos;
	@FXML
	private TableColumn<EmprestimoVO, String> colunaTitulo;
	@FXML
	private TableColumn<EmprestimoVO, Integer> colunaQuantidade;
	@FXML
	private TableColumn<EmprestimoVO, Calendar> colunaDataEmprestimo;
	@FXML
	private TableColumn<EmprestimoVO, String> colunaValorTotal;

	ObservableList<EmprestimoVO> listaEmprestimos = FXCollections.observableArrayList();

	public Calendar localDateToCalendar(DatePicker data) {
		LocalDate localDate = data.getValue();
		Calendar calendar = Calendar.getInstance();

		calendar.set(localDate.getYear(), localDate.getMonthValue() - 1, localDate.getDayOfMonth());

		return calendar;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		loadEmprestimos();
	}

	private void loadEmprestimos() {
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
		colunaValorTotal.setCellValueFactory(param -> new SimpleStringProperty());
	}

	@FXML
	public void gerar() {
		String stringClinte = cliente.getText();

		EmprestimoBO emprestimoBO = new EmprestimoBO();
		ClienteBO clienteBO = new ClienteBO();
		ClienteVO cliente = clienteBO.pesquisarCpf(stringClinte);

		listaEmprestimos.clear();
		listaEmprestimos.addAll(emprestimoBO.gerarRelatorioCliente(cliente, localDateToCalendar(dataInicio),
				localDateToCalendar(dataFim)));
		tabelaEmprestimos.setItems(listaEmprestimos);
		tabelaEmprestimos.getItems().stream().forEach(doc -> System.out.println(doc.toString()));
	}

	@FXML
	public void relatorioObjeto() {
		ViewSwitcher.switchTo(View.RELATORIO_OBJETO);
	}

	@FXML
	public void faturamento() {
		ViewSwitcher.switchTo(View.FATURAMENTO);
	}
}
