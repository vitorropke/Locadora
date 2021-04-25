package br.edu.ufersa.ropke.model.BO;

import java.util.ArrayList;
import java.util.Calendar;

import br.edu.ufersa.ropke.model.VO.ClienteVO;
import br.edu.ufersa.ropke.model.VO.DiscoVO;
import br.edu.ufersa.ropke.model.VO.EmprestavelVO;
import br.edu.ufersa.ropke.model.VO.EmprestimoVO;
import br.edu.ufersa.ropke.model.VO.LivroVO;

public class EmprestimoBO {
	public static void alugar(EmprestimoVO emprestimo, int[] quantidade,
			Calendar[] dataDevolucaoProposta, ClienteVO cliente, EmprestavelVO[] ...emprestavel) {
		// Verifica se os dados estão consistentes
		// Se os vetores não são nulos
		/*
		if ((emprestimo != null) && (emprestavel != null) && (quantidade != null) && (dataDevolucaoProposta != null)
				&& (cliente != null)) {
			// Se os vetores possuem o mesmo tamanho
			if ((emprestavel.length == quantidade.length) && (emprestavel.length == dataDevolucaoProposta.length)) {
				// Insere os empréstimos que já existem
				Calendar dataAtual = Calendar.getInstance();
				int numeroEmprestaveis = emprestavel.length;
				int numeroEmprestaveisEmprestados = emprestimo.getEmprestavel().length;

				// Insere os atributos do empréstimo relacionados ao emprestavel
				ArrayList<EmprestavelVO> emprestaveis = new ArrayList<EmprestavelVO>();
				// Array de emprestaveis
				for (int x = 0; x < numeroEmprestaveisEmprestados; x++) {
					emprestaveis.add(emprestimo.getEmprestavel()[x]);
				}
				ArrayList<Integer> quantidades = new ArrayList<Integer>();
				// Array de quantidade de emprestaveis
				for (int x = 0; x < numeroEmprestaveisEmprestados; x++) {
					quantidades.add(emprestimo.getQuantidadeEmprestavel()[x]);
				}
				ArrayList<Calendar> datas = new ArrayList<Calendar>();
				// Array com datas de devolução
				for (int x = 0; x < numeroEmprestaveisEmprestados; x++) {
					datas.add(emprestimo.getDataDevolucaoEmprestavel()[x]);
				}

				// Percorre os emprestaveis
				for (int x = 0; x < numeroEmprestaveis; x++) {
					// Verifica se o emprestavel e a data não são nulos
					if ((emprestavel[x] != null) && (dataDevolucaoProposta[x] != null)) {
						// Verifica se existe quantidade suficiente para ser emprestado
						if (emprestavel[x].getNumeroExemplares() >= quantidade[x]) {
							// Verifica se a data de devolução proposta não é no passado
							if (dataDevolucaoProposta[x].after(dataAtual)) {
								// Verifica se a quantidade é maior que 0
								if (quantidade[x] > 0) {
									emprestaveis.add(emprestavel[x]);
									quantidades.add(quantidade[x]);
									datas.add(dataDevolucaoProposta[x]);

									// Atualiza alguns atributos do emprestavel
									// Número de exemplares
									emprestavel[x].setNumeroExemplares(emprestavel[x].getNumeroExemplares() - quantidade[x]);
									// Número de empréstimos
									emprestavel[x].setNumeroEmprestimos(emprestavel[x].getNumeroEmprestimos() + 1);

									// Atualiza o faturamento
									EmprestimoVO.setFaturamento(EmprestimoVO.getFaturamento()
											+ (emprestavel[x].getValorAluguel() * quantidade[x]));
								} else {
									System.out.println("Quantidade proposta do emprestavel " + emprestavel[x].getTitulo()
											+ " nao pode ser 0 ou negativo!\n");
								}
							} else {
								System.out.println("Data de devolucao do emprestavel " + emprestavel[x].getTitulo()
										+ " nao pode ser no passado!\n");
							}
						} else {
							System.out
									.println("Emprestavel " + emprestavel[x].getTitulo() + " nao possui quantidade suficiente!\n");
						}
					}
				}

				// Verifica se existem emprestaveis válidos
				if (emprestaveis.size() != 0) {
					EmprestavelVO[] vetorEmprestaveis = new EmprestavelVO[emprestaveis.size()];
					int[] vetorQuantidades = new int[quantidades.size()];
					Calendar[] vetorDatas = new Calendar[datas.size()];

					// Conversão de array para vetor
					vetorEmprestaveis = emprestaveis.toArray(vetorEmprestaveis);

					for (int x = 0; x < quantidades.size(); x++) {
						vetorQuantidades[x] = quantidades.get(x);
					}

					vetorDatas = datas.toArray(vetorDatas);

					// Atualiza os atributos da classe emprestimo
					emprestimo.setEmprestavel(vetorEmprestaveis);
					emprestimo.setQuantidadeEmprestavel(vetorQuantidades);
					emprestimo.setDataDevolucaoEmprestavel(vetorDatas);
					emprestimo.setCliente(cliente);
				} else {
					System.out.println("Sem emprestaveis validos!");
				}
			} else {
				System.out.println("Dados inconsistentes!");
			}
		}
		*/
	}
/*
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
*/
	public static void devolver(EmprestimoVO emprestimo, int[] quantidade,
			Calendar[] dataDevolucaoEfetiva, EmprestavelVO ...emprestavel) {
		/*// Verifica se os dados estão consistentes
		// Se os vetores e objetos não são nulos
		if ((emprestimo != null) && (emprestimo.getEmprestavel() != null) && (emprestavel != null) && (quantidade != null)
				&& (dataDevolucaoEfetiva != null)) {
			// Se os vetores possuem o mesmo tamanho
			if ((emprestavel.length == quantidade.length) && (emprestavel.length == dataDevolucaoEfetiva.length)) {
				int numeroEmprestaveis = emprestavel.length;
				int numeroEmprestaveisEmprestados = emprestimo.getEmprestavel().length;

				// Insere os atributos do empréstimo relacionados ao emprestavel
				ArrayList<EmprestavelVO> emprestaveis = new ArrayList<EmprestavelVO>();
				// Array de emprestaveis
				for (int x = 0; x < numeroEmprestaveisEmprestados; x++) {
					emprestaveis.add(emprestimo.getEmprestavel()[x]);
				}
				ArrayList<Integer> quantidades = new ArrayList<Integer>();
				// Array de quantidade de emprestaveis
				for (int x = 0; x < numeroEmprestaveisEmprestados; x++) {
					quantidades.add(emprestimo.getQuantidadeEmprestavel()[x]);
				}
				ArrayList<Calendar> datas = new ArrayList<Calendar>();
				// Array com datas de devolução
				for (int x = 0; x < numeroEmprestaveisEmprestados; x++) {
					datas.add(emprestimo.getDataDevolucaoEmprestavel()[x]);
				}

				int posicaoEmprestavel;
				int quantidadeEmprestaveis;
				int adicionaisMonetarios;
				int diferencaDias;
				long diferencaMilissegundos;
				// Percorre os emprestaveis
				for (int x = 0; x < numeroEmprestaveis; x++) {
					// Verifica se o emprestavel e a data não são nulos e a quantidade não é 0
					if ((emprestavel[x] != null) && (dataDevolucaoEfetiva[x] != null) && (quantidade[x] != 0)) {
						// Obtem a posição no vetor de emprestaveis
						posicaoEmprestavel = emprestaveis.indexOf(emprestavel[x]);

						// Faz as modificações quando alguma posição é encontrada
						if (posicaoEmprestavel == -1) {
							System.out.println("Emprestavel nao emprestado!");
						} else {
							// Verifica se a quantidade a ser devolvida é menor ou igual a quantidade de
							// emprestaveis que foram emprestados
							if (quantidade[x] <= emprestimo.getQuantidadeEmprestavel()[posicaoEmprestavel]) {
								// Verifica se a data de devolução é depois da data de empréstimo
								if (dataDevolucaoEfetiva[x].after(emprestimo.getDataEmprestimo())) {
									// Repõe os emprestaveis
									emprestavel[x].setNumeroExemplares(emprestavel[x].getNumeroExemplares() + quantidade[x]);

									// Obtem a quantidade de emprestaveis e subtrai da quantidade devolvida
									quantidadeEmprestaveis = quantidades.get(posicaoEmprestavel) - quantidade[x];

									// Adiciona as modificações no vetor
									// Se sobrarem emprestaveis, atualiza a quantidade no vetor
									if (quantidadeEmprestaveis != 0) {
										quantidades.set(posicaoEmprestavel, quantidadeEmprestaveis);
									} else {
										// Se não sobrarem emprestaveis, remove essa posição dos vetores
										emprestaveis.remove(posicaoEmprestavel);
										quantidades.remove(posicaoEmprestavel);
										datas.remove(posicaoEmprestavel);
									}

									// Cálculo de adicionais e multa se existirem
									adicionaisMonetarios = 0;
									// A cada 10 dias de empréstimo há um adicional de 2% do valor de empréstimo do
									// emprestavel no valor final

									// http://burnignorance.com/java-web-development-tips/calculating-difference-between-two-dates-using-java/
									diferencaMilissegundos = 0;
									diferencaDias = 0;

									// Get the difference between two dates in milliseconds
									// diferença entre data de empréstimo e data de devolução
									diferencaMilissegundos = dataDevolucaoEfetiva[x].getTimeInMillis()
											- emprestimo.getDataEmprestimo().getTimeInMillis();

									// Get difference between two dates in days
									diferencaDias = (int) diferencaMilissegundos / (24 * 60 * 60 * 1000);

									// Adiciona os dias no atributo do emprestavel, dias alugado
									emprestavel[x].setNumeroDiasAlugado(emprestavel[x].getNumeroDiasAlugado() + diferencaDias);

									// Calcula os ciclos de 10 dias de empréstimo
									adicionaisMonetarios = (int) (diferencaDias) / 10;

									// Atualiza o faturamento com os adicionais
									EmprestimoVO.setFaturamento(EmprestimoVO.getFaturamento()
											+ (emprestavel[x].getValorAluguel() * adicionaisMonetarios * 0.02f));

									// Verifica se há multa
									// a data de devolução efetiva ocorre depois da data de devolução proposta
									if (dataDevolucaoEfetiva[x]
											.after(emprestimo.getDataDevolucaoEmprestavel()[posicaoEmprestavel])) {
										// A multa será de 5% do valor de empréstimo do emprestavel e se repetirá a cada 3
										// dias até a data de devolução

										adicionaisMonetarios = 0;

										// difereça entre o dia de devolução proposto e o dia de devolução atual
										diferencaMilissegundos = dataDevolucaoEfetiva[x].getTimeInMillis()
												- emprestimo.getDataDevolucaoEmprestavel()[posicaoEmprestavel].getTimeInMillis();

										// Get difference between two dates in days
										diferencaDias = (int) diferencaMilissegundos / (24 * 60 * 60 * 1000);

										// Calcula os ciclos de 3 dias de multa
										adicionaisMonetarios = (int) (diferencaDias) / 3;

										// Atualiza o faturamento com a multa
										EmprestimoVO.setFaturamento(EmprestimoVO.getFaturamento()
												+ (emprestavel[x].getValorAluguel() * adicionaisMonetarios * 0.05f));
									}
								} else {
									System.out.println("Data de devolucao do emprestavel " + emprestavel[x].getTitulo()
											+ " nao pode ser antes da data de emprestimo!\n");
								}
							} else {
								System.out.println("Voce nao tem essa quantidade de emprestaveis para devolver\n");
							}
						}
					}
				}

				// Define os novos valores dos atributos do empréstimo
				if (emprestaveis.size() != 0) {
					EmprestavelVO[] vetorEmprestaveis = new EmprestavelVO[emprestaveis.size()];
					int[] vetorQuantidades = new int[quantidades.size()];
					Calendar[] vetorDatas = new Calendar[datas.size()];

					// Conversão de array para vetor
					vetorEmprestaveis = emprestaveis.toArray(vetorEmprestaveis);

					for (int x = 0; x < quantidades.size(); x++) {
						vetorQuantidades[x] = quantidades.get(x);
					}

					vetorDatas = datas.toArray(vetorDatas);

					// Atualiza os atributos da classe emprestimo
					emprestimo.setEmprestavel(vetorEmprestaveis);
					emprestimo.setQuantidadeEmprestavel(vetorQuantidades);
					emprestimo.setDataDevolucaoEmprestavel(vetorDatas);
				} else {
					// Esvazia os vetores relacionados ao emprestavel
					EmprestavelVO[] vetorEmprestaveis = new EmprestavelVO[0];
					int[] vetorQuantidades = new int[0];
					Calendar[] vetorDatas = new Calendar[0];

					// Atualiza os atributos da classe emprestimo
					emprestimo.setEmprestavel(vetorEmprestaveis);
					emprestimo.setQuantidadeEmprestavel(vetorQuantidades);
					emprestimo.setDataDevolucaoEmprestavel(vetorDatas);
				}
			} else {
				System.out.println("Dados inconsistentes!");
			}
		}*/
	}
/*
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
*/
	public static void gerarRelatorio(Calendar dataInicio, Calendar dataFim) {

	}

