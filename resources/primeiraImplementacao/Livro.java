import java.util.Calendar;

class Livro {
	// Variáveis
	private String titulo, genero;
	private int quantidadePaginas, quantidadeExemplares, numeroEmprestimos, diasAlugado, anoLancamento;
	private float valorAluguel;
	
	/*
	// Construtor
	public Livro(String titulo, String genero, int quantidadePaginas, int quantidadeExemplares, int anoLancamento, float valorAluguel) {
		this.titulo = titulo;
		this.genero = genero;
		this.quantidadePaginas = quantidadePaginas;
		this.quantidadeExemplares = quantidadeExemplares;
		this.numeroEmprestimos = 0;
		this.diasAlugado = 0;
		this.anoLancamento = anoLancamento;
		this.valorAluguel = valorAluguel;
	}
	*/
	
	// Getters e Setters
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		if (titulo != null && titulo != "") {
			this.titulo = titulo;
		} else {
			System.out.println("Titulo nao pode ser vazio!");
		}
	}
	
	public String getGenero() {
		return genero;
	}
	public void setGenero(String genero) {
		if (genero != null && genero != "") {
			this.genero = genero;
		} else {
			System.out.println("Genero nao pode ser vazio!");
		}
	}
	
	public int getQuantidadePaginas() {
		return quantidadePaginas;
	}
	public void setQuantidadePaginas(int quantidadePaginas) {
		if (quantidadePaginas > 0) {
			this.quantidadePaginas = quantidadePaginas;
		} else {
			System.out.println("Quantidade de paginas nao pode ser 0!");
		}
	}
	
	public int getQuantidadeExemplares() {
		return quantidadeExemplares;
	}
	public void setQuantidadeExemplares(int quantidadeExemplares) {
		if (quantidadeExemplares >= 0) {
			this.quantidadeExemplares = quantidadeExemplares;
		} else {
			System.out.println("Quantidade de exemplares nao pode ser menor que 0!");
		}
	}

	public int getNumeroEmprestimos() {
		return numeroEmprestimos;
	}
	public void setNumeroEmprestimos(int numeroEmprestimos) {
		if (numeroEmprestimos >= 0) {
			this.numeroEmprestimos = numeroEmprestimos;
		} else {
			System.out.println("Numero de emprestimos nao pode ser menor que 0!");
		}
	}

	public int getDiasAlugado() {
		return diasAlugado;
	}
	public void setDiasAlugado(int diasAlugado) {
		if (diasAlugado >= 0) {
			this.diasAlugado = diasAlugado;
		} else {
			System.out.println("Dias alugado nao pode ser menor que 0!");
		}
	}

	public int getAnoLancamento() {
		return anoLancamento;
	}
	public void setAnoLancamento(int anoLancamento) {
		int anoAtual = Calendar.getInstance().get(Calendar.YEAR);

		if (anoLancamento <= anoAtual) {
			this.anoLancamento = anoLancamento;
		} else {
			System.out.println("Ano de lancamento nao pode ser no futuro!");
		}
	}
	
	/*
	// Métodos
	public void cadastrar(Livro livro) {
		// adiciona o livro ao "banco de dados"
	}
	
	public void alterar(Livro livro) {
		// altera os atributos do livro no "banco de dados"
	}
	
	public void deletar(Livro livro) {
		// apaga o livro do "banco de dados"

		livro.titulo = null;
		livro.genero = null;
		livro.quantidadePaginas = null;
		livro.quantidadeExemplares = null;
		livro.numeroEmprestimos = null;
		livro.diasAlugado = null;
		livro.anoLancamento = null;
		livro.valorAluguel = null;
	}
	
	public Livro pesquisarTitulo(String titulo) {
		return Livro;
	}
	
	public Livro[] pesquisarGenero(String genero) {
		return Livro;
	}
	
	public Livro[] pesquisarAno(Calendar anoLancamento) {
		return Livro;
	}
	*/
}
