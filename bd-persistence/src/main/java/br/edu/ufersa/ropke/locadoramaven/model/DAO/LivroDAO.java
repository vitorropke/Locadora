package br.edu.ufersa.ropke.locadoramaven.model.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import br.edu.ufersa.ropke.locadoramaven.model.VO.LivroVO;

public class LivroDAO extends EmprestavelDAO<LivroVO> {
	@Override
	public void cadastrar(LivroVO livro) {
		try {
			super.cadastrar(livro);

			String sql = "INSERT INTO livros (id_emprestavel, genero, numero_paginas) VALUES (?, ?, ?);";
			PreparedStatement ptst;

			ptst = getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

			ptst.setLong(1, livro.getIdEmprestavel());
			ptst.setString(2, livro.getGenero());
			ptst.setInt(3, livro.getNumeroPaginas());

			int affectedRows = ptst.executeUpdate();

			if (affectedRows == 0) {
				throw new SQLException("A insercao falhou. Nenhuma linha foi alterada");
			}

			ResultSet generatedKeys = ptst.getGeneratedKeys();

			if (generatedKeys.next()) {
				livro.setId(generatedKeys.getLong(1));
			} else {
				throw new SQLException("A insercao falhou. Nenhuma linha foi alterada");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void alterar(LivroVO livro) {
		try {
			super.alterar(livro);

			String sql = "UPDATE livros SET (genero, numero_paginas) = (?, ?) WHERE id = ?;";
			PreparedStatement ptst;

			ptst = getConnection().prepareStatement(sql);

			ptst.setString(1, livro.getGenero());
			ptst.setInt(2, livro.getNumeroPaginas());
			ptst.setLong(3, livro.getId());

			ptst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public ResultSet listar() {
		String sql = "SELECT * FROM livros LEFT JOIN emprestaveis ON (livros.id_emprestavel = emprestaveis.id);";
		Statement st;
		ResultSet rs = null;

		try {
			st = getConnection().createStatement();
			rs = st.executeQuery(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return rs;
	}

	@Override
	public ResultSet pesquisarId(long idLivro) {
		String sql = "SELECT * FROM livros LEFT JOIN emprestaveis ON (livros.id_emprestavel = emprestaveis.id) "
				+ "WHERE livros.id = ?;";
		PreparedStatement ptst;
		ResultSet rs = null;

		try {
			ptst = getConnection().prepareStatement(sql);

			ptst.setLong(1, idLivro);

			rs = ptst.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return rs;
	}

	@Override
	public ResultSet pesquisarTitulo(String titulo) {
		String sql = "SELECT * FROM livros LEFT JOIN emprestaveis ON (livros.id_emprestavel = emprestaveis.id) "
				+ "WHERE titulo LIKE ?;";
		PreparedStatement ptst;
		ResultSet rs = null;

		try {
			ptst = getConnection().prepareStatement(sql);

			ptst.setString(1, titulo);

			rs = ptst.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return rs;
	}

	@Override
	public ResultSet pesquisarAnoLancamento(int anoLancamento) {
		String sql = "SELECT * FROM livros LEFT JOIN emprestaveis ON (livros.id_emprestavel = emprestaveis.id) "
				+ "WHERE ano_lancamento = ?;";
		PreparedStatement ptst;
		ResultSet rs = null;

		try {
			ptst = getConnection().prepareStatement(sql);

			ptst.setInt(1, anoLancamento);

			rs = ptst.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return rs;
	}

	public ResultSet pesquisarGenero(String genero) {
		String sql = "SELECT * FROM livros LEFT JOIN emprestaveis ON (livros.id_emprestavel = emprestaveis.id) "
				+ "WHERE genero LIKE ?;";
		PreparedStatement ptst;
		ResultSet rs = null;

		try {
			ptst = getConnection().prepareStatement(sql);

			ptst.setString(1, genero);

			rs = ptst.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return rs;
	}

	public ResultSet pesquisarNumeroPaginas(int numeroPaginas) {
		String sql = "SELECT * FROM livros LEFT JOIN emprestaveis ON (livros.id_emprestavel = emprestaveis.id) "
				+ "WHERE numero_paginas = ?;";
		PreparedStatement ptst;
		ResultSet rs = null;

		try {
			ptst = getConnection().prepareStatement(sql);

			ptst.setInt(1, numeroPaginas);

			rs = ptst.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return rs;
	}
}
