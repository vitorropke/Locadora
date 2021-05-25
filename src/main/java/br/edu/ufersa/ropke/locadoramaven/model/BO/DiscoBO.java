package br.edu.ufersa.ropke.locadoramaven.model.BO;

import java.io.File;

import br.edu.ufersa.ropke.locadoramaven.model.DAO.DiscoDAO;
import br.edu.ufersa.ropke.locadoramaven.model.VO.DiscoVO;
import br.edu.ufersa.ropke.locadoramaven.model.VO.EmprestavelVO;

public class DiscoBO extends EmprestavelBO<DiscoVO> {
	private static final File arquivo = DiscoDAO.getArquivo();
	private static DiscoDAO discoDAO = new DiscoDAO();

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

	public DiscoVO[] pesquisarTitulo(String titulo) {
		EmprestavelVO[] emprestaveis = super.pesquisarTitulo(titulo, arquivo);
		int tamanhoVetorEmprestaveis = emprestaveis.length;

		DiscoVO[] discos = new DiscoVO[tamanhoVetorEmprestaveis];

		// cast de cada posição do vetor emprestável para disco
		for (int i = 0; i < tamanhoVetorEmprestaveis; i++) {
			discos[i] = (DiscoVO) emprestaveis[i];
		}

		return discos;
	}

	public DiscoVO[] pesquisarAnoLancamento(int anoLancamento) {
		EmprestavelVO[] emprestaveis = super.pesquisarAnoLancamento(anoLancamento, arquivo);
		int tamanhoVetorEmprestaveis = emprestaveis.length;

		DiscoVO[] discos = new DiscoVO[tamanhoVetorEmprestaveis];

		// cast de cada posição do vetor emprestável para disco
		for (int i = 0; i < tamanhoVetorEmprestaveis; i++) {
			discos[i] = (DiscoVO) emprestaveis[i];
		}

		return discos;
	}

	public DiscoVO[] pesquisarBanda(String banda) {
		if (banda != null && banda != "") {
			return discoDAO.pesquisarBanda(banda);
		} else {
			DiscoVO[] semDiscos = new DiscoVO[0];
			return semDiscos;
		}
	}

	public DiscoVO[] pesquisarEstilo(String estilo) {
		if (estilo != null && estilo != "") {
			return discoDAO.pesquisarEstilo(estilo);
		} else {
			DiscoVO[] semDiscos = new DiscoVO[0];
			return semDiscos;
		}
	}
}
