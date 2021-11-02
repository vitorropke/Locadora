package br.edu.ufersa.ropke.locadoramaven.model.BO;

import java.io.File;
import java.sql.SQLException;

import br.edu.ufersa.ropke.locadoramaven.exception.InvalidParameterException;
import br.edu.ufersa.ropke.locadoramaven.exception.NotFoundException;
import br.edu.ufersa.ropke.locadoramaven.exception.FoundException;
import br.edu.ufersa.ropke.locadoramaven.model.DAO.PessoaDAO;
import br.edu.ufersa.ropke.locadoramaven.model.VO.PessoaVO;

public abstract class PessoaBO<VO extends PessoaVO> implements OperacaoInterBO<VO> {
	private PessoaDAO<VO> pessoaDAO = new PessoaDAO<VO>();

	public boolean isNull(VO pessoa) {
		if (pessoa != null) {
			if ((pessoa.getNome() != null) && pessoa.getEnderecos().isEmpty()) {
				return false;
			} else {
				return true;
			}
		} else {
			return true;
		}
	}

	@Override
	public void cadastrar(VO pessoa) throws FoundException, InvalidParameterException {
		if (!isNull(pessoa)) {
			try {
				if (pessoaDAO.pesquisar(pessoa) == null) {
					pessoaDAO.cadastrar(pessoa);
				} else {
					throw new FoundException();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			throw new InvalidParameterException();
		}
	}

	@Override
	public void alterar(VO pessoa) {
		// Verifica se a entrada de argumentos não é nula
		if (!isNull(pessoa)) {
			if (pessoaDAO.pesquisar(pessoa) != null) {
				pessoaDAO.alterar(pessoa);
			} else {
				throw new NotFoundException();
			}
		} else {
			throw new InvalidParameterException();
		}
	}

	@Override
	public void deletar(VO pessoa, File arquivo) {
		// Verifica se a entrada de argumentos não é nula
		if (!isNull(pessoa, arquivo)) {
			if (pessoaDAO.pesquisar(pessoa, arquivo) != null) {
				pessoaDAO.deletar(pessoa, arquivo);
			} else {
				throw new NotFoundException();
			}
		} else {
			throw new InvalidParameterException();
		}
	}

	@Override
	public VO pesquisar(VO pessoa, File arquivo) {
		// Verifica se a entrada de argumentos não é nula
		if (!isNull(pessoa, arquivo)) {
			return pessoaDAO.pesquisar(pessoa, arquivo);
		} else {
			return null;
		}
	}
}
