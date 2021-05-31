package br.edu.ufersa.ropke.locadoramaven.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;

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
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class CadastroEmprestimoController {
	@FXML
	private Label emprestimoJaCadastrado;
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
		Calendar calendar = Calendar.getInstance();

		calendar.set(localDate.getYear(), localDate.getMonthValue() - 1, localDate.getDayOfMonth(), 23, 59, 59);

		return calendar;
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

			EmprestimoVO emprestimoVO = new EmprestimoVO();
			LivroBO livroBO = new LivroBO();
			DiscoBO discoBO = new DiscoBO();
			ClienteBO clienteBO = new ClienteBO();
			ClienteVO clienteVO = clienteBO.pesquisarCpf(stringCliente);

			ArrayList<LivroVO> livros = livroBO.pesquisarTitulo(stringEmprestavel);
			ArrayList<DiscoVO> discos = discoBO.pesquisarTitulo(stringEmprestavel);

			ArrayList<Calendar> datas = new ArrayList<Calendar>();
			ArrayList<EmprestavelVO> emprestaveis = new ArrayList<EmprestavelVO>();
			ArrayList<Integer> quantidades = new ArrayList<Integer>();

			datas.add(calendarDataDevolucao);
			emprestaveis.addAll(livros);
			emprestaveis.addAll(discos);
			quantidades.add(intQuantidadeEmprestavel);

			emprestimoBO.alugar(emprestimoVO, datas, emprestaveis, quantidades);
			emprestimoVO.setCliente(clienteVO);
			emprestimoBO.cadastrar(emprestimoVO);

			emprestimoJaCadastrado.setVisible(false);
			dadosIncompletos.setVisible(false);
			dadosIncorretos.setVisible(false);

			return true;
		} catch (NumberFormatException e) {
			emprestimoJaCadastrado.setVisible(false);
			dadosIncompletos.setVisible(false);
			dadosIncorretos.setVisible(true);
		} catch (FoundException e) {
			emprestimoJaCadastrado.setVisible(true);
			dadosIncompletos.setVisible(false);
			dadosIncorretos.setVisible(false);
		} catch (InvalidParameterException e) {
			emprestimoJaCadastrado.setVisible(false);
			dadosIncompletos.setVisible(true);
			dadosIncorretos.setVisible(false);
		}

		return false;
	}
}
