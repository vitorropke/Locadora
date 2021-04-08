package br.edu.ufersa.ropke.model.DAO;

import br.edu.ufersa.ropke.model.VO.Livro;
import br.edu.ufersa.ropke.model.VO.Disco;
import br.edu.ufersa.ropke.model.VO.Cliente;
import br.edu.ufersa.ropke.model.VO.Usuario;
import br.edu.ufersa.ropke.model.VO.Funcionario;
import br.edu.ufersa.ropke.model.VO.Gerente;
import br.edu.ufersa.ropke.model.VO.Emprestimo;

class MainDAO {
	public static void main(String[] args) throws Exception {
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

		LivroDAO livroDAO = new LivroDAO();
		DiscoDAO discoDAO = new DiscoDAO();
		ClienteDAO clienteDAO = new ClienteDAO();
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
		GerenteDAO gerenteDAO = new GerenteDAO();
		EmprestimoDAO emprestimoDAO = new EmprestimoDAO();

		System.out.println("\nLivro\n");
		livroDAO.cadastrar(livro1);
		livroDAO.cadastrar(livro2);
		livroDAO.pesquisar();

		System.out.println();

		// Disco
		System.out.println("\nDisco\n");
		discoDAO.cadastrar(disco1);
		discoDAO.pesquisar();

		System.out.println();

		// Cliente
		System.out.println("\nCliente\n");
		clienteDAO.cadastrar(cliente1);
		clienteDAO.pesquisar();
		
		System.out.println();

		// Usuário
		System.out.println("\nUsuario\n");
		usuarioDAO.cadastrar(usuario1);
		usuarioDAO.pesquisar();

		System.out.println();

		// Funcionário
		System.out.println("\nFuncionario\n");
		funcionarioDAO.cadastrar(funcionario1);
		funcionarioDAO.pesquisar();

		System.out.println();

		// Gerente
		System.out.println("\nGerente\n");
		gerenteDAO.cadastrar(gerente1);
		gerenteDAO.pesquisar();

		System.out.println();

		// Empréstimo
		System.out.println("\nEmprestimo\n");
		emprestimoDAO.cadastrar(emprestimo1);
		emprestimoDAO.pesquisar();
		
		System.out.println();
	}
}
