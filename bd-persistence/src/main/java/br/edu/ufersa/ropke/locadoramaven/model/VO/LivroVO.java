package br.edu.ufersa.ropke.locadoramaven.model.VO;

public class LivroVO extends EmprestavelVO {
	private long id;
	private String genero;
	private int numeroPaginas;

	public LivroVO(String titulo, String genero, int numeroPaginas, int numeroExemplares, int numeroEmprestimos,
			int numeroDiasAlugado, int anoLancamento, float valorAluguel) {
		super(titulo, numeroExemplares, numeroEmprestimos, numeroDiasAlugado, anoLancamento, valorAluguel);
		setGenero(genero);
		setNumeroPaginas(numeroPaginas);
	}

	@Override
	public String toString() {
		String livro;

		livro = "\nID livro:\t\t\t" + id;
		livro += "\nGenero:\t\t\t\t" + genero;
		livro += "\nNumero de paginas:\t\t" + numeroPaginas + "\n";
		livro += super.toString();

		return livro;
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

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		if ((genero != null) && !genero.isBlank()) {
			this.genero = genero.trim();
		} else {
			System.out.println("Genero vazio");
		}
	}

	public int getNumeroPaginas() {
		return numeroPaginas;
	}

	public void setNumeroPaginas(int numeroPaginas) {
		if (numeroPaginas > 0) {
			this.numeroPaginas = numeroPaginas;
		} else {
			System.out.println("Numero de paginas 0 ou negativo");
		}
	}
}
