package br.edu.ufersa.ropke.locadoramaven.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import br.edu.ufersa.ropke.locadoramaven.model.BO.LivroBO;
import br.edu.ufersa.ropke.locadoramaven.model.VO.LivroVO;
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
	private TableColumn<LivroVO, Float> colunaValorAluguel;

	ObservableList<LivroVO> listaLivros = FXCollections.observableArrayList();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		loadLivros();

		LivroBO livroBO = new LivroBO();

		listaLivros.addAll(livroBO.listar());
		tabelaLivros.setItems(listaLivros);
		tabelaLivros.getItems().stream().forEach(doc -> System.out.println(doc.toString()));
	}

	private void loadLivros() {
		colunaTitulo.setCellValueFactory(new PropertyValueFactory<>("titulo"));
		colunaGenero.setCellValueFactory(new PropertyValueFactory<>("genero"));
		colunaNumeroPaginas.setCellValueFactory(new PropertyValueFactory<>("numeroPaginas"));
		colunaQuantidade.setCellValueFactory(new PropertyValueFactory<>("numeroExemplares"));
		colunaValorAluguel.setCellValueFactory(new PropertyValueFactory<>("valorAluguel"));
	}

	@FXML
	public void pesquisar() {
		String stringPesquisaLivro = pesquisaLivro.getText();

		LivroBO livroBO = new LivroBO();
		LivroVO livroAtual = new LivroVO();

		ArrayList<LivroVO> livros = livroBO.listar();
		ArrayList<LivroVO> livrosValidos = new ArrayList<LivroVO>();

		int numeroLivros = livros.size();
		int intPesquisaEmprestimo = 0;
		boolean isNumero = false;

		try {
			intPesquisaEmprestimo = Integer.parseInt(stringPesquisaLivro);
			isNumero = true;
		} catch (NumberFormatException e) {
		}

		for (int i = 0; i < numeroLivros; i++) {
			livroAtual = livros.get(i);

			if ((livroAtual.getTitulo().contains(stringPesquisaLivro))
					|| (livroAtual.getGenero().contains(stringPesquisaLivro))) {
				livrosValidos.add(livroAtual);
			}

			if (isNumero) {
				if ((livroAtual.getAnoLancamento()) == (intPesquisaEmprestimo)) {
					livrosValidos.add(livroAtual);
				}
			}
		}

		listaLivros.clear();
		listaLivros.addAll(livrosValidos);
		tabelaLivros.setItems(listaLivros);
		tabelaLivros.getItems().stream().forEach(doc -> System.out.println(doc.toString()));
	}

	@FXML
	public void atualizar() {
		LivroBO livroBO = new LivroBO();

		listaLivros.clear();
		listaLivros.addAll(livroBO.listar());
		tabelaLivros.setItems(listaLivros);
		tabelaLivros.getItems().stream().forEach(doc -> System.out.println(doc.toString()));
	}

	@FXML
	public void irParaTelaPrincipal() {
		ViewSwitcher.switchTo(View.PRINCIPAL_GERENTE);
	}

	@FXML
	public void cadastrarLivro() {
		ViewSwitcher.switchTo(View.CADASTRO_LIVRO);
	}

	@FXML
	public void acessarDiscos() {
		ViewSwitcher.switchTo(View.DISCO);
	}

	@FXML
	public void acessarClientes() {
		ViewSwitcher.switchTo(View.CLIENTE_GERENTE);
	}
}
