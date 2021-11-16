package br.edu.ufersa.ropke.locadoramaven.model.DAO;

import java.sql.ResultSet;

import br.edu.ufersa.ropke.locadoramaven.model.VO.PessoaVO;

public interface PessoaInterDAO<VO extends PessoaVO> extends BaseInterDAO<VO> {
	public ResultSet pesquisarNome(String nome);

	public ResultSet pesquisarCpf(String cpf);
}
