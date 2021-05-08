package br.edu.ufersa.ropke.model.BO;

import java.io.File;

import br.edu.ufersa.ropke.model.DAO.EmprestavelDAO;
import br.edu.ufersa.ropke.model.VO.EmprestavelVO;

public abstract class EmprestavelBO extends OperacaoBO {
	public static void cadastrar(EmprestavelVO emprestavel, File arquivo) {
		// Verifica se a entrada de argumentos não é nula
		if ((emprestavel != null) && (arquivo != null)) {
			// Verifica se parâmetros importantes não são nulos
			if ((emprestavel.getTitulo() != null)) {
				// Verifica se o emprestavel não existe no sistema
				if (EmprestavelDAO.pesquisar(emprestavel, arquivo) == null) {
					EmprestavelDAO.cadastrar(emprestavel, arquivo);
				}
			}
		}
	}

	public static void alterar(EmprestavelVO emprestavel, File arquivo) {
		// Verifica se a entrada de argumentos não é nula
		if ((emprestavel != null) && (arquivo != null)) {
			// Verifica se parâmetros importantes não são nulos
			if ((emprestavel.getTitulo() != null)) {
				// Verifica se o emprestavel existe no sistema
				if (EmprestavelDAO.pesquisar(emprestavel, arquivo) != null) {
					EmprestavelDAO.alterar(emprestavel, arquivo);
				}
			}
		}
	}

	public static void deletar(EmprestavelVO emprestavel, File arquivo) {
		// Verifica se a entrada de argumentos não é nula
		if ((emprestavel != null) && (arquivo != null)) {
			// Verifica se parâmetros importantes não são nulos
			if ((emprestavel.getTitulo() != null)) {
				// Verifica se o emprestavel existe no sistema
				if (EmprestavelDAO.pesquisar(emprestavel, arquivo) != null) {
					EmprestavelDAO.deletar(emprestavel, arquivo);
				}
			}
		}
	}

	public static void pesquisar(File arquivo) {
		if (arquivo != null) {
			EmprestavelDAO.pesquisar(arquivo);
		}
	}

	public static EmprestavelVO pesquisar(EmprestavelVO emprestavel, File arquivo) {
		// Verifica se a entrada de argumentos não é nula
		if ((emprestavel != null) && (arquivo != null)) {
			// Verifica se parâmetros importantes não são nulos
			if ((emprestavel.getTitulo() != null)) {
				return EmprestavelDAO.pesquisar(emprestavel, arquivo);
			} else {
				return null;
			}
		} else {
			return null;
		}
	}

	public static EmprestavelVO[] pesquisarTitulo(String titulo, File arquivo) {
		// Verifica se a entrada de argumentos não é nula
		if ((titulo != null) && (arquivo != null)) {
			// Verifica se parâmetros importantes não são nulos
			if ((titulo != "")) {
				// Verifica se o emprestavel existe no sistema
				return EmprestavelDAO.pesquisarTitulo(titulo, arquivo);
			} else {
				EmprestavelVO[] semEmprestaveis = new EmprestavelVO[0];
				return semEmprestaveis;
			}
		} else {
			EmprestavelVO[] semEmprestaveis = new EmprestavelVO[0];
			return semEmprestaveis;
		}
	}

	public static EmprestavelVO[] pesquisarAnoLancamento(int anoLancamento, File arquivo) {
		// Verifica se a entrada de argumentos não é nula
		if (arquivo != null) {
			return EmprestavelDAO.pesquisarAnoLancamento(anoLancamento, arquivo);
		} else {
			EmprestavelVO[] semEmprestaveis = new EmprestavelVO[0];
			return semEmprestaveis;
		}
	}
}
