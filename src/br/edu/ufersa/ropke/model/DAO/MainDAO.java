package br.edu.ufersa.ropke.model.DAO;

import java.util.Calendar;

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

		// Livros
		System.out.println("-------------------------------------------Livros--------------------------------------------");
		System.out.println("\nLivro\n");
		LivroDAO.cadastrar(livro1);
		LivroDAO.cadastrar(livro2);
		LivroDAO.pesquisar();

		System.out.println();
		System.out.println("---------------------------------------Altera livro 1----------------------------------------");
		livro1.setNumeroPaginas(140);
		LivroDAO.alterar(livro1);
		LivroDAO.pesquisar();

		System.out.println();
		System.out.println("---------------------------------------Altera livro 2----------------------------------------");
		livro2.setAnoLancamento(1999);
		LivroDAO.alterar(livro2);
		LivroDAO.pesquisar();

		/*
		System.out.println();
		System.out.println("---------------------------------------Deleta livro 1----------------------------------------");
		LivroDAO.deletar(livro1);
		LivroDAO.pesquisar();
		*/

		System.out.println();
		System.out.println("---------------------------------------Pesquisa titulo----------------------------------------");
		Livro livro = LivroDAO.pesquisarTitulo("em");
		System.out.println(livro.toString());

		System.out.println();
		System.out.println("---------------------------------------Pesquisa genero----------------------------------------");
		Livro[] livros = LivroDAO.pesquisarGenero("acao");
		int numeroLivros = livros.length;
		for (int i = 0; i < numeroLivros; i++) {
			System.out.println(livros[i].toString());
		}

		System.out.println();
		System.out.println("---------------------------------------Pesquisa ano-------------------------------------------");
		livros = LivroDAO.pesquisarAno(1999);
		numeroLivros = livros.length;
		for (int i = 0; i < numeroLivros; i++) {
			System.out.println(livros[i].toString());
		}

		// Discos
		System.out.println("-------------------------------------------Discos---------------------------------------------");
		System.out.println("\nDisco\n");
		DiscoDAO.cadastrar(disco1);
		DiscoDAO.pesquisar();

		System.out.println();

		System.out.println();
		System.out.println("---------------------------------------Altera disco 1----------------------------------------");
		disco1.setNumeroExemplares(80);
		DiscoDAO.alterar(disco1);
		DiscoDAO.pesquisar();
		
		/*
		System.out.println();
		System.out.println("---------------------------------------Deleta disco 1----------------------------------------");
		DiscoDAO.deletar(disco1);
		DiscoDAO.pesquisar();
		*/
		
		System.out.println();
		System.out.println("---------------------------------------Pesquisa titulo----------------------------------------");
		Disco disco = DiscoDAO.pesquisarTitulo("em");
		System.out.println(disco.toString());

		System.out.println();
		System.out.println("---------------------------------------Pesquisa banda-----------------------------------------");
		Disco[] discos = DiscoDAO.pesquisarBanda("ssd");
		int numeroDiscos = discos.length;
		for (int i = 0; i < numeroDiscos; i++) {
			System.out.println(discos[i].toString());
		}

		System.out.println();
		System.out.println("---------------------------------------Pesquisa estilo----------------------------------------");
		discos = DiscoDAO.pesquisarEstilo("pagode");
		numeroDiscos = discos.length;
		for (int i = 0; i < numeroDiscos; i++) {
			System.out.println(discos[i].toString());
		}

		System.out.println();
		System.out.println("---------------------------------------Pesquisa ano-------------------------------------------");
		discos = DiscoDAO.pesquisarAno(2018);
		numeroDiscos = discos.length;
		for (int i = 0; i < numeroDiscos; i++) {
			System.out.println(discos[i].toString());
		}
		
		// Clientes
		System.out.println("-------------------------------------------Clientes--------------------------------------------");
		System.out.println("\nCliente\n");
		ClienteDAO.cadastrar(cliente1);
		ClienteDAO.pesquisar();
		
		System.out.println();
		System.out.println("---------------------------------------Altera cliente 1----------------------------------------");
		cliente1.setEndereco("Rua longe do local");
		ClienteDAO.alterar(cliente1);
		ClienteDAO.pesquisar();
		
		/*
		System.out.println();
		System.out.println("---------------------------------------Deleta cliente 1----------------------------------------");
		ClienteDAO.deletar(cliente1);
		ClienteDAO.pesquisar();
		*/

		// Usuários
		System.out.println("-------------------------------------------Usuarios--------------------------------------------");
		System.out.println("\nUsuario\n");
		UsuarioDAO.cadastrar(usuario1);
		UsuarioDAO.pesquisar();

		System.out.println();
		System.out.println("---------------------------------------Altera usuario 1----------------------------------------");
		usuario1.setEndereco("Rua longe do local");
		UsuarioDAO.alterar(usuario1);
		UsuarioDAO.pesquisar();

		/*
		System.out.println();
		System.out.println("---------------------------------------Deleta usuario 1----------------------------------------");
		UsuarioDAO.deletar(usuario1);
		UsuarioDAO.pesquisar();
		*/

		// Funcionários
		System.out.println("-----------------------------------------Funcionarios------------------------------------------");
		System.out.println("\nFuncionario\n");
		FuncionarioDAO.cadastrar(funcionario1);
		FuncionarioDAO.pesquisar();

		System.out.println();
		System.out.println("---------------------------------------Altera funcionario 1----------------------------------------");
		funcionario1.setEndereco("Rua longe do local");
		FuncionarioDAO.alterar(funcionario1);
		FuncionarioDAO.pesquisar();
		
		/*
		System.out.println();
		System.out.println("---------------------------------------Deleta funcionario 1----------------------------------------");
		FuncionarioDAO.deletar(funcionario1);
		FuncionarioDAO.pesquisar();
		*/

		// Gerentes
		System.out.println("-------------------------------------------Gerentes--------------------------------------------");
		System.out.println("\nGerente\n");
		GerenteDAO.cadastrar(gerente1);
		GerenteDAO.pesquisar();

		System.out.println();
		System.out.println("---------------------------------------Altera funcionario 1----------------------------------------");
		gerente1.setEndereco("Rua longe do local");
		GerenteDAO.alterar(gerente1);
		GerenteDAO.pesquisar();
		
		/*
		System.out.println();
		System.out.println("---------------------------------------Deleta funcionario 1----------------------------------------");
		GerenteDAO.deletar(gerente1);
		GerenteDAO.pesquisar();
		*/
		
		// Empréstimos
		// https://www.tutorialspoint.com/java/util/calendar_setfield4
		// create a calendar
		Calendar cal = Calendar.getInstance();
		// set the year,month, day, hour, minute, second
		// the month starts with 0 and goes to 11
		cal.set(2021, 3, 04, 04, 15, 20);

		System.out.println("------------------------------------------Emprestimos------------------------------------------");
		System.out.println("\nEmprestimo\n");
		EmprestimoDAO.cadastrar(emprestimo1);
		EmprestimoDAO.pesquisar();
		
		System.out.println();
		System.out.println("---------------------------------------Altera emprestimo 1----------------------------------------");
		emprestimo1.setDataEmprestimo(cal);
		EmprestimoDAO.alterar(emprestimo1);
		EmprestimoDAO.pesquisar();
		
		/*
		System.out.println();
		System.out.println("---------------------------------------Deleta emprestimo 1----------------------------------------");
		EmprestimoDAO.deletar(emprestimo1);
		EmprestimoDAO.pesquisar();
		*/
	}
}
