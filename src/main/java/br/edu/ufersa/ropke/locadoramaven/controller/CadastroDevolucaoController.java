package br.edu.ufersa.ropke.locadoramaven.controller;

import java.util.ArrayList;

import br.edu.ufersa.ropke.locadoramaven.exception.InvalidParameterException;
import br.edu.ufersa.ropke.locadoramaven.model.BO.ClienteBO;
import br.edu.ufersa.ropke.locadoramaven.model.BO.DiscoBO;
import br.edu.ufersa.ropke.locadoramaven.model.BO.EmprestimoBO;
import br.edu.ufersa.ropke.locadoramaven.model.BO.LivroBO;
import br.edu.ufersa.ropke.locadoramaven.model.VO.ClienteVO;
import br.edu.ufersa.ropke.locadoramaven.model.VO.DiscoVO;
import br.edu.ufersa.ropke.locadoramaven.model.VO.EmprestavelVO;
import br.edu.ufersa.ropke.locadoramaven.model.VO.EmprestimoVO;
import br.edu.ufersa.ropke.locadoramaven.model.VO.LivroVO;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class CadastroDevolucaoController {
	@FXML
	private Label dadosIncompletos;
	@FXML
	private Label dadosIncorretos;
	@FXML
	private TextField cliente;
	@FXML
	private TextField emprestavel;
	@FXML
	private TextField quantidadeEmprestavel;

	public boolean cadastrarDevolucaoSuper() {
		EmprestimoBO emprestimoBO = new EmprestimoBO();

		String stringCliente = cliente.getText();
		String stringEmprestavel = emprestavel.getText();
		String stringQuantidadeEmprestavel = quantidadeEmprestavel.getText();

		int intQuantidadeEmprestavel;

		try {
			intQuantidadeEmprestavel = Integer.parseInt(stringQuantidadeEmprestavel);

			ClienteBO clienteBO = new ClienteBO();
			ClienteVO clienteVO = clienteBO.pesquisarCpf(stringCliente);

			if (clienteVO != null) {
				LivroBO livroBO = new LivroBO();
				DiscoBO discoBO = new DiscoBO();

				ArrayList<LivroVO> livros = livroBO.pesquisarTitulo(stringEmprestavel);
				ArrayList<DiscoVO> discos = discoBO.pesquisarTitulo(stringEmprestavel);
				ArrayList<EmprestavelVO> emprestaveis = new ArrayList<EmprestavelVO>();
				ArrayList<Integer> quantidades = new ArrayList<Integer>();
				ArrayList<EmprestimoVO> emprestimos = emprestimoBO.listar();
				ArrayList<EmprestimoVO> emprestimosValidos = new ArrayList<EmprestimoVO>();

				int numeroEmprestimos = emprestimos.size();

				// Obtém os empréstimos válidos
				for (int i = 0; i < numeroEmprestimos; i++) {
					if (emprestimos.get(i).getCliente().getCpf().equals(clienteVO.getCpf())) {
						emprestimosValidos.add(emprestimos.get(i));
					}
				}

				emprestaveis.addAll(livros);
				emprestaveis.addAll(discos);
				quantidades.add(intQuantidadeEmprestavel);

				emprestimoBO.devolver(emprestimosValidos.get(0), emprestaveis, quantidades);
				emprestimoBO.alterar(emprestimosValidos.get(0));

				dadosIncompletos.setVisible(false);
				dadosIncorretos.setVisible(false);

				return true;
			} else {
				dadosIncompletos.setVisible(false);
				dadosIncorretos.setVisible(true);
			}
		} catch (NumberFormatException e) {
			dadosIncompletos.setVisible(false);
			dadosIncorretos.setVisible(true);
		} catch (InvalidParameterException e) {
			dadosIncompletos.setVisible(true);
			dadosIncorretos.setVisible(false);
		}

		return false;
	}
}
