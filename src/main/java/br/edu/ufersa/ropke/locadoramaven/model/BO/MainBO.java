package br.edu.ufersa.ropke.locadoramaven.model.BO;

import java.util.ArrayList;
import java.util.Calendar;

import br.edu.ufersa.ropke.locadoramaven.model.VO.ClienteVO;
import br.edu.ufersa.ropke.locadoramaven.model.VO.DiscoVO;
import br.edu.ufersa.ropke.locadoramaven.model.VO.EmprestavelVO;
import br.edu.ufersa.ropke.locadoramaven.model.VO.EmprestimoVO;
import br.edu.ufersa.ropke.locadoramaven.model.VO.FuncionarioVO;
import br.edu.ufersa.ropke.locadoramaven.model.VO.GerenteVO;
import br.edu.ufersa.ropke.locadoramaven.model.VO.LivroVO;

public class MainBO {
	public static void main(String[] args) {
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

		ArrayList<DiscoVO> discos = new ArrayList<DiscoVO>();
		ArrayList<LivroVO> livros = new ArrayList<LivroVO>();
		ArrayList<EmprestavelVO> emprestaveis = new ArrayList<EmprestavelVO>();
		int[] quantidades = { 3, 1 };

		discos.add(disco1);

		livros.add(livro1);
		livros.add(livro2);

		emprestaveis.add(livro2);
		emprestaveis.add(disco1);

		// https://www.tutorialspoint.com/java/util/calendar_setfield4
		// create a calendar
		Calendar cal = Calendar.getInstance();
		Calendar cal0 = Calendar.getInstance();
		// set the year, month, day, hour, minute, second
		// the month starts with 0 and goes to 11
		cal.set(2021, 3, 29, 04, 15, 20);
		cal0.set(2021, 5, 1, 04, 15, 20);
		Calendar[] datas = { cal, cal0 };

		// Cria objetos do tipo BO
		LivroBO livroBO = new LivroBO();
		DiscoBO discoBO = new DiscoBO();
		ClienteBO clienteBO = new ClienteBO();
		FuncionarioBO funcionarioBO = new FuncionarioBO();
		GerenteBO gerenteBO = new GerenteBO();
		EmprestimoBO emprestimoBO = new EmprestimoBO();

		// emprestimoBO.alugar(emprestimo0, quantidades, datas, cliente1, livros);

		Calendar[] datas0 = { cal };
		int[] quantidades0 = { 3 };
		// emprestimoBO.alugar(emprestimo0, quantidades0, datas0, cliente1, discos);

		System.out.println(emprestimo0);

		System.out.println("----------------------------------------Devolucao-------------------------------------");
		LivroVO[] livros0 = { livro1, livro2 };
		quantidades[0] = 3;
		quantidades[1] = 1;
		// emprestimoBO.devolver(emprestimo0, quantidades, datas, livros0);
		// emprestimoBO.devolver(emprestimo0, quantidades0, datas0, discos);

		System.out.println(emprestimo0);
		System.out.println(emprestimo1);

		// Livros
		System.out.println(
				"-------------------------------------------Livros--------------------------------------------");
		System.out.println("\nLivro\n");
		livroBO.cadastrar(livro1);
		livroBO.cadastrar(livro2);
		livroBO.pesquisar();

		System.out.println();
		System.out.println(
				"---------------------------------------Altera livro 1----------------------------------------");
		livro1.setNumeroPaginas(140);
		livroBO.alterar(livro1);
		livroBO.pesquisar();

		System.out.println();
		System.out.println(
				"---------------------------------------Altera livro 2----------------------------------------");
		livro2.setAnoLancamento(1999);
		livroBO.alterar(livro2);
		livroBO.pesquisar();

		/*
		 * System.out.println(); System.out.
		 * println("---------------------------------------Deleta livro 1----------------------------------------"
		 * ); livroBO.deletar(livro1); livroBO.pesquisar();
		 */

		System.out.println();
		System.out.println(
				"---------------------------------------Pesquisa titulo----------------------------------------");
		livros = livroBO.pesquisarTitulo("em");
		int numeroLivros = livros.size();
		for (int i = 0; i < numeroLivros; i++) {
			System.out.println(livros.get(i));
		}

		System.out.println();
		System.out.println(
				"---------------------------------------Pesquisa genero----------------------------------------");
		livros = livroBO.pesquisarGenero("acao");
		numeroLivros = livros.size();
		for (int i = 0; i < numeroLivros; i++) {
			System.out.println(livros.get(i));
		}

		System.out.println();
		System.out.println(
				"---------------------------------------Pesquisa ano-------------------------------------------");
		livros = livroBO.pesquisarAnoLancamento(1999);
		numeroLivros = livros.size();
		for (int i = 0; i < numeroLivros; i++) {
			System.out.println(livros.get(i));
		}

		// Discos
		System.out.println(
				"-------------------------------------------Discos---------------------------------------------");
		System.out.println("\nDisco\n");
		discoBO.cadastrar(disco1);
		discoBO.pesquisar();

		System.out.println();

		System.out.println();
		System.out.println(
				"---------------------------------------Altera disco 1----------------------------------------");
		disco1.setNumeroExemplares(80);
		discoBO.alterar(disco1);
		discoBO.pesquisar();

		/*
		 * System.out.println(); System.out.
		 * println("---------------------------------------Deleta disco 1----------------------------------------"
		 * ); discoBO.deletar(disco1); discoBO.pesquisar();
		 */

		System.out.println();
		System.out.println(
				"---------------------------------------Pesquisa titulo----------------------------------------");
		discos = discoBO.pesquisarTitulo("em");
		int numeroDiscos = discos.size();
		for (int i = 0; i < numeroDiscos; i++) {
			System.out.println(discos.get(i));
		}

		System.out.println();
		System.out.println(
				"---------------------------------------Pesquisa banda-----------------------------------------");
		discos = discoBO.pesquisarBanda("ssd");
		numeroDiscos = discos.size();
		for (int i = 0; i < numeroDiscos; i++) {
			System.out.println(discos.get(i));
		}

		System.out.println();
		System.out.println(
				"---------------------------------------Pesquisa estilo----------------------------------------");
		discos = discoBO.pesquisarEstilo("pagode");
		numeroDiscos = discos.size();
		for (int i = 0; i < numeroDiscos; i++) {
			System.out.println(discos.get(i));
		}

		System.out.println();
		System.out.println(
				"---------------------------------------Pesquisa ano-------------------------------------------");
		discos = discoBO.pesquisarAnoLancamento(2018);
		numeroDiscos = discos.size();
		for (int i = 0; i < numeroDiscos; i++) {
			System.out.println(discos.get(i));
		}

		// Clientes
		System.out.println(
				"-------------------------------------------Clientes--------------------------------------------");
		System.out.println("\nCliente\n");
		clienteBO.cadastrar(cliente1);
		clienteBO.pesquisar();

		System.out.println();
		System.out.println(
				"---------------------------------------Altera cliente 1----------------------------------------");
		enderecos[0] = "Rua longe do local";
		cliente1.setEndereco(enderecos);
		clienteBO.alterar(cliente1);
		clienteBO.pesquisar();

		/*
		 * System.out.println(); System.out.
		 * println("---------------------------------------Deleta cliente 1----------------------------------------"
		 * ); clienteBO.deletar(cliente1); clienteBO.pesquisar();
		 */

		// Funcionários
		System.out.println(
				"-----------------------------------------Funcionarios------------------------------------------");
		System.out.println("\nFuncionario\n");
		funcionarioBO.cadastrar(funcionario1);
		funcionarioBO.pesquisar();

		System.out.println();
		System.out.println(
				"---------------------------------------Altera funcionario 1----------------------------------------");

		funcionario1.setEndereco(enderecos);
		funcionarioBO.alterar(funcionario1);
		funcionarioBO.pesquisar();

		/*
		 * System.out.println(); System.out.
		 * println("---------------------------------------Deleta funcionario 1----------------------------------------"
		 * ); FuncionarioBO.deletar(funcionario1); FuncionarioBO.pesquisar();
		 */

		// Gerentes
		System.out.println(
				"-------------------------------------------Gerentes--------------------------------------------");
		System.out.println("\nGerente\n");
		gerenteBO.cadastrar(gerente1);
		gerenteBO.pesquisar();

		System.out.println();
		System.out.println(
				"---------------------------------------Altera gerente 1----------------------------------------");
		gerente1.setEndereco(enderecos);
		gerenteBO.alterar(gerente1);
		gerenteBO.pesquisar();

		/*
		 * System.out.println(); System.out.
		 * println("---------------------------------------Deleta gerente 1----------------------------------------"
		 * ); gerenteBO.deletar(gerente1); gerenteBO.pesquisar();
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
		// emprestimoBO.cadastrar(emprestimo1);
		emprestimoBO.pesquisar();

		System.out.println();
		System.out.println(
				"---------------------------------------Altera emprestimo 1----------------------------------------");
		emprestimo1.setDataEmprestimo(cal);
		// emprestimoBO.alterar(emprestimo1);
		emprestimoBO.pesquisar();

		/*
		 * System.out.println(); System.out.
		 * println("---------------------------------------Deleta emprestimo 1----------------------------------------"
		 * ); emprestimoBO.deletar(emprestimo1); emprestimoBO.pesquisar();
		 */
	}
}
