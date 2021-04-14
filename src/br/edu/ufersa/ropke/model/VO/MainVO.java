package br.edu.ufersa.ropke.model.VO;

public class MainVO {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String[] enderecos = { "rua 1" };
		String[] emails = { "123abc@gmail.com" };
		String[] telefones = { "(84) 3000-0000" };
		// enderecos[0] = "";
		// emails[0] = "";
		// emails[1] = "";
		// telefones[0] = "";

		// titulo, genero, numeroPaginas, 'numeroExemplares', 'numeroEmprestimos',
		// 'numeroDiasAlugado', anoLancamento, valorAluguel
		LivroVO livro1 = new LivroVO("oi", "romance", 150, 4, 2010, 12);
		LivroVO livro2 = new LivroVO("em", "acao", 220, 1, 2018, 26);

		// titulo, nomeBanda, estilo, 'numeroExemplares', 'numeroEmprestimos',
		// 'numeroDiasAlugado', anoLancamento, valorAluguel
		DiscoVO disco1 = new DiscoVO("ola", "ssd", "pagode", 15, 2, 28, 2018, 8.80f);

		// nome, cpf, endereco, 'email', 'telefone'
		ClienteVO cliente1 = new ClienteVO("joao", "19315322061", enderecos, emails, telefones);

		// login, senha, nome, cpf, endereco, 'email', 'telefone'
		enderecos[0] = "rua 3";
		emails[0] = "a@hotmail.com";
		telefones[0] = "(84) 0000-0000";
		FuncionarioVO funcionario1 = new FuncionarioVO("a", "vcdf", "Menino", "072.325.480-01", enderecos, emails,
				telefones);

		enderecos[0] = "rua 4";
		emails[0] = "gen@gmail.com";
		telefones[0] = "(84) 99999-9999";
		GerenteVO gerente1 = new GerenteVO("gen", "asds", "aaaa", "327.886.720-39", enderecos, emails, telefones);

		EmprestimoVO emprestimo1 = new EmprestimoVO();

		// Livros
		System.out.println(
				"-------------------------------------------Livros--------------------------------------------");
		System.out.println("\nLivro");
		System.out.println(livro1);
		System.out.println(livro2);

		// Discos
		System.out.println(
				"-------------------------------------------Discos---------------------------------------------");
		System.out.println("\nDisco");
		System.out.println(disco1);

		// Clientes
		System.out.println(
				"-------------------------------------------Clientes--------------------------------------------");
		System.out.println("\nCliente");
		System.out.println(cliente1);

		// Funcionários
		System.out.println(
				"-----------------------------------------Funcionarios------------------------------------------");
		System.out.println("\nFuncionario");
		System.out.println(funcionario1);

		// Gerentes
		System.out.println(
				"-------------------------------------------Gerentes--------------------------------------------");
		System.out.println("\nGerente");
		System.out.println(gerente1);

		// Empréstimos
		System.out.println(
				"------------------------------------------Emprestimos------------------------------------------");
		System.out.println("\nEmprestimo");
		System.out.println(emprestimo1);
	}
}
