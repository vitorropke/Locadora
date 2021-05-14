package br.edu.ufersa.ropke.model.BO;

import java.io.File;

import br.edu.ufersa.ropke.model.DAO.PessoaDAO;
import br.edu.ufersa.ropke.model.VO.PessoaVO;

public abstract class PessoaBO<VO extends PessoaVO> extends OperacaoBO<VO> {
	VO pessoaDAO = new PessoaDAO();
	
	@Override
	public void cadastrar(VO pessoa, File arquivo) {
		// Verifica se a entrada de argumentos não é nula
		if ((pessoa != null) && (arquivo != null)) {
			// Verifica se parâmetros importantes não são nulos
			if ((pessoa.getNome() != null) && (pessoa.getCpf() != null) && (pessoa.getEndereco() != null)) {
				// Verifica se a pessoa não existe no sistema
				if (pessoaDAO.pesquisar(pessoa, arquivo) == null) {
					pessoaDAO.cadastrar(pessoa, arquivo);
				}
			}
		}
	}

	@Override
	public void alterar(VO pessoa, File arquivo) {
		// Verifica se a entrada de argumentos não é nula
		if ((pessoa != null) && (arquivo != null)) {
			// Verifica se parâmetros importantes não são nulos
			if ((pessoa.getNome() != null) && (pessoa.getCpf() != null) && (pessoa.getEndereco() != null)) {
				// Verifica se a pessoa existe no sistema
				if (pessoaDAO.pesquisar(pessoa, arquivo) != null) {
					pessoaDAO.alterar(pessoa, arquivo);
				}
			}
		}
	}

	@Override
	public void deletar(VO pessoa, File arquivo) {
		// Verifica se a entrada de argumentos não é nula
		if ((pessoa != null) && (arquivo != null)) {
			// Verifica se parâmetros importantes não são nulos
			if ((pessoa.getNome() != null) && (pessoa.getCpf() != null) && (pessoa.getEndereco() != null)) {
				// Verifica se a pessoa existe no sistema
				if (pessoaDAO.pesquisar(pessoa, arquivo) != null) {
					pessoaDAO.deletar(pessoa, arquivo);
				}
			}
		}
	}

	@Override
	public void pesquisar(File arquivo) {
		if (arquivo != null) {
			pessoaDAO.pesquisar(arquivo);
		}
	}

	@Override
	public VO pesquisar(VO pessoa, File arquivo) {
		// Verifica se a entrada de argumentos não é nula
		if ((pessoa != null) && (arquivo != null)) {
			// Verifica se parâmetros importantes não são nulos
			if ((pessoa.getNome() != null) && (pessoa.getCpf() != null) && (pessoa.getEndereco() != null)) {
				return pessoaDAO.pesquisar(pessoa, arquivo);
			} else {
				return null;
			}
		} else {
			return null;
		}
	}
}
