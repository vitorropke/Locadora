package br.edu.ufersa.ropke.locadoramaven.controller;

import java.net.URL;
import java.text.DateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.ResourceBundle;

import br.edu.ufersa.ropke.locadoramaven.model.BO.EmprestimoBO;
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
import javafx.scene.control.cell.PropertyValueFactory;

public class RelatorioObjetoController extends ComumRelatorioController {
	@FXML
	private DatePicker dataInicio = new DatePicker();
	@FXML
	private DatePicker dataFim = new DatePicker();
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
		Calendar calendarDataInicio = Calendar.getInstance();
		Calendar calendarDataFim = Calendar.getInstance();

		LocalDate localDate = dataInicio.getValue();
		if (localDate != null) {
			calendarDataInicio.set(localDate.getYear(), localDate.getMonthValue() - 1, localDate.getDayOfMonth(), 0, 0,
					0);
		} else {
			calendarDataInicio = null;
		}

		localDate = dataFim.getValue();
		if (localDate != null) {
			calendarDataFim.set(localDate.getYear(), localDate.getMonthValue() - 1, localDate.getDayOfMonth(), 23, 59,
					59);
		} else {
			calendarDataFim = null;
		}

		EmprestimoBO emprestimoBO = new EmprestimoBO();

		listaEmprestimos.clear();
		listaEmprestimos.addAll(emprestimoBO.gerarRelatorio(calendarDataInicio, calendarDataFim));
		tabelaEmprestimos.setItems(listaEmprestimos);
		tabelaEmprestimos.getItems().stream().forEach(doc -> System.out.println(doc.toString()));
	}

	@FXML
	public void relatorioCliente() {
		ViewSwitcher.switchTo(View.RELATORIO_CLIENTE);
	}

	@FXML
	public void faturamento() {
		ViewSwitcher.switchTo(View.FATURAMENTO);
	}
}
