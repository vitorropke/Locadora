package br.edu.ufersa.ropke.model.BO;

import java.util.Calendar;

import br.edu.ufersa.ropke.model.VO.ClienteVO;
import br.edu.ufersa.ropke.model.VO.DiscoVO;
import br.edu.ufersa.ropke.model.VO.EmprestimoVO;
import br.edu.ufersa.ropke.model.VO.FuncionarioVO;
import br.edu.ufersa.ropke.model.VO.GerenteVO;
import br.edu.ufersa.ropke.model.VO.LivroVO;

public class MainBO {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EmprestimoVO emprestimo0 = new EmprestimoVO();
		EmprestimoVO emprestimo1 = new EmprestimoVO();

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

		DiscoVO[] discos = { disco1 };
		LivroVO[] livros = { livro1, livro2 };
		int[] quantidades = { 3, 1 };

		// https://www.tutorialspoint.com/java/util/calendar_setfield4
		// create a calendar
		Calendar cal = Calendar.getInstance();
		Calendar cal0 = Calendar.getInstance();
		// set the year, month, day, hour, minute, second
		// the month starts with 0 and goes to 11
		cal.set(2021, 3, 29, 04, 15, 20);
		cal0.set(2021, 4, 1, 04, 15, 20);
		Calendar[] datas = { cal, cal0 };

		EmprestimoBO.alugar(emprestimo0, quantidades, datas, cliente1, livros);

		Calendar[] datas0 = { cal };
		int[] quantidades0 = { 3 };
		EmprestimoBO.alugar(emprestimo0, quantidades0, datas0, cliente1, discos);

		System.out.println(emprestimo0);

		System.out.println("----------------------------------------Devolucao-------------------------------------");
		LivroVO[] livros0 = { livro1, livro2 };
		quantidades[0] = 3;
		quantidades[1] = 1;
		EmprestimoBO.devolver(emprestimo0, quantidades, datas, livros0);
		EmprestimoBO.devolver(emprestimo0, quantidades0, datas0, discos);

		System.out.println(emprestimo0);
		System.out.println(emprestimo1);

		// Livros
		System.out.println(
				"-------------------------------------------Livros--------------------------------------------");
		System.out.println("\nLivro\n");
		LivroBO.cadastrar(livro1);
		LivroBO.cadastrar(livro2);
		LivroBO.pesquisar();

		System.out.println();
		System.out.println(
				"---------------------------------------Altera livro 1----------------------------------------");
		livro1.setNumeroPaginas(140);
		LivroBO.alterar(livro1);
		LivroBO.pesquisar();

		System.out.println();
		System.out.println(
				"---------------------------------------Altera livro 2----------------------------------------");
		livro2.setAnoLancamento(1999);
		LivroBO.alterar(livro2);
		LivroBO.pesquisar();

		/*
		 * System.out.println(); System.out.
		 * println("---------------------------------------Deleta livro 1----------------------------------------"
		 * ); LivroBO.deletar(livro1); LivroBO.pesquisar();
		 */

		System.out.println();
		System.out.println(
				"---------------------------------------Pesquisa titulo----------------------------------------");
		LivroVO livro = LivroBO.pesquisarTitulo("em");
		System.out.println(livro.toString());

		System.out.println();
		System.out.println(
				"---------------------------------------Pesquisa genero----------------------------------------");
		livros = LivroBO.pesquisarGenero("acao");
		int numeroLivros = livros.length;
		for (int i = 0; i < numeroLivros; i++) {
			System.out.println(livros[i].toString());
		}

		System.out.println();
		System.out.println(
				"---------------------------------------Pesquisa ano-------------------------------------------");
		livros = LivroBO.pesquisarAnoLancamento(1999);
		numeroLivros = livros.length;
		for (int i = 0; i < numeroLivros; i++) {
			System.out.println(livros[i].toString());
		}

		// Discos
		System.out.println(
				"-------------------------------------------Discos---------------------------------------------");
		System.out.println("\nDisco\n");
		DiscoBO.cadastrar(disco1);
		DiscoBO.pesquisar();

		System.out.println();

		System.out.println();
		System.out.println(
				"---------------------------------------Altera disco 1----------------------------------------");
		disco1.setNumeroExemplares(80);
		DiscoBO.alterar(disco1);
		DiscoBO.pesquisar();

		/*
		 * System.out.println(); System.out.
		 * println("---------------------------------------Deleta disco 1----------------------------------------"
		 * ); DiscoBO.deletar(disco1); DiscoBO.pesquisar();
		 */

