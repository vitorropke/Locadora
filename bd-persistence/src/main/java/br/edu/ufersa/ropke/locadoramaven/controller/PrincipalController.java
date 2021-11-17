package br.edu.ufersa.ropke.locadoramaven.controller;

import java.net.URL;
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
import br.edu.ufersa.ropke.locadoramaven.model.VO.ObjetoEmprestadoVO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
	private TableColumn<EmprestimoVO, Calendar> colunaDataEmprestimo;
	@FXML
	private TableColumn<EmprestimoVO, ClienteVO> colunaCliente;
	@FXML
	private TableColumn<EmprestimoVO, ObjetoEmprestadoVO> colunaObjetos;

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
		colunaCliente.setCellValueFactory(new PropertyValueFactory<>("cliente"));
		colunaDataEmprestimo.setCellValueFactory(new PropertyValueFactory<>("dataOperacao"));
		colunaObjetos.setCellValueFactory(new PropertyValueFactory<>("objetos"));
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
					if ((emprestimos.get(i).getObjetos().get(k).getObjeto().getTitulo()
							.equals(emprestaveis.get(j).getTitulo()))
							|| ((emprestimos.get(i).getObjetos().get(k).getObjeto().getAnoLancamento()) == (emprestaveis
									.get(j).getAnoLancamento()))) {
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
