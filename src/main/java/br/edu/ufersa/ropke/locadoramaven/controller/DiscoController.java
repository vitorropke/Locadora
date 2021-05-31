package br.edu.ufersa.ropke.locadoramaven.controller;

import java.net.URL;
import java.util.ArrayList;
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

	ObservableList<DiscoVO> listaDiscos = FXCollections.observableArrayList();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		loadDiscos();

		DiscoBO discoBO = new DiscoBO();

		listaDiscos.addAll(discoBO.listar());
		tabelaDiscos.setItems(listaDiscos);
		tabelaDiscos.getItems().stream().forEach(doc -> System.out.println(doc.toString()));
	}

	private void loadDiscos() {
		colunaTitulo.setCellValueFactory(new PropertyValueFactory<>("titulo"));
		colunaBanda.setCellValueFactory(new PropertyValueFactory<>("banda"));
		colunaEstilo.setCellValueFactory(new PropertyValueFactory<>("estilo"));
		colunaQuantidade.setCellValueFactory(new PropertyValueFactory<>("numeroExemplares"));
		colunaValorAluguel.setCellValueFactory(new PropertyValueFactory<>("valorAluguel"));
	}

	@FXML
	public void pesquisar() {
		String stringPesquisaEmprestimo = pesquisaDisco.getText();

		DiscoBO discoBO = new DiscoBO();
		DiscoVO discoAtual = new DiscoVO();

		ArrayList<DiscoVO> discos = discoBO.listar();
		ArrayList<DiscoVO> discosValidos = new ArrayList<DiscoVO>();

		int numeroDiscos = discos.size();
		int intPesquisaEmprestimo = 0;
		boolean isNumero = false;

		try {
			intPesquisaEmprestimo = Integer.parseInt(stringPesquisaEmprestimo);
			isNumero = true;
		} catch (NumberFormatException e) {
		}

		for (int i = 0; i < numeroDiscos; i++) {
			discoAtual = discos.get(i);

			if ((discoAtual.getTitulo().contains(stringPesquisaEmprestimo))
					|| (discoAtual.getBanda().contains(stringPesquisaEmprestimo))
					|| (discoAtual.getEstilo().contains(stringPesquisaEmprestimo))) {
				discosValidos.add(discoAtual);
			}

			if (isNumero) {
				if ((discoAtual.getAnoLancamento()) == (intPesquisaEmprestimo)) {
					discosValidos.add(discoAtual);
				}
			}
		}

		listaDiscos.clear();
		listaDiscos.addAll(discosValidos);
		tabelaDiscos.setItems(listaDiscos);
		tabelaDiscos.getItems().stream().forEach(doc -> System.out.println(doc.toString()));
	}

	@FXML
	public void atualizar() {
		DiscoBO discoBO = new DiscoBO();

		listaDiscos.clear();
		listaDiscos.addAll(discoBO.listar());
		tabelaDiscos.setItems(listaDiscos);
		tabelaDiscos.getItems().stream().forEach(doc -> System.out.println(doc.toString()));
	}

	@FXML
	public void irParaTelaPrincipal() {
		ViewSwitcher.switchTo(View.PRINCIPAL_GERENTE);
	}

	@FXML
	public void cadastrarDisco() {
		ViewSwitcher.switchTo(View.CADASTRO_DISCO);
	}

	@FXML
	public void acessarLivros() {
		ViewSwitcher.switchTo(View.LIVRO);
	}

	@FXML
	public void acessarClientes() {
		ViewSwitcher.switchTo(View.CLIENTE_GERENTE);
	}
}
