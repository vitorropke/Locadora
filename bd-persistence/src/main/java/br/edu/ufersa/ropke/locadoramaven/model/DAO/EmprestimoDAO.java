package br.edu.ufersa.ropke.locadoramaven.model.DAO;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import br.edu.ufersa.ropke.locadoramaven.model.VO.EmprestimoVO;
import br.edu.ufersa.ropke.locadoramaven.model.VO.ObjetoEmprestadoVO;

public class EmprestimoDAO extends ConexaoDAO implements BaseInterDAO<EmprestimoVO> {
	@Override
	public void cadastrar(EmprestimoVO emprestimo) {
		String sql = "INSERT INTO emprestimos (data_emprestimo, id_cliente) VALUES (?, ?);";
		PreparedStatement ptst;

		try {
			ptst = getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

			ptst.setDate(1, new Date(emprestimo.getDataOperacao().getTimeInMillis()));
			ptst.setLong(2, emprestimo.getCliente().getId());

			int affectedRows = ptst.executeUpdate();

			if (affectedRows == 0) {
				throw new SQLException("A insercao falhou. Nenhuma linha foi alterada");
			}

			ResultSet generatedKeys = ptst.getGeneratedKeys();

			if (generatedKeys.next()) {
				emprestimo.setId(generatedKeys.getLong(1));

				for (ObjetoEmprestadoVO objetoAtual : emprestimo.getObjetos()) {
					ObjetoEmprestadoDAO.cadastrar(objetoAtual, emprestimo.getId());
				}
			} else {
				throw new SQLException("A insercao falhou. Nenhuma linha foi alterada");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void alterar(EmprestimoVO emprestimo) {
		for (ObjetoEmprestadoVO objetoAtual : emprestimo.getObjetos()) {
			ObjetoEmprestadoDAO.alterar(objetoAtual);
		}
	}

	@Override
	public void deletar(EmprestimoVO emprestimo) {
		String sql = "DELETE FROM emprestimos WHERE id = ?;";
		PreparedStatement ptst;

		try {
			ptst = getConnection().prepareStatement(sql);

			ptst.setLong(1, emprestimo.getId());

			ptst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public ResultSet listar() {
		String sql = "SELECT * FROM emprestimos;";
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
	public ResultSet pesquisarId(long idEmprestimo) {
		String sql = "SELECT * FROM emprestimos WHERE id = ?;";
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
