package br.edu.ufersa.ropke.model.BO;

import java.io.File;

import br.edu.ufersa.ropke.model.DAO.PessoaDAO;
import br.edu.ufersa.ropke.model.VO.PessoaVO;

public abstract class PessoaBO extends OperacaoBO {
	public static void cadastrar(PessoaVO pessoa, File arquivo) {
		// Verifica se a entrada de argumentos não é nula
		if ((pessoa != null) && (arquivo != null)) {
			// Verifica se parâmetros importantes não são nulos
			if ((pessoa.getNome() != null) && (pessoa.getCpf() != null) && (pessoa.getEndereco() != null)) {
				// Verifica se a pessoa não existe no sistema
				if (PessoaDAO.pesquisar(pessoa, arquivo) == null) {
					PessoaDAO.cadastrar(pessoa, arquivo);
				}
			}
		}
	}

	public static void alterar(PessoaVO pessoa, File arquivo) {
		// Verifica se a entrada de argumentos não é nula
		if ((pessoa != null) && (arquivo != null)) {
			// Verifica se parâmetros importantes não são nulos
			if ((pessoa.getNome() != null) && (pessoa.getCpf() != null) && (pessoa.getEndereco() != null)) {
				// Verifica se a pessoa existe no sistema
				if (PessoaDAO.pesquisar(pessoa, arquivo) != null) {
					PessoaDAO.alterar(pessoa, arquivo);
				}
			}
		}
	}

	public static void deletar(PessoaVO pessoa, File arquivo) {
		// Verifica se a entrada de argumentos não é nula
		if ((pessoa != null) && (arquivo != null)) {
			// Verifica se parâmetros importantes não são nulos
			if ((pessoa.getNome() != null) && (pessoa.getCpf() != null) && (pessoa.getEndereco() != null)) {
				// Verifica se a pessoa existe no sistema
				if (PessoaDAO.pesquisar(pessoa, arquivo) != null) {
					PessoaDAO.deletar(pessoa, arquivo);
				}
			}
		}
	}

	public static void pesquisar(File arquivo) {
		if (arquivo != null) {
			PessoaDAO.pesquisar(arquivo);
		}
	}

	public static PessoaVO pesquisar(PessoaVO pessoa, File arquivo) {
		// Verifica se a entrada de argumentos não é nula
		if ((pessoa != null) && (arquivo != null)) {
			// Verifica se parâmetros importantes não são nulos
			if ((pessoa.getNome() != null) && (pessoa.getCpf() != null) && (pessoa.getEndereco() != null)) {
				return PessoaDAO.pesquisar(pessoa, arquivo);
			} else {
				return null;
			}
		} else {
			return null;
		}
	}
}
