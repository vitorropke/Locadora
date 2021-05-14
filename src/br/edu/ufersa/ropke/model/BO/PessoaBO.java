package br.edu.ufersa.ropke.model.BO;

import java.io.File;

import br.edu.ufersa.ropke.model.DAO.PessoaDAO;
import br.edu.ufersa.ropke.model.VO.PessoaVO;

public abstract class PessoaBO<VO extends PessoaVO> extends OperacaoBO<VO> {
	private static PessoaDAO<PessoaVO> pessoaDAO = new PessoaDAO<PessoaVO>();

	public boolean isNull(VO pessoa, File arquivo) {
		// Verifica se a entrada de argumentos não é nula
		if ((pessoa != null) && (arquivo != null)) {
			// Verifica se parâmetros importantes não são nulos
			if ((pessoa.getNome() != null) && (pessoa.getCpf() != null) && (pessoa.getEndereco() != null)) {
				return false;
			} else {
				return true;
			}
		} else {
			return true;
		}
	}

	@Override
	public void cadastrar(VO pessoa, File arquivo) {
		// Verifica se a entrada de argumentos não é nula
		if (!isNull(pessoa, arquivo)) {
			if (pessoaDAO.pesquisar(pessoa, arquivo) == null) {
				pessoaDAO.cadastrar(pessoa, arquivo);
			}
		}
	}

	@Override
	public void alterar(VO pessoa, File arquivo) {
		// Verifica se a entrada de argumentos não é nula
		if (!isNull(pessoa, arquivo)) {
			if (pessoaDAO.pesquisar(pessoa, arquivo) != null) {
				pessoaDAO.alterar(pessoa, arquivo);
			}
		}
	}

	@Override
	public void deletar(VO pessoa, File arquivo) {
		// Verifica se a entrada de argumentos não é nula
		if (!isNull(pessoa, arquivo)) {
			if (pessoaDAO.pesquisar(pessoa, arquivo) != null) {
				pessoaDAO.deletar(pessoa, arquivo);
			}
		}
	}

	@Override
	public void pesquisar(File arquivo) {
		// Verifica se a entrada de argumento não é nula
		if (arquivo != null) {
			pessoaDAO.pesquisar(arquivo);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public VO pesquisar(VO pessoa, File arquivo) {
		// Verifica se a entrada de argumentos não é nula
		if (!isNull(pessoa, arquivo)) {
			return (VO) pessoaDAO.pesquisar(pessoa, arquivo);
		} else {
			return null;
		}
	}
}
