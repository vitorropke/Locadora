package br.edu.ufersa.ropke.locadoramaven.model.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import br.edu.ufersa.ropke.locadoramaven.model.VO.UsuarioVO;

public abstract class UsuarioDAO<VO extends UsuarioVO> extends PessoaDAO<VO> implements UsuarioInterDAO<VO> {
	@Override
	public void cadastrar(VO usuario) {
		try {
			super.cadastrar(usuario);

			String sql = "INSERT INTO usuarios (id_pessoa, login, senha) VALUES (?, ?, ?);";
			PreparedStatement ptst;

			ptst = getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

			ptst.setLong(1, usuario.getIdPessoa());
			ptst.setString(2, usuario.getLogin());
			ptst.setString(3, usuario.getSenha());

			int affectedRows = ptst.executeUpdate();

			if (affectedRows == 0) {
				throw new SQLException("A insercao falhou. Nenhuma linha foi alterada");
			}

			ResultSet generatedKeys = ptst.getGeneratedKeys();

			if (generatedKeys.next()) {
				usuario.setIdUsuario(generatedKeys.getLong(1));
			} else {
				throw new SQLException("A insercao falhou. Nenhuma linha foi alterada");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void alterar(VO usuario) {
		try {
			super.alterar(usuario);

			String sql = "UPDATE usuarios SET (login, senha) = (?, ?) WHERE id = ?;";
			PreparedStatement ptst;

			ptst = getConnection().prepareStatement(sql);

			ptst.setString(1, usuario.getLogin());
			ptst.setString(2, usuario.getSenha());
			ptst.setLong(3, usuario.getIdUsuario());

			ptst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public ResultSet listar() {
		String sql = "SELECT * FROM usuarios LEFT JOIN pessoas ON (usuarios.id_pessoa = pessoas.id);";
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
	public ResultSet pesquisarId(long idUsuario) {
		String sql = "SELECT * FROM usuarios LEFT JOIN pessoas ON (usuarios.id_pessoa = pessoas.id) "
				+ "WHERE usuarios.id = ?;";
		PreparedStatement ptst;
		ResultSet rs = null;

		try {
			ptst = getConnection().prepareStatement(sql);

			ptst.setLong(1, idUsuario);

			rs = ptst.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return rs;
	}

	@Override
	public ResultSet pesquisarNome(String nome) {
		String sql = "SELECT * FROM usuarios LEFT JOIN pessoas ON (usuarios.id_pessoa = pessoas.id) "
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
		String sql = "SELECT * FROM usuarios LEFT JOIN pessoas ON (usuarios.id_pessoa = pessoas.id) "
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

	@Override
	public ResultSet pesquisarLogin(String login) {
		String sql = "SELECT * FROM usuarios LEFT JOIN pessoas ON (usuarios.id_pessoa = pessoas.id) "
				+ "WHERE login = ?;";
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
