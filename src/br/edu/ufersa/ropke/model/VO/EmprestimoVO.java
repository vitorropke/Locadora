package br.edu.ufersa.ropke.model.VO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;

public class EmprestimoVO implements Serializable {
	private static final long serialVersionUID = 1L;

	// Variáveis
	private static long contadorId = 0;
	private static float faturamento = 0;
	private long idEmprestimo;
	private Calendar dataEmprestimo;
	private Calendar[] dataDevolucaoLivro;
	private Calendar[] dataDevolucaoDisco;
	private LivroVO[] livro;
	private DiscoVO[] disco;
	private int quantidadeLivro[];
	private int quantidadeDisco[];
	private ClienteVO cliente;

	// Construtores
	public EmprestimoVO() {
		// TODO Auto-generated constructor stub
		Calendar dataAtual = Calendar.getInstance();

		setIdEmprestimo(contadorId++);
		setDataEmprestimo(dataAtual);
	}

	// toString
	@Override
	public String toString() {
		String emprestimo = "";
		String faturamentoDoisDecimais = String.format("%.02f", faturamento);

		emprestimo = "\nId:\t\t\t\t" + idEmprestimo;
		emprestimo += "\nFaturamento:\t\t\tR$" + faturamentoDoisDecimais;
		emprestimo += "\nData de emprestimo:\t\t";

		if (dataEmprestimo != null) {
			emprestimo += dataEmprestimo.getTime();
		} else {
			emprestimo += "Nao definida";
		}

		emprestimo += "\n----------------------------------------------";
		emprestimo += "\nData de devolucao de livros:\n";

		if (dataDevolucaoLivro != null) {
			if (dataDevolucaoLivro.length == 0) {
				emprestimo += "\t\t\t\tSem livros\n";
			} else {
				for (int x = 0; x < dataDevolucaoLivro.length; x++) {
					emprestimo += "\t\t\t\t" + dataDevolucaoLivro[x].getTime() + "\n";
				}
			}
		} else {
			emprestimo += "\t\t\t\tSem livros\n";
		}

		emprestimo += "\n----------------------------------------------";
		emprestimo += "\nData de devolucao de discos:\n";

		if (dataDevolucaoDisco != null) {
			if (dataDevolucaoDisco.length == 0) {
				emprestimo += "\t\t\t\tSem discos\n";
			} else {
				for (int x = 0; x < dataDevolucaoDisco.length; x++) {
					emprestimo += "\t\t\t\t" + dataDevolucaoDisco[x].getTime() + "\n";
				}
			}
		} else {
			emprestimo += "\t\t\t\tSem discos\n";
		}

		emprestimo += "\n----------------------------------------------";
		emprestimo += "\nLivros:\n";

		if (livro != null) {
			if (livro.length == 0) {
				emprestimo += "\t\t\t\tSem livros\n";
			} else {
				for (int x = 0; x < livro.length; x++) {
					emprestimo += livro[x].toString();
					emprestimo += "\n-------------------------------";
				}
			}
		} else {
			emprestimo += "\t\t\t\tSem livros\n";
		}

		emprestimo += "\n----------------------------------------------";
		emprestimo += "\nDiscos:\n";

		if (disco != null) {
			if (disco.length == 0) {
				emprestimo += "\t\t\t\tSem discos\n";
			} else {
				for (int x = 0; x < disco.length; x++) {
					emprestimo += disco[x];
					emprestimo += "\n-------------------------------";
				}
			}
		} else {
			emprestimo += "\t\t\t\tSem discos\n";
		}

		emprestimo += "\n\nCliente:\n";

		if (cliente != null) {
			emprestimo += cliente.toString();
		} else {
			emprestimo += "\t\t\t\tSem cliente\n";
		}

		return emprestimo;
	}

	// Getters e setters
	public static float getFaturamento() {
		return faturamento;
	}

	public static void setFaturamento(float faturamento) {
		if (faturamento >= 0) {
			EmprestimoVO.faturamento = faturamento;
		} else {
			System.out.println("Faturamento nao pode ser negativo!");
		}
	}

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

