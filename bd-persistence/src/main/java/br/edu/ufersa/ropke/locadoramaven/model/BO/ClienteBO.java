package br.edu.ufersa.ropke.locadoramaven.model.BO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.ufersa.ropke.locadoramaven.exception.FoundException;
import br.edu.ufersa.ropke.locadoramaven.exception.InvalidParameterException;
import br.edu.ufersa.ropke.locadoramaven.exception.NotFoundException;
import br.edu.ufersa.ropke.locadoramaven.model.DAO.ClienteDAO;
import br.edu.ufersa.ropke.locadoramaven.model.DAO.EmailDAO;
import br.edu.ufersa.ropke.locadoramaven.model.DAO.EnderecoDAO;
import br.edu.ufersa.ropke.locadoramaven.model.DAO.TelefoneDAO;
import br.edu.ufersa.ropke.locadoramaven.model.VO.ClienteVO;
import br.edu.ufersa.ropke.locadoramaven.model.VO.EnderecoVO;
import br.edu.ufersa.ropke.locadoramaven.model.VO.TelefoneVO;
import br.edu.ufersa.ropke.locadoramaven.model.VO.Validador;

public class ClienteBO extends PessoaBO<ClienteVO> {
	private static final ClienteDAO clienteDAO = new ClienteDAO();

	@Override
	public void cadastrar(ClienteVO cliente) throws FoundException, InvalidParameterException {
		if (!super.isInvalid(cliente)) {
			ResultSet rs = clienteDAO.pesquisarCpf(cliente.getCpf());

			try {
				if (rs.next()) {
					throw new FoundException();
				} else {
					clienteDAO.cadastrar(cliente);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			throw new InvalidParameterException();
		}
	}

	@Override
	public void alterar(ClienteVO cliente) throws NotFoundException, InvalidParameterException {
		if (!super.isInvalid(cliente)) {
			ResultSet rs = clienteDAO.pesquisarId(cliente.getId());

			try {
				if (rs.next()) {
					clienteDAO.alterar(cliente);
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
	public void deletar(long idCliente) throws NotFoundException, InvalidParameterException {
		if (idCliente != 0) {
			ResultSet rs = clienteDAO.pesquisarId(idCliente);

			try {
				if (rs.next()) {
					clienteDAO.deletar(idCliente);
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
	public List<ClienteVO> listar() {
		List<ClienteVO> clientes = new ArrayList<ClienteVO>();

		ResultSet rs = clienteDAO.listar();

		try {
			while (rs.next()) {
				clientes.add(getCliente(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return clientes;
	}

	@Override
	public ClienteVO pesquisarId(long idCliente) throws NotFoundException, InvalidParameterException {
		if (idCliente != 0) {
			ResultSet rs = clienteDAO.pesquisarId(idCliente);

			try {
				if (rs.next()) {
					return getCliente(rs);
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
	public List<ClienteVO> pesquisarNome(String nome) throws InvalidParameterException {
		List<ClienteVO> clientes = new ArrayList<ClienteVO>();

		if ((nome != null) && !nome.isBlank()) {
			ResultSet rs = clienteDAO.pesquisarNome('%' + nome.trim() + '%');

			try {
				while (rs.next()) {
					clientes.add(getCliente(rs));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			throw new InvalidParameterException();
		}

		return clientes;
	}

	@Override
	public ClienteVO pesquisarCpf(String cpf) throws NotFoundException, InvalidParameterException {
		if (Validador.isCpf(cpf)) {
			ResultSet rs = clienteDAO.pesquisarCpf(cpf.replaceAll("\\D", ""));

			try {
				if (rs.next()) {
					return getCliente(rs);
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

	private ClienteVO getCliente(ResultSet rs) {
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

			ClienteVO cliente = new ClienteVO(rs.getString("nome"), rs.getString("cpf"), enderecos, emails, telefones);
			cliente.setId(rs.getLong("id"));
			cliente.setIdPessoa(idPessoa);

			return cliente;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}
}
