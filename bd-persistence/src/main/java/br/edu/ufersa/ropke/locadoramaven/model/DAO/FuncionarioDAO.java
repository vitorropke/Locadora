package br.edu.ufersa.ropke.locadoramaven.model.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import br.edu.ufersa.ropke.locadoramaven.model.VO.FuncionarioVO;

public class FuncionarioDAO extends UsuarioDAO<FuncionarioVO> {
	@Override
	public void cadastrar(FuncionarioVO funcionario) {
		try {
			super.cadastrar(funcionario);

			String sql = "INSERT INTO funcionarios (id_usuario) VALUES (?);";
			PreparedStatement ptst;

			ptst = getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

			ptst.setLong(1, funcionario.getIdUsuario());

			int affectedRows = ptst.executeUpdate();

			if (affectedRows == 0) {
				throw new SQLException("A insercao falhou. Nenhuma linha foi alterada");
			}

			ResultSet generatedKeys = ptst.getGeneratedKeys();

			if (generatedKeys.next()) {
				funcionario.setId(generatedKeys.getLong(1));
			} else {
				throw new SQLException("A insercao falhou. Nenhuma linha foi alterada");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public ResultSet listar() {
		String sql = "SELECT * FROM funcionarios LEFT JOIN usuarios ON (funcionarios.id_usuario = usuarios.id) "
				+ "LEFT JOIN pessoas ON (usuarios.id_pessoa = pessoas.id);";
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
	public ResultSet pesquisarId(long idFuncionario) {
		String sql = "SELECT * FROM funcionarios LEFT JOIN usuarios ON (funcionarios.id_usuario = usuarios.id) "
				+ "LEFT JOIN pessoas ON (usuarios.id_pessoa = pessoas.id) WHERE funcionarios.id = ?;";
		PreparedStatement ptst;
		ResultSet rs = null;

		try {
			ptst = getConnection().prepareStatement(sql);

			ptst.setLong(1, idFuncionario);

			rs = ptst.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return rs;
	}

	@Override
	public ResultSet pesquisarNome(String nome) {
		String sql = "SELECT * FROM funcionarios LEFT JOIN usuarios ON (funcionarios.id_usuario = usuarios.id) "
				+ "LEFT JOIN pessoas ON (usuarios.id_pessoa = pessoas.id) WHERE nome LIKE ?;";
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
		String sql = "SELECT * FROM funcionarios LEFT JOIN usuarios ON (funcionarios.id_usuario = usuarios.id) "
				+ "LEFT JOIN pessoas ON (usuarios.id_pessoa = pessoas.id) WHERE cpf = ?;";
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

	@Override
	public ResultSet pesquisarLogin(String login) {
		String sql = "SELECT * FROM funcionarios LEFT JOIN usuarios ON (funcionarios.id_usuario = usuarios.id) "
				+ "LEFT JOIN pessoas ON (usuarios.id_pessoa = pessoas.id) WHERE login = ?;";
		PreparedStatement ptst;
		ResultSet rs = null;

		try {
			ptst = getConnection().prepareStatement(sql);

			ptst.setString(1, login);

			rs = ptst.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return rs;
	}
}
