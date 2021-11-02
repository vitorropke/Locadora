package br.edu.ufersa.ropke.locadoramaven.model.VO;

import java.util.Calendar;

public abstract class EmprestavelVO {
	private static long idCount = 0;
	private long id;
	private String titulo;
	private int numeroExemplares;
	private int numeroEmprestimos;
	private int numeroDiasAlugado;
	private int anoLancamento;
	private float valorAluguel;

	public EmprestavelVO(String titulo, int numeroExemplares, int numeroEmprestimos, int numeroDiasAlugado,
			int anoLancamento, float valorAluguel) {
		id = idCount++;
		setTitulo(titulo);
		setNumeroExemplares(numeroExemplares);
		setNumeroEmprestimos(numeroEmprestimos);
		setNumeroDiasAlugado(numeroDiasAlugado);
		setAnoLancamento(anoLancamento);
		setValorAluguel(valorAluguel);
	}

	@Override
	public String toString() {
		String emprestavel;
		String valorAluguelDoisDecimais = String.format("%.02f", valorAluguel);

		emprestavel = "\nID emprestavel:\t\t\t" + id;
		emprestavel += "\nTitulo:\t\t\t\t" + titulo;
		emprestavel += "\nNumero de exemplares:\t\t" + numeroExemplares;
		emprestavel += "\nNumero de emprestimos:\t\t" + numeroEmprestimos;
		emprestavel += "\nNumero de dias alugado:\t\t" + numeroDiasAlugado;
		emprestavel += "\nAno de lancamento:\t\t" + anoLancamento;
		emprestavel += "\nValor do aluguel:\t\t" + "R$" + valorAluguelDoisDecimais + '\n';

		return emprestavel;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		if (id >= 0) {
			this.id = id;
		} else {
			System.out.println("ID negativo");
		}
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		if ((titulo != null) && !titulo.isBlank()) {
			this.titulo = titulo.trim();
		} else {
			System.out.println("Titulo vazio");
		}
	}

	public int getNumeroExemplares() {
		return numeroExemplares;
	}

	public void setNumeroExemplares(int numeroExemplares) {
		if (numeroExemplares >= 0) {
			this.numeroExemplares = numeroExemplares;
		} else {
			System.out.println("Numero de exemplares negativo");
		}
	}

	public int getNumeroEmprestimos() {
		return numeroEmprestimos;
	}

	public void setNumeroEmprestimos(int numeroEmprestimos) {
		if (numeroEmprestimos >= 0) {
			this.numeroEmprestimos = numeroEmprestimos;
		} else {
			System.out.println("Numero de emprestimos negativo");
		}
	}

	public int getNumeroDiasAlugado() {
		return numeroDiasAlugado;
	}

	public void setNumeroDiasAlugado(int numeroDiasAlugado) {
		if (numeroDiasAlugado >= 0) {
			this.numeroDiasAlugado = numeroDiasAlugado;
		} else {
			System.out.println("Dias alugado negativo");
		}
	}

	public int getAnoLancamento() {
		return anoLancamento;
	}

	public void setAnoLancamento(int anoLancamento) {
		if (anoLancamento <= Calendar.getInstance().get(Calendar.YEAR)) {
			this.anoLancamento = anoLancamento;
		} else {
			System.out.println("Ano de lancamento no futuro");
		}
	}

	public float getValorAluguel() {
		return valorAluguel;
	}

	public void setValorAluguel(float valorAluguel) {
		if (valorAluguel >= 0) {
			this.valorAluguel = valorAluguel;
		} else {
			System.out.println("Valor do aluguel negativo");
		}
	}
}
