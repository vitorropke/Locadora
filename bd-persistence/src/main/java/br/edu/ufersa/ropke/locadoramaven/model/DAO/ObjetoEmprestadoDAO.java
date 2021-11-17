package br.edu.ufersa.ropke.locadoramaven.model.DAO;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import br.edu.ufersa.ropke.locadoramaven.model.VO.ObjetoEmprestadoVO;

public class ObjetoEmprestadoDAO extends ConexaoDAO {
	public static void cadastrar(ObjetoEmprestadoVO objeto, long idEmprestimo) {
		String sql = "INSERT INTO objetos_emprestados (id_emprestimo, id_emprestavel, data_devolucao, quantidade) VALUES (?, ?, ?, ?);";
		PreparedStatement ptst;

		try {
			ptst = getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

			ptst.setLong(1, idEmprestimo);
			ptst.setLong(2, objeto.getObjeto().getIdEmprestavel());
			ptst.setDate(3, new Date(objeto.getDataDevolucao().getTimeInMillis()));
			ptst.setInt(4, objeto.getQuantidade());

			int affectedRows = ptst.executeUpdate();

			if (affectedRows == 0) {
				throw new SQLException("A insercao falhou. Nenhuma linha foi alterada");
			}

			ResultSet generatedKeys = ptst.getGeneratedKeys();

			if (generatedKeys.next()) {
				objeto.setId(generatedKeys.getLong(1));
			} else {
				throw new SQLException("A insercao falhou. Nenhuma linha foi alterada");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void alterar(ObjetoEmprestadoVO objeto) {
		String sql = "UPDATE objetos_emprestados SET (data_devolucao, quantidade) = (?, ?) WHERE id = ?;";
		PreparedStatement ptst;

		try {
			ptst = getConnection().prepareStatement(sql);

			ptst.setDate(1, new Date(objeto.getDataDevolucao().getTimeInMillis()));
			ptst.setInt(2, objeto.getQuantidade());
			ptst.setLong(3, objeto.getId());

			ptst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void deletar(long idEmprestimo) {
		String sql = "DELETE FROM objetos_emprestados WHERE id_emprestimo = ?;";
		PreparedStatement ptst;

		try {
			ptst = getConnection().prepareStatement(sql);

			ptst.setLong(1, idEmprestimo);

			ptst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static ResultSet listar(long idEmprestimo) {
		String sql = "SELECT * FROM objetos_emprestados WHERE id_emprestimo = ?;";
		PreparedStatement ptst;
		ResultSet rs = null;

		try {
			ptst = getConnection().prepareStatement(sql);

			ptst.setLong(1, idEmprestimo);

			rs = ptst.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return rs;
	}
}
