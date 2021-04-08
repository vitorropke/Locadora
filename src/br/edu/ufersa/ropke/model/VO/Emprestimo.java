package br.edu.ufersa.ropke.model.VO;

import java.io.Serializable;
import java.util.Calendar;

public class Emprestimo implements Serializable {
	private static final long serialVersionUID = 1L;

	// Variáveis
	private static float	faturamento = 0;
	private long			idEmprestimo = -1;
	private Calendar		dataEmprestimo;
	private Calendar		dataDevolucao;
	private Cliente			cliente;
	private Livro[]			livro;
	private Disco[]			disco;

	// Construtor
	public Emprestimo() {
		long id = getIdEmprestimo();

		id++;

		setIdEmprestimo(id);
	}

	public String toString() {
		String emprestimo = "";
		String faturamentoDoisDecimais = String.format("%.02f", faturamento);

		emprestimo = "Id:                 " + idEmprestimo;
		emprestimo += "\nFaturamento:        " + "R$" + faturamentoDoisDecimais;
		emprestimo += "\nData de emprestimo: ";

		if (dataEmprestimo != null) {
			emprestimo += dataEmprestimo.getTime();
		} else {
			emprestimo += "Nao definida";
		}

		emprestimo += "\nData de devolucao:  ";

		if (dataDevolucao != null) {
			emprestimo += dataDevolucao.getTime();
		} else {
			emprestimo += "Nao definida";
		}

		emprestimo += "\n\nLivro:              ";

		if (livro != null) {
			for (int x = 0; x < livro.length; x++) {
				emprestimo += "\n\n" + livro[x].toString();
			}
			emprestimo += "\n";
		} else {
			emprestimo += "Sem livros";
		}

		emprestimo += "\nDisco:              ";

		if (disco != null) {
			for (int x = 0; x < disco.length; x++) {
				emprestimo += "\n\n" + disco[x].toString();
			}
			emprestimo += "\n";
		} else {
			emprestimo += "Sem discos";
		}
		
		return emprestimo;
	}

	// Getters e Setters
	public long getIdEmprestimo() {
		return idEmprestimo;
	}
	public void setIdEmprestimo(long idEmprestimo) {
		if (idEmprestimo < 0) {
			System.out.println("Id nao pode ser negativo!");
		} else {
			this.idEmprestimo = idEmprestimo;
		}
	}

	public static float getFaturamento() {
		return Emprestimo.faturamento;
	}
	public static void setFaturamento(float faturamento) {
		Emprestimo.faturamento = faturamento;
	}

	public Calendar getDataEmprestimo() {
		return dataEmprestimo;
	}
	public void setDataEmprestimo(Calendar dataEmprestimo) {
		Calendar dataAtual = Calendar.getInstance();
		// Obtem a data atual

		if (dataEmprestimo.after(dataAtual)) {
			System.out.println("Data de empréstimo nao pode ser no futuro!");
		} else {
			this.dataEmprestimo = dataEmprestimo;
		}
	}
	
	public Calendar getDataDevolucao() {
		return dataDevolucao;
	}
	public void setDataDevolucao(Calendar dataDevolucao) {
		Calendar dataAtual = Calendar.getInstance();
		// Obtem a data atual

		if (dataDevolucao.after(dataAtual)) {
			this.dataDevolucao = dataDevolucao;
		} else {
			System.out.println("Data de devolucao so pode ser no futuro!");
		}
	}

	public Livro[] getLivro() {
		if (this.livro == null) {
			return null;
		}

		Livro[] livros = new Livro[this.livro.length];

		for (int x = 0; x < this.livro.length; x++) {
			livros[x] = this.livro[x];
		}

		return livros;
	}
	public void setLivro(Livro[] livro) {
		this.livro = livro;
	}
	
	public Disco[] getDisco() {
		if (this.disco == null) {
			return null;
		}

		Disco[] discos = new Disco[this.disco.length];

		for (int x = 0; x < this.disco.length; x++) {
			discos[x] = this.disco[x];
		}

		return discos;
	}
	public void setDisco(Disco[] disco) {
		this.disco = disco;
	}
	
