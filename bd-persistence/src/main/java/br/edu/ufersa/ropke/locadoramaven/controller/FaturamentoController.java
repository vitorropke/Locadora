package br.edu.ufersa.ropke.locadoramaven.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.ResourceBundle;

import br.edu.ufersa.ropke.locadoramaven.model.BO.EmprestimoBO;
import br.edu.ufersa.ropke.locadoramaven.model.VO.EmprestavelVO;
import br.edu.ufersa.ropke.locadoramaven.model.VO.EmprestimoVO;
import br.edu.ufersa.ropke.locadoramaven.model.VO.ObjetoEmprestadoVO;
import br.edu.ufersa.ropke.locadoramaven.view.View;
import br.edu.ufersa.ropke.locadoramaven.view.ViewSwitcher;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class FaturamentoController extends ComumRelatorioController {
	@FXML
	private TableView<ObjetoEmprestadoVO> tabelaEmprestaveis;
	@FXML
	private TableColumn<EmprestavelVO, String> colunaTitulo;
	@FXML
	private TableColumn<EmprestavelVO, Integer> colunaNumeroExemplares;
	@FXML
	private TableColumn<EmprestavelVO, Integer> colunaNumeroEmprestimos;
	@FXML
	private TableColumn<EmprestavelVO, Integer> colunaNumeroDiasAlugado;
	@FXML
	private TableColumn<EmprestavelVO, Float> colunaValorTotal;
	@FXML
	private TextField faturamentoMensal;

	ObservableList<ObjetoEmprestadoVO> listaEmprestimos = FXCollections.observableArrayList();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		loadEmprestimos();

		Calendar dataInicio = Calendar.getInstance();
		Calendar dataAtual = Calendar.getInstance();
		dataInicio.set(Calendar.YEAR, Calendar.MONTH, 0, 0, 0, 0);

		EmprestimoBO emprestimoBO = new EmprestimoBO();

		List<EmprestimoVO> emprestimos = emprestimoBO.gerarRelatorio(dataInicio, dataAtual);
		List<ObjetoEmprestadoVO> emprestaveis = new ArrayList<ObjetoEmprestadoVO>();
		int numeroEmprestimosMes = emprestimos.size();

		for (int i = 0; i < numeroEmprestimosMes; i++) {
			emprestaveis.addAll(emprestimos.get(i).getObjetos());
		}

		listaEmprestimos.addAll(emprestaveis);
		tabelaEmprestaveis.setItems(listaEmprestimos);
		tabelaEmprestaveis.getItems().stream().forEach(doc -> System.out.println(doc.toString()));

		float floatFaturamentoMensal = 0;

		for (int i = 0; i < numeroEmprestimosMes; i++) {
			int numeroEmprestaveis = emprestimos.get(i).getObjetos().size();

			for (int j = 0; j < numeroEmprestaveis; j++) {
				floatFaturamentoMensal += emprestimos.get(i).getObjetos().get(j).getObjeto().getValorAluguel()
						* emprestimos.get(i).getObjetos().get(j).getQuantidade();
			}
		}

		faturamentoMensal.setText(String.format("%.02f", floatFaturamentoMensal));
	}

	private void loadEmprestimos() {
		colunaTitulo.setCellValueFactory(new PropertyValueFactory<>("titulo"));
		colunaNumeroExemplares.setCellValueFactory(new PropertyValueFactory<>("numeroExemplares"));
		colunaNumeroEmprestimos.setCellValueFactory(new PropertyValueFactory<>("numeroEmprestimos"));
		colunaNumeroDiasAlugado.setCellValueFactory(new PropertyValueFactory<>("numeroDiasAlugado"));
		colunaValorTotal.setCellValueFactory(new PropertyValueFactory<>("valorAluguel"));
	}

	@FXML
	public void relatorioObjeto() {
		ViewSwitcher.switchTo(View.RELATORIO_OBJETO);
	}

	@FXML
	public void relatorioCliente() {
		ViewSwitcher.switchTo(View.RELATORIO_CLIENTE);
	}
}
