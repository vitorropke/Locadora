package br.edu.ufersa.ropke.model.BO;

import java.io.File;

import br.edu.ufersa.ropke.model.DAO.DiscoDAO;
import br.edu.ufersa.ropke.model.VO.DiscoVO;
import br.edu.ufersa.ropke.model.VO.EmprestavelVO;

public class DiscoBO extends EmprestavelBO {
	private static final File arquivo = DiscoDAO.getArquivo();

	public static void cadastrar(DiscoVO disco) {
		EmprestavelBO.cadastrar(disco, arquivo);
	}

	public static void alterar(DiscoVO disco) {
		EmprestavelBO.alterar(disco, arquivo);
	}

	public static void deletar(DiscoVO disco) {
		EmprestavelBO.deletar(disco, arquivo);
	}

	public static void pesquisar() {
		EmprestavelBO.pesquisar(arquivo);
	}

	public static void pesquisar(DiscoVO disco) {
		EmprestavelBO.pesquisar(disco, arquivo);
	}

	public static DiscoVO pesquisarTitulo(String titulo) {
		return (DiscoVO) EmprestavelBO.pesquisarTitulo(titulo, arquivo);
	}

	public static DiscoVO[] pesquisarAnoLancamento(int anoLancamento) {
		EmprestavelVO[] emprestaveis = EmprestavelBO.pesquisarAnoLancamento(anoLancamento, arquivo);
		int tamanhoVetorEmprestaveis = emprestaveis.length;

		DiscoVO[] discos = new DiscoVO[tamanhoVetorEmprestaveis];

		// cast de cada posição do vetor emprestável para disco
		for (int i = 0; i < tamanhoVetorEmprestaveis; i++) {
			discos[i] = (DiscoVO) emprestaveis[i];
		}

		return discos;
	}

	public static DiscoVO[] pesquisarBanda(String banda) {
		if (banda != null && banda != "") {
			return DiscoDAO.pesquisarBanda(banda);
		} else {
			DiscoVO[] semDiscos = new DiscoVO[0];
			return semDiscos;
		}
	}

	public static DiscoVO[] pesquisarEstilo(String estilo) {
		if (estilo != null && estilo != "") {
			return DiscoDAO.pesquisarEstilo(estilo);
		} else {
			DiscoVO[] semDiscos = new DiscoVO[0];
			return semDiscos;
		}
	}
}