		System.out.println();
		System.out.println(
				"---------------------------------------Pesquisa titulo----------------------------------------");
		DiscoVO disco = DiscoBO.pesquisarTitulo("em");
		if (disco != null) {
			System.out.println(disco.toString());
		}

		System.out.println();
		System.out.println(
				"---------------------------------------Pesquisa banda-----------------------------------------");
		discos = DiscoBO.pesquisarBanda("ssd");
		int numeroDiscos = discos.length;
		for (int i = 0; i < numeroDiscos; i++) {
			System.out.println(discos[i].toString());
		}

		System.out.println();
		System.out.println(
				"---------------------------------------Pesquisa estilo----------------------------------------");
		discos = DiscoBO.pesquisarEstilo("pagode");
		numeroDiscos = discos.length;
		for (int i = 0; i < numeroDiscos; i++) {
			System.out.println(discos[i].toString());
		}

		System.out.println();
		System.out.println(
				"---------------------------------------Pesquisa ano-------------------------------------------");
		discos = DiscoBO.pesquisarAnoLancamento(2018);
		numeroDiscos = discos.length;
		for (int i = 0; i < numeroDiscos; i++) {
			System.out.println(discos[i].toString());
		}

		// Clientes
		System.out.println(
				"-------------------------------------------Clientes--------------------------------------------");
		System.out.println("\nCliente\n");
		ClienteBO.cadastrar(cliente1);
		ClienteBO.pesquisar();

		System.out.println();
		System.out.println(
				"---------------------------------------Altera cliente 1----------------------------------------");
		enderecos[0] = "Rua longe do local";
		cliente1.setEndereco(enderecos);
		ClienteBO.alterar(cliente1);
		ClienteBO.pesquisar();

		/*
		 * System.out.println(); System.out.
		 * println("---------------------------------------Deleta cliente 1----------------------------------------"
		 * ); ClienteBO.deletar(cliente1); ClienteBO.pesquisar();
		 */

		// Funcionários
		System.out.println(
				"-----------------------------------------Funcionarios------------------------------------------");
		System.out.println("\nFuncionario\n");
		FuncionarioBO.cadastrar(funcionario1);
		FuncionarioBO.pesquisar();

		System.out.println();
		System.out.println(
				"---------------------------------------Altera funcionario 1----------------------------------------");

		funcionario1.setEndereco(enderecos);
		FuncionarioBO.alterar(funcionario1);
		FuncionarioBO.pesquisar();

		/*
		 * System.out.println(); System.out.
		 * println("---------------------------------------Deleta funcionario 1----------------------------------------"
		 * ); FuncionarioBO.deletar(funcionario1); FuncionarioBO.pesquisar();
		 */

		// Gerentes
		System.out.println(
				"-------------------------------------------Gerentes--------------------------------------------");
		System.out.println("\nGerente\n");
		GerenteBO.cadastrar(gerente1);
		GerenteBO.pesquisar();

		System.out.println();
		System.out.println(
				"---------------------------------------Altera gerente 1----------------------------------------");
		gerente1.setEndereco(enderecos);
		GerenteBO.alterar(gerente1);
		GerenteBO.pesquisar();

		/*
		 * System.out.println(); System.out.
		 * println("---------------------------------------Deleta gerente 1----------------------------------------"
		 * ); GerenteBO.deletar(gerente1); GerenteBO.pesquisar();
		 */

		// Empréstimos
		// https://www.tutorialspoint.com/java/util/calendar_setfield4
		// create a calendar
		// set the year,month, day, hour, minute, second
		// the month starts with 0 and goes to 11
		cal.set(2021, 3, 04, 04, 15, 20);

		System.out.println(
				"------------------------------------------Emprestimos------------------------------------------");
		System.out.println("\nEmprestimo\n");
		EmprestimoBO.cadastrar(emprestimo1);
		EmprestimoBO.pesquisar();

		System.out.println();
		System.out.println(
				"---------------------------------------Altera emprestimo 1----------------------------------------");
		emprestimo1.setDataEmprestimo(cal);
		EmprestimoBO.alterar(emprestimo1);
		EmprestimoBO.pesquisar();

		/*
		 * System.out.println(); System.out.
		 * println("---------------------------------------Deleta emprestimo 1----------------------------------------"
		 * ); EmprestimoBO.deletar(emprestimo1); EmprestimoBO.pesquisar();
		 */
	}
}
