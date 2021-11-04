package br.edu.ufersa.ropke.locadoramaven.model.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import br.edu.ufersa.ropke.locadoramaven.model.VO.GerenteVO;

public class GerenteDAO extends UsuarioDAO<GerenteVO> {
	@Override
	public void cadastrar(GerenteVO gerente) {
		try {
			super.cadastrar(gerente);

			String sql = "INSERT INTO gerentes (id_usuario) VALUES (?)";
			PreparedStatement ptst;

			ptst = getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ptst.setLong(1, gerente.getIdUsuario());

			int affectedRows = ptst.executeUpdate();

			if (affectedRows == 0) {
				throw new SQLException("A insercao falhou. Nenhuma linha foi alterada");
			}

			ResultSet generatedKeys = ptst.getGeneratedKeys();

			if (generatedKeys.next()) {
				gerente.setId(generatedKeys.getLong(1));
			} else {
				throw new SQLException("A insercao falhou. Nenhuma linha foi alterada");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void alterar(GerenteVO gerente) {
		// super.alterar(gerente, arquivo);
	}

	public void deletar(GerenteVO gerente) {
		// super.deletar(gerente, arquivo);
	}

	public void pesquisar() {
		// super.pesquisar(arquivo);
	}

	public ResultSet pesquisar(GerenteVO gerente) {
		return null;
	}

	public ResultSet listar() {
		String sql = "SELECT * FROM gerentes LEFT JOIN usuarios ON (gerentes.id_usuario = usuarios.id) LEFT JOIN pessoas ON (usuarios.id_pessoa = pessoas.id);";
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

	public GerenteVO pesquisarLogin(GerenteVO gerente) {

		return null;
	}
}
