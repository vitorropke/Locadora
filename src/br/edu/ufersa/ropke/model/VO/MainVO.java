package br.edu.ufersa.ropke.model.VO;

class MainVO {
	public static void main(String[] args) {
		// titulo, genero, numeroPaginas, numeroExemplares, 'numeroEmprestimos', 'numeroDiasAlugado'
		// anoLancamento, valorAluguel
		Livro livro1 = new Livro("oi", "romance", 150, 4, 2010, 12);
		Livro livro2 = new Livro("em", "acao", 220, 1, 2018, 26);
		// titulo, nomeBanda, estilo, numeroExemplares, 'numeroEmprestimos', 'numeroDiasAlugado'
		// anoLancamento, valorAluguel
		Disco disco1 = new Disco("ola", "ssd", "pagode", 15, 2, 28, 2018, 8.80f);
		
		// nome, cpf, endereco, 'email', 'telefone'
		Cliente cliente1 = new Cliente("joao", "19315322061", "rua 1",
			"123abc@gmail.com", "(84) 3000-0000");
		
		// login, senha, nome, cpf, endereco, 'email', 'telefone'
		Usuario usuario1 = new Usuario("jose123", "abc123", "jose", "324.281.100-39",
			"rua 2", "abc@gmail.com", "(84) 1234-5678");
		// login, senha, nome, cpf, endereco, 'email', 'telefone'
		Funcionario funcionario1 = new Funcionario("a", "vcdf", "Menino",
			"072.325.480-01", "rua 3", "a@hotmail.com", "(84) 0000-0000");
		// login, senha, nome, cpf, endereco, 'email', 'telefone'
		Gerente gerente1 = new Gerente("gen", "asds", "aaaa", "327.886.720-39",
			"rua 4", "gen@gmail.com", "(84) 99999-9999");
		
		Emprestimo emprestimo1 = new Emprestimo();
	}
}
