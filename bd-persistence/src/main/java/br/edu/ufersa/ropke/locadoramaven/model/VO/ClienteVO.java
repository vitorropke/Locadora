package br.edu.ufersa.ropke.locadoramaven.model.VO;

import java.util.ArrayList;
import java.util.List;

public class ClienteVO extends PessoaVO {
	private long id;
	private List<EmprestimoVO> emprestimos = new ArrayList<EmprestimoVO>();
	private List<EmprestimoVO> devolucoes = new ArrayList<EmprestimoVO>();

	public ClienteVO(String nome, String cpf, List<Endereco> enderecos, List<String> emails, List<Telefone> telefones) {
		super(nome, cpf, enderecos, emails, telefones);
	}

	@Override
	public String toString() {
		String cliente;

		cliente = "\nID cliente:\t\t" + id + '\n';
		cliente += super.toString();

		return cliente;
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

	public List<EmprestimoVO> getEmprestimos() {
		return emprestimos;
	}

	public void setEmprestimos(List<EmprestimoVO> emprestimos) {
		if ((emprestimos != null) && !emprestimos.isEmpty()) {
			int indice = 0;
			// Procura por empréstimos vazios ou inválidos
			for (EmprestimoVO emprestimoAtual : emprestimos) {
				if ((emprestimoAtual == null) || (emprestimoAtual.getDataOperacao() == null)
						|| (emprestimoAtual.getCliente() == null) || emprestimoAtual.getObjetos().isEmpty()) {
					System.out.println("Emprestimo " + indice + " vazio ou invalido");
					return;
				}
				indice++;
			}

			this.emprestimos.addAll(emprestimos);
		} else {
			System.out.println("Emprestimos vazios");
		}
	}

	public List<EmprestimoVO> getDevolucoes() {
		return devolucoes;
	}

	public void setDevolucoes(List<EmprestimoVO> devolucoes) {
		if ((devolucoes != null) && !devolucoes.isEmpty()) {
			int indice = 0;
			// Procura por empréstimos vazios ou inválidos
			for (EmprestimoVO devolucaoAtual : devolucoes) {
				if ((devolucaoAtual == null) || (devolucaoAtual.getDataOperacao() == null)
						|| (devolucaoAtual.getCliente() == null) || devolucaoAtual.getObjetos().isEmpty()) {
					System.out.println("Devolucao " + indice + " vazia ou invalida");
					return;
				}
			}

			this.devolucoes.addAll(devolucoes);
		} else {
			System.out.println("Devolucoes vazias");
		}
	}

	public void addEmprestimo(EmprestimoVO emprestimo) {
		if ((emprestimo != null) && (emprestimo.getDataOperacao() != null) && (emprestimo.getCliente() != null)
				&& !emprestimo.getObjetos().isEmpty()) {
			this.emprestimos.add(emprestimo);
		} else {
			System.out.println("Emprestimo vazio ou invalido");
		}
	}

	public void addDevolucao(EmprestimoVO devolucao) {
		if ((devolucao != null) && (devolucao.getDataOperacao() != null) && (devolucao.getCliente() != null)
				&& !devolucao.getObjetos().isEmpty()) {
			this.devolucoes.add(devolucao);
		} else {
			System.out.println("Devolucao vazia ou invalida");
		}
	}
}
