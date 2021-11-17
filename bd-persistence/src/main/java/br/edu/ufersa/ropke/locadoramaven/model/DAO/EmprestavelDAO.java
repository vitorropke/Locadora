package br.edu.ufersa.ropke.locadoramaven.model.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import br.edu.ufersa.ropke.locadoramaven.model.VO.EmprestavelVO;

public abstract class EmprestavelDAO<VO extends EmprestavelVO> extends ConexaoDAO implements EmprestavelInterDAO<VO> {
	@Override
	public void cadastrar(VO emprestavel) {
		String sql = "INSERT INTO emprestaveis (titulo, numero_exemplares, numero_emprestimos, numero_dias_alugado, ano_lancamento, valor_aluguel) "
				+ "VALUES (?, ?, ?, ?, ?, ?);";
		PreparedStatement ptst;

		try {
			ptst = getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

			ptst.setString(1, emprestavel.getTitulo());
			ptst.setInt(2, emprestavel.getNumeroExemplares());
			ptst.setInt(3, emprestavel.getNumeroEmprestimos());
			ptst.setInt(4, emprestavel.getNumeroDiasAlugado());
			ptst.setInt(5, emprestavel.getAnoLancamento());
			ptst.setFloat(6, emprestavel.getValorAluguel());

			int affectedRows = ptst.executeUpdate();

			if (affectedRows == 0) {
				throw new SQLException("A insercao falhou. Nenhuma linha foi alterada");
			}

			ResultSet generatedKeys = ptst.getGeneratedKeys();

			if (generatedKeys.next()) {
				emprestavel.setIdEmprestavel(generatedKeys.getLong(1));
			} else {
				throw new SQLException("A insercao falhou. Nenhuma linha foi alterada");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void alterar(VO emprestavel) {
		String sql = "UPDATE emprestaveis SET (titulo, numero_exemplares, numero_emprestimos, numero_dias_alugado, ano_lancamento, valor_aluguel) = (?, ?, ?, ?, ? ,?) "
				+ "WHERE id = ?;";
		PreparedStatement ptst;

		try {
			ptst = getConnection().prepareStatement(sql);

			ptst.setString(1, emprestavel.getTitulo());
			ptst.setInt(2, emprestavel.getNumeroExemplares());
			ptst.setInt(3, emprestavel.getNumeroEmprestimos());
			ptst.setInt(4, emprestavel.getNumeroDiasAlugado());
			ptst.setInt(5, emprestavel.getAnoLancamento());
			ptst.setFloat(6, emprestavel.getValorAluguel());
			ptst.setLong(7, emprestavel.getIdEmprestavel());

			ptst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deletar(VO emprestavel) {
		String sql = "DELETE FROM emprestaveis WHERE id = ?;";
		PreparedStatement ptst;

		try {
			ptst = getConnection().prepareStatement(sql);

			ptst.setLong(1, emprestavel.getIdEmprestavel());

			ptst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public ResultSet listar() {
		String sql = "SELECT * FROM emprestaveis;";
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
	public ResultSet pesquisarId(long idEmprestavel) {
		String sql = "SELECT * FROM emprestaveis WHERE id = ?;";
		PreparedStatement ptst;
		ResultSet rs = null;

		try {
			ptst = getConnection().prepareStatement(sql);

			ptst.setLong(1, idEmprestavel);

			rs = ptst.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return rs;
	}

	@Override
	public ResultSet pesquisarTitulo(String titulo) {
		String sql = "SELECT * FROM emprestaveis WHERE titulo LIKE ?;";
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
		String sql = "SELECT * FROM emprestaveis WHERE ano_lancamento = ?;";
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
}
