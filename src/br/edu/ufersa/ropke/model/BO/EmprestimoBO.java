package br.edu.ufersa.ropke.model.BO;

import java.util.ArrayList;
import java.util.Calendar;

import br.edu.ufersa.ropke.model.VO.ClienteVO;
import br.edu.ufersa.ropke.model.VO.EmprestimoVO;
import br.edu.ufersa.ropke.model.VO.LivroVO;

public class EmprestimoBO {
	public void alugarLivro(EmprestimoVO emprestimo, LivroVO[] livro, int[] quantidade,
			Calendar[] dataDevolucaoProposta, ClienteVO cliente) {
		// Verifica se os dados estão consistentes
		// Se os vetores não são nulos
		if ((emprestimo != null) && (livro != null) && (quantidade != null) && (dataDevolucaoProposta != null)
				&& (cliente != null)) {
			// Se os vetores possuem o mesmo tamanho
			if ((livro.length == quantidade.length) && (livro.length == dataDevolucaoProposta.length)) {
				int numeroLivros = livro.length;
				Calendar dataAtual = Calendar.getInstance();
				// Obtem a data atual
				ArrayList<LivroVO> livros = new ArrayList<LivroVO>();
				// Array de livros a ser adicionado
				ArrayList<Integer> quantidades = new ArrayList<Integer>();
				// Array de quantidade de livros
				ArrayList<Calendar> datas = new ArrayList<Calendar>();
				// Array com datas de devolução

				// Percorre os livros
				for (int x = 0; x < numeroLivros; x++) {
					// Verifica se o livro não é nulo
					if (livro[x] != null) {
						// Verifica se existe quantidade suficiente para ser emprestado
						if (livro[x].getNumeroExemplares() >= quantidade[x]) {
							// Verifica se a data de devolução proposta não é no passado
							if (dataDevolucaoProposta[x].after(dataAtual)) {
								// Verifica se a quantidade é maior que 0
								if (quantidade[x] > 0) {
									livros.add(livro[x]);
									quantidades.add(quantidade[x]);
									datas.add(dataDevolucaoProposta[x]);

									// Atualiza alguns atributos do livro
									// Número de exemplares
									livro[x].setNumeroExemplares(livro[x].getNumeroExemplares() - quantidade[x]);
									// Número de empréstimos
									livro[x].setNumeroEmprestimos(livro[x].getNumeroEmprestimos() + 1);

									// Atualiza o faturamento
									EmprestimoVO.setFaturamento(EmprestimoVO.getFaturamento()
											+ (livro[x].getValorAluguel() * quantidade[x]));
								} else {
									System.out.println("Quantidade proposta do livro " + livro[x].getTitulo()
											+ " nao pode ser 0 ou negativo!\n");
								}
							} else {
								System.out.println("Data de devolucao do livro " + livro[x].getTitulo()
										+ " nao pode ser no passado!\n");
							}
						} else {
							System.out
									.println("Livro " + livro[x].getTitulo() + " nao possui quantidade suficiente!\n");
						}
					}
				}

				// Verifica se existem livros válidos
				if (livros.size() != 0) {
					LivroVO[] vetorLivros = new LivroVO[livros.size()];
					int[] vetorQuantidades = new int[quantidades.size()];
					Calendar[] vetorDatas = new Calendar[datas.size()];

					// Conversão de array para vetor
					vetorLivros = livros.toArray(vetorLivros);

					for (int x = 0; x < quantidades.size(); x++) {
						vetorQuantidades[x] = quantidades.get(x);
					}

					vetorDatas = datas.toArray(vetorDatas);

					// Atualiza os atributos da classe emprestimo
					emprestimo.setLivro(vetorLivros);
					emprestimo.setQuantidadeLivro(vetorQuantidades);
					emprestimo.setDataDevolucaoLivro(vetorDatas);
					emprestimo.setCliente(cliente);
				} else {
					System.out.println("Sem livros validos!");
				}
			} else {
				System.out.println("Dados inconsistentes!");
			}
		}
	}
}
