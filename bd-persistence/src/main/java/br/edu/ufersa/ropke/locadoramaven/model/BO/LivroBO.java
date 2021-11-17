package br.edu.ufersa.ropke.locadoramaven.model.BO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.ufersa.ropke.locadoramaven.exception.InvalidParameterException;
import br.edu.ufersa.ropke.locadoramaven.exception.NotFoundException;
import br.edu.ufersa.ropke.locadoramaven.model.DAO.LivroDAO;
import br.edu.ufersa.ropke.locadoramaven.model.VO.LivroVO;

public class LivroBO extends EmprestavelBO<LivroVO> {
	private static final LivroDAO livroDAO = new LivroDAO();

	@Override
	public boolean isInvalid(LivroVO livro) {
		if (!super.isInvalid(livro) && (livro.getGenero() != null) && (livro.getNumeroPaginas() != 0)) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	public void cadastrar(LivroVO livro) throws InvalidParameterException {
		if (!isInvalid(livro)) {
			livroDAO.cadastrar(livro);
		} else {
			throw new InvalidParameterException();
		}
	}

	@Override
	public void alterar(LivroVO livro) throws NotFoundException, InvalidParameterException {
		if (!isInvalid(livro)) {
			ResultSet rs = livroDAO.pesquisarId(livro.getId());

			try {
				if (rs.next()) {
					livroDAO.alterar(livro);
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
	public void deletar(LivroVO livro) throws NotFoundException, InvalidParameterException {
		if (!isInvalid(livro)) {
			ResultSet rs = livroDAO.pesquisarId(livro.getId());

			try {
				if (rs.next()) {
					livroDAO.deletar(livro);
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
	public List<LivroVO> listar() {
		List<LivroVO> livros = new ArrayList<LivroVO>();

		ResultSet rs = livroDAO.listar();

		try {
			while (rs.next()) {
				livros.add(getLivro(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return livros;
	}

	@Override
	public LivroVO pesquisarId(long idLivro) throws NotFoundException, InvalidParameterException {
		if (idLivro != 0) {
			ResultSet rs = livroDAO.pesquisarId(idLivro);

			try {
				if (rs.next()) {
					return getLivro(rs);
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

	@Override
	public List<LivroVO> pesquisarTitulo(String titulo) throws InvalidParameterException {
		List<LivroVO> livros = new ArrayList<LivroVO>();

		if ((titulo != null) && !titulo.isBlank()) {
			ResultSet rs = livroDAO.pesquisarTitulo('%' + titulo.trim() + '%');

			try {
				while (rs.next()) {
					livros.add(getLivro(rs));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			throw new InvalidParameterException();
		}

		return livros;
	}

	@Override
	public List<LivroVO> pesquisarAnoLancamento(int anoLancamento) {
		List<LivroVO> livros = new ArrayList<LivroVO>();
		ResultSet rs = livroDAO.pesquisarAnoLancamento(anoLancamento);

		try {
			while (rs.next()) {
				livros.add(getLivro(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return livros;
	}

	public List<LivroVO> pesquisarGenero(String genero) {
		List<LivroVO> livros = new ArrayList<LivroVO>();

		if ((genero != null) && !genero.isBlank()) {
			ResultSet rs = livroDAO.pesquisarGenero('%' + genero.trim() + '%');

			try {
				while (rs.next()) {
					livros.add(getLivro(rs));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			throw new InvalidParameterException();
		}

		return livros;
	}

	public List<LivroVO> pesquisarNumeroPaginas(int numeroPaginas) throws InvalidParameterException {
		List<LivroVO> livros = new ArrayList<LivroVO>();

		if (numeroPaginas != 0) {
			ResultSet rs = livroDAO.pesquisarNumeroPaginas(numeroPaginas);

			try {
				while (rs.next()) {
					livros.add(getLivro(rs));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			throw new InvalidParameterException();
		}

		return livros;
	}

	private LivroVO getLivro(ResultSet rs) {
		try {
			LivroVO livro = new LivroVO(rs.getString("titulo"), rs.getString("genero"), rs.getInt("numero_paginas"),
					rs.getInt("numero_exemplares"), rs.getInt("numero_emprestimos"), rs.getInt("numero_dias_alugado"),
					rs.getInt("ano_lancamento"), rs.getFloat("valor_aluguel"));
			livro.setId(rs.getLong("id"));
			livro.setIdEmprestavel(rs.getLong("id_emprestavel"));

			return livro;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}
}
