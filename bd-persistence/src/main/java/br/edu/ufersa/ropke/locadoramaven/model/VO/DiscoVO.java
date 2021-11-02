package br.edu.ufersa.ropke.locadoramaven.model.VO;

public class DiscoVO extends EmprestavelVO {
	private long id;
	private String banda;
	private String estilo;

	public DiscoVO(String titulo, String banda, String estilo, int numeroExemplares, int numeroEmprestimos,
			int numeroDiasAlugado, int anoLancamento, float valorAluguel) {
		super(titulo, numeroExemplares, numeroEmprestimos, numeroDiasAlugado, anoLancamento, valorAluguel);
		setBanda(banda);
		setEstilo(estilo);
	}

	@Override
	public String toString() {
		String disco;

		disco = "\nID disco:\t\t\t" + id;
		disco += "\nBanda:\t\t\t\t" + banda;
		disco += "\nEstilo:\t\t\t\t" + estilo + "\n";
		disco += super.toString();

		return disco;
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

	public String getBanda() {
		return banda;
	}

	public void setBanda(String banda) {
		if ((banda != null) && !banda.isBlank()) {
			this.banda = banda.trim();
		} else {
			System.out.println("Banda vazia");
		}
	}

	public String getEstilo() {
		return estilo;
	}

	public void setEstilo(String estilo) {
		if ((estilo != null) && !estilo.isBlank()) {
			this.estilo = estilo.trim();
		} else {
			System.out.println("Estilo vazio");
		}
	}
}
