package br.edu.ufersa.ropke.model.DAO;

import java.io.File;

import br.edu.ufersa.ropke.model.VO.GerenteVO;

public class GerenteDAO extends UsuarioDAO {
	private static final File arquivo = new File("src/br/edu/ufersa/ropke/model/DAO/arquivos/gerentes.dat");

	public static File getArquivo() {
		return arquivo;
	}

	public static void cadastrar(GerenteVO gerente) {
		UsuarioDAO.cadastrar(gerente, arquivo);
	}

	public static void alterar(GerenteVO gerente) {
		UsuarioDAO.alterar(gerente, arquivo);
	}

	public static void deletar(GerenteVO gerente) {
		UsuarioDAO.deletar(gerente, arquivo);
	}

	public static void pesquisar() {
		UsuarioDAO.pesquisar(arquivo);
	}

	public static GerenteVO pesquisar(GerenteVO gerente) {
		return (GerenteVO) UsuarioDAO.pesquisar(gerente, arquivo);
	}
}
