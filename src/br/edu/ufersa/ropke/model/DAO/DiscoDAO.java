package br.edu.ufersa.ropke.model.DAO;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import br.edu.ufersa.ropke.model.VO.DiscoVO;
import br.edu.ufersa.ropke.model.VO.EmprestavelVO;

public class DiscoDAO extends EmprestavelDAO {
	private static final File arquivo = new File("src/br/edu/ufersa/ropke/model/DAO/arquivos/discos.dat");

	public static File getArquivo() {
		return arquivo;
	}

	public static void cadastrar(DiscoVO disco) {
		EmprestavelDAO.cadastrar(disco, arquivo);
	}

	public static void alterar(DiscoVO disco) {
		EmprestavelDAO.alterar(disco, arquivo);
	}

	public static void deletar(DiscoVO disco) {
		EmprestavelDAO.deletar(disco, arquivo);
	}

	public static void pesquisar() {
		EmprestavelDAO.pesquisar(arquivo);
	}

	public static DiscoVO pesquisarTitulo(String titulo) {
		return (DiscoVO) EmprestavelDAO.pesquisarTitulo(titulo, arquivo);
	}

	public static DiscoVO[] pesquisarAnoLancamento(int anoLancamento) {
		EmprestavelVO[] emprestaveis = EmprestavelDAO.pesquisarAnoLancamento(anoLancamento, arquivo);
		int tamanhoVetorEmprestaveis = emprestaveis.length;

		DiscoVO[] discos = new DiscoVO[tamanhoVetorEmprestaveis];

		// cast de cada posição do vetor emprestável para disco
		for (int i = 0; i < tamanhoVetorEmprestaveis; i++) {
			discos[i] = (DiscoVO) emprestaveis[i];
		}

		return discos;
	}

	public static DiscoVO[] pesquisarBanda(String banda) {
		try {
			ArrayList<DiscoVO> discos = new ArrayList<DiscoVO>();

			// Procura pelo objeto enquanto salva os outros em um vetor de objetos
			if (arquivo.exists() && arquivo.isFile() && arquivo.canRead()) {
				FileInputStream arquivoLeitura = new FileInputStream(arquivo);
				ObjectInputStream objetoLeitura;
				DiscoVO discoLeitura;

				while (arquivoLeitura.available() > 0) {
					// Classe responsável por recuperar os objetos do arquivo
					objetoLeitura = new ObjectInputStream(arquivoLeitura);

					discoLeitura = (DiscoVO) objetoLeitura.readObject();

					// Salva o disco no vetor quando a banda for igual ao parametro
					if (discoLeitura.getBanda().equals(banda)) {
						discos.add(discoLeitura);
					}
				}

				arquivoLeitura.close();
			}

			// Verifica se existem discos desse genero
			if (discos.size() != 0) {
				// ArrayList discos para vetor 'vetorDiscos'
				DiscoVO[] vetorDiscos = new DiscoVO[discos.size()];
				vetorDiscos = discos.toArray(vetorDiscos);
				return vetorDiscos;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		DiscoVO[] semDiscos = new DiscoVO[0];
		return semDiscos;
	}

	public static DiscoVO[] pesquisarEstilo(String estilo) {
		try {
			ArrayList<DiscoVO> discos = new ArrayList<DiscoVO>();

			// Procura pelo objeto enquanto salva os outros em um vetor de objetos
			if (arquivo.exists() && arquivo.isFile() && arquivo.canRead()) {
				FileInputStream arquivoLeitura = new FileInputStream(arquivo);
				ObjectInputStream objetoLeitura;
				DiscoVO discoLeitura;

				while (arquivoLeitura.available() > 0) {
					// Classe responsável por recuperar os objetos do arquivo
					objetoLeitura = new ObjectInputStream(arquivoLeitura);

					discoLeitura = (DiscoVO) objetoLeitura.readObject();

					// Salva o disco no vetor quando a banda for igual ao parametro
					if (discoLeitura.getEstilo().equals(estilo)) {
						discos.add(discoLeitura);
					}
				}

				arquivoLeitura.close();
			}

			// Verifica se existem discos desse genero
			if (discos.size() != 0) {
				// ArrayList discos para vetor 'vetorDiscos'
				DiscoVO[] vetorDiscos = new DiscoVO[discos.size()];
				vetorDiscos = discos.toArray(vetorDiscos);
				return vetorDiscos;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		DiscoVO[] semDiscos = new DiscoVO[0];
		return semDiscos;
	}
}