	public static void gerarRelatorioCliente(ClienteVO cliente, Calendar dataInicio, Calendar dataFim) {

	}

	public static float simularMulta(Calendar dataDevolucaoProposta,
			Calendar[] dataDevolucaoEfetiva, EmprestavelVO ...emprestavel) {
		float somaMultas = 0;

		// Verifica se os parâmetros não são nulos
		if ((emprestavel != null) && (dataDevolucaoProposta != null) && (dataDevolucaoEfetiva != null)) {
			// Verifica se os vetores possuem o mesmo tamanho
			if (emprestavel.length == dataDevolucaoEfetiva.length) {
				int numeroEmprestaveis = emprestavel.length;
				int adicionaisMonetarios;
				int diferencaDias;
				long diferencaMilissegundos;

				// Percorre os emprestaveis
				for (int x = 0; x < numeroEmprestaveis; x++) {
					// Verifica se o emprestavel e a data não são nulos
					if ((emprestavel[x] != null) && (dataDevolucaoEfetiva[x] != null)) {
						// Verifica se há multa
						// a data de devolução efetiva ocorre depois da data de devolução proposta
						if (dataDevolucaoEfetiva[x].after(dataDevolucaoProposta)) {
							// A multa será de 5% do valor de empréstimo do emprestavel e se repetirá a cada 3
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
							somaMultas += emprestavel[x].getValorAluguel() * adicionaisMonetarios * 0.05f;
						}
					}
				}
			}
		}

		return somaMultas;
	}

