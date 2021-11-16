package br.edu.ufersa.ropke.locadoramaven.model.BO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.ufersa.ropke.locadoramaven.exception.AuthenticationException;
import br.edu.ufersa.ropke.locadoramaven.exception.FoundException;
import br.edu.ufersa.ropke.locadoramaven.exception.InvalidParameterException;
import br.edu.ufersa.ropke.locadoramaven.exception.NotFoundException;
import br.edu.ufersa.ropke.locadoramaven.model.DAO.EmailDAO;
import br.edu.ufersa.ropke.locadoramaven.model.DAO.EnderecoDAO;
import br.edu.ufersa.ropke.locadoramaven.model.DAO.FuncionarioDAO;
import br.edu.ufersa.ropke.locadoramaven.model.DAO.TelefoneDAO;
import br.edu.ufersa.ropke.locadoramaven.model.VO.EnderecoVO;
import br.edu.ufersa.ropke.locadoramaven.model.VO.FuncionarioVO;
import br.edu.ufersa.ropke.locadoramaven.model.VO.TelefoneVO;
import br.edu.ufersa.ropke.locadoramaven.model.VO.Validador;

public class FuncionarioBO extends UsuarioBO<FuncionarioVO> {
	private static final FuncionarioDAO funcionarioDAO = new FuncionarioDAO();

	@Override
	public void cadastrar(FuncionarioVO funcionario) throws FoundException, InvalidParameterException {
		if (!super.isInvalid(funcionario)) {
			ResultSet rs = funcionarioDAO.pesquisarCpf(funcionario.getCpf());

			try {
				if (rs.next()) {
					throw new FoundException();
				} else {
					funcionarioDAO.cadastrar(funcionario);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			throw new InvalidParameterException();
		}
	}

	@Override
	public void alterar(FuncionarioVO funcionario) throws NotFoundException, InvalidParameterException {
		if (!super.isInvalid(funcionario)) {
			ResultSet rs = funcionarioDAO.pesquisarId(funcionario.getId());

			try {
				if (rs.next()) {
					funcionarioDAO.alterar(funcionario);
				} else {
					throw new NotFoundException();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			throw new InvalidParameterException();
		}
	}

	@Override
	public void deletar(long idFuncionario) throws NotFoundException, InvalidParameterException {
		if (idFuncionario != 0) {
			ResultSet rs = funcionarioDAO.pesquisarId(idFuncionario);

			try {
				if (rs.next()) {
					funcionarioDAO.deletar(idFuncionario);
				} else {
					throw new NotFoundException();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			throw new InvalidParameterException();
		}
	}

	@Override
	public List<FuncionarioVO> listar() {
		List<FuncionarioVO> funcionarios = new ArrayList<FuncionarioVO>();

		ResultSet rs = funcionarioDAO.listar();

		try {
			while (rs.next()) {
				funcionarios.add(getFuncionario(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return funcionarios;
	}

	@Override
	public FuncionarioVO pesquisarId(long idFuncionario) throws NotFoundException, InvalidParameterException {
		if (idFuncionario != 0) {
			ResultSet rs = funcionarioDAO.pesquisarId(idFuncionario);

			try {
				if (rs.next()) {
					return getFuncionario(rs);
				} else {
					throw new NotFoundException();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			throw new InvalidParameterException();
		}

		return null;
	}

	@Override
	public List<FuncionarioVO> pesquisarNome(String nome) throws InvalidParameterException {
		List<FuncionarioVO> funcionarios = new ArrayList<FuncionarioVO>();

		if ((nome != null) && !nome.isBlank()) {
			ResultSet rs = funcionarioDAO.pesquisarNome('%' + nome.trim() + '%');

			try {
				while (rs.next()) {
					funcionarios.add(getFuncionario(rs));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			throw new InvalidParameterException();
		}

		return funcionarios;
	}

	@Override
	public FuncionarioVO pesquisarCpf(String cpf) throws NotFoundException, InvalidParameterException {
		if (Validador.isCpf(cpf)) {
			ResultSet rs = funcionarioDAO.pesquisarCpf(cpf.replaceAll("\\D", ""));

			try {
				if (rs.next()) {
					return getFuncionario(rs);
				} else {
					throw new NotFoundException();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			throw new InvalidParameterException();
		}

		return null;
	}

	@Override
	public FuncionarioVO pesquisarLogin(String login) throws NotFoundException, InvalidParameterException {
		if ((login != null) && !login.isBlank()) {
			ResultSet rs = funcionarioDAO.pesquisarLogin(login.trim());

			try {
				if (rs.next()) {
					return getFuncionario(rs);
				} else {
					throw new NotFoundException();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			throw new InvalidParameterException();
		}

		return null;
	}

	@Override
	public boolean autenticar(String login, String senha) throws AuthenticationException, InvalidParameterException {
		if ((senha != null) && !senha.isBlank()) {
			FuncionarioVO funcionario = pesquisarLogin(login);

			if ((funcionario != null) && funcionario.getSenha().equals(senha)) {
				return true;
			} else {
				throw new AuthenticationException();
			}
		} else {
			throw new InvalidParameterException();
		}
	}

	private FuncionarioVO getFuncionario(ResultSet rs) {
		try {
			List<EnderecoVO> enderecos = new ArrayList<EnderecoVO>();
			List<String> emails = new ArrayList<String>();
			List<TelefoneVO> telefones = new ArrayList<TelefoneVO>();

			long idPessoa = rs.getLong("id_pessoa");

			ResultSet rsEnderecos = EnderecoDAO.listar(idPessoa);
			while (rsEnderecos.next()) {
				enderecos.add(new EnderecoVO(rsEnderecos.getString("logradouro"), rsEnderecos.getString("numero"),
						rsEnderecos.getString("complemento"), rsEnderecos.getString("referencia"),
						rsEnderecos.getString("bairro"), rsEnderecos.getString("cidade"),
						rsEnderecos.getString("estado"), rsEnderecos.getString("cep")));
			}
			ResultSet rsEmails = EmailDAO.listar(idPessoa);
			while (rsEmails.next()) {
				emails.add(new String(rsEmails.getString("email")));
			}
			ResultSet rsTelefones = TelefoneDAO.listar(idPessoa);
			while (rsTelefones.next()) {
				telefones.add(new TelefoneVO(rsTelefones.getString("ddd"), rsTelefones.getString("telefone")));
			}

			FuncionarioVO funcionario = new FuncionarioVO(rs.getString("login"), rs.getString("senha"),
					rs.getString("nome"), rs.getString("cpf"), enderecos, emails, telefones);
			funcionario.setId(rs.getLong("id"));
			funcionario.setIdUsuario(rs.getLong("id_usuario"));
			funcionario.setIdPessoa(idPessoa);

			return funcionario;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}
}
