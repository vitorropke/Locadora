package br.edu.ufersa.ropke.locadoramaven.controller;

import br.edu.ufersa.ropke.locadoramaven.exception.FoundException;
import br.edu.ufersa.ropke.locadoramaven.exception.InvalidParameterException;
import br.edu.ufersa.ropke.locadoramaven.model.BO.DiscoBO;
import br.edu.ufersa.ropke.locadoramaven.model.VO.DiscoVO;
import br.edu.ufersa.ropke.locadoramaven.view.View;
import br.edu.ufersa.ropke.locadoramaven.view.ViewSwitcher;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class CadastroDiscoController {
	@FXML
	private Label discoJaCadastrado;
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

	@FXML
	public void cadastrarDisco() {
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

			discoBO.cadastrar(discoVO);

			discoJaCadastrado.setVisible(false);
			dadosIncompletos.setVisible(false);
			dadosIncorretos.setVisible(false);
			ViewSwitcher.switchTo(View.DISCO);
		} catch (NumberFormatException e) {
			discoJaCadastrado.setVisible(false);
			dadosIncompletos.setVisible(false);
			dadosIncorretos.setVisible(true);
		} catch (FoundException e) {
			discoJaCadastrado.setVisible(true);
			dadosIncompletos.setVisible(false);
			dadosIncorretos.setVisible(false);
		} catch (InvalidParameterException e) {
			discoJaCadastrado.setVisible(false);
			dadosIncompletos.setVisible(true);
			dadosIncorretos.setVisible(false);
		}
	}

	@FXML
	public void cancelarCadastro() {
		ViewSwitcher.switchTo(View.DISCO);
	}
}
