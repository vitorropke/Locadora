package br.edu.ufersa.ropke.locadoramaven.model.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import br.edu.ufersa.ropke.locadoramaven.model.VO.UsuarioVO;

public class UsuarioDAO<VO extends UsuarioVO> extends PessoaDAO<VO> {
	@Override
	public void cadastrar(VO usuario) {
		try {
			super.cadastrar(usuario);

			String sql = "INSERT INTO usuarios (id_pessoa, login, senha) VALUES (?,?,?)";
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
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
}
