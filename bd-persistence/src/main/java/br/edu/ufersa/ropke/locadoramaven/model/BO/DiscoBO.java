package br.edu.ufersa.ropke.locadoramaven.model.BO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.ufersa.ropke.locadoramaven.exception.InvalidParameterException;
import br.edu.ufersa.ropke.locadoramaven.exception.NotFoundException;
import br.edu.ufersa.ropke.locadoramaven.model.DAO.DiscoDAO;
import br.edu.ufersa.ropke.locadoramaven.model.VO.DiscoVO;

public class DiscoBO extends EmprestavelBO<DiscoVO> {
	private static final DiscoDAO discoDAO = new DiscoDAO();

	@Override
	public boolean isInvalid(DiscoVO disco) {
		if (!super.isInvalid(disco) && (disco.getBanda() != null) && (disco.getEstilo() != null)) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	public void cadastrar(DiscoVO disco) throws InvalidParameterException {
		if (!isInvalid(disco)) {
			discoDAO.cadastrar(disco);
		} else {
			throw new InvalidParameterException();
		}
	}

	@Override
	public void alterar(DiscoVO disco) throws NotFoundException, InvalidParameterException {
		if (!isInvalid(disco)) {
			ResultSet rs = discoDAO.pesquisarId(disco.getId());

			try {
				if (rs.next()) {
					discoDAO.alterar(disco);
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
	public void deletar(long idDisco) throws NotFoundException, InvalidParameterException {
		if (idDisco != 0) {
			ResultSet rs = discoDAO.pesquisarId(idDisco);

			try {
				if (rs.next()) {
					discoDAO.deletar(idDisco);
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
	public List<DiscoVO> listar() {
		List<DiscoVO> discos = new ArrayList<DiscoVO>();

		ResultSet rs = discoDAO.listar();

		try {
			while (rs.next()) {
				discos.add(getDisco(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return discos;
	}

	@Override
	public DiscoVO pesquisarId(long idDisco) throws NotFoundException, InvalidParameterException {
		if (idDisco != 0) {
			ResultSet rs = discoDAO.pesquisarId(idDisco);

			try {
				if (rs.next()) {
					return getDisco(rs);
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
	public List<DiscoVO> pesquisarTitulo(String titulo) throws InvalidParameterException {
		List<DiscoVO> discos = new ArrayList<DiscoVO>();

		if ((titulo != null) && !titulo.isBlank()) {
			ResultSet rs = discoDAO.pesquisarTitulo('%' + titulo.trim() + '%');

			try {
				while (rs.next()) {
					discos.add(getDisco(rs));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			throw new InvalidParameterException();
		}

		return discos;
	}

	@Override
	public List<DiscoVO> pesquisarAnoLancamento(int anoLancamento) {
		List<DiscoVO> discos = new ArrayList<DiscoVO>();
		ResultSet rs = discoDAO.pesquisarAnoLancamento(anoLancamento);

		try {
			while (rs.next()) {
				discos.add(getDisco(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return discos;
	}

	public List<DiscoVO> pesquisarBanda(String banda) throws InvalidParameterException {
		List<DiscoVO> discos = new ArrayList<DiscoVO>();

		if ((banda != null) && !banda.isBlank()) {
			ResultSet rs = discoDAO.pesquisarBanda('%' + banda.trim() + '%');

			try {
				while (rs.next()) {
					discos.add(getDisco(rs));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			throw new InvalidParameterException();
		}

		return discos;
	}

	public List<DiscoVO> pesquisarEstilo(String estilo) throws InvalidParameterException {
		List<DiscoVO> discos = new ArrayList<DiscoVO>();

		if ((estilo != null) && !estilo.isBlank()) {
			ResultSet rs = discoDAO.pesquisarEstilo('%' + estilo.trim() + '%');

			try {
				while (rs.next()) {
					discos.add(getDisco(rs));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			throw new InvalidParameterException();
		}

		return discos;
	}

	private DiscoVO getDisco(ResultSet rs) {
		try {
			DiscoVO disco = new DiscoVO(rs.getString("titulo"), rs.getString("banda"), rs.getString("estilo"),
					rs.getInt("numero_exemplares"), rs.getInt("numero_emprestimos"), rs.getInt("numero_dias_alugado"),
					rs.getInt("ano_lancamento"), rs.getFloat("valor_aluguel"));
			disco.setId(rs.getLong("id"));
			disco.setIdEmprestavel(rs.getLong("id_emprestavel"));

			return disco;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}
}
