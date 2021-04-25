package br.edu.ufersa.ropke.model.DAO;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import br.edu.ufersa.ropke.model.VO.DiscoVO;
import br.edu.ufersa.ropke.model.VO.EmprestavelVO;

public class DiscoDAO extends EmprestavelDAO {
	private static final File arquivo = new File("src/br/edu/ufersa/ropke/model/DAO/arquivos/discos.dat");

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
		EmprestavelVO emprestavel = EmprestavelDAO.pesquisarTitulo(titulo, arquivo);

		// Só retorna o disco se ele não for nulo
		if (emprestavel.getTitulo() == null) {
			DiscoVO discoVazio = new DiscoVO();
			return discoVazio;
		} else {
			return (DiscoVO) emprestavel;
		}
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
			// Gera o arquivo para armazenar o objeto
			File arquivo = new File("src/br/edu/ufersa/ropke/model/DAO/arquivos/discos.dat");

			ArrayList<DiscoVO> discos = new ArrayList<DiscoVO>();
			// Procura pelo objeto enquanto salva os outros em um vetor de objetos
			if (arquivo.exists() && arquivo.isFile() && arquivo.canRead()) {
				FileInputStream arquivoLeitura = new FileInputStream(arquivo);

				while (arquivoLeitura.available() > 0) {
					// Classe responsável por recuperar os objetos do arquivo
					ObjectInputStream objetoLeitura = new ObjectInputStream(arquivoLeitura);

					DiscoVO discoLeitura = (DiscoVO) objetoLeitura.readObject();

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
			} else {
				System.out.println("Sem discos com essa banda!");
				DiscoVO[] semDiscos = new DiscoVO[0];
				return semDiscos;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("Sem discos com essa banda!");
		DiscoVO[] semDiscos = new DiscoVO[0];
		return semDiscos;
	}

	public static DiscoVO[] pesquisarEstilo(String estilo) {
		try {
			// Gera o arquivo para armazenar o objeto
			File arquivo = new File("src/br/edu/ufersa/ropke/model/DAO/arquivos/discos.dat");

			ArrayList<DiscoVO> discos = new ArrayList<DiscoVO>();
			// Procura pelo objeto enquanto salva os outros em um vetor de objetos
			if (arquivo.exists() && arquivo.isFile() && arquivo.canRead()) {
				FileInputStream arquivoLeitura = new FileInputStream(arquivo);

				while (arquivoLeitura.available() > 0) {
					// Classe responsável por recuperar os objetos do arquivo
					ObjectInputStream objetoLeitura = new ObjectInputStream(arquivoLeitura);

					DiscoVO discoLeitura = (DiscoVO) objetoLeitura.readObject();

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
			} else {
				System.out.println("Sem discos com essa banda!");
				DiscoVO[] semDiscos = new DiscoVO[0];
				return semDiscos;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("Sem discos com essa banda!");
		DiscoVO[] semDiscos = new DiscoVO[0];
		return semDiscos;
	}
}