	// Métodos
	public void alugarLivro(Livro livro, int quantidade, Cliente cliente, Calendar dataDevolucaoProposta) {
		Calendar dataAtual = Calendar.getInstance();
		// Obtem a data atual

		if (quantidade <= livro.getNumeroExemplares()) {
			// Verifica se existe quantidade suficiente de livros

			// Adiciona o livro
			if (getLivro() != null) {
				// Se já existirem livros

				Livro[] livros = new Livro[(getLivro().length) + 1];
				// Cria o vetor de livros com espaço para o novo livro

				for (int x = 0; x < getLivro().length; x++) {
					livros[x] = getLivro()[x];
					// Coloca os livros existentes no vetor de livros
				}

				livros[getLivro().length] = livro;
				// Coloca o novo livro no final do vetor de livros

				setLivro(livros);
				// Coloca o vetor no atributo 'livro'
			} else {
				// Se o clientes não tiver livros

				Livro[] livros = new Livro[1];
				// Cria o vetor de livros

				livros[0] = livro;
				// Coloca o livro no vetor

				setLivro(livros);
				// Coloca o vetor no atributo 'livro'
			}
			
			livro.setNumeroExemplares((livro.getNumeroExemplares()) - quantidade);
			// Diminui a quantidade de livros disponíveis
			
			livro.setNumeroEmprestimos((livro.getNumeroEmprestimos()) + 1);
			// Adiciona ao número de empréstimos desse livro

			Emprestimo.setFaturamento(getFaturamento() + ((livro.getValorAluguel()) * quantidade));
			// Adiciona ao faturamento geral

			setDataEmprestimo(dataAtual);
			setDataDevolucao(dataDevolucaoProposta);
			//System.out.println("atual :" + dataAtual.getTime());
			//System.out.println("devolução :" + dataDevolucaoProposta.getTime());
			// Define a data de empréstimo e de devolução
		} else {
			System.out.println("Sem livros suficientes!");
		}
	}
	
	public void devolverLivro(Livro livro, int quantidade, Cliente cliente, Calendar dataDevolucaoEfetiva) {
		/*	
			A cada 10 dias de empréstimo há um adicional de 2%
			do valor de empreśtimo do livro no valor final
		*/
		int adicionaisMonetarios = 0;

		livro.setNumeroExemplares(livro.getNumeroExemplares() + quantidade);
		// Repõe os livros

		// Calcula a difereça de dias entre o dia de empréstimo e o dia de devolução
		// http://burnignorance.com/java-web-development-tips/calculating-difference-between-two-dates-using-java/
		long	diferencaMilissegundos = 0;
		long	diferencaDias = 0;

		// Get the difference between two dates in milliseconds
		diferencaMilissegundos = dataDevolucaoEfetiva.getTimeInMillis() - this.dataEmprestimo.getTimeInMillis();

		// Get difference between two dates in days
		diferencaDias = diferencaMilissegundos / (24 * 60 * 60 * 1000);

		// Calcula os ciclos de 10 dias de empréstimo
		adicionaisMonetarios = (int)(diferencaDias) / 10;

		// Adicionais monetários
		Emprestimo.setFaturamento(getFaturamento() + ((livro.getValorAluguel()) * adicionaisMonetarios * 0.02f));

		// Cálculo da multa
		if (dataDevolucaoEfetiva.after(this.dataDevolucao)) {
			// Se a data de devolução atual for depois da data proposta, caracteriza-se multa
			/*
				A multa será de 5% do valor de empréstimo do 
				disco e se repetirá a cada 3 dias até o dia de devolução
			*/
			adicionaisMonetarios = 0;

			// Calcula a difereça de dias entre o dia de devolução proposto e o dia de devolução atual
			// http://burnignorance.com/java-web-development-tips/calculating-difference-between-two-dates-using-java/
			// Get the difference between two dates in milliseconds
			diferencaMilissegundos = dataDevolucaoEfetiva.getTimeInMillis() - this.dataDevolucao.getTimeInMillis();

			// Get difference between two dates in days
			diferencaDias = diferencaMilissegundos / (24 * 60 * 60 * 1000);

			// Calcula os ciclos de 3 dias de multa
			adicionaisMonetarios = (int)(diferencaDias) / 3;

			// Adicionais monetários
			Emprestimo.setFaturamento(getFaturamento() + ((livro.getValorAluguel()) * adicionaisMonetarios * 0.05f));
		}

		// Remove o livro
		int		indiceLivros = 0;
		Livro[]	livros = new Livro[(getLivro().length) - 1];
		// Faz o vetor de livros com 1 espaço a menos, pois 1 livro irá sair

		if (livros.length <= 0) {
			// Se o vetor se esvaziar, significa que não têm mais livros
			setLivro(null);
		} else {
			for (int x = 0; x < getLivro().length; x++) {
				if (getLivro()[x] != livro) {
					// Só leva para o vetor, os livros que não serão removidos
					livros[indiceLivros] = getLivro()[x];
					indiceLivros++;
				}
			}
			setLivro(livros);
		}
	}

