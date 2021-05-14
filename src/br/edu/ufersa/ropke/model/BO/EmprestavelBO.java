package br.edu.ufersa.ropke.model.BO;

import java.io.File;

import br.edu.ufersa.ropke.exception.FoundException;
import br.edu.ufersa.ropke.exception.InvalidParameterException;
import br.edu.ufersa.ropke.exception.NotFoundException;
import br.edu.ufersa.ropke.model.DAO.EmprestavelDAO;
import br.edu.ufersa.ropke.model.VO.EmprestavelVO;

public abstract class EmprestavelBO<VO extends EmprestavelVO> extends OperacaoBO<VO> {
	private static EmprestavelDAO<EmprestavelVO> emprestavelDAO = new EmprestavelDAO<EmprestavelVO>();

	public boolean isNull(VO emprestavel, File arquivo) {
		// Verifica se a entrada de argumentos não é nula
		if ((emprestavel != null) && (arquivo != null)) {
			// Verifica se parâmetros importantes não são nulos
			if (emprestavel.getTitulo() != null) {
				return false;
			} else {
				return true;
			}
		} else {
			return true;
		}
	}

	@Override
	public void cadastrar(VO emprestavel, File arquivo) {
		// Verifica se a entrada de argumentos não é nula
		if (!isNull(emprestavel, arquivo)) {
			// Verifica se o emprestavel não existe no sistema
			if (emprestavelDAO.pesquisar(emprestavel, arquivo) == null) {
				emprestavelDAO.cadastrar(emprestavel, arquivo);
			} else {
				throw new FoundException();
			}
		} else {
			throw new InvalidParameterException();
		}
	}

	@Override
	public void alterar(VO emprestavel, File arquivo) {
		// Verifica se a entrada de argumentos não é nula
		if (!isNull(emprestavel, arquivo)) {
			// Verifica se o emprestavel existe no sistema
			if (emprestavelDAO.pesquisar(emprestavel, arquivo) != null) {
				emprestavelDAO.alterar(emprestavel, arquivo);
			} else {
				throw new NotFoundException();
			}
		} else {
			throw new InvalidParameterException();
		}
	}

	@Override
	public void deletar(VO emprestavel, File arquivo) {
		// Verifica se a entrada de argumentos não é nula
		if (!isNull(emprestavel, arquivo)) {
			// Verifica se o emprestavel existe no sistema
			if (emprestavelDAO.pesquisar(emprestavel, arquivo) != null) {
				emprestavelDAO.deletar(emprestavel, arquivo);
			} else {
				throw new NotFoundException();
			}
		} else {
			throw new InvalidParameterException();
		}
	}

	@Override
	public void pesquisar(File arquivo) {
		if (arquivo != null) {
			emprestavelDAO.pesquisar(arquivo);
		} else {
			throw new InvalidParameterException();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public VO pesquisar(VO emprestavel, File arquivo) {
		// Verifica se a entrada de argumentos não é nula
		if (!isNull(emprestavel, arquivo)) {
			return (VO) emprestavelDAO.pesquisar(emprestavel, arquivo);
		} else {
			return null;
		}
	}

	public EmprestavelVO[] pesquisarTitulo(String titulo, File arquivo) {
		// Verifica se a entrada de argumentos não é nula
		if ((titulo != null) && (arquivo != null)) {
			// Verifica se parâmetros importantes não são nulos
			if ((titulo != "")) {
				// Verifica se o emprestavel existe no sistema
				return emprestavelDAO.pesquisarTitulo(titulo, arquivo);
			} else {
				EmprestavelVO[] semEmprestaveis = new EmprestavelVO[0];
				return semEmprestaveis;
			}
		} else {
			EmprestavelVO[] semEmprestaveis = new EmprestavelVO[0];
			return semEmprestaveis;
		}
	}

	public EmprestavelVO[] pesquisarAnoLancamento(int anoLancamento, File arquivo) {
		// Verifica se a entrada de argumentos não é nula
		if (arquivo != null) {
			return emprestavelDAO.pesquisarAnoLancamento(anoLancamento, arquivo);
		} else {
			EmprestavelVO[] semEmprestaveis = new EmprestavelVO[0];
			return semEmprestaveis;
		}
	}
}
