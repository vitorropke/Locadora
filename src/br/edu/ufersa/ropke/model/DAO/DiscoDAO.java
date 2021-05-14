package br.edu.ufersa.ropke.model.DAO;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import br.edu.ufersa.ropke.model.VO.DiscoVO;
import br.edu.ufersa.ropke.model.VO.EmprestavelVO;

public class DiscoDAO extends EmprestavelDAO<DiscoVO> {
	private static final File arquivo = new File("src/br/edu/ufersa/ropke/model/DAO/arquivos/discos.dat");

	public static File getArquivo() {
		return arquivo;
	}

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

					// Salva o disco no vetor quando parte do nome da banda coincidir com o
					// parâmetro
					if (discoLeitura.getBanda().contains(banda)) {
						discos.add(discoLeitura);
					}
				}

				arquivoLeitura.close();
			}

			int numeroDiscos = discos.size();
			// Verifica se o vetor de discos não é vazio
			if (numeroDiscos != 0) {
				// ArrayList discos para vetor 'vetorDiscos'
				DiscoVO[] vetorDiscos = new DiscoVO[numeroDiscos];
				vetorDiscos = discos.toArray(vetorDiscos);
				return vetorDiscos;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("Nao existem discos dessa banda");
		DiscoVO[] semDiscos = new DiscoVO[0];
		return semDiscos;
	}

	public DiscoVO[] pesquisarEstilo(String estilo) {
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

					// Salva o disco no vetor quando parte do nome do estilo coincidir com o
					// parâmetro
					if (discoLeitura.getEstilo().contains(estilo)) {
						discos.add(discoLeitura);
					}
				}

				arquivoLeitura.close();
			}

			int numeroDiscos = discos.size();
			// Verifica se o vetor de discos não é vazio
			if (numeroDiscos != 0) {
				// ArrayList discos para vetor 'vetorDiscos'
				DiscoVO[] vetorDiscos = new DiscoVO[numeroDiscos];
				vetorDiscos = discos.toArray(vetorDiscos);
				return vetorDiscos;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("Nao existem discos nesse estilo");
		DiscoVO[] semDiscos = new DiscoVO[0];
		return semDiscos;
	}
}
