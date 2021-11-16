package br.edu.ufersa.ropke.locadoramaven.model.BO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.edu.ufersa.ropke.locadoramaven.exception.InvalidParameterException;
import br.edu.ufersa.ropke.locadoramaven.exception.NotFoundException;
import br.edu.ufersa.ropke.locadoramaven.model.DAO.EmprestimoDAO;
import br.edu.ufersa.ropke.locadoramaven.model.VO.ClienteVO;
import br.edu.ufersa.ropke.locadoramaven.model.VO.EmprestimoVO;

public class EmprestimoBO implements BaseInterBO<EmprestimoVO> {
	private static final EmprestimoDAO emprestimoDAO = new EmprestimoDAO();

	public boolean isInvalid(EmprestimoVO emprestimo) {
		if ((emprestimo != null) && (emprestimo.getDataOperacao() != null) && (emprestimo.getCliente() != null)
				&& !emprestimo.getObjetos().isEmpty()) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	public void cadastrar(EmprestimoVO emprestimo) throws InvalidParameterException {
		if (!isInvalid(emprestimo)) {
			emprestimoDAO.cadastrar(emprestimo);
		} else {
			throw new InvalidParameterException();
		}
	}

	@Override
	public void alterar(EmprestimoVO emprestimo) throws NotFoundException, InvalidParameterException {
		if (!isInvalid(emprestimo)) {
			ResultSet rs = emprestimoDAO.pesquisarId(emprestimo.getId());

			try {
				if (rs.next()) {
					emprestimoDAO.alterar(emprestimo);
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
	public void deletar(long idEmprestimo) throws NotFoundException, InvalidParameterException {
		if (idEmprestimo != 0) {
			ResultSet rs = emprestimoDAO.pesquisarId(idEmprestimo);

			try {
				if (rs.next()) {
					emprestimoDAO.deletar(idEmprestimo);
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
	public List<EmprestimoVO> listar() {
		List<EmprestimoVO> emprestimos = new ArrayList<EmprestimoVO>();

		ResultSet rs = emprestimoDAO.listar();

		if (rs != null) {
			try {
				while (rs.next()) {
					emprestimos.add(getEmprestimo(rs));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return emprestimos;
	}

	@Override
	public EmprestimoVO pesquisarId(long idEmprestimo) throws NotFoundException, InvalidParameterException {
		if (idEmprestimo != 0) {
			ResultSet rs = emprestimoDAO.pesquisarId(idEmprestimo);

			try {
				if (rs.next()) {
					return getEmprestimo(rs);
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

	public List<EmprestimoVO> gerarRelatorio(Calendar dataInicio, Calendar dataFim) {
		if ((dataInicio != null) && (dataFim != null)) {
			List<EmprestimoVO> emprestimos = listar();
			List<EmprestimoVO> emprestimosValidos = new ArrayList<EmprestimoVO>();
			int numeroEmprestimos = emprestimos.size();

			// Obtém os empréstimos válidos
			for (int i = 0; i < numeroEmprestimos; i++) {
				if (emprestimos.get(i).getDataOperacao().after(dataInicio)
						&& emprestimos.get(i).getDataOperacao().before(dataFim)) {
					emprestimosValidos.add(emprestimos.get(i));
				}
			}

			return emprestimosValidos;
		}

		throw new InvalidParameterException();
	}

	public List<EmprestimoVO> gerarRelatorioCliente(ClienteVO cliente, Calendar dataInicio, Calendar dataFim) {
		if ((cliente != null) && (dataInicio != null) && (dataFim != null)) {
			List<EmprestimoVO> emprestimos = listar();
			List<EmprestimoVO> emprestimosValidos = new ArrayList<EmprestimoVO>();
			int numeroEmprestimos = emprestimos.size();

			// Obtém os empréstimos válidos
			for (int i = 0; i < numeroEmprestimos; i++) {
				if (emprestimos.get(i).getCliente().getCpf().equals(cliente.getCpf())
						&& emprestimos.get(i).getDataOperacao().after(dataInicio)
						&& emprestimos.get(i).getDataOperacao().before(dataFim)) {
					emprestimosValidos.add(emprestimos.get(i));
				}
			}

			return emprestimosValidos;
		}

		throw new InvalidParameterException();
	}

	private EmprestimoVO getEmprestimo(ResultSet rs) {
		try {
			ClienteBO clienteBO = new ClienteBO();
			ClienteVO cliente = clienteBO.pesquisarId(rs.getLong("id_cliente"));

			EmprestimoVO emprestimo = new EmprestimoVO(cliente, null);
			emprestimo.setId(rs.getLong("id"));

			return emprestimo;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}
}
