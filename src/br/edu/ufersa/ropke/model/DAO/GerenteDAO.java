package br.edu.ufersa.ropke.model.DAO;

import java.io.File;

import br.edu.ufersa.ropke.model.VO.GerenteVO;

public class GerenteDAO extends UsuarioDAO<GerenteVO> {
	private static final File arquivo = new File("src/br/edu/ufersa/ropke/model/DAO/arquivos/gerentes.dat");

	public static File getArquivo() {
		return arquivo;
	}

	public void cadastrar(GerenteVO gerente) {
		super.cadastrar(gerente, arquivo);
	}

	public void alterar(GerenteVO gerente) {
		super.alterar(gerente, arquivo);
	}

	public void deletar(GerenteVO gerente) {
		super.deletar(gerente, arquivo);
	}

	public void pesquisar() {
		super.pesquisar(arquivo);
	}

	public GerenteVO pesquisar(GerenteVO gerente) {
		return super.pesquisar(gerente, arquivo);
	}
}
