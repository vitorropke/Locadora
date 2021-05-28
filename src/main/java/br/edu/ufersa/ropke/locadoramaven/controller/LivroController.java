package br.edu.ufersa.ropke.locadoramaven.controller;

import java.net.URL;
import java.util.Calendar;
import java.util.ResourceBundle;

import br.edu.ufersa.ropke.locadoramaven.model.BO.LivroBO;
import br.edu.ufersa.ropke.locadoramaven.model.VO.LivroVO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class LivroController extends ComumController implements Initializable {
	@FXML
	private TextField pesquisaLivro;
	@FXML
	private TableView<LivroVO> tabelaLivros;
	@FXML
	private TableColumn<LivroVO, String> colunaTitulo;
	@FXML
	private TableColumn<LivroVO, String> colunaGenero;
	@FXML
	private TableColumn<LivroVO, String> colunaNumeroPaginas;
	@FXML
	private TableColumn<LivroVO, Integer> colunaQuantidade;
	@FXML
	private TableColumn<LivroVO, Calendar> colunaValorAluguel;

	ObservableList<LivroVO> listaDeLivros = FXCollections.observableArrayList();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		loadEmprestimo();

		LivroBO livroBO = new LivroBO();

		listaDeLivros.addAll(livroBO.listar());
		tabelaLivros.setItems(listaDeLivros);
		tabelaLivros.getItems().stream().forEach(doc -> System.out.println(doc.toString()));
	}

	private void loadEmprestimo() {
		colunaTitulo.setCellValueFactory(new PropertyValueFactory<>("titulo"));
		colunaGenero.setCellValueFactory(new PropertyValueFactory<>("genero"));
		colunaNumeroPaginas.setCellValueFactory(new PropertyValueFactory<>("numeroPaginas"));
		colunaQuantidade.setCellValueFactory(new PropertyValueFactory<>("numeroExemplares"));
		colunaValorAluguel.setCellValueFactory(new PropertyValueFactory<>("valorAluguel"));
	}

	@FXML
	public void cadastrarLivro() {

	}
}
