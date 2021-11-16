package br.edu.ufersa.ropke.locadoramaven.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.edu.ufersa.ropke.locadoramaven.exception.FoundException;
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
import br.edu.ufersa.ropke.locadoramaven.model.VO.ObjetoEmprestadoVO;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class CadastroEmprestimoController {
	@FXML
	private Label jaCadastrado;
	@FXML
	private Label dadosIncompletos;
	@FXML
	private Label dadosIncorretos;
	@FXML
	private TextField cliente;
	@FXML
	private DatePicker dataDevolucao;
	@FXML
	private TextField emprestavel;
	@FXML
	private TextField quantidadeEmprestavel;

	public Calendar localDateToCalendar(DatePicker data) {
		LocalDate localDate = data.getValue();

		if (localDate != null) {
			Calendar calendar = Calendar.getInstance();

			calendar.set(localDate.getYear(), localDate.getMonthValue() - 1, localDate.getDayOfMonth(), 23, 59, 59);

			return calendar;
		} else {
			return null;
		}
	}

	public boolean cadastrarEmprestimoSuper() {
		EmprestimoBO emprestimoBO = new EmprestimoBO();

		String stringCliente = cliente.getText();
		Calendar calendarDataDevolucao = localDateToCalendar(dataDevolucao);
		String stringEmprestavel = emprestavel.getText();
		String stringQuantidadeEmprestavel = quantidadeEmprestavel.getText();

		int intQuantidadeEmprestavel;

		try {
			intQuantidadeEmprestavel = Integer.parseInt(stringQuantidadeEmprestavel);

			LivroBO livroBO = new LivroBO();
			DiscoBO discoBO = new DiscoBO();
			ClienteBO clienteBO = new ClienteBO();
			ClienteVO clienteVO;

			List<ClienteVO> clientes = clienteBO.pesquisarNome(stringCliente);
			int quantidadeClientes = clientes.size();

			if (quantidadeClientes == 1) {
				clienteVO = clientes.get(0);
			} else {
				clienteVO = clienteBO.pesquisarCpf(stringCliente);
			}

			List<LivroVO> livros = livroBO.pesquisarTitulo(stringEmprestavel);
			List<DiscoVO> discos = discoBO.pesquisarTitulo(stringEmprestavel);
			List<Calendar> datas = new ArrayList<Calendar>();
			List<EmprestavelVO> emprestaveis = new ArrayList<EmprestavelVO>();
			List<Integer> quantidades = new ArrayList<Integer>();

			datas.add(calendarDataDevolucao);
			emprestaveis.addAll(livros);
			emprestaveis.addAll(discos);
			quantidades.add(intQuantidadeEmprestavel);

			List<ObjetoEmprestadoVO> objetos = new ArrayList<ObjetoEmprestadoVO>();

			for (int i = 0; i < datas.size(); i++) {
				objetos.add(new ObjetoEmprestadoVO(livros.get(i), datas.get(i), quantidades.get(i)));
			}

			EmprestimoVO emprestimoVO = new EmprestimoVO(clienteVO, objetos);
			emprestimoBO.cadastrar(emprestimoVO);

			jaCadastrado.setVisible(false);
			dadosIncompletos.setVisible(false);
			dadosIncorretos.setVisible(false);

			return true;
		} catch (NumberFormatException e) {
			jaCadastrado.setVisible(false);
			dadosIncompletos.setVisible(false);
			dadosIncorretos.setVisible(true);
		} catch (FoundException e) {
			jaCadastrado.setVisible(true);
			dadosIncompletos.setVisible(false);
			dadosIncorretos.setVisible(false);
		} catch (InvalidParameterException e) {
			jaCadastrado.setVisible(false);
			dadosIncompletos.setVisible(true);
			dadosIncorretos.setVisible(false);
		}

		return false;
	}
}
