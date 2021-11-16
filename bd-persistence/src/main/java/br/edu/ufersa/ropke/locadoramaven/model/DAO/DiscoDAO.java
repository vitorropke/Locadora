package br.edu.ufersa.ropke.locadoramaven.model.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import br.edu.ufersa.ropke.locadoramaven.model.VO.DiscoVO;

public class DiscoDAO extends EmprestavelDAO<DiscoVO> {
	@Override
	public void cadastrar(DiscoVO disco) {
		try {
			super.cadastrar(disco);

			String sql = "INSERT INTO discos (id_emprestavel, banda, estilo) VALUES (?, ?, ?);";
			PreparedStatement ptst;

			ptst = getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

			ptst.setLong(1, disco.getIdEmprestavel());
			ptst.setString(2, disco.getBanda());
			ptst.setString(3, disco.getEstilo());

			int affectedRows = ptst.executeUpdate();

			if (affectedRows == 0) {
				throw new SQLException("A insercao falhou. Nenhuma linha foi alterada");
			}

			ResultSet generatedKeys = ptst.getGeneratedKeys();

			if (generatedKeys.next()) {
				disco.setId(generatedKeys.getLong(1));
			} else {
				throw new SQLException("A insercao falhou. Nenhuma linha foi alterada");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void alterar(DiscoVO disco) {
		try {
			super.alterar(disco);

			String sql = "UPDATE discos SET (banda, estilo) = (?, ?) WHERE id = ?;";
			PreparedStatement ptst;

			ptst = getConnection().prepareStatement(sql);

			ptst.setString(1, disco.getBanda());
			ptst.setString(2, disco.getEstilo());
			ptst.setLong(3, disco.getId());

			ptst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public ResultSet listar() {
		String sql = "SELECT * FROM discos LEFT JOIN emprestaveis ON (discos.id_emprestavel = emprestaveis.id);";
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
	public ResultSet pesquisarId(long idDisco) {
		String sql = "SELECT * FROM discos LEFT JOIN emprestaveis ON (discos.id_emprestavel = emprestaveis.id) "
				+ "WHERE discos.id = ?;";
		PreparedStatement ptst;
		ResultSet rs = null;

		try {
			ptst = getConnection().prepareStatement(sql);

			ptst.setLong(1, idDisco);

			rs = ptst.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return rs;
	}

	@Override
	public ResultSet pesquisarTitulo(String titulo) {
		String sql = "SELECT * FROM discos LEFT JOIN emprestaveis ON (discos.id_emprestavel = emprestaveis.id) "
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
		String sql = "SELECT * FROM discos LEFT JOIN emprestaveis ON (discos.id_emprestavel = emprestaveis.id) "
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

	public ResultSet pesquisarBanda(String banda) {
		String sql = "SELECT * FROM discos LEFT JOIN emprestaveis ON (discos.id_emprestavel = emprestaveis.id) "
				+ "WHERE banda LIKE ?;";
		PreparedStatement ptst;
		ResultSet rs = null;

		try {
			ptst = getConnection().prepareStatement(sql);

			ptst.setString(1, banda);

			rs = ptst.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return rs;
	}

	public ResultSet pesquisarEstilo(String estilo) {
		String sql = "SELECT * FROM discos LEFT JOIN emprestaveis ON (discos.id_emprestavel = emprestaveis.id) "
				+ "WHERE estilo LIKE ?;";
		PreparedStatement ptst;
		ResultSet rs = null;

		try {
			ptst = getConnection().prepareStatement(sql);

			ptst.setString(1, estilo);

			rs = ptst.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return rs;
	}
}
