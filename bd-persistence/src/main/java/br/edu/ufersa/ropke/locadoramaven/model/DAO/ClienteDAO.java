package br.edu.ufersa.ropke.locadoramaven.model.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import br.edu.ufersa.ropke.locadoramaven.model.VO.ClienteVO;

public class ClienteDAO extends PessoaDAO<ClienteVO> {
	@Override
	public void cadastrar(ClienteVO cliente) {
		try {
			super.cadastrar(cliente);

			String sql = "INSERT INTO clientes (id_pessoa) VALUES (?);";
			PreparedStatement ptst;

			ptst = getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

			ptst.setLong(1, cliente.getIdPessoa());

			int affectedRows = ptst.executeUpdate();

			if (affectedRows == 0) {
				throw new SQLException("A insercao falhou. Nenhuma linha foi alterada");
			}

			ResultSet generatedKeys = ptst.getGeneratedKeys();

			if (generatedKeys.next()) {
				cliente.setId(generatedKeys.getLong(1));
			} else {
				throw new SQLException("A insercao falhou. Nenhuma linha foi alterada");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public ResultSet listar() {
		String sql = "SELECT * FROM clientes LEFT JOIN pessoas ON (clientes.id_pessoa = pessoas.id);";
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
	public ResultSet pesquisarId(long idCliente) {
		String sql = "SELECT * FROM clientes LEFT JOIN pessoas ON (clientes.id_pessoa = pessoas.id) "
				+ "WHERE clientes.id = ?;";
		PreparedStatement ptst;
		ResultSet rs = null;

		try {
			ptst = getConnection().prepareStatement(sql);

			ptst.setLong(1, idCliente);

			rs = ptst.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return rs;
	}

	@Override
	public ResultSet pesquisarNome(String nome) {
		String sql = "SELECT * FROM clientes LEFT JOIN pessoas ON (clientes.id_pessoa = pessoas.id) "
				+ "WHERE nome LIKE ?;";
		PreparedStatement ptst;
		ResultSet rs = null;

		try {
			ptst = getConnection().prepareStatement(sql);

			ptst.setString(1, nome);

			rs = ptst.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return rs;
	}

	@Override
	public ResultSet pesquisarCpf(String cpf) {
		String sql = "SELECT * FROM clientes LEFT JOIN pessoas ON (clientes.id_pessoa = pessoas.id) "
				+ "WHERE cpf = ?;";
		PreparedStatement ptst;
		ResultSet rs = null;

		try {
			ptst = getConnection().prepareStatement(sql);

			ptst.setString(1, cpf);

			rs = ptst.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return rs;
	}
}
