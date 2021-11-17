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
import br.edu.ufersa.ropke.locadoramaven.model.DAO.GerenteDAO;
import br.edu.ufersa.ropke.locadoramaven.model.DAO.TelefoneDAO;
import br.edu.ufersa.ropke.locadoramaven.model.VO.EnderecoVO;
import br.edu.ufersa.ropke.locadoramaven.model.VO.GerenteVO;
import br.edu.ufersa.ropke.locadoramaven.model.VO.TelefoneVO;
import br.edu.ufersa.ropke.locadoramaven.model.VO.Validador;

public class GerenteBO extends UsuarioBO<GerenteVO> {
	private static final GerenteDAO gerenteDAO = new GerenteDAO();

	@Override
	public void cadastrar(GerenteVO gerente) throws FoundException, InvalidParameterException {
		if (!super.isInvalid(gerente)) {
			ResultSet rs = gerenteDAO.pesquisarCpf(gerente.getCpf());

			try {
				if (rs.next()) {
					throw new FoundException();
				} else {
					gerenteDAO.cadastrar(gerente);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			throw new InvalidParameterException();
		}
	}

	@Override
	public void alterar(GerenteVO gerente) throws NotFoundException, InvalidParameterException {
		if (!super.isInvalid(gerente)) {
			ResultSet rs = gerenteDAO.pesquisarId(gerente.getId());

			try {
				if (rs.next()) {
					gerenteDAO.alterar(gerente);
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
	public void deletar(GerenteVO gerente) throws NotFoundException, InvalidParameterException {
		if (!super.isInvalid(gerente)) {
			ResultSet rs = gerenteDAO.pesquisarId(gerente.getId());

			try {
				if (rs.next()) {
					gerenteDAO.deletar(gerente);
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
	public List<GerenteVO> listar() {
		List<GerenteVO> gerentes = new ArrayList<GerenteVO>();

		ResultSet rs = gerenteDAO.listar();

		try {
			while (rs.next()) {
				gerentes.add(getGerente(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return gerentes;
	}

	@Override
	public GerenteVO pesquisarId(long idGerente) throws NotFoundException, InvalidParameterException {
		if (idGerente != 0) {
			ResultSet rs = gerenteDAO.pesquisarId(idGerente);

			try {
				if (rs.next()) {
					return getGerente(rs);
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
	public List<GerenteVO> pesquisarNome(String nome) throws InvalidParameterException {
		List<GerenteVO> gerentes = new ArrayList<GerenteVO>();

		if ((nome != null) && !nome.isBlank()) {
			ResultSet rs = gerenteDAO.pesquisarNome('%' + nome.trim() + '%');

			try {
				while (rs.next()) {
					gerentes.add(getGerente(rs));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			throw new InvalidParameterException();
		}

		return gerentes;
	}

	@Override
	public GerenteVO pesquisarCpf(String cpf) throws NotFoundException, InvalidParameterException {
		if (Validador.isCpf(cpf)) {
			ResultSet rs = gerenteDAO.pesquisarCpf(cpf.replaceAll("\\D", ""));

			try {
				if (rs.next()) {
					return getGerente(rs);
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
	public GerenteVO pesquisarLogin(String login) throws NotFoundException, InvalidParameterException {
		if ((login != null) && !login.isBlank()) {
			ResultSet rs = gerenteDAO.pesquisarLogin(login.trim());

			try {
				if (rs.next()) {
					return getGerente(rs);
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
			GerenteVO gerente = pesquisarLogin(login);

			if ((gerente != null) && gerente.getSenha().equals(senha)) {
				return true;
			} else {
				throw new AuthenticationException();
			}
		} else {
			throw new InvalidParameterException();
		}
	}

	private GerenteVO getGerente(ResultSet rs) {
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

			GerenteVO gerente = new GerenteVO(rs.getString("login"), rs.getString("senha"), rs.getString("nome"),
					rs.getString("cpf"), enderecos, emails, telefones);
			gerente.setId(rs.getLong("id"));
			gerente.setIdUsuario(rs.getLong("id_usuario"));
			gerente.setIdPessoa(idPessoa);

			return gerente;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}
}