	public void alugarDisco(Disco disco, int quantidade, Cliente cliente, Calendar dataDevolucao) {
		Calendar dataAtual = Calendar.getInstance();
		// Obtem a data atual

		if (quantidade <= disco.getNumeroExemplares()) {
			// Verifica se existe quantidade suficiente de discos

			// Adiciona o disco
			if (getDisco() != null) {
				// Se já existirem discos

				Disco[] discos = new Disco[(getDisco().length) + 1];
				// Cria o vetor de discos com espaço para o novo disco

				for (int x = 0; x < getDisco().length; x++) {
					discos[x] = getDisco()[x];
					// Coloca os discos existentes no vetor de discos
				}

				discos[getDisco().length] = disco;
				// Coloca o novo disco no final do vetor de discos

				setDisco(discos);
				// Coloca o vetor no atributo 'disco'
			} else {
				// Se o clientes não tiver discos

				Disco[] discos = new Disco[1];
				// Cria o vetor de discos

				discos[0] = disco;
				// Coloca o disco no vetor

				setDisco(discos);
				// Coloca o vetor no atributo 'disco'
			}
			
			disco.setNumeroExemplares((disco.getNumeroExemplares()) - quantidade);
			// Diminui a quantidade de discos disponíveis
			
			disco.setNumeroEmprestimos((disco.getNumeroEmprestimos()) + 1);
			// Adiciona ao número de empréstimos desse disco

			Emprestimo.setFaturamento(getFaturamento() + ((disco.getValorAluguel()) * quantidade));
			// Adiciona ao faturamento geral

			setDataEmprestimo(dataAtual);
			setDataDevolucao(dataDevolucao);
			// Define a data de empréstimo e de devolução
		} else {
			System.out.println("Sem discos suficientes!");
		}
	}

	public void devolverDisco(Disco disco, int quantidade, Cliente cliente, Calendar dataDevolucaoEfetiva) {
		int adicionaisMonetarios = 0;	/*	
											A cada 10 dias de empréstimo há um adicional de 2%
											do valor de empreśtimo do disco no valor final
										*/

		disco.setNumeroExemplares(disco.getNumeroExemplares() + quantidade);
		// Repõe os discos

		// Calcula a difereça de dias entre o dia de empréstimo e o dia de devolução
		// http://burnignorance.com/java-web-development-tips/calculating-difference-between-two-dates-using-java/
		long	diferencaMilissegundos = 0;
		long	diferencaDias = 0;

		// Get the difference between two dates in milliseconds
		diferencaMilissegundos = dataDevolucaoEfetiva.getTimeInMillis() - this.dataEmprestimo.getTimeInMillis();

		// Get difference between two dates in days
		diferencaDias = diferencaMilissegundos / (24 * 60 * 60 * 1000);

		// Calcula os ciclos de 10 dias de empréstimo
		adicionaisMonetarios = (int)(diferencaDias) / 10;

		// Adicionais monetários
		Emprestimo.setFaturamento(getFaturamento() + ((disco.getValorAluguel()) * adicionaisMonetarios * 0.02f));

		// Cálculo da multa
		if (dataDevolucaoEfetiva.after(this.dataDevolucao)) {
			// Se a data de devolução atual for depois da data proposta, caracteriza-se multa

			/*
				A multa será de 5% do valor de empréstimo do 
				disco e se repetirá a cada 3 dias até o dia de devolução
			*/
			adicionaisMonetarios = 0;

			// Calcula a difereça de dias entre o dia de devolução proposto e o dia de devolução atual
			// http://burnignorance.com/java-web-development-tips/calculating-difference-between-two-dates-using-java/
			// Get the difference between two dates in milliseconds
			diferencaMilissegundos = dataDevolucaoEfetiva.getTimeInMillis() - this.dataDevolucao.getTimeInMillis();

			// Get difference between two dates in days
			diferencaDias = diferencaMilissegundos / (24 * 60 * 60 * 1000);

			// Calcula os ciclos de 3 dias de multa
			adicionaisMonetarios = (int)(diferencaDias) / 3;

			// Adicionais monetários
			Emprestimo.setFaturamento(getFaturamento() + ((disco.getValorAluguel()) * adicionaisMonetarios * 0.05f));
		}

		// Remove o disco
		int		indiceDiscos = 0;
		Disco[]	discos = new Disco[(getDisco().length) - 1];
		// Faz o vetor de discos com 1 espaço a menos, pois 1 disco irá sair

		if (discos.length <= 0) {
			// Se o vetor se esvaziar, significa que não têm mais discos
			setDisco(null);
		} else {
			for (int x = 0; x < getDisco().length; x++) {
				if (getDisco()[x] != disco) {
					// Só leva para o vetor, os discos que não serão removidos
					discos[indiceDiscos] = getDisco()[x];
					indiceDiscos++;
				}
			}
			setDisco(discos);
		}
	}

