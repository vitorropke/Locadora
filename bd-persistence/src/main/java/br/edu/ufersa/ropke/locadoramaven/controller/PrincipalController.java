package br.edu.ufersa.ropke.locadoramaven.controller;

import java.net.URL;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.ResourceBundle;

import br.edu.ufersa.ropke.locadoramaven.model.BO.ClienteBO;
import br.edu.ufersa.ropke.locadoramaven.model.BO.DiscoBO;
import br.edu.ufersa.ropke.locadoramaven.model.BO.EmprestimoBO;
import br.edu.ufersa.ropke.locadoramaven.model.BO.LivroBO;
import br.edu.ufersa.ropke.locadoramaven.model.VO.ClienteVO;
import br.edu.ufersa.ropke.locadoramaven.model.VO.DiscoVO;
import br.edu.ufersa.ropke.locadoramaven.model.VO.EmprestavelVO;
import br.edu.ufersa.ropke.locadoramaven.model.VO.EmprestimoVO;
import br.edu.ufersa.ropke.locadoramaven.model.VO.LivroVO;
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
		colunaDataDevolucao.setCellValueFactory(new PropertyValueFactory<>("dataDevolucaoEmprestavel"));
	}

	@FXML
	public void pesquisar() {
		String stringPesquisaEmprestimo = pesquisaEmprestimo.getText();

		EmprestimoBO emprestimoBO = new EmprestimoBO();
		ClienteBO clienteBO = new ClienteBO();
		LivroBO livroBO = new LivroBO();
		DiscoBO discoBO = new DiscoBO();
		ClienteVO cliente = clienteBO.pesquisarCpf(stringPesquisaEmprestimo);

		List<EmprestimoVO> emprestimos = emprestimoBO.listar();
		List<EmprestavelVO> emprestaveis = new ArrayList<EmprestavelVO>();
		List<EmprestimoVO> emprestimosValidos = new ArrayList<EmprestimoVO>();
		List<ClienteVO> clientes = clienteBO.pesquisarNome(stringPesquisaEmprestimo);
		List<LivroVO> livros = livroBO.pesquisarTitulo(stringPesquisaEmprestimo);
		List<DiscoVO> discos = discoBO.pesquisarTitulo(stringPesquisaEmprestimo);

		if (cliente != null) {
			clientes.add(cliente);
		}

		try {
			int intPesquisaEmprestimo = Integer.parseInt(stringPesquisaEmprestimo);
			livros.addAll(livroBO.pesquisarAnoLancamento(intPesquisaEmprestimo));
			discos.addAll(discoBO.pesquisarAnoLancamento(intPesquisaEmprestimo));
		} catch (NumberFormatException e) {
		}

		emprestaveis.addAll(livros);
		emprestaveis.addAll(discos);

		int numeroEmprestimos = emprestimos.size();
		int numeroClientes = clientes.size();
		int numeroEmprestaveis = emprestaveis.size();

		for (int i = 0; i < numeroEmprestimos; i++) {
			for (int j = 0; j < numeroClientes; j++) {
				if (emprestimos.get(i).getCliente().getCpf().equals(clientes.get(j).getCpf())) {
					emprestimosValidos.add(emprestimos.get(i));
				}
			}

			for (int j = 0; j < numeroEmprestaveis; j++) {
				int numeroEmprestaveisEmprestimo = emprestimos.get(i).getObjetos().size();

				for (int k = 0; k < numeroEmprestaveisEmprestimo; k++) {
					if ((emprestimos.get(i).getObjetos().get(k).getObjeto().getTitulo().equals(emprestaveis.get(j).getTitulo()))
							|| ((emprestimos.get(i).getObjetos().get(k).getObjeto().getAnoLancamento()) == (emprestaveis.get(j)
									.getAnoLancamento()))) {
						emprestimosValidos.add(emprestimos.get(i));
					}
				}
			}
		}

		listaEmprestimos.clear();
		listaEmprestimos.addAll(emprestimosValidos);
		tabelaEmprestimos.setItems(listaEmprestimos);
		tabelaEmprestimos.getItems().stream().forEach(doc -> System.out.println(doc.toString()));
	}

	@FXML
	public void atualizar() {
		EmprestimoBO emprestimoBO = new EmprestimoBO();

		listaEmprestimos.clear();
		listaEmprestimos.addAll(emprestimoBO.listar());
		tabelaEmprestimos.setItems(listaEmprestimos);
		tabelaEmprestimos.getItems().stream().forEach(doc -> System.out.println(doc.toString()));
	}
}
