package br.edu.ufersa.ropke.model.VO;

public class DiscoVO extends EmprestavelVO {
	private static final long serialVersionUID = 1L;

	// Variáveis
	private String banda;
	private String estilo;

	// Construtores
	public DiscoVO() {
	}

	public DiscoVO(String titulo, String banda, String estilo, int anoLancamento, float valorAluguel) {
		super(titulo, anoLancamento, valorAluguel);
		// TODO Auto-generated constructor stub
		this.banda = banda;
		this.estilo = estilo;
	}

	public DiscoVO(String titulo, String banda, String estilo, int numeroExemplares, int anoLancamento,
			float valorAluguel) {
		super(titulo, numeroExemplares, anoLancamento, valorAluguel);
		// TODO Auto-generated constructor stub
		this.banda = banda;
		this.estilo = estilo;
	}

	public DiscoVO(String titulo, String banda, String estilo, int numeroExemplares, int numeroEmprestimos,
			int numeroDiasAlugado, int anoLancamento, float valorAluguel) {
		super(titulo, numeroExemplares, numeroEmprestimos, numeroDiasAlugado, anoLancamento, valorAluguel);
		// TODO Auto-generated constructor stub
		this.banda = banda;
		this.estilo = estilo;
	}

	// toString
	@Override
	public String toString() {
		String disco = "";

		disco = super.toString();
		disco += "\nBanda:                  " + banda;
		disco += "\nEstilo:                 " + estilo + "\n";

		return disco;
	}

	// Getters e setters
	public String getBanda() {
		return banda;
	}

	public void setBanda(String banda) {
		if ((banda != null) && (banda != "")) {
			this.banda = banda;
		} else {
			System.out.println("Banda nao pode ser vazia!");
		}
	}

	public String getEstilo() {
		return estilo;
	}

	public void setEstilo(String estilo) {
		if ((estilo != null) && (estilo != "")) {
			this.estilo = estilo;
		} else {
			System.out.println("Estilo nao pode ser vazio!");
		}
	}
}
