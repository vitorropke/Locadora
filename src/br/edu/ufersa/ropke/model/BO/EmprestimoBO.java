package br.edu.ufersa.ropke.model.BO;

import java.util.ArrayList;
import java.util.Calendar;

import br.edu.ufersa.ropke.model.DAO.EmprestimoDAO;
import br.edu.ufersa.ropke.model.VO.ClienteVO;
import br.edu.ufersa.ropke.model.VO.DiscoVO;
import br.edu.ufersa.ropke.model.VO.EmprestavelVO;
import br.edu.ufersa.ropke.model.VO.EmprestimoVO;
import br.edu.ufersa.ropke.model.VO.LivroVO;

public class EmprestimoBO extends OperacaoBO {
	public static void cadastrar(EmprestimoVO emprestimo) {
		// Verifica se a entrada de argumentos não é nula
		if (emprestimo != null) {
			// Verifica se parâmetros importantes não são nulos
			if ((emprestimo.getIdEmprestimo() != -1) && (emprestimo.getCliente() != null)
					&& (emprestimo.getDataEmprestimo() != null)) {
				// Verifica se o emprestimo não existe no sistema
				if (EmprestimoDAO.pesquisar(emprestimo) == null) {
					EmprestimoDAO.cadastrar(emprestimo);
				}
			}
		}
	}
	
	public static void alterar(EmprestimoVO emprestimo) {
		// Verifica se a entrada de argumentos não é nula
		if (emprestimo != null) {
			// Verifica se parâmetros importantes não são nulos
			if ((emprestimo.getIdEmprestimo() != -1) && (emprestimo.getCliente() != null)
					&& (emprestimo.getDataEmprestimo() != null)) {
				// Verifica se o emprestimo existe no sistema
				if (EmprestimoDAO.pesquisar(emprestimo) != null) {
					EmprestimoDAO.alterar(emprestimo);
				}
			}
		}
	}
	
	public static void deletar(EmprestimoVO emprestimo) {
		// Verifica se a entrada de argumentos não é nula
		if (emprestimo != null) {
			// Verifica se parâmetros importantes não são nulos
			if ((emprestimo.getIdEmprestimo() != -1) && (emprestimo.getCliente() != null)
					&& (emprestimo.getDataEmprestimo() != null)) {
				// Verifica se o emprestimo existe no sistema
				if (EmprestimoDAO.pesquisar(emprestimo) != null) {
					EmprestimoDAO.deletar(emprestimo);
				}
			}
		}
	}

	public static EmprestimoVO pesquisar(EmprestimoVO emprestimo) {
		if (emprestimo != null) {
			if ((emprestimo.getIdEmprestimo() != -1) && (emprestimo.getCliente() != null)
					&& (emprestimo.getDataEmprestimo() != null)) {
				return EmprestimoDAO.pesquisar(emprestimo);
			} else {
				return null;
			}
		} else {
			return null;
		}
	}