	public Calendar getDataEmprestimo() {
		return dataEmprestimo;
	}

	public void setDataEmprestimo(Calendar dataEmprestimo) {
		if (dataEmprestimo != null) {
			Calendar dataAtual = Calendar.getInstance();
			// Obtem a data atual

			if (dataEmprestimo.after(dataAtual)) {
				System.out.println("Data de emprestimo nao pode ser no futuro!");
			} else {
				this.dataEmprestimo = dataEmprestimo;
			}
		} else {
			System.out.println("Data de emprestimo nao pode ser vazia!");
		}
	}

	public Calendar[] getDataDevolucaoLivro() {
		return dataDevolucaoLivro;
	}

	public void setDataDevolucaoLivro(Calendar[] dataDevolucaoLivro) {
		if (dataDevolucaoLivro != null) {
			ArrayList<Calendar> datasValidas = new ArrayList<Calendar>();
			Calendar dataAtual = Calendar.getInstance();
			// Obtem a data atual

			// Insere somente endereços válidos
			for (int x = 0; x < dataDevolucaoLivro.length; x++) {
				if ((dataDevolucaoLivro[x] != null)) {
					if (dataDevolucaoLivro[x].after(dataAtual)) {
						datasValidas.add(dataDevolucaoLivro[x]);
					} else {
						System.out.println("Data de devolucao so pode ser no futuro!");
					}
				} else {
					System.out.println("Data de devolucao nao pode ser vazia!");
				}
			}

			// Verifica se existem endereços válidos
			if (datasValidas.size() != 0) {
				// ArrayList para vetor
				Calendar[] dataDevolucaoLivros = new Calendar[datasValidas.size()];
				dataDevolucaoLivros = datasValidas.toArray(dataDevolucaoLivros);
				this.dataDevolucaoLivro = dataDevolucaoLivros;
			} else {
				System.out.println("Sem datas validas!");
				Calendar[] semDatas = new Calendar[0];
				this.dataDevolucaoLivro = semDatas;
			}
		} else {
			System.out.println("Datas de devolucao nao podem ser vazias!");
		}
	}

	public Calendar[] getDataDevolucaoDisco() {
		return dataDevolucaoDisco;
	}

	public void setDataDevolucaoDisco(Calendar[] dataDevolucaoDisco) {
		if (dataDevolucaoDisco != null) {
			ArrayList<Calendar> datasValidas = new ArrayList<Calendar>();
			Calendar dataAtual = Calendar.getInstance();
			// Obtem a data atual

			// Insere somente endereços válidos
			for (int x = 0; x < dataDevolucaoDisco.length; x++) {
				if ((dataDevolucaoDisco[x] != null)) {
					if (dataDevolucaoDisco[x].after(dataAtual)) {
						datasValidas.add(dataDevolucaoDisco[x]);
					} else {
						System.out.println("Data de devolucao so pode ser no futuro!");
					}
				} else {
					System.out.println("Data nao pode ser vazia!");
				}
			}

			// Verifica se existem endereços válidos
			if (datasValidas.size() != 0) {
				// ArrayList para vetor
				Calendar[] dataDevolucaoDiscos = new Calendar[datasValidas.size()];
				dataDevolucaoDiscos = datasValidas.toArray(dataDevolucaoDiscos);
				this.dataDevolucaoDisco = dataDevolucaoDiscos;
			} else {
				System.out.println("Sem datas validas!");
				Calendar[] semDatas = new Calendar[0];
				this.dataDevolucaoDisco = semDatas;
			}
		} else {
			System.out.println("Datas de devolucao nao podem ser vazias!");
		}
	}

	public LivroVO[] getLivro() {
		return livro;
	}

