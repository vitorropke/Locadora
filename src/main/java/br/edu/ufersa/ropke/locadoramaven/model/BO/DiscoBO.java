package br.edu.ufersa.ropke.locadoramaven.model.BO;

import java.io.File;
import java.util.ArrayList;

import br.edu.ufersa.ropke.locadoramaven.model.DAO.DiscoDAO;
import br.edu.ufersa.ropke.locadoramaven.model.VO.DiscoVO;

public class DiscoBO extends EmprestavelBO<DiscoVO> {
	private final File arquivo = DiscoDAO.getArquivo();
	private DiscoDAO discoDAO = new DiscoDAO();

	public void cadastrar(DiscoVO disco) {
		super.cadastrar(disco, arquivo);
	}

	public void alterar(DiscoVO disco) {
		super.alterar(disco, arquivo);
	}

	public void deletar(DiscoVO disco) {
		super.deletar(disco, arquivo);
	}

	public void pesquisar() {
		super.pesquisar(arquivo);
	}

	public DiscoVO pesquisar(DiscoVO disco) {
		return super.pesquisar(disco, arquivo);
	}

	public ArrayList<DiscoVO> listar() {
		return super.listar(arquivo);
	}

	public ArrayList<DiscoVO> pesquisarTitulo(String titulo) {
		return super.pesquisarTitulo(titulo, arquivo);
	}

	public ArrayList<DiscoVO> pesquisarAnoLancamento(int anoLancamento) {
		return super.pesquisarAnoLancamento(anoLancamento, arquivo);
	}

	public ArrayList<DiscoVO> pesquisarBanda(String banda) {
		if ((banda != null) && (!banda.isBlank())) {
			return discoDAO.pesquisarBanda(banda);
		} else {
			return new ArrayList<DiscoVO>();
		}
	}

	public ArrayList<DiscoVO> pesquisarEstilo(String estilo) {
		if ((estilo != null) && (!estilo.isBlank())) {
			return discoDAO.pesquisarEstilo(estilo);
		} else {
			return new ArrayList<DiscoVO>();
		}
	}
}
