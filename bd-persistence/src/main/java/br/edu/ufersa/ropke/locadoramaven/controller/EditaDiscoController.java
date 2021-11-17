package br.edu.ufersa.ropke.locadoramaven.controller;

import java.util.List;

import br.edu.ufersa.ropke.locadoramaven.exception.InvalidParameterException;
import br.edu.ufersa.ropke.locadoramaven.exception.NotFoundException;
import br.edu.ufersa.ropke.locadoramaven.model.BO.DiscoBO;
import br.edu.ufersa.ropke.locadoramaven.model.VO.DiscoVO;
import br.edu.ufersa.ropke.locadoramaven.view.View;
import br.edu.ufersa.ropke.locadoramaven.view.ViewSwitcher;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class EditaDiscoController {
	@FXML
	private Label naoEncontrado;
	@FXML
	private Label dadosIncompletos;
	@FXML
	private Label dadosIncorretos;
	@FXML
	private TextField titulo;
	@FXML
	private TextField banda;
	@FXML
	private TextField estilo;
	@FXML
	private TextField anoLancamento;
	@FXML
	private TextField numeroExemplares;
	@FXML
	private TextField numeroEmprestimos;
	@FXML
	private TextField numeroDiasAlugado;
	@FXML
	private TextField valorAluguel;

	private long idDisco;
	private long idEmprestavel;

	@FXML
	public void pesquisar() {
		String stringPesquisaDisco = titulo.getText();

		DiscoBO discoBO = new DiscoBO();
		List<DiscoVO> discos = discoBO.pesquisarTitulo(stringPesquisaDisco);
		int quantidadeDiscos = discos.size();

		if (quantidadeDiscos == 1) {
			DiscoVO discoAtual = discos.get(0);
			idDisco = discoAtual.getId();
			idEmprestavel = discoAtual.getIdEmprestavel();

			banda.setText(discoAtual.getBanda());
			estilo.setText(discoAtual.getEstilo());
			anoLancamento.setText(String.valueOf(discoAtual.getAnoLancamento()));
			numeroExemplares.setText(String.valueOf(discoAtual.getNumeroExemplares()));
			numeroEmprestimos.setText(String.valueOf(discoAtual.getNumeroEmprestimos()));
			numeroDiasAlugado.setText(String.valueOf(discoAtual.getNumeroDiasAlugado()));
			valorAluguel.setText(String.valueOf(discoAtual.getValorAluguel()));

			naoEncontrado.setVisible(false);
			dadosIncompletos.setVisible(false);
			dadosIncorretos.setVisible(false);
		} else {
			naoEncontrado.setVisible(true);
			dadosIncompletos.setVisible(false);
			dadosIncorretos.setVisible(false);
		}
	}

	@FXML
	public void editar() {
		DiscoBO discoBO = new DiscoBO();

		String stringTitulo = titulo.getText();
		String stringBanda = banda.getText();
		String stringEstilo = estilo.getText();
		String stringAnoLancamento = anoLancamento.getText();
		String stringNumeroExemplares = numeroExemplares.getText();
		String stringNumeroEmprestimos = numeroEmprestimos.getText();
		String stringNumeroDiasAlugado = numeroDiasAlugado.getText();
		String stringValorAluguel = valorAluguel.getText();

		int intAnoLancamento;
		int intNumeroExemplares = 0;
		int intNumeroEmprestimos = 0;
		int intNumeroDiasAlugado = 0;
		float floatValorAluguel = 0;

		try {
			intAnoLancamento = Integer.parseInt(stringAnoLancamento);

			if ((stringNumeroExemplares != null) && (!stringNumeroExemplares.isBlank())) {
				intNumeroExemplares = Integer.parseInt(stringNumeroExemplares);
			}
			if ((stringNumeroExemplares != null) && (!stringNumeroExemplares.isBlank())) {
				intNumeroEmprestimos = Integer.parseInt(stringNumeroEmprestimos);
			}
			if ((stringNumeroExemplares != null) && (!stringNumeroExemplares.isBlank())) {
				intNumeroDiasAlugado = Integer.parseInt(stringNumeroDiasAlugado);
			}
			if ((stringNumeroExemplares != null) && (!stringNumeroExemplares.isBlank())) {
				floatValorAluguel = Float.parseFloat(stringValorAluguel);
			}

			// titulo, banda, estilo, 'numeroExemplares', 'numeroEmprestimos',
			// 'numeroDiasAlugado', anoLancamento, valorAluguel
			DiscoVO discoVO = new DiscoVO(stringTitulo, stringBanda, stringEstilo, intNumeroExemplares,
					intNumeroEmprestimos, intNumeroDiasAlugado, intAnoLancamento, floatValorAluguel);
			discoVO.setId(idDisco);
			discoVO.setIdEmprestavel(idEmprestavel);

			discoBO.alterar(discoVO);

			naoEncontrado.setVisible(false);
			dadosIncompletos.setVisible(false);
			dadosIncorretos.setVisible(false);
			ViewSwitcher.switchTo(View.DISCO);
		} catch (NumberFormatException e) {
			naoEncontrado.setVisible(false);
			dadosIncompletos.setVisible(false);
			dadosIncorretos.setVisible(true);
		} catch (NotFoundException e) {
			naoEncontrado.setVisible(true);
			dadosIncompletos.setVisible(false);
			dadosIncorretos.setVisible(false);
		} catch (InvalidParameterException e) {
			naoEncontrado.setVisible(false);
			dadosIncompletos.setVisible(true);
			dadosIncorretos.setVisible(false);
		}
	}

	@FXML
	public void apagar() {
		String stringPesquisaDisco = titulo.getText();

		DiscoBO discoBO = new DiscoBO();
		List<DiscoVO> discos = discoBO.pesquisarTitulo(stringPesquisaDisco);
		int quantidadeDiscos = discos.size();

		if (quantidadeDiscos == 1) {
			DiscoVO discoAtual = discos.get(0);

			discoBO.deletar(discoAtual);

			naoEncontrado.setVisible(false);
			dadosIncompletos.setVisible(false);
			dadosIncorretos.setVisible(false);
			ViewSwitcher.switchTo(View.DISCO);
		} else {
			naoEncontrado.setVisible(true);
			dadosIncompletos.setVisible(false);
			dadosIncorretos.setVisible(false);
		}
	}

	@FXML
	public void cancelar() {
		naoEncontrado.setVisible(false);
		dadosIncompletos.setVisible(false);
		dadosIncorretos.setVisible(false);
		ViewSwitcher.switchTo(View.DISCO);
	}
}