	public void setLivro(LivroVO[] livro) {
		if (livro != null) {
			ArrayList<LivroVO> livrosValidos = new ArrayList<LivroVO>();

			// Insere somente livros válidos
			for (int x = 0; x < livro.length; x++) {
				if (livro[x] != null) {
					livrosValidos.add(livro[x]);
				} else {
					System.out.println("Livro nao pode ser vazio!");
				}
			}

			// Verifica se existem livros válidos
			if (livrosValidos.size() != 0) {
				// ArrayList para vetor
				LivroVO[] livros = new LivroVO[livrosValidos.size()];
				livros = livrosValidos.toArray(livros);
				this.livro = livros;
			} else {
				System.out.println("Sem livros validos!");
				LivroVO[] semLivros = new LivroVO[0];
				this.livro = semLivros;
			}
		} else {
			System.out.println("Livros nao podem ser vazios!");
		}
	}

	public DiscoVO[] getDisco() {
		return disco;
	}

	public void setDisco(DiscoVO[] disco) {
		if (disco != null) {
			ArrayList<DiscoVO> discosValidos = new ArrayList<DiscoVO>();

			// Insere somente discos válidos
			for (int x = 0; x < disco.length; x++) {
				if (disco[x] != null) {
					discosValidos.add(disco[x]);
				} else {
					System.out.println("Disco nao pode ser vazio!");
				}
			}

			// Verifica se existem discos válidos
			if (discosValidos.size() != 0) {
				// ArrayList para vetor
				DiscoVO[] discos = new DiscoVO[discosValidos.size()];
				discos = discosValidos.toArray(discos);
				this.disco = discos;
			} else {
				System.out.println("Sem discos validos!");
				DiscoVO[] semDiscos = new DiscoVO[0];
				this.disco = semDiscos;
			}
		} else {
			System.out.println("Discos nao podem ser vazios!");
		}
	}

	public int[] getQuantidadeLivro() {
		return quantidadeLivro;
	}

	public void setQuantidadeLivro(int[] quantidadeLivro) {
		if (quantidadeLivro != null) {
			// Procura valores maior que 0
			int numeroValoresValidos = 0;
			int indiceValoresValidos = 0;
			for (int x = 0; x < quantidadeLivro.length; x++) {
				if (quantidadeLivro[x] > 0) {
					numeroValoresValidos++;
				} else {
					System.out.println("Quantidade so pode ser maior que 0!");
				}
			}

			// Criar vetor com número de valores maior que 0
			int[] valoresValidos = new int[numeroValoresValidos];

			// Insere valores maior que 0 no vetor 'valoresValidos'
			for (int x = 0; x < quantidadeLivro.length; x++) {
				if (quantidadeLivro[x] > 0) {
					valoresValidos[indiceValoresValidos] = quantidadeLivro[x];
					indiceValoresValidos++;
				}
			}

			this.quantidadeLivro = valoresValidos;
		} else {
			System.out.println("Quantidades nao podem ser vazias!");
		}
	}

	public int[] getQuantidadeDisco() {
		return quantidadeDisco;
	}

	public void setQuantidadeDisco(int[] quantidadeDisco) {
		if (quantidadeDisco != null) {
			// Procura valores maior que 0
			int numeroValoresValidos = 0;
			int indiceValoresValidos = 0;
			for (int x = 0; x < quantidadeDisco.length; x++) {
				if (quantidadeDisco[x] > 0) {
					numeroValoresValidos++;
				} else {
					System.out.println("Quantidade so pode ser maior que 0!");
				}
			}

			// Criar vetor com número de valores maior que 0
			int[] valoresValidos = new int[numeroValoresValidos];

			// Insere valores maior que 0 no vetor 'valoresValidos'
			for (int x = 0; x < quantidadeDisco.length; x++) {
				if (quantidadeDisco[x] > 0) {
					valoresValidos[indiceValoresValidos] = quantidadeDisco[x];
					indiceValoresValidos++;
				}
			}

			this.quantidadeDisco = valoresValidos;
		} else {
			System.out.println("Quantidades nao podem ser vazias!");
		}
	}

	public ClienteVO getCliente() {
		return cliente;
	}

	public void setCliente(ClienteVO cliente) {
		if (cliente != null) {
			this.cliente = cliente;
		} else {
			System.out.println("Cliente nao pode ser vazio!");
		}
	}
}
