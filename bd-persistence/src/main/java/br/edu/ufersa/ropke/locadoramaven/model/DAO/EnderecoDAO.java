package br.edu.ufersa.ropke.locadoramaven.model.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import br.edu.ufersa.ropke.locadoramaven.model.VO.EnderecoVO;

public class EnderecoDAO extends ConexaoDAO {
	public static void cadastrar(EnderecoVO endereco, long idPessoa) {
		String sql = "INSERT INTO enderecos (id_pessoa, logradouro, numero, complemento, referencia, bairro, cidade, estado, cep) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);";
		PreparedStatement ptst;

		try {
			ptst = getConnection().prepareStatement(sql);

			ptst.setLong(1, idPessoa);
			ptst.setString(2, endereco.getLogradouro());
			ptst.setString(3, endereco.getNumero());
			ptst.setString(4, endereco.getComplemento());
			ptst.setString(5, endereco.getReferencia());
			ptst.setString(6, endereco.getBairro());
			ptst.setString(7, endereco.getCidade());
			ptst.setString(8, endereco.getEstado());
			ptst.setString(9, endereco.getCep());

			int affectedRows = ptst.executeUpdate();

			if (affectedRows == 0) {
				throw new SQLException("A insercao falhou. Nenhuma linha foi alterada");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void deletar(long idPessoa) {
		String sql = "DELETE FROM enderecos WHERE id_pessoa = ?;";
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
		String sql = "SELECT * FROM enderecos WHERE id_pessoa = ?;";
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
