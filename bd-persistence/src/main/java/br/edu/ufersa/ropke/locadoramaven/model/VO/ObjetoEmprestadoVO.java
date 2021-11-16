package br.edu.ufersa.ropke.locadoramaven.model.VO;

import java.util.Calendar;

public class ObjetoEmprestadoVO {
	private long id;
	private EmprestavelVO objeto;
	private Calendar dataDevolucao;
	private int quantidade;

	public ObjetoEmprestadoVO(EmprestavelVO objeto, Calendar dataDevolucao, int quantidade) {
		setObjeto(objeto);
		setDataDevolucao(dataDevolucao);
		setQuantidade(quantidade);
	}

	@Override
	public String toString() {
		String objetoEmprestado;

		objetoEmprestado = "\nObjeto:\n" + objeto;
		objetoEmprestado += "\nData de devolucao:\t\t" + dataDevolucao.getTime();
		objetoEmprestado += "\nQuantidade:\t\t\t" + quantidade + '\n';

		return objetoEmprestado;
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

	public EmprestavelVO getObjeto() {
		return objeto;
	}

	public void setObjeto(EmprestavelVO objeto) {
		if ((objeto != null) && (objeto.getTitulo() != null)) {
			this.objeto = objeto;
		} else {
			System.out.println("Objeto vazio ou invalido");
		}
	}

	public Calendar getDataDevolucao() {
		return dataDevolucao;
	}

	public void setDataDevolucao(Calendar dataDevolucao) {
		if ((dataDevolucao != null) && dataDevolucao.after(Calendar.getInstance())) {
			this.dataDevolucao = dataDevolucao;
		} else {
			System.out.println("Data de devolucao vazia ou no passado");
		}
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		if ((quantidade > 0) && (objeto != null) && (quantidade <= objeto.getNumeroExemplares())) {
			this.quantidade = quantidade;
		} else {
			System.out.println("Quantidade nula, negativa ou indisponivel");
		}
	}

}
