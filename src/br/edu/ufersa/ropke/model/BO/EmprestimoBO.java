package br.edu.ufersa.ropke.model.BO;

import java.util.ArrayList;
import java.util.Calendar;

import br.edu.ufersa.ropke.model.VO.ClienteVO;
import br.edu.ufersa.ropke.model.VO.DiscoVO;
import br.edu.ufersa.ropke.model.VO.EmprestimoVO;
import br.edu.ufersa.ropke.model.VO.LivroVO;

public class EmprestimoBO {
	public static void alugarLivro(EmprestimoVO emprestimo, LivroVO[] livro, int[] quantidade,
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
					// Verifica se o livro e a data não são nulos
					if ((livro[x] != null) && (dataDevolucaoProposta[x] != null)) {
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

	public static void alugarDisco(EmprestimoVO emprestimo, DiscoVO[] disco, int[] quantidade,
			Calendar[] dataDevolucaoProposta, ClienteVO cliente) {
		// Verifica se os dados estão consistentes
		// Se os vetores não são nulos
		if ((emprestimo != null) && (disco != null) && (quantidade != null) && (dataDevolucaoProposta != null)
				&& (cliente != null)) {
			// Se os vetores possuem o mesmo tamanho
			if ((disco.length == quantidade.length) && (disco.length == dataDevolucaoProposta.length)) {
				int numeroDiscos = disco.length;
				Calendar dataAtual = Calendar.getInstance();
				// Obtem a data atual
				ArrayList<DiscoVO> discos = new ArrayList<DiscoVO>();
				// Array de discos a ser adicionado
				ArrayList<Integer> quantidades = new ArrayList<Integer>();
				// Array de quantidade de discos
				ArrayList<Calendar> datas = new ArrayList<Calendar>();
				// Array com datas de devolução

				// Percorre os discos
				for (int x = 0; x < numeroDiscos; x++) {
					// Verifica se o disco e a data não são nulos
					if ((disco[x] != null) && (dataDevolucaoProposta[x] != null)) {
						// Verifica se existe quantidade suficiente para ser emprestado
						if (disco[x].getNumeroExemplares() >= quantidade[x]) {
							// Verifica se a data de devolução proposta não é no passado
							if (dataDevolucaoProposta[x].after(dataAtual)) {
								// Verifica se a quantidade é maior que 0
								if (quantidade[x] > 0) {
									discos.add(disco[x]);
									quantidades.add(quantidade[x]);
									datas.add(dataDevolucaoProposta[x]);

									// Atualiza alguns atributos do disco
									// Número de exemplares
									disco[x].setNumeroExemplares(disco[x].getNumeroExemplares() - quantidade[x]);
									// Número de empréstimos
									disco[x].setNumeroEmprestimos(disco[x].getNumeroEmprestimos() + 1);

									// Atualiza o faturamento
									EmprestimoVO.setFaturamento(EmprestimoVO.getFaturamento()
											+ (disco[x].getValorAluguel() * quantidade[x]));
								} else {
									System.out.println("Quantidade proposta do disco " + disco[x].getTitulo()
											+ " nao pode ser 0 ou negativo!\n");
								}
							} else {
								System.out.println("Data de devolucao do disco " + disco[x].getTitulo()
										+ " nao pode ser no passado!\n");
							}
						} else {
							System.out
									.println("Disco " + disco[x].getTitulo() + " nao possui quantidade suficiente!\n");
						}
					}
				}

				// Verifica se existem livros válidos
				if (discos.size() != 0) {
					DiscoVO[] vetorDiscos = new DiscoVO[discos.size()];
					int[] vetorQuantidades = new int[quantidades.size()];
					Calendar[] vetorDatas = new Calendar[datas.size()];

					// Conversão de array para vetor
					vetorDiscos = discos.toArray(vetorDiscos);

					for (int x = 0; x < quantidades.size(); x++) {
						vetorQuantidades[x] = quantidades.get(x);
					}

					vetorDatas = datas.toArray(vetorDatas);

					// Atualiza os atributos da classe emprestimo
					emprestimo.setDisco(vetorDiscos);
					emprestimo.setQuantidadeDisco(vetorQuantidades);
					emprestimo.setDataDevolucaoDisco(vetorDatas);
					emprestimo.setCliente(cliente);
				} else {
					System.out.println("Sem discos validos!");
				}
			} else {
				System.out.println("Dados inconsistentes!");
			}
		}
	}

	public static void devolverLivro(EmprestimoVO emprestimo, LivroVO[] livro, int[] quantidade,
			Calendar[] dataDevolucaoEfetiva) {
		// Verifica se os dados estão consistentes
		// Se os vetores e objetos não são nulos
		if ((emprestimo != null) && (emprestimo.getLivro() != null) && (livro != null) && (quantidade != null)
				&& (dataDevolucaoEfetiva != null)) {
			// Se os vetores possuem o mesmo tamanho
			if ((livro.length == quantidade.length) && (livro.length == dataDevolucaoEfetiva.length)) {
				int numeroLivros = livro.length;
				int numeroLivrosEmprestados = emprestimo.getLivro().length;

				// Insere os atributos do empréstimo relacionados ao livro
				ArrayList<LivroVO> livros = new ArrayList<LivroVO>();
				// Array de livros
				for (int x = 0; x < numeroLivrosEmprestados; x++) {
					livros.add(emprestimo.getLivro()[x]);
				}
				ArrayList<Integer> quantidades = new ArrayList<Integer>();
				// Array de quantidade de livros
				for (int x = 0; x < numeroLivrosEmprestados; x++) {
					quantidades.add(emprestimo.getQuantidadeLivro()[x]);
				}
				ArrayList<Calendar> datas = new ArrayList<Calendar>();
				// Array com datas de devolução
				for (int x = 0; x < numeroLivrosEmprestados; x++) {
					datas.add(emprestimo.getDataDevolucaoLivro()[x]);
				}

				int posicaoLivro;
				int quantidadeLivros;
				int adicionaisMonetarios;
				int diferencaDias;
				long diferencaMilissegundos;
				// Percorre os livros
				for (int x = 0; x < numeroLivros; x++) {
					// Verifica se o livro e a data não são nulos e a quantidade não é 0
					if ((livro[x] != null) && (dataDevolucaoEfetiva[x] != null) && (quantidade[x] != 0)) {
						// Obtem a posição no vetor de livros
						posicaoLivro = livros.indexOf(livro[x]);

						// Faz as modificações quando alguma posição é encontrada
						if (posicaoLivro == -1) {
							System.out.println("Livro nao emprestado!");
						} else {
							// Verifica se a quantidade a ser devolvida é menor ou igual a quantidade de
							// livros que foram emprestados
							if (quantidade[x] <= emprestimo.getQuantidadeLivro()[posicaoLivro]) {
								// Verifica se a data de devolução é depois da data de empréstimo
								if (dataDevolucaoEfetiva[x].after(emprestimo.getDataEmprestimo())) {
									// Repõe os livros
									livro[x].setNumeroExemplares(livro[x].getNumeroExemplares() + quantidade[x]);

									// Obtem a quantidade de livros e subtrai da quantidade devolvida
									quantidadeLivros = quantidades.get(posicaoLivro) - quantidade[x];

									// Adiciona as modificações no vetor
									// Se sobrarem livros, atualiza a quantidade no vetor
									if (quantidadeLivros != 0) {
										quantidades.set(posicaoLivro, quantidadeLivros);
									} else {
										// Se não sobrarem livros, remove essa posição dos vetores
										livros.remove(posicaoLivro);
										quantidades.remove(posicaoLivro);
										datas.remove(posicaoLivro);
									}

									// Cálculo de adicionais e multa se existirem
									adicionaisMonetarios = 0;
									// A cada 10 dias de empréstimo há um adicional de 2% do valor de empréstimo do
									// livro no valor final

									// http://burnignorance.com/java-web-development-tips/calculating-difference-between-two-dates-using-java/
									diferencaMilissegundos = 0;
									diferencaDias = 0;

									// Get the difference between two dates in milliseconds
									// diferença entre data de empréstimo e data de devolução
									diferencaMilissegundos = dataDevolucaoEfetiva[x].getTimeInMillis()
											- emprestimo.getDataEmprestimo().getTimeInMillis();

									// Get difference between two dates in days
									diferencaDias = (int) diferencaMilissegundos / (24 * 60 * 60 * 1000);

									// Adiciona os dias no atributo do livro, dias alugado
									livro[x].setNumeroDiasAlugado(livro[x].getNumeroDiasAlugado() + diferencaDias);

									// Calcula os ciclos de 10 dias de empréstimo
									adicionaisMonetarios = (int) (diferencaDias) / 10;

									// Atualiza o faturamento com os adicionais
									EmprestimoVO.setFaturamento(EmprestimoVO.getFaturamento()
											+ (livro[x].getValorAluguel() * adicionaisMonetarios * 0.02f));

									// Verifica se há multa
									// a data de devolução efetiva ocorre depois da data de devolução proposta
									if (dataDevolucaoEfetiva[x]
											.after(emprestimo.getDataDevolucaoLivro()[posicaoLivro])) {
										// A multa será de 5% do valor de empréstimo do livro e se repetirá a cada 3
										// dias até a data de devolução

										adicionaisMonetarios = 0;

										// difereça entre o dia de devolução proposto e o dia de devolução atual
										diferencaMilissegundos = dataDevolucaoEfetiva[x].getTimeInMillis()
												- emprestimo.getDataDevolucaoLivro()[posicaoLivro].getTimeInMillis();

										// Get difference between two dates in days
										diferencaDias = (int) diferencaMilissegundos / (24 * 60 * 60 * 1000);

										// Calcula os ciclos de 3 dias de multa
										adicionaisMonetarios = (int) (diferencaDias) / 3;

										// Atualiza o faturamento com a multa
										EmprestimoVO.setFaturamento(EmprestimoVO.getFaturamento()
												+ (livro[x].getValorAluguel() * adicionaisMonetarios * 0.05f));
									}
								} else {
									System.out.println("Data de devolucao do livro " + livro[x].getTitulo()
											+ " nao pode ser antes da data de emprestimo!\n");
								}
							} else {
								System.out.println("Voce nao tem essa quantidade de livros para devolver\n");
							}
						}
					}
				}

				// Define os novos valores dos atributos do empréstimo
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
				} else {
					// Esvazia os vetores relacionados ao livro
					LivroVO[] vetorLivros = new LivroVO[0];
					int[] vetorQuantidades = new int[0];
					Calendar[] vetorDatas = new Calendar[0];

					// Atualiza os atributos da classe emprestimo
					emprestimo.setLivro(vetorLivros);
					emprestimo.setQuantidadeLivro(vetorQuantidades);
					emprestimo.setDataDevolucaoLivro(vetorDatas);
				}
			} else {
				System.out.println("Dados inconsistentes!");
			}
		}
	}

	public static void devolverDisco(EmprestimoVO emprestimo, DiscoVO[] disco, int[] quantidade,
			Calendar[] dataDevolucaoEfetiva) {
		// Verifica se os dados estão consistentes
		// Se os vetores e objetos não são nulos
		if ((emprestimo != null) && (emprestimo.getDisco() != null) && (disco != null) && (quantidade != null)
				&& (dataDevolucaoEfetiva != null)) {
			// Se os vetores possuem o mesmo tamanho
			if ((disco.length == quantidade.length) && (disco.length == dataDevolucaoEfetiva.length)) {
				int numeroDiscos = disco.length;
				int numeroDiscosEmprestados = emprestimo.getDisco().length;

				// Insere os atributos do empréstimo relacionados ao disco
				ArrayList<DiscoVO> discos = new ArrayList<DiscoVO>();
				// Array de discos
				for (int x = 0; x < numeroDiscosEmprestados; x++) {
					discos.add(emprestimo.getDisco()[x]);
				}
				ArrayList<Integer> quantidades = new ArrayList<Integer>();
				// Array de quantidade de discos
				for (int x = 0; x < numeroDiscosEmprestados; x++) {
					quantidades.add(emprestimo.getQuantidadeDisco()[x]);
				}
				ArrayList<Calendar> datas = new ArrayList<Calendar>();
				// Array com datas de devolução
				for (int x = 0; x < numeroDiscosEmprestados; x++) {
					datas.add(emprestimo.getDataDevolucaoDisco()[x]);
				}

				int posicaoDisco;
				int quantidadeDiscos;
				int adicionaisMonetarios;
				int diferencaDias;
				long diferencaMilissegundos;
				// Percorre os discos
				for (int x = 0; x < numeroDiscos; x++) {
					// Verifica se o disco e a data não são nulos e a quantidade não é 0
					if ((disco[x] != null) && (dataDevolucaoEfetiva[x] != null) && (quantidade[x] != 0)) {
						// Obtem a posição no vetor de discos
						posicaoDisco = discos.indexOf(disco[x]);

						// Faz as modificações quando alguma posição é encontrada
						if (posicaoDisco == -1) {
							System.out.println("Disco nao emprestado!");
						} else {
							// Verifica se a quantidade a ser devolvida é menor ou igual a quantidade de
							// discos que foram emprestados
							if (quantidade[x] <= emprestimo.getQuantidadeDisco()[posicaoDisco]) {
								// Verifica se a data de devolução é depois da data de empréstimo
								if (dataDevolucaoEfetiva[x].after(emprestimo.getDataEmprestimo())) {
									// Repõe os discos
									disco[x].setNumeroExemplares(disco[x].getNumeroExemplares() + quantidade[x]);

									// Obtem a quantidade de discos e subtrai da quantidade devolvida
									quantidadeDiscos = quantidades.get(posicaoDisco) - quantidade[x];

									// Adiciona as modificações no vetor
									// Se sobrarem discos, atualiza a quantidade no vetor
									if (quantidadeDiscos != 0) {
										quantidades.set(posicaoDisco, quantidadeDiscos);
									} else {
										// Se não sobrarem discos, remove essa posição dos vetores
										discos.remove(posicaoDisco);
										quantidades.remove(posicaoDisco);
										datas.remove(posicaoDisco);
									}

									// Cálculo de adicionais e multa se existirem
									adicionaisMonetarios = 0;
									// A cada 10 dias de empréstimo há um adicional de 2% do valor de empréstimo do
									// disco no valor final

									// http://burnignorance.com/java-web-development-tips/calculating-difference-between-two-dates-using-java/
									diferencaMilissegundos = 0;
									diferencaDias = 0;

									// Get the difference between two dates in milliseconds
									// diferença entre data de empréstimo e data de devolução
									diferencaMilissegundos = dataDevolucaoEfetiva[x].getTimeInMillis()
											- emprestimo.getDataEmprestimo().getTimeInMillis();

									// Get difference between two dates in days
									diferencaDias = (int) diferencaMilissegundos / (24 * 60 * 60 * 1000);

									// Adiciona os dias no atributo do disco, dias alugado
									disco[x].setNumeroDiasAlugado(disco[x].getNumeroDiasAlugado() + diferencaDias);

									// Calcula os ciclos de 10 dias de empréstimo
									adicionaisMonetarios = (int) (diferencaDias) / 10;

									// Atualiza o faturamento com os adicionais
									EmprestimoVO.setFaturamento(EmprestimoVO.getFaturamento()
											+ (disco[x].getValorAluguel() * adicionaisMonetarios * 0.02f));

									// Verifica se há multa
									// a data de devolução efetiva ocorre depois da data de devolução proposta
									if (dataDevolucaoEfetiva[x]
											.after(emprestimo.getDataDevolucaoDisco()[posicaoDisco])) {
										// A multa será de 5% do valor de empréstimo do disco e se repetirá a cada 3
										// dias até a data de devolução

										adicionaisMonetarios = 0;

										// difereça entre o dia de devolução proposto e o dia de devolução atual
										diferencaMilissegundos = dataDevolucaoEfetiva[x].getTimeInMillis()
												- emprestimo.getDataDevolucaoDisco()[posicaoDisco].getTimeInMillis();

										// Get difference between two dates in days
										diferencaDias = (int) diferencaMilissegundos / (24 * 60 * 60 * 1000);

										// Calcula os ciclos de 3 dias de multa
										adicionaisMonetarios = (int) (diferencaDias) / 3;

										// Atualiza o faturamento com a multa
										EmprestimoVO.setFaturamento(EmprestimoVO.getFaturamento()
												+ (disco[x].getValorAluguel() * adicionaisMonetarios * 0.05f));
									}
								} else {
									System.out.println("Data de devolucao do disco " + disco[x].getTitulo()
											+ " nao pode ser antes da data de emprestimo!\n");
								}
							} else {
								System.out.println("Voce nao tem essa quantidade de discos para devolver\n");
							}
						}
					}
				}

				// Define os novos valores dos atributos do empréstimo
				if (discos.size() != 0) {
					DiscoVO[] vetorDiscos = new DiscoVO[discos.size()];
					int[] vetorQuantidades = new int[quantidades.size()];
					Calendar[] vetorDatas = new Calendar[datas.size()];

					// Conversão de array para vetor
					vetorDiscos = discos.toArray(vetorDiscos);

					for (int x = 0; x < quantidades.size(); x++) {
						vetorQuantidades[x] = quantidades.get(x);
					}

					vetorDatas = datas.toArray(vetorDatas);

					// Atualiza os atributos da classe emprestimo
					emprestimo.setDisco(vetorDiscos);
					emprestimo.setQuantidadeDisco(vetorQuantidades);
					emprestimo.setDataDevolucaoDisco(vetorDatas);
				} else {
					// Esvazia os vetores relacionados ao disco
					DiscoVO[] vetorDiscos = new DiscoVO[0];
					int[] vetorQuantidades = new int[0];
					Calendar[] vetorDatas = new Calendar[0];

					// Atualiza os atributos da classe emprestimo
					emprestimo.setDisco(vetorDiscos);
					emprestimo.setQuantidadeDisco(vetorQuantidades);
					emprestimo.setDataDevolucaoDisco(vetorDatas);
				}
			} else {
				System.out.println("Dados inconsistentes!");
			}
		}
	}

	public static void alugarLivroDisco(EmprestimoVO emprestimo, LivroVO[] livro, DiscoVO[] disco, int[] quantidade,
			Calendar[] dataDevolucaoEfetiva, ClienteVO cliente) {
		EmprestimoBO.alugarLivro(emprestimo, livro, quantidade, dataDevolucaoEfetiva, cliente);
		EmprestimoBO.alugarDisco(emprestimo, disco, quantidade, dataDevolucaoEfetiva, cliente);
	}

	public static void devolverLivroDisco(EmprestimoVO emprestimo, LivroVO[] livro, DiscoVO[] disco, int[] quantidade,
			Calendar[] dataDevolucaoEfetiva) {
		EmprestimoBO.devolverLivro(emprestimo, livro, quantidade, dataDevolucaoEfetiva);
		EmprestimoBO.devolverDisco(emprestimo, disco, quantidade, dataDevolucaoEfetiva);
	}

	public static void gerarRelatorio(Calendar dataInicio, Calendar dataFim) {

	}

	public static void gerarRelatorioCliente(ClienteVO cliente, Calendar dataInicio, Calendar dataFim) {

	}

	public static float simularMultaLivro(LivroVO[] livro, Calendar dataDevolucaoProposta,
			Calendar[] dataDevolucaoEfetiva) {
		float somaMultas = 0;

		// Verifica se os parâmetros não são nulos
		if ((livro != null) && (dataDevolucaoProposta != null) && (dataDevolucaoEfetiva != null)) {
			// Verifica se os vetores possuem o mesmo tamanho
			if (livro.length == dataDevolucaoEfetiva.length) {
				int numeroLivros = livro.length;
				int adicionaisMonetarios;
				int diferencaDias;
				long diferencaMilissegundos;

				// Percorre os livros
				for (int x = 0; x < numeroLivros; x++) {
					// Verifica se o livro e a data não são nulos
					if ((livro[x] != null) && (dataDevolucaoEfetiva[x] != null)) {
						// Verifica se há multa
						// a data de devolução efetiva ocorre depois da data de devolução proposta
						if (dataDevolucaoEfetiva[x].after(dataDevolucaoProposta)) {
							// A multa será de 5% do valor de empréstimo do livro e se repetirá a cada 3
							// dias até a data de devolução

							adicionaisMonetarios = 0;

							// difereça entre o dia de devolução proposto e o dia de devolução atual
							diferencaMilissegundos = dataDevolucaoEfetiva[x].getTimeInMillis()
									- dataDevolucaoProposta.getTimeInMillis();

							// Get difference between two dates in days
							diferencaDias = (int) diferencaMilissegundos / (24 * 60 * 60 * 1000);

							// Calcula os ciclos de 3 dias de multa
							adicionaisMonetarios = (int) (diferencaDias) / 3;

							// Atualiza o faturamento com a multa
							somaMultas += livro[x].getValorAluguel() * adicionaisMonetarios * 0.05f;
						}
					}
				}
			}
		}

		return somaMultas;
	}

	public static float simularMultaDisco(DiscoVO[] disco, Calendar dataDevolucaoProposta,
			Calendar[] dataDevolucaoEfetiva) {
		float somaMultas = 0;

		// Verifica se os parâmetros não são nulos
		if ((disco != null) && (dataDevolucaoProposta != null) && (dataDevolucaoEfetiva != null)) {
			// Verifica se os vetores possuem o mesmo tamanho
			if (disco.length == dataDevolucaoEfetiva.length) {
				int numeroDiscos = disco.length;
				int adicionaisMonetarios;
				int diferencaDias;
				long diferencaMilissegundos;

				// Percorre os discos
				for (int x = 0; x < numeroDiscos; x++) {
					// Verifica se o disco e a data não são nulos
					if ((disco[x] != null) && (dataDevolucaoEfetiva[x] != null)) {
						// Verifica se há multa
						// a data de devolução efetiva ocorre depois da data de devolução proposta
						if (dataDevolucaoEfetiva[x].after(dataDevolucaoProposta)) {
							// A multa será de 5% do valor de empréstimo do disco e se repetirá a cada 3
							// dias até a data de devolução

							adicionaisMonetarios = 0;

							// difereça entre o dia de devolução proposto e o dia de devolução atual
							diferencaMilissegundos = dataDevolucaoEfetiva[x].getTimeInMillis()
									- dataDevolucaoProposta.getTimeInMillis();

							// Get difference between two dates in days
							diferencaDias = (int) diferencaMilissegundos / (24 * 60 * 60 * 1000);

							// Calcula os ciclos de 3 dias de multa
							adicionaisMonetarios = (int) (diferencaDias) / 3;

							// Atualiza o faturamento com a multa
							somaMultas += disco[x].getValorAluguel() * adicionaisMonetarios * 0.05f;
						}
					}
				}
			}
		}

		return somaMultas;
	}

	public static float simularMultaLivroDisco(LivroVO[] livro, DiscoVO[] disco, Calendar dataDevolucaoProposta,
			Calendar[] dataDevolucaoEfetiva) {
		float somaMultas = 0;

		somaMultas += EmprestimoBO.simularMultaLivro(livro, dataDevolucaoProposta, dataDevolucaoEfetiva);
		somaMultas += EmprestimoBO.simularMultaDisco(disco, dataDevolucaoProposta, dataDevolucaoEfetiva);

		return somaMultas;
	}

	public static float simularEmprestimoLivro(LivroVO[] livro, int[] quantidade, Calendar dataEmprestimo,
			Calendar[] dataDevolucaoProposta) {
		float valorTotal = 0;

		// Verifica se os parâmetros não são nulos
		if ((livro != null) && (quantidade != null) && (dataDevolucaoProposta != null)) {
			// Verifica se os vetores possuem o mesmo tamanho
			if ((livro.length == dataDevolucaoProposta.length) && (livro.length == quantidade.length)) {
				int numeroLivros = livro.length;
				int adicionaisMonetarios;
				int diferencaDias;
				long diferencaMilissegundos;

				// Percorre os livros
				for (int x = 0; x < numeroLivros; x++) {
					// Verifica se o livro e a data não são nulos e a quantidade não é 0
					if ((livro[x] != null) && (dataDevolucaoProposta[x] != null) && (quantidade[x] != 0)) {
						// Verifica se a data de devolução é depois da data de empréstimo
						if (dataDevolucaoProposta[x].after(dataEmprestimo)) {
							// Primeira adição de valor
							valorTotal += livro[x].getValorAluguel() * quantidade[x];

							// Cálculo de adicionais e multa se existirem
							adicionaisMonetarios = 0;
							// A cada 10 dias de empréstimo há um adicional de 2% do valor de empréstimo do
							// livro no valor final

							// http://burnignorance.com/java-web-development-tips/calculating-difference-between-two-dates-using-java/
							diferencaMilissegundos = 0;
							diferencaDias = 0;

							// Get the difference between two dates in milliseconds
							// diferença entre data de empréstimo e data de devolução
							diferencaMilissegundos = dataDevolucaoProposta[x].getTimeInMillis()
									- dataEmprestimo.getTimeInMillis();

							// Get difference between two dates in days
							diferencaDias = (int) diferencaMilissegundos / (24 * 60 * 60 * 1000);

							// Calcula os ciclos de 10 dias de empréstimo
							adicionaisMonetarios = (int) (diferencaDias) / 10;

							// Adicionais ao valor total
							valorTotal += livro[x].getValorAluguel() * adicionaisMonetarios * 0.02f;
						}
					}
				}
			}
		}

		return valorTotal;
	}

	public static float simularEmprestimoDisco(DiscoVO[] disco, int[] quantidade, Calendar dataEmprestimo,
			Calendar[] dataDevolucaoProposta) {
		float valorTotal = 0;

		// Verifica se os parâmetros não são nulos
		if ((disco != null) && (quantidade != null) && (dataDevolucaoProposta != null)) {
			// Verifica se os vetores possuem o mesmo tamanho
			if ((disco.length == dataDevolucaoProposta.length) && (disco.length == quantidade.length)) {
				int numeroDiscos = disco.length;
				int adicionaisMonetarios;
				int diferencaDias;
				long diferencaMilissegundos;

				// Percorre os discos
				for (int x = 0; x < numeroDiscos; x++) {
					// Verifica se o disco e a data não são nulos e a quantidade não é 0
					if ((disco[x] != null) && (dataDevolucaoProposta[x] != null) && (quantidade[x] != 0)) {
						// Verifica se a data de devolução é depois da data de empréstimo
						if (dataDevolucaoProposta[x].after(dataEmprestimo)) {
							// Primeira adição de valor
							valorTotal += disco[x].getValorAluguel() * quantidade[x];

							// Cálculo de adicionais e multa se existirem
							adicionaisMonetarios = 0;
							// A cada 10 dias de empréstimo há um adicional de 2% do valor de empréstimo do
							// disco no valor final

							// http://burnignorance.com/java-web-development-tips/calculating-difference-between-two-dates-using-java/
							diferencaMilissegundos = 0;
							diferencaDias = 0;

							// Get the difference between two dates in milliseconds
							// diferença entre data de empréstimo e data de devolução
							diferencaMilissegundos = dataDevolucaoProposta[x].getTimeInMillis()
									- dataEmprestimo.getTimeInMillis();

							// Get difference between two dates in days
							diferencaDias = (int) diferencaMilissegundos / (24 * 60 * 60 * 1000);

							// Calcula os ciclos de 10 dias de empréstimo
							adicionaisMonetarios = (int) (diferencaDias) / 10;

							// Adicionais ao valor total
							valorTotal += disco[x].getValorAluguel() * adicionaisMonetarios * 0.02f;
						}
					}
				}
			}
		}

		return valorTotal;
	}

	public static float simularEmprestimoLivroDisco(LivroVO[] livro, DiscoVO[] disco, int[] quantidade,
			Calendar dataEmprestimo, Calendar[] dataDevolucaoProposta) {
		float valorTotal = 0;

		valorTotal += EmprestimoBO.simularEmprestimoLivro(livro, quantidade, dataEmprestimo, dataDevolucaoProposta);
		valorTotal += EmprestimoBO.simularEmprestimoDisco(disco, quantidade, dataEmprestimo, dataDevolucaoProposta);

		return valorTotal;
	}
}
