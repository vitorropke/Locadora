package br.edu.ufersa.ropke.locadoramaven.controller;

import java.util.List;

import br.edu.ufersa.ropke.locadoramaven.exception.InvalidParameterException;
import br.edu.ufersa.ropke.locadoramaven.exception.NotFoundException;
import br.edu.ufersa.ropke.locadoramaven.model.BO.LivroBO;
import br.edu.ufersa.ropke.locadoramaven.model.VO.LivroVO;
import br.edu.ufersa.ropke.locadoramaven.view.View;
import br.edu.ufersa.ropke.locadoramaven.view.ViewSwitcher;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class EditaLivroController {
	@FXML
	private Label naoEncontrado;
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
	
	private long idLivro;
	private long idEmprestavel;

	@FXML
	public void pesquisar() {
		String stringPesquisaLivro = titulo.getText();

		LivroBO livroBO = new LivroBO();
		List<LivroVO> livros = livroBO.pesquisarTitulo(stringPesquisaLivro);
		int quantidadeLivros = livros.size();

		if (quantidadeLivros == 1) {
			LivroVO livroAtual = livros.get(0);
			idLivro = livroAtual.getId();
			idEmprestavel = livroAtual.getIdEmprestavel();

			titulo.setText(livroAtual.getTitulo());
			genero.setText(livroAtual.getGenero());
			anoLancamento.setText(String.valueOf(livroAtual.getAnoLancamento()));
			numeroPaginas.setText(String.valueOf(livroAtual.getNumeroPaginas()));
			numeroExemplares.setText(String.valueOf(livroAtual.getNumeroExemplares()));
			numeroEmprestimos.setText(String.valueOf(livroAtual.getNumeroEmprestimos()));
			numeroDiasAlugado.setText(String.valueOf(livroAtual.getNumeroDiasAlugado()));
			valorAluguel.setText(String.valueOf(livroAtual.getValorAluguel()));

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
			livroVO.setId(idLivro);
			livroVO.setIdEmprestavel(idEmprestavel);

			livroBO.alterar(livroVO);

			naoEncontrado.setVisible(false);
			dadosIncompletos.setVisible(false);
			dadosIncorretos.setVisible(false);
			ViewSwitcher.switchTo(View.LIVRO);
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
		String stringPesquisaLivro = titulo.getText();

		LivroBO livroBO = new LivroBO();
		List<LivroVO> livros = livroBO.pesquisarTitulo(stringPesquisaLivro);
		int quantidadeLivros = livros.size();

		if (quantidadeLivros == 1) {
			LivroVO livroAtual = livros.get(0);

			livroBO.deletar(livroAtual);

			naoEncontrado.setVisible(false);
			dadosIncompletos.setVisible(false);
			dadosIncorretos.setVisible(false);
			ViewSwitcher.switchTo(View.LIVRO);
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
		ViewSwitcher.switchTo(View.LIVRO);
	}
}
