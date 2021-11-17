package br.edu.ufersa.ropke.locadoramaven.model.VO;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class EmprestimoVO {
	private static float faturamento = 0;
	private long id;
	private Calendar dataOperacao;
	private ClienteVO cliente;
	private List<ObjetoEmprestadoVO> objetos = new ArrayList<ObjetoEmprestadoVO>();

	public EmprestimoVO(ClienteVO cliente, List<ObjetoEmprestadoVO> objetos) {
		dataOperacao = Calendar.getInstance();
		setCliente(cliente);
		emprestar(objetos);
	}

	@Override
	public String toString() {
		String emprestimo;
		String faturamentoDoisDecimais = String.format("%.02f", faturamento);

		emprestimo = "\nId:\t\t\t" + id;
		emprestimo += "\nFaturamento:\t\tR$" + faturamentoDoisDecimais;
		emprestimo += "\nData de operacao:\t";

		if (dataOperacao != null) {
			emprestimo += dataOperacao.getTime();
		} else {
			emprestimo += "Nao definida";
		}

		emprestimo += "\n\n--------------------------------------------\n";
		emprestimo += "\nObjetos:";

		if (!objetos.isEmpty()) {
			emprestimo += '\n';
			for (ObjetoEmprestadoVO objetoAtual : objetos) {
				emprestimo += objetoAtual;
				emprestimo += "\n\n----------------------\n";
			}
		} else {
			emprestimo += "\t\tSem objetos\n";
		}

		emprestimo += "\n--------------------------------------------\n";
		emprestimo += "\nCliente:";

		if (cliente != null) {
			emprestimo += "\n" + cliente;
		} else {
			emprestimo += "\t\tSem cliente\n";
		}

		return emprestimo;
	}

	public static float getFaturamento() {
		return faturamento;
	}

	public static void setFaturamento(float faturamento) {
		if (faturamento >= 0) {
			EmprestimoVO.faturamento = faturamento;
		} else {
			System.out.println("Faturamento nao pode ser negativo");
		}
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

	public Calendar getDataOperacao() {
		return dataOperacao;
	}

	public void setDataOperacao(Calendar dataOperacao) {
		if (dataOperacao != null) {
			this.dataOperacao = dataOperacao;
		} else {
			System.out.println("Data de operacao vazia");
		}
	}

	public ClienteVO getCliente() {
		return cliente;
	}

	public void setCliente(ClienteVO cliente) {
		if ((cliente != null) && (cliente.getNome() != null) && !cliente.getEnderecos().isEmpty()) {
			this.cliente = cliente;
		} else {
			System.out.println("Cliente vazio ou invalido");
		}
	}

	public List<ObjetoEmprestadoVO> getObjetos() {
		return objetos;
	}

	public void emprestar(List<ObjetoEmprestadoVO> objetosEmprestados) {
		if ((objetosEmprestados != null) && !objetosEmprestados.isEmpty()) {
			int indice = 0;
			// Procura por objetos vazios ou inválidos
			for (ObjetoEmprestadoVO objetoAtual : objetosEmprestados) {
				Calendar dataAtual = Calendar.getInstance();
				// Verifica se o objeto atual é nulo
				// Se a data não é no futuro
				// Se a quantidade a ser emprestada é 0
				if ((objetoAtual == null) || (objetoAtual.getObjeto() == null)
						|| (objetoAtual.getDataDevolucao() == null) || !objetoAtual.getDataDevolucao().after(dataAtual)
						|| (objetoAtual.getQuantidade() == 0)) {
					System.out.println("Objeto " + indice + " vazio ou invalido");
					return;
				}
				indice++;
			}

			for (ObjetoEmprestadoVO objetoAtual : objetosEmprestados) {
				EmprestavelVO objetoEmprestado = objetoAtual.getObjeto();
				int quantidadeObjeto = objetoAtual.getQuantidade();

				// Reduz o número de exemplares do objeto
				objetoEmprestado.setNumeroExemplares(objetoEmprestado.getNumeroExemplares() - quantidadeObjeto);
				// Adiciona 1 na contagem de empréstimos do objeto
				objetoEmprestado.setNumeroEmprestimos(objetoEmprestado.getNumeroEmprestimos() + 1);
				// Adiciona faturamento
				setFaturamento(getFaturamento() + objetoEmprestado.getValorAluguel() * quantidadeObjeto);
			}

			objetos = objetosEmprestados;
			cliente.addEmprestimo(this);
		} else {
			System.out.println("Sem objetos emprestados");
		}
	}

	public void devolver(List<ObjetoEmprestadoVO> objetosDevolvidos, List<Integer> quantidadeDevolucao) {
		if ((objetosDevolvidos != null) && (quantidadeDevolucao != null) && !objetosDevolvidos.isEmpty()
				&& !quantidadeDevolucao.isEmpty() && (objetosDevolvidos.size() == quantidadeDevolucao.size())) {
			int indice = 0;
			// Procura por objetos vazios ou inválidos
			for (ObjetoEmprestadoVO objetoAtual : objetosDevolvidos) {
				// Verifica se o índice atual do objeto e da quantidade de devolução é nulo
				// Se a quantidade a ser devolvida é 0
				// Se o emprestável não foi emprestado
				// Se a quantidade que está sendo devolvida é maior que a quantidade emprestada
				if ((objetoAtual == null) || (objetoAtual.getObjeto() == null)
						|| (objetoAtual.getDataDevolucao() == null) || (objetoAtual.getQuantidade() == 0)
						|| (quantidadeDevolucao.get(indice) == null) || (quantidadeDevolucao.get(indice) == 0)
						|| (indiceObjeto(objetoAtual) == -1)
						|| (quantidadeDevolucao.get(indice) > objetoAtual.getQuantidade())) {
					System.out.println("Objeto " + indice + " vazio ou invalido");
					return;
				}
				indice++;
			}

			// Altera dados nos emprestáveis
			List<ObjetoEmprestadoVO> objetosRemoviveis = new ArrayList<ObjetoEmprestadoVO>();
			Calendar dataAtual = Calendar.getInstance();
			indice = 0;

			for (ObjetoEmprestadoVO objetoAtual : objetosDevolvidos) {
				EmprestavelVO objetoDevolvido = objetoAtual.getObjeto();
				int quantidadeDevolvida = quantidadeDevolucao.get(indice);

				// Readiciona os exemplares
				objetoDevolvido.setNumeroExemplares(objetoDevolvido.getNumeroExemplares() + quantidadeDevolvida);

				int diasAlugado = diferencaTempoDias(dataOperacao, dataAtual);
				// Adiciona os dias alugado
				objetoDevolvido.setNumeroDiasAlugado(objetoDevolvido.getNumeroDiasAlugado() + diasAlugado);
				// Adicional no faturamento de dias alugado
				// A cada 10 dias é adicionado um valor que multiplica o aluguel do emprestável
				// e uma porcentagem
				setFaturamento(getFaturamento()
						+ adicionalMonetario(objetoDevolvido.getValorAluguel(), diasAlugado, 10, 0.08f));

				int diasAtraso = diferencaTempoDias(objetoAtual.getDataDevolucao(), dataAtual);
				// Se houver atraso
				if (diasAtraso > 0) {
					// Adiciona os dias de atraso
					objetoDevolvido.setNumeroDiasAlugado(objetoDevolvido.getNumeroDiasAlugado() + diasAtraso);
					// Multa é 20% do emprestável que é adicionado a cada 5 dias
					setFaturamento(getFaturamento()
							+ adicionalMonetario(objetoDevolvido.getValorAluguel(), diasAtraso, 5, 0.2f));
				}

				int quantidadeRestante = objetoAtual.getQuantidade() - quantidadeDevolucao.get(indice);
				// Se não sobrar apague dos objetos emprestados
				// Senão define nova quantidade
				if (quantidadeRestante == 0) {
					objetosRemoviveis.add(objetoAtual);
				} else {
					objetoAtual.setQuantidade(quantidadeRestante);
				}
				indice++;
			}

			// Remove objetos com quantidade 0
			objetos.removeAll(objetosRemoviveis);
		} else {
			System.out.println("Dados invalidos");
		}
	}

	private int indiceObjeto(ObjetoEmprestadoVO objeto) {
		int indice = 0;

		for (ObjetoEmprestadoVO objetoAtual : objetos) {
			if (objetoAtual.getId() == objeto.getId()) {
				return indice;
			}
			indice++;
		}

		System.out.println("Objeto nao emprestado");
		return -1;
	}

	private int diferencaTempoDias(Calendar tempoAntes, Calendar tempoDepois) {
		long diferenca = tempoDepois.getTimeInMillis() - tempoAntes.getTimeInMillis();

		return (int) (diferenca / (24 * 60 * 60 * 1000));
	}

	private float adicionalMonetario(float valorBase, int diasDiferenca, int ciclosDias, float porcentagem) {
		return valorBase * (diasDiferenca / ciclosDias) * porcentagem;
	}
}
