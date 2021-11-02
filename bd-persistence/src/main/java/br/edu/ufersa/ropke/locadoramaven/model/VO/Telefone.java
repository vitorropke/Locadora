package br.edu.ufersa.ropke.locadoramaven.model.VO;

public class Telefone {
	private long id;
	private String ddd;
	private String telefone;

	public Telefone(String ddd, String telefone) {
		setDdd(ddd);
		setTelefone(telefone);
	}

	@Override
	public String toString() {
		String telefone;

		telefone = "\n(" + ddd + ") ";

		if (this.telefone.length() == 8) {
			telefone += this.telefone.substring(0, 4);
			telefone += '-' + this.telefone.substring(4);
		} else {
			telefone += this.telefone.substring(0, 1) + ' ';
			telefone += this.telefone.substring(1, 5);
			telefone += '-' + this.telefone.substring(5) + '\n';
		}

		return telefone;
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

	public String getDdd() {
		return ddd;
	}

	public void setDdd(String ddd) {
		if ((ddd != null) && !ddd.isBlank()) {
			// Reduz o DDD, removendo tudo que não é dígito
			ddd = ddd.replaceAll("\\D", "");

			if (ddd.length() == 2) {
				this.ddd = ddd;
			} else if ((ddd.length() == 3) && (ddd.charAt(0) == '0')) {
				this.ddd = ddd.substring(1);
			} else {
				System.out.println("DDD invalido");
			}
		} else {
			System.out.println("DDD vazio");
		}
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		if ((telefone != null) && !telefone.isBlank()) {
			// Reduz o telefone, removendo tudo que não é dígito
			telefone = telefone.replaceAll("\\D", "");

			if ((telefone.length() == 8) || (telefone.length() == 9)) {
				this.telefone = telefone;
			} else {
				System.out.println("Telefone invalido");
			}
		} else {
			System.out.println("Telefone vazio");
		}
	}
}