	public static void alugar(EmprestimoVO emprestimo, int[] quantidade, Calendar[] dataDevolucaoProposta,
			ClienteVO cliente, EmprestavelVO[] emprestavel) {
		// Verifica se os dados estão consistentes
		// Se os vetores não são nulos
		if ((emprestimo != null) && (emprestavel != null) && (quantidade != null) && (dataDevolucaoProposta != null)
				&& (cliente != null)) {
			// Se o emprestável é um livro ou não
			boolean isLivro = false;
			if (emprestavel[0].getClass() == LivroVO.class) {
				isLivro = true;
			}

			// Se os vetores possuem o mesmo tamanho
			if ((emprestavel.length == quantidade.length) && (emprestavel.length == dataDevolucaoProposta.length)) {
				// Insere os empréstimos que já existem
				Calendar dataAtual = Calendar.getInstance();
				int numeroEmprestaveis = emprestavel.length;
				int numeroEmprestaveisEmprestadosAnteriormente;

				// Insere os atributos do empréstimo relacionados ao emprestavel
				ArrayList<EmprestavelVO> emprestaveis = new ArrayList<EmprestavelVO>();
				ArrayList<Integer> quantidades = new ArrayList<Integer>();
				ArrayList<Calendar> datas = new ArrayList<Calendar>();

				if (isLivro) {
					if (emprestimo.getLivro() != null) {
						// Inicializa arrays com livros

						numeroEmprestaveisEmprestadosAnteriormente = emprestimo.getLivro().length;

						// Array de emprestaveis antigos
						for (int x = 0; x < numeroEmprestaveisEmprestadosAnteriormente; x++) {
							emprestaveis.add(emprestimo.getLivro()[x]);
						}

						// Array de quantidade de emprestaveis
						for (int x = 0; x < numeroEmprestaveisEmprestadosAnteriormente; x++) {
							quantidades.add(emprestimo.getQuantidadeLivro()[x]);
						}

						// Array com datas de devolução
						for (int x = 0; x < numeroEmprestaveisEmprestadosAnteriormente; x++) {
							datas.add(emprestimo.getDataDevolucaoLivro()[x]);
						}
					}
				} else {
					if (emprestimo.getDisco() != null) {
						// Inicializa arrays com discos

						numeroEmprestaveisEmprestadosAnteriormente = emprestimo.getDisco().length;

						// Array de emprestaveis antigos
						for (int x = 0; x < numeroEmprestaveisEmprestadosAnteriormente; x++) {
							emprestaveis.add(emprestimo.getDisco()[x]);
						}
						// Array de quantidade de emprestaveis
						for (int x = 0; x < numeroEmprestaveisEmprestadosAnteriormente; x++) {
							quantidades.add(emprestimo.getQuantidadeDisco()[x]);
						}
						// Array com datas de devolução
						for (int x = 0; x < numeroEmprestaveisEmprestadosAnteriormente; x++) {
							datas.add(emprestimo.getDataDevolucaoDisco()[x]);
						}
					}
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
									emprestavel[x]
											.setNumeroExemplares(emprestavel[x].getNumeroExemplares() - quantidade[x]);
									// Número de empréstimos
									emprestavel[x].setNumeroEmprestimos(emprestavel[x].getNumeroEmprestimos() + 1);

									// Atualiza o faturamento
									EmprestimoVO.setFaturamento(EmprestimoVO.getFaturamento()
											+ (emprestavel[x].getValorAluguel() * quantidade[x]));
								} else {
									System.out.println("Quantidade proposta do emprestavel "
											+ emprestavel[x].getTitulo() + " nao pode ser 0 ou negativo!\n");
								}
							} else {
								System.out.println("Data de devolucao do emprestavel " + emprestavel[x].getTitulo()
										+ " nao pode ser no passado!\n");
							}
						} else {
							System.out.println("Emprestavel " + emprestavel[x].getTitulo()
									+ " nao possui quantidade suficiente!\n");
						}
					}
				}

				// Verifica se existem emprestaveis válidos
				int tamanhoVetorEmprestaveis = emprestaveis.size();
				if (tamanhoVetorEmprestaveis != 0) {
					EmprestavelVO[] vetorEmprestaveis = new EmprestavelVO[tamanhoVetorEmprestaveis];
					int[] vetorQuantidades = new int[quantidades.size()];
					Calendar[] vetorDatas = new Calendar[datas.size()];

					// Conversão de array para vetor
					vetorEmprestaveis = emprestaveis.toArray(vetorEmprestaveis);

					for (int x = 0; x < quantidades.size(); x++) {
						vetorQuantidades[x] = quantidades.get(x);
					}

					vetorDatas = datas.toArray(vetorDatas);

					// Atualiza os atributos da classe emprestimo
					if (isLivro) {
						// Atributos são livros

						LivroVO[] livros = new LivroVO[tamanhoVetorEmprestaveis];

						// cast de cada posição do vetor emprestável para livro
						for (int i = 0; i < tamanhoVetorEmprestaveis; i++) {
							livros[i] = (LivroVO) vetorEmprestaveis[i];
						}

						emprestimo.setLivro(livros);
						emprestimo.setQuantidadeLivro(vetorQuantidades);
						emprestimo.setDataDevolucaoLivro(vetorDatas);
					} else {
						// Atributos são discos

						DiscoVO[] discos = new DiscoVO[tamanhoVetorEmprestaveis];

						// cast de cada posição do vetor emprestável para disco
						for (int i = 0; i < tamanhoVetorEmprestaveis; i++) {
							discos[i] = (DiscoVO) vetorEmprestaveis[i];
						}

						emprestimo.setDisco(discos);
						emprestimo.setQuantidadeDisco(vetorQuantidades);
						emprestimo.setDataDevolucaoDisco(vetorDatas);
					}

					emprestimo.setCliente(cliente);
					
					// Adiciona no arquivo
					if (EmprestimoBO.pesquisar(emprestimo) == null) {
						EmprestimoBO.cadastrar(emprestimo);
					} else {
						EmprestimoBO.alterar(emprestimo);
					}

				} else {
					System.out.println("Sem emprestaveis validos!");
				}
			} else {
				System.out.println("Dados inconsistentes!");
			}
		}
	}

	public static void devolver(EmprestimoVO emprestimo, int[] quantidade, Calendar[] dataDevolucaoEfetiva,
			EmprestavelVO[] emprestavel) {

		// Verifica se os dados estão consistentes
		// Se os vetores e objetos não são nulos
		if ((emprestimo != null) && (emprestavel != null) && (quantidade != null) && (dataDevolucaoEfetiva != null)) {
			// Se o emprestável é um livro ou não
			boolean isLivro = false;
			if (emprestavel[0].getClass() == LivroVO.class) {
				isLivro = true;
			}

			// Sai da função se não existirem emprestáveis emprestados
			if (isLivro) {
				// Se não houver livros a serem devolvidos
				if (emprestimo.getLivro() == null) {
					System.out.println("Sem livros a serem devolvidos");
					return;
				}
			} else {
				// Se não houver discos a serem devolvidos
				if (emprestimo.getDisco() == null) {
					System.out.println("Sem discos a serem devolvidos");
					return;
				}
			}

			// Se os vetores possuem o mesmo tamanho
			if ((emprestavel.length == quantidade.length) && (emprestavel.length == dataDevolucaoEfetiva.length)) {
				int numeroEmprestaveis = emprestavel.length;
				int numeroEmprestaveisEmprestadosAnteriormente;

				// Insere os atributos do empréstimo relacionados ao emprestavel
				ArrayList<EmprestavelVO> emprestaveis = new ArrayList<EmprestavelVO>();
				ArrayList<Integer> quantidades = new ArrayList<Integer>();
				ArrayList<Calendar> datas = new ArrayList<Calendar>();

				if (isLivro) {
					// Inicializa arrays com livros

					numeroEmprestaveisEmprestadosAnteriormente = emprestimo.getLivro().length;

					// Array de emprestaveis antigos
					for (int x = 0; x < numeroEmprestaveisEmprestadosAnteriormente; x++) {
						emprestaveis.add(emprestimo.getLivro()[x]);
					}

					// Array de quantidade de emprestaveis
					for (int x = 0; x < numeroEmprestaveisEmprestadosAnteriormente; x++) {
						quantidades.add(emprestimo.getQuantidadeLivro()[x]);
					}

					// Array com datas de devolução
					for (int x = 0; x < numeroEmprestaveisEmprestadosAnteriormente; x++) {
						datas.add(emprestimo.getDataDevolucaoLivro()[x]);
					}

				} else {
					// Inicializa arrays com discos

					numeroEmprestaveisEmprestadosAnteriormente = emprestimo.getDisco().length;

					// Array de emprestaveis antigos
					for (int x = 0; x < numeroEmprestaveisEmprestadosAnteriormente; x++) {
						emprestaveis.add(emprestimo.getDisco()[x]);
					}
					// Array de quantidade de emprestaveis
					for (int x = 0; x < numeroEmprestaveisEmprestadosAnteriormente; x++) {
						quantidades.add(emprestimo.getQuantidadeDisco()[x]);
					}
					// Array com datas de devolução
					for (int x = 0; x < numeroEmprestaveisEmprestadosAnteriormente; x++) {
						datas.add(emprestimo.getDataDevolucaoDisco()[x]);
					}

				}

				int posicaoEmprestavel;
				int quantidadeEmprestaveis;
				int adicionaisMonetarios;
				int diferencaDias;
				int quantidadeDeterminadoEmprestavelEmprestadoAnteriormente;
				long diferencaMilissegundos;
				Calendar dataDevolucaoDeterminadoEmprestavelEmprestadoAnteriormente;
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
							// emprestaveis que foram emprestados anteriormente

							if (isLivro) {
								quantidadeDeterminadoEmprestavelEmprestadoAnteriormente = emprestimo
										.getQuantidadeLivro()[posicaoEmprestavel];
								dataDevolucaoDeterminadoEmprestavelEmprestadoAnteriormente = emprestimo
										.getDataDevolucaoLivro()[posicaoEmprestavel];
							} else {
								quantidadeDeterminadoEmprestavelEmprestadoAnteriormente = emprestimo
										.getQuantidadeDisco()[posicaoEmprestavel];
								dataDevolucaoDeterminadoEmprestavelEmprestadoAnteriormente = emprestimo
										.getDataDevolucaoDisco()[posicaoEmprestavel];
							}
							if (quantidade[x] <= quantidadeDeterminadoEmprestavelEmprestadoAnteriormente) {
								// Verifica se a data de devolução é depois da data de empréstimo
								if (dataDevolucaoEfetiva[x].after(emprestimo.getDataEmprestimo())) {
									// Repõe os emprestaveis
									emprestavel[x]
											.setNumeroExemplares(emprestavel[x].getNumeroExemplares() + quantidade[x]);

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
									emprestavel[x].setNumeroDiasAlugado(
											emprestavel[x].getNumeroDiasAlugado() + diferencaDias);

									// Calcula os ciclos de 10 dias de empréstimo
									adicionaisMonetarios = (int) (diferencaDias) / 10;

									// Atualiza o faturamento com os adicionais
									EmprestimoVO.setFaturamento(EmprestimoVO.getFaturamento()
											+ (emprestavel[x].getValorAluguel() * adicionaisMonetarios * 0.02f));

									// Verifica se há multa
									// a data de devolução efetiva ocorre depois da data de devolução proposta
									if (dataDevolucaoEfetiva[x]
											.after(dataDevolucaoDeterminadoEmprestavelEmprestadoAnteriormente)) {
										// A multa será de 5% do valor de empréstimo do emprestavel e se repetirá a cada
										// 3
										// dias até a data de devolução

										adicionaisMonetarios = 0;

										// difereça entre o dia de devolução proposto e o dia de devolução atual
										diferencaMilissegundos = dataDevolucaoEfetiva[x].getTimeInMillis()
												- dataDevolucaoDeterminadoEmprestavelEmprestadoAnteriormente
														.getTimeInMillis();

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
				int tamanhoVetorEmprestaveis = emprestaveis.size();
				if (tamanhoVetorEmprestaveis != 0) {
					EmprestavelVO[] vetorEmprestaveis = new EmprestavelVO[tamanhoVetorEmprestaveis];
					int[] vetorQuantidades = new int[quantidades.size()];
					Calendar[] vetorDatas = new Calendar[datas.size()];

					// Conversão de array para vetor
					vetorEmprestaveis = emprestaveis.toArray(vetorEmprestaveis);

					for (int x = 0; x < quantidades.size(); x++) {
						vetorQuantidades[x] = quantidades.get(x);
					}

					vetorDatas = datas.toArray(vetorDatas);

					// Atualiza os atributos da classe emprestimo
					if (isLivro) {
						// Atributos são livros

						LivroVO[] livros = new LivroVO[tamanhoVetorEmprestaveis];

						// cast de cada posição do vetor emprestável para livro
						for (int i = 0; i < tamanhoVetorEmprestaveis; i++) {
							livros[i] = (LivroVO) vetorEmprestaveis[i];
						}

						emprestimo.setLivro(livros);
						emprestimo.setQuantidadeLivro(vetorQuantidades);
						emprestimo.setDataDevolucaoLivro(vetorDatas);
					} else {
						// Atributos são discos

						DiscoVO[] discos = new DiscoVO[tamanhoVetorEmprestaveis];

						// cast de cada posição do vetor emprestável para disco
						for (int i = 0; i < tamanhoVetorEmprestaveis; i++) {
							discos[i] = (DiscoVO) vetorEmprestaveis[i];
						}

						emprestimo.setDisco(discos);
						emprestimo.setQuantidadeDisco(vetorQuantidades);
						emprestimo.setDataDevolucaoDisco(vetorDatas);
					}
				} else {
					// Quando não sobrarem emprestáveis
					if (isLivro) {
						// Esvazia os vetores relacionados ao livro
						LivroVO[] vetorLivros = new LivroVO[0];
						int[] vetorQuantidades = new int[0];
						Calendar[] vetorDatas = new Calendar[0];

						// Atualiza os atributos da classe emprestimo
						emprestimo.setLivro(vetorLivros);
						emprestimo.setQuantidadeLivro(vetorQuantidades);
						emprestimo.setDataDevolucaoLivro(vetorDatas);
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
				}
			} else {
				System.out.println("Dados inconsistentes!");
			}
		}
	}

	public static void gerarRelatorio(Calendar dataInicio, Calendar dataFim) {

	}

	public static void gerarRelatorioCliente(ClienteVO cliente, Calendar dataInicio, Calendar dataFim) {

	}

	public static float simularMulta(Calendar dataDevolucaoProposta, Calendar[] dataDevolucaoEfetiva,
			EmprestavelVO[] emprestavel) {
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
							// A multa será de 5% do valor de empréstimo do emprestavel e se repetirá a cada
							// 3
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

	public static float simularEmprestimo(int[] quantidade, Calendar dataEmprestimo, Calendar[] dataDevolucaoProposta,
			EmprestavelVO[] emprestavel) {
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
