package br.edu.ufersa.ropke.locadoramaven.controller;

import br.edu.ufersa.ropke.locadoramaven.exception.FoundException;
import br.edu.ufersa.ropke.locadoramaven.exception.InvalidParameterException;
import br.edu.ufersa.ropke.locadoramaven.model.BO.LivroBO;
import br.edu.ufersa.ropke.locadoramaven.model.VO.LivroVO;
import br.edu.ufersa.ropke.locadoramaven.view.View;
import br.edu.ufersa.ropke.locadoramaven.view.ViewSwitcher;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class CadastroLivroController {
	@FXML
	private Label livroJaCadastrado;
	@FXML
	private Label dadosIncompletos;
	@FXML
	private Label dadosIncorretos;
	@FXML
	private TextField titulo;
	@FXML
	private TextField genero;
	@FXML
	private TextField anoLancamento;
	@FXML
	private TextField numeroPaginas;
	@FXML
	private TextField numeroExemplares;
	@FXML
	private TextField numeroEmprestimos;
	@FXML
	private TextField numeroDiasAlugado;
	@FXML
	private TextField valorAluguel;

	@FXML
	public void cadastrar() {
		LivroBO livroBO = new LivroBO();

		String stringTitulo = titulo.getText();
		String stringGenero = genero.getText();
		String stringAnoLancamento = anoLancamento.getText();
		String stringNumeroPaginas = numeroPaginas.getText();
		String stringNumeroExemplares = numeroExemplares.getText();
		String stringNumeroEmprestimos = numeroEmprestimos.getText();
		String stringNumeroDiasAlugado = numeroDiasAlugado.getText();
		String stringValorAluguel = valorAluguel.getText();

		int intAnoLancamento;
		int intNumeroPaginas;
		int intNumeroExemplares = 0;
		int intNumeroEmprestimos = 0;
		int intNumeroDiasAlugado = 0;
		float floatValorAluguel = 0;

		try {
			intAnoLancamento = Integer.parseInt(stringAnoLancamento);
			intNumeroPaginas = Integer.parseInt(stringNumeroPaginas);

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

			// titulo, genero, numeroPaginas, 'numeroExemplares', 'numeroEmprestimos',
			// 'numeroDiasAlugado', anoLancamento, valorAluguel
			LivroVO livroVO = new LivroVO(stringTitulo, stringGenero, intNumeroPaginas, intNumeroExemplares,
					intNumeroEmprestimos, intNumeroDiasAlugado, intAnoLancamento, floatValorAluguel);

			livroBO.cadastrar(livroVO);

			livroJaCadastrado.setVisible(false);
			dadosIncompletos.setVisible(false);
			dadosIncorretos.setVisible(false);
			ViewSwitcher.switchTo(View.LIVRO);
		} catch (NumberFormatException e) {
			livroJaCadastrado.setVisible(false);
			dadosIncompletos.setVisible(false);
			dadosIncorretos.setVisible(true);
		} catch (FoundException e) {
			livroJaCadastrado.setVisible(true);
			dadosIncompletos.setVisible(false);
			dadosIncorretos.setVisible(false);
		} catch (InvalidParameterException e) {
			livroJaCadastrado.setVisible(false);
			dadosIncompletos.setVisible(true);
			dadosIncorretos.setVisible(false);
		}
	}

	@FXML
	public void cancelar() {
		ViewSwitcher.switchTo(View.LIVRO);
	}
}