	public void simularMultaLivro(Livro livro, Calendar dataDevolucaoProposta , Calendar dataDevolucaoEfetiva) {
		if (dataDevolucaoEfetiva.after(this.dataDevolucao)) {
			/*
				A multa será de 5% do valor de empréstimo do 
				livro e se repetirá a cada 3 dias até o dia de devolução
			*/
			long	diferencaMilissegundos = 0;
			long	diferencaDias = 0;
			int		adicionaisMonetarios = 0;		
			float	multa = 0;

			// Calcula a difereça de dias entre o dia de devolução proposto e o dia de devolução atual
			// http://burnignorance.com/java-web-development-tips/calculating-difference-between-two-dates-using-java/
			// Get the difference between two dates in milliseconds
			diferencaMilissegundos = dataDevolucaoEfetiva.getTimeInMillis() - dataDevolucaoProposta.getTimeInMillis();

			// Get difference between two dates in days
			diferencaDias = diferencaMilissegundos / (24 * 60 * 60 * 1000);

			// Calcula os ciclos de 3 dias de multa
			adicionaisMonetarios = (int)(diferencaDias) / 3;

			// Adicionais monetários
			multa = livro.getValorAluguel() * adicionaisMonetarios * 0.05f;
			String multaDoisDecimais = String.format("%.02f", multa);

			System.out.println("Dias de atraso: " + diferencaDias + " dias");
			System.out.println("Multa:          " + "R$" + multaDoisDecimais);
		} else {
			System.out.println("Sem multa");
		}
	}

	public void simularMultaDisco(Disco disco, Calendar dataDevolucaoProposta, Calendar dataDevolucaoEfetiva) {
		if (dataDevolucaoEfetiva.after(this.dataDevolucao)) {
			/*
				A multa será de 5% do valor de empréstimo do 
				disco e se repetirá a cada 3 dias até o dia de devolução
			*/
			long	diferencaMilissegundos = 0;
			long	diferencaDias = 0;
			int		adicionaisMonetarios = 0;		
			float	multa = 0;

			// Calcula a difereça de dias entre o dia de devolução proposto e o dia de devolução atual
			// http://burnignorance.com/java-web-development-tips/calculating-difference-between-two-dates-using-java/
			// Get the difference between two dates in milliseconds
			diferencaMilissegundos = dataDevolucaoEfetiva.getTimeInMillis() - dataDevolucaoProposta.getTimeInMillis();

			// Get difference between two dates in days
			diferencaDias = diferencaMilissegundos / (24 * 60 * 60 * 1000);

			// Calcula os ciclos de 3 dias de multa
			adicionaisMonetarios = (int)(diferencaDias) / 3;

			// Adicionais monetários
			multa = disco.getValorAluguel() * adicionaisMonetarios * 0.05f;
			String multaDoisDecimais = String.format("%.02f", multa);

			System.out.println("Dias de atraso: " + diferencaDias + " dias");
			System.out.println("Multa:          " + "R$" + multaDoisDecimais);
		} else {
			System.out.println("Sem multa");
		}
	}

	/*
	public void gerarRelatorio(Calendar dataInicio, Calendar dataFim) {
		
	}

	public void gerarRelatorioCliente(Cliente cliente, Calendar dataInicio, Calendar dataFim) {
		
	}
	*/
}
