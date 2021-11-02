package br.edu.ufersa.ropke.locadoramaven.model.VO;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class Endereco {
	private long id;
	private String logradouro;
	private String numero;
	private String complemento;
	private String bairro;
	private String cidade;
	private String estado;
	private String cep;

	public Endereco(String logradouro, String numero, String complemento, String bairro, String cidade, String estado,
			String cep) {
		setLogradouro(logradouro);
		setNumero(numero);
		setComplemento(complemento);
		setBairro(bairro);
		setCidade(cidade);
		setEstado(estado);
		setCep(cep);
	}

	@Override
	public String toString() {
		String endereco;

		endereco = "\nLogradouro:\t" + logradouro;
		endereco += "\nNumero:\t\t" + numero;
		endereco += "\nComplemento:\t" + complemento;
		endereco += "\nBairro:\t\t" + bairro;
		endereco += "\nCidade:\t\t" + cidade;
		endereco += "\nEstado:\t\t" + estado;
		endereco += "\nCEP:\t\t" + cep.substring(0, 5) + '-' + cep.substring(5) + '\n';

		return endereco;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		if (id >= 0) {
			this.id = id;
		} else {
			System.out.println("ID negativo");
		}
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		if ((logradouro != null) && !logradouro.isBlank()) {
			this.logradouro = logradouro.trim();
		} else {
			System.out.println("Logradouro vazio");
		}
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		if ((numero != null) && !numero.isBlank()) {
			this.numero = numero.trim();
		} else {
			System.out.println("Numero vazio");
		}
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		if ((complemento != null) && !complemento.isBlank()) {
			this.complemento = complemento.trim();
		} else {
			System.out.println("Complemento vazio");
		}
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		if ((bairro != null) && !bairro.isBlank()) {
			this.bairro = bairro.trim();
		} else {
			System.out.println("Bairro vazio");
		}
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		if ((cidade != null) && !cidade.isBlank()) {
			this.cidade = cidade.trim();
		} else {
			System.out.println("Cidade vazia");
		}
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		if ((estado != null) && !estado.isBlank()) {
			estado = estado.trim();

			// O estado é no formato de sigla. 2 caracteres maiúsculos
			if (estado.length() == 2) {
				this.estado = estado.toUpperCase();
			} else {
				System.out.println("Estado invalido");
			}
		} else {
			System.out.println("Estado vazio");
		}
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		if ((cep != null) && !cep.isBlank()) {
			// Reduz o CEP, removendo tudo que não é dígito
			cep = cep.replaceAll("\\D", "");

			if (cep.length() == 8) {
				this.cep = cep;
			} else {
				System.out.println("CEP invalido");
			}
		} else {
			System.out.println("CEP vazio");
		}
	}

	// https://www.blogson.com.br/busca-automatica-de-cep-em-java-netbeans/
	public void buscarCep(String cep) {
		if ((cep != null) && !cep.isBlank()) {
			String json;

			try {
				URL url = new URL("http://viacep.com.br/ws/" + cep + "/json");
				URLConnection urlConnection = url.openConnection();
				InputStream is = urlConnection.getInputStream();
				BufferedReader br = new BufferedReader(new InputStreamReader(is));

				StringBuilder jsonSb = new StringBuilder();

				br.lines().forEach(l -> jsonSb.append(l.trim()));
				json = jsonSb.toString();

				json = json.replaceAll("[{},:]", "");
				json = json.replaceAll("\"", "\n");
				String array[] = new String[30];
				array = json.split("\n");

				if (array.length >= 7) {
					logradouro = array[7];
					bairro = array[15];
					cidade = array[19];
					estado = array[23];
				} else {
					System.out.println("CEP invalido");
				}
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		} else {
			System.out.println("CEP vazio");
		}
	}
}
