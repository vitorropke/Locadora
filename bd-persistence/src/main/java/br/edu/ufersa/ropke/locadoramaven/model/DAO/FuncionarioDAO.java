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

			String sql = "INSERT INTO funcionarios (id_usuario) VALUES (?)";
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
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void alterar(FuncionarioVO funcionario) {
		// super.alterar(funcionario, arquivo);
	}

	public void deletar(FuncionarioVO funcionario) {
		// super.deletar(funcionario, arquivo);
	}

	public ResultSet pesquisar(FuncionarioVO funcionario) {
		return null;
	}

	public ResultSet listar() {
		return null;
	}

	public FuncionarioVO pesquisarLogin(FuncionarioVO funcionario) {
		return null;
	}
}
