package br.edu.ufersa.ropke.locadoramaven.model.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import br.edu.ufersa.ropke.locadoramaven.model.VO.PessoaVO;

public class PessoaDAO<VO extends PessoaVO> extends OperacaoDAO<VO> {
	public void cadastrar(VO pessoa) throws SQLException {
		String sql = "INSERT INTO pessoas (nome, cpf) VALUES (?,?)";
		PreparedStatement ptst;

		try {
			ptst = getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ptst.setString(1, pessoa.getNome());
			ptst.setString(2, pessoa.getCpf());

			int affectedRows = ptst.executeUpdate();

			if (affectedRows == 0) {
				throw new SQLException("A insercao falhou. Nenhuma linha foi alterada");
			}

			ResultSet generatedKeys = ptst.getGeneratedKeys();

			if (generatedKeys.next()) {
				pessoa.setIdPessoa(generatedKeys.getLong(1));
			} else {
				throw new SQLException("A insercao falhou. Nenhuma linha foi alterada");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void alterar(VO pessoa) throws SQLException {
		String sql = "UPDATE pessoas SET nome = ? WHERE id= ?";
		PreparedStatement ptst;

		try {
			ptst = getConnection().prepareStatement(sql);
			ptst.setString(1, pessoa.getNome());
			ptst.setLong(2, pessoa.getIdPessoa());
			ptst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void deletar(VO pessoa) throws SQLException {
		String sql = "DELETE FROM pessoas WHERE id = ?";
		PreparedStatement ptst;

		try {
			ptst = getConnection().prepareStatement(sql);
			ptst.setLong(1, pessoa.getIdPessoa());
			ptst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public ResultSet pesquisar(VO pessoa) throws SQLException {
		String sql = "SELECT * FROM pessoas WHERE id = ?";
		PreparedStatement ptst;
		ResultSet rs = null;

		try {
			ptst = getConnection().prepareStatement(sql);
			ptst.setLong(1, pessoa.getIdPessoa());
			System.out.println(ptst);
			rs = ptst.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return rs;
	}

	public ResultSet listar() throws SQLException {
		String sql = "SELECT * FROM pessoas";
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
}
