package br.edu.ufersa.ropke.locadoramaven.model.BO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.edu.ufersa.ropke.locadoramaven.exception.InvalidParameterException;
import br.edu.ufersa.ropke.locadoramaven.exception.NotFoundException;
import br.edu.ufersa.ropke.locadoramaven.model.DAO.DiscoDAO;
import br.edu.ufersa.ropke.locadoramaven.model.DAO.EmprestimoDAO;
import br.edu.ufersa.ropke.locadoramaven.model.DAO.LivroDAO;
import br.edu.ufersa.ropke.locadoramaven.model.DAO.ObjetoEmprestadoDAO;
import br.edu.ufersa.ropke.locadoramaven.model.VO.ClienteVO;
import br.edu.ufersa.ropke.locadoramaven.model.VO.DiscoVO;
import br.edu.ufersa.ropke.locadoramaven.model.VO.EmprestimoVO;
import br.edu.ufersa.ropke.locadoramaven.model.VO.LivroVO;
import br.edu.ufersa.ropke.locadoramaven.model.VO.ObjetoEmprestadoVO;

public class EmprestimoBO implements BaseInterBO<EmprestimoVO> {
	private static final EmprestimoDAO emprestimoDAO = new EmprestimoDAO();

	public boolean isInvalid(EmprestimoVO emprestimo) {
		if ((emprestimo != null) && (emprestimo.getDataOperacao() != null) && (emprestimo.getCliente() != null)
				&& !emprestimo.getObjetos().isEmpty()) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	public void cadastrar(EmprestimoVO emprestimo) throws InvalidParameterException {
		if (!isInvalid(emprestimo)) {
			emprestimoDAO.cadastrar(emprestimo);
		} else {
			throw new InvalidParameterException();
		}
	}

	@Override
	public void alterar(EmprestimoVO emprestimo) throws NotFoundException, InvalidParameterException {
		if (!isInvalid(emprestimo)) {
			ResultSet rs = emprestimoDAO.pesquisarId(emprestimo.getId());

			try {
				if (rs.next()) {
					emprestimoDAO.alterar(emprestimo);
				} else {
					throw new NotFoundException();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			throw new InvalidParameterException();
		}
	}

	@Override
	public void deletar(EmprestimoVO emprestimo) throws NotFoundException, InvalidParameterException {
		if (!isInvalid(emprestimo)) {
			ResultSet rs = emprestimoDAO.pesquisarId(emprestimo.getId());

			try {
				if (rs.next()) {
					emprestimoDAO.deletar(emprestimo);
				} else {
					throw new NotFoundException();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			throw new InvalidParameterException();
		}
	}

	@Override
	public List<EmprestimoVO> listar() {
		List<EmprestimoVO> emprestimos = new ArrayList<EmprestimoVO>();

		ResultSet rs = emprestimoDAO.listar();

		if (rs != null) {
			try {
				while (rs.next()) {
					emprestimos.add(getEmprestimo(rs));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return emprestimos;
	}

	@Override
	public EmprestimoVO pesquisarId(long idEmprestimo) throws NotFoundException, InvalidParameterException {
		if (idEmprestimo != 0) {
			ResultSet rs = emprestimoDAO.pesquisarId(idEmprestimo);

			try {
				if (rs.next()) {
					return getEmprestimo(rs);
				} else {
					throw new NotFoundException();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			throw new InvalidParameterException();
		}

		return null;
	}

	public List<EmprestimoVO> gerarRelatorio(Calendar dataInicio, Calendar dataFim) {
		if ((dataInicio != null) && (dataFim != null)) {
			List<EmprestimoVO> emprestimos = listar();
			List<EmprestimoVO> emprestimosValidos = new ArrayList<EmprestimoVO>();
			int numeroEmprestimos = emprestimos.size();

			// Obtém os empréstimos válidos
			for (int i = 0; i < numeroEmprestimos; i++) {
				if (emprestimos.get(i).getDataOperacao().after(dataInicio)
						&& emprestimos.get(i).getDataOperacao().before(dataFim)) {
					emprestimosValidos.add(emprestimos.get(i));
				}
			}

			return emprestimosValidos;
		}

		throw new InvalidParameterException();
	}

	public List<EmprestimoVO> gerarRelatorioCliente(ClienteVO cliente, Calendar dataInicio, Calendar dataFim) {
		if ((cliente != null) && (dataInicio != null) && (dataFim != null)) {
			List<EmprestimoVO> emprestimos = listar();
			List<EmprestimoVO> emprestimosValidos = new ArrayList<EmprestimoVO>();
			int numeroEmprestimos = emprestimos.size();

			// Obtém os empréstimos válidos
			for (int i = 0; i < numeroEmprestimos; i++) {
				if (emprestimos.get(i).getCliente().getCpf().equals(cliente.getCpf())
						&& emprestimos.get(i).getDataOperacao().after(dataInicio)
						&& emprestimos.get(i).getDataOperacao().before(dataFim)) {
					emprestimosValidos.add(emprestimos.get(i));
				}
			}

			return emprestimosValidos;
		}

		throw new InvalidParameterException();
	}

	private EmprestimoVO getEmprestimo(ResultSet rs) {
		try {
			ClienteBO clienteBO = new ClienteBO();
			ClienteVO cliente = clienteBO.pesquisarId(rs.getLong("id_cliente"));

			List<ObjetoEmprestadoVO> objetos = new ArrayList<ObjetoEmprestadoVO>();

			long idEmprestimo = rs.getLong("id");

			ResultSet rsObjetos = ObjetoEmprestadoDAO.listar(idEmprestimo);
			if (rsObjetos != null) {
				while (rsObjetos.next()) {
					// Procura por livros. Se o id_emprestavel do livro for igual ao id_emprestavel
					// do objeto então ele é adicionado como emprestado. Senão procura por discos
					boolean encontrado = false;
					LivroDAO livroDAO = new LivroDAO();
					ResultSet rsLivros = livroDAO.listar();
					if (rsLivros != null) {
						while (rsLivros.next() && !encontrado) {
							if (rsLivros.getLong("id_emprestavel") == rsObjetos.getLong("id_emprestavel")) {
								LivroVO livro = new LivroVO(rsLivros.getString("titulo"), rsLivros.getString("genero"),
										rsLivros.getInt("numero_paginas"), rsLivros.getInt("numero_exemplares"),
										rsLivros.getInt("numero_emprestimos"), rsLivros.getInt("numero_dias_alugado"),
										rsLivros.getInt("ano_lancamento"), rsLivros.getFloat("valor_aluguel"));
								livro.setId(rsLivros.getLong("id"));
								livro.setIdEmprestavel(rsLivros.getLong("id_emprestavel"));

								Calendar calendar = Calendar.getInstance();
								calendar.setTime(rsObjetos.getDate("data_devolucao"));

								objetos.add(new ObjetoEmprestadoVO(livro, calendar, rsObjetos.getInt("quantidade")));
								encontrado = true;
							}
						}
					}

					if (!encontrado) {
						DiscoDAO discoDAO = new DiscoDAO();
						ResultSet rsDiscos = discoDAO.listar();
						if (rsDiscos != null) {
							while (rsDiscos.next() && !encontrado) {
								if (rsDiscos.getLong("id_emprestavel") == rsObjetos.getLong("id_emprestavel")) {
									DiscoVO disco = new DiscoVO(rsDiscos.getString("titulo"),
											rsDiscos.getString("banda"), rsDiscos.getString("estilo"),
											rsDiscos.getInt("numero_exemplares"), rsDiscos.getInt("numero_emprestimos"),
											rsDiscos.getInt("numero_dias_alugado"), rsDiscos.getInt("ano_lancamento"),
											rsDiscos.getFloat("valor_aluguel"));
									disco.setId(rsDiscos.getLong("id"));
									disco.setIdEmprestavel(rsDiscos.getLong("id_emprestavel"));

									Calendar calendar = Calendar.getInstance();
									calendar.setTime(rsObjetos.getDate("data_devolucao"));

									objetos.add(
											new ObjetoEmprestadoVO(disco, calendar, rsObjetos.getInt("quantidade")));
									encontrado = true;
								}
							}
						}
					}
				}
			}

			EmprestimoVO emprestimo = new EmprestimoVO(cliente, objetos);
			emprestimo.setId(idEmprestimo);

			return emprestimo;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}
}
