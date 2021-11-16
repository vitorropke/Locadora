package br.edu.ufersa.ropke.locadoramaven.model.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import br.edu.ufersa.ropke.locadoramaven.model.VO.EnderecoVO;
import br.edu.ufersa.ropke.locadoramaven.model.VO.PessoaVO;
import br.edu.ufersa.ropke.locadoramaven.model.VO.TelefoneVO;

public abstract class PessoaDAO<VO extends PessoaVO> extends ConexaoDAO implements PessoaInterDAO<VO> {
	@Override
	public void cadastrar(VO pessoa) {
		String sql = "INSERT INTO pessoas (nome, cpf) VALUES (?, ?);";
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

				for (EnderecoVO enderecoAtual : pessoa.getEnderecos()) {
					EnderecoDAO.cadastrar(enderecoAtual, pessoa.getIdPessoa());
				}
				for (String emailAtual : pessoa.getEmails()) {
					EmailDAO.cadastrar(emailAtual, pessoa.getIdPessoa());
				}
				for (TelefoneVO telefoneAtual : pessoa.getTelefones()) {
					TelefoneDAO.cadastrar(telefoneAtual, pessoa.getIdPessoa());
				}
			} else {
				throw new SQLException("A insercao falhou. Nenhuma linha foi alterada");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void alterar(VO pessoa) {
		String sql = "UPDATE pessoas SET (nome, cpf) = (?, ?) WHERE id = ?;";
		PreparedStatement ptst;

		try {
			ptst = getConnection().prepareStatement(sql);

			ptst.setString(1, pessoa.getNome());
			ptst.setString(2, pessoa.getCpf());
			ptst.setLong(3, pessoa.getIdPessoa());

			ptst.executeUpdate();

			EnderecoDAO.deletar(pessoa.getIdPessoa());
			EmailDAO.deletar(pessoa.getIdPessoa());
			TelefoneDAO.deletar(pessoa.getIdPessoa());

			for (EnderecoVO enderecoAtual : pessoa.getEnderecos()) {
				EnderecoDAO.cadastrar(enderecoAtual, pessoa.getIdPessoa());
			}
			for (String emailAtual : pessoa.getEmails()) {
				EmailDAO.cadastrar(emailAtual, pessoa.getIdPessoa());
			}
			for (TelefoneVO telefoneAtual : pessoa.getTelefones()) {
				TelefoneDAO.cadastrar(telefoneAtual, pessoa.getIdPessoa());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deletar(long idPessoa) {
		String sql = "DELETE FROM pessoas WHERE id = ?;";
		PreparedStatement ptst;

		try {
			ptst = getConnection().prepareStatement(sql);

			ptst.setLong(1, idPessoa);

			ptst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public ResultSet listar() {
		String sql = "SELECT * FROM pessoas;";
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
	public ResultSet pesquisarId(long idPessoa) {
		String sql = "SELECT * FROM pessoas WHERE id = ?;";
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

	@Override
	public ResultSet pesquisarNome(String nome) {
		String sql = "SELECT * FROM pessoas WHERE nome LIKE ?;";
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
		String sql = "SELECT * FROM pessoas WHERE cpf = ?;";
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