	public static float simularEmprestimo(int[] quantidade, Calendar dataEmprestimo,
			Calendar[] dataDevolucaoProposta, EmprestavelVO ...emprestavel) {
		float valorTotal = 0;

		// Verifica se os parâmetros não são nulos
		if ((emprestavel != null) && (quantidade != null) && (dataDevolucaoProposta != null)) {
			// Verifica se os vetores possuem o mesmo tamanho
			if ((emprestavel.length == dataDevolucaoProposta.length) && (emprestavel.length == quantidade.length)) {
				int numeroEmprestaveis = emprestavel.length;
				int adicionaisMonetarios;
				int diferencaDias;
				long diferencaMilissegundos;

				// Percorre os emprestaveis
				for (int x = 0; x < numeroEmprestaveis; x++) {
					// Verifica se o emprestavel e a data não são nulos e a quantidade não é 0
					if ((emprestavel[x] != null) && (dataDevolucaoProposta[x] != null) && (quantidade[x] != 0)) {
						// Verifica se a data de devolução é depois da data de empréstimo
						if (dataDevolucaoProposta[x].after(dataEmprestimo)) {
							// Primeira adição de valor
							valorTotal += emprestavel[x].getValorAluguel() * quantidade[x];

							// Cálculo de adicionais e multa se existirem
							adicionaisMonetarios = 0;
							// A cada 10 dias de empréstimo há um adicional de 2% do valor de empréstimo do
							// emprestavel no valor final

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
							valorTotal += emprestavel[x].getValorAluguel() * adicionaisMonetarios * 0.02f;
						}
					}
				}
			}
		}

		return valorTotal;
	}
}
