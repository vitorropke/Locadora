package br.edu.ufersa.ropke.locadoramaven.model.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.edu.ufersa.ropke.locadoramaven.model.VO.TelefoneVO;

public class TelefoneDAO extends ConexaoDAO {
	public static void cadastrar(TelefoneVO telefone, long idPessoa) {
		String sql = "INSERT INTO telefones (id_pessoa, ddd, telefone) VALUES (?, ?, ?);";
		PreparedStatement ptst;

		try {
			ptst = getConnection().prepareStatement(sql);

			ptst.setLong(1, idPessoa);
			ptst.setString(2, telefone.getDdd());
			ptst.setString(3, telefone.getTelefone());

			int affectedRows = ptst.executeUpdate();

			if (affectedRows == 0) {
				throw new SQLException("A insercao falhou. Nenhuma linha foi alterada");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void deletar(long idPessoa) {
		String sql = "DELETE FROM telefones WHERE id_pessoa = ?;";
		PreparedStatement ptst;

		try {
			ptst = getConnection().prepareStatement(sql);

			ptst.setLong(1, idPessoa);

			ptst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static ResultSet listar(long idPessoa) {
		String sql = "SELECT * FROM telefones WHERE id_pessoa = ?;";
		PreparedStatement ptst;
		ResultSet rs = null;

		try {
			ptst = getConnection().prepareStatement(sql);

			ptst.setLong(1, idPessoa);

			rs = ptst.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return rs;
	}
}
