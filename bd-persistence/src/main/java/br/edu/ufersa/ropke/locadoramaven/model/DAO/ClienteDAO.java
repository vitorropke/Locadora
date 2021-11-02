package br.edu.ufersa.ropke.locadoramaven.model.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import br.edu.ufersa.ropke.locadoramaven.model.VO.ClienteVO;

public class ClienteDAO extends PessoaDAO<ClienteVO> {
	@Override
	public void cadastrar(ClienteVO cliente) {
		try {
			super.cadastrar(cliente);

			String sql = "INSERT INTO clientes (id_pessoa) VALUES (?)";
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
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void alterar(ClienteVO cliente) {
		// super.alterar(cliente);
	}

	public void deletar(ClienteVO cliente) {
		// super.deletar(cliente);
	}

	public ResultSet pesquisar(ClienteVO cliente) {
		// return super.pesquisar(cliente, arquivo);
		return null;
	}

	public ResultSet listar() {
		String sql = "SELECT * FROM clientes";
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

	public ArrayList<ClienteVO> pesquisarNome(String nome) {
		return null;
	}

	public ClienteVO pesquisarCpf(String cpf) {
		return null;
	}
}
