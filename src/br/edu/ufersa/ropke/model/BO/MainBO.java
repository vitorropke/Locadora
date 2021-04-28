package br.edu.ufersa.ropke.model.BO;

import java.util.Calendar;

import br.edu.ufersa.ropke.model.DAO.ClienteDAO;
import br.edu.ufersa.ropke.model.DAO.DiscoDAO;
import br.edu.ufersa.ropke.model.DAO.EmprestimoDAO;
import br.edu.ufersa.ropke.model.DAO.FuncionarioDAO;
import br.edu.ufersa.ropke.model.DAO.GerenteDAO;
import br.edu.ufersa.ropke.model.DAO.LivroDAO;
import br.edu.ufersa.ropke.model.VO.ClienteVO;
import br.edu.ufersa.ropke.model.VO.DiscoVO;
import br.edu.ufersa.ropke.model.VO.EmprestimoVO;
import br.edu.ufersa.ropke.model.VO.LivroVO;

public class MainBO {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EmprestimoVO emprestimo0 = new EmprestimoVO();
		EmprestimoVO emprestimo1 = new EmprestimoVO();

		String[] enderecos = { "rua 1" };
		String[] emails = { "123abc@gmail.com" };
		String[] telefones = { "(84) 3000-0000" };

		// titulo, genero, numeroPaginas, 'numeroExemplares', 'numeroEmprestimos',
		// 'numeroDiasAlugado', anoLancamento, valorAluguel
		LivroVO livro1 = new LivroVO("oi", "romance", 150, 4, 2010, 12);
		LivroVO livro2 = new LivroVO("em", "acao", 220, 1, 2018, 26);

		// titulo, nomeBanda, estilo, numeroExemplares, 'numeroEmprestimos',
		// 'numeroDiasAlugado'
		// anoLancamento, valorAluguel
		DiscoVO disco1 = new DiscoVO("ola", "ssd", "pagode", 15, 2, 28, 2018, 8.80f);
		DiscoVO[] discos = { disco1 };

		// nome, cpf, endereco, 'email', 'telefone'
		ClienteVO cliente1 = new ClienteVO("joao", "19315322061", enderecos, emails, telefones);

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
		/*
		 * EmprestimoBO.alugar(emprestimo0, quantidades, datas, cliente1, livros);
		 * 
		 * Calendar[] datas0 = { cal }; int[] quantidades0 = { 3 };
		 * EmprestimoBO.alugar(emprestimo0, quantidades0, datas0, cliente1, discos);
		 * 
		 * System.out.println(emprestimo0);
		 * 
		 * System.out.println(
		 * "----------------------------------------Devolucao-------------------------------------"
		 * ); LivroVO[] livros0 = { livro1, livro2 }; quantidades[0] = 3; quantidades[1]
		 * = 1; EmprestimoBO.devolver(emprestimo0, quantidades, datas, livros0);
		 * EmprestimoBO.devolver(emprestimo0, quantidades0, datas0, discos);
		 * 
		 * System.out.println(emprestimo0); System.out.println(emprestimo1);
		 */

		/**/
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
		LivroBO.pesquisarAnoLancamento(2020);

		System.out.println();
		System.out.println(
				"---------------------------------------Altera livro 2----------------------------------------");
		livro2.setAnoLancamento(1999);
		LivroBO.alterar(livro2);
		LivroBO.pesquisar();

		System.out.println();
		System.out.println(
				"---------------------------------------Deleta livro 1----------------------------------------");
		LivroBO.deletar(livro1);
		LivroBO.pesquisar();
		
		System.out.println(
				"---------------------------------------Deleta livro 2----------------------------------------");
		
		LivroBO.deletar(livro2);
		LivroBO.pesquisar();
		LivroBO.pesquisarTitulo("abc");
		DiscoBO.cadastrar(disco1);
		DiscoBO.pesquisar();
		DiscoBO.pesquisarAnoLancamento(2018);

		/*
		 * System.out.println(); System.out.println(
		 * "---------------------------------------Pesquisa titulo----------------------------------------"
		 * ); LivroVO livro = LivroDAO.pesquisarTitulo("em");
		 * System.out.println(livro.toString());
		 * 
		 * System.out.println(); System.out.println(
		 * "---------------------------------------Pesquisa genero----------------------------------------"
		 * ); LivroVO[] livros = LivroDAO.pesquisarGenero("acao"); int numeroLivros =
		 * livros.length; for (int i = 0; i < numeroLivros; i++) {
		 * System.out.println(livros[i].toString()); }
		 * 
		 * System.out.println(); System.out.println(
		 * "---------------------------------------Pesquisa ano-------------------------------------------"
		 * ); livros = LivroDAO.pesquisarAnoLancamento(1999); numeroLivros =
		 * livros.length; for (int i = 0; i < numeroLivros; i++) {
		 * System.out.println(livros[i].toString()); }
		 * 
		 * // Discos System.out.println(
		 * "-------------------------------------------Discos---------------------------------------------"
		 * ); System.out.println("\nDisco\n"); DiscoDAO.cadastrar(disco1);
		 * DiscoDAO.pesquisar();
		 * 
		 * System.out.println();
		 * 
		 * System.out.println(); System.out.println(
		 * "---------------------------------------Altera disco 1----------------------------------------"
		 * ); disco1.setNumeroExemplares(80); DiscoDAO.alterar(disco1);
		 * DiscoDAO.pesquisar();
		 * 
		 * /* System.out.println(); System.out.
		 * println("---------------------------------------Deleta disco 1----------------------------------------"
		 * ); DiscoDAO.deletar(disco1); DiscoDAO.pesquisar();
		 * 
		 * 
		 * System.out.println(); System.out.println(
		 * "---------------------------------------Pesquisa titulo----------------------------------------"
		 * ); DiscoVO disco = DiscoDAO.pesquisarTitulo("em"); if (disco != null) {
		 * System.out.println(disco.toString()); }
		 * 
		 * System.out.println(); System.out.println(
		 * "---------------------------------------Pesquisa banda-----------------------------------------"
		 * ); DiscoVO[] discos = DiscoDAO.pesquisarBanda("ssd"); int numeroDiscos =
		 * discos.length; for (int i = 0; i < numeroDiscos; i++) {
		 * System.out.println(discos[i].toString()); }
		 * 
		 * System.out.println(); System.out.println(
		 * "---------------------------------------Pesquisa estilo----------------------------------------"
		 * ); discos = DiscoDAO.pesquisarEstilo("pagode"); numeroDiscos = discos.length;
		 * for (int i = 0; i < numeroDiscos; i++) {
		 * System.out.println(discos[i].toString()); }
		 * 
		 * System.out.println(); System.out.println(
		 * "---------------------------------------Pesquisa ano-------------------------------------------"
		 * ); discos = DiscoDAO.pesquisarAnoLancamento(2018); numeroDiscos =
		 * discos.length; for (int i = 0; i < numeroDiscos; i++) {
		 * System.out.println(discos[i].toString()); }
		 * 
		 * // Clientes System.out.println(
		 * "-------------------------------------------Clientes--------------------------------------------"
		 * ); System.out.println("\nCliente\n"); ClienteDAO.cadastrar(cliente1);
		 * ClienteDAO.pesquisar();
		 * 
		 * System.out.println(); System.out.println(
		 * "---------------------------------------Altera cliente 1----------------------------------------"
		 * ); enderecos[0] = "Rua longe do local"; cliente1.setEndereco(enderecos);
		 * ClienteDAO.alterar(cliente1); ClienteDAO.pesquisar();
		 * 
		 * /* System.out.println(); System.out.
		 * println("---------------------------------------Deleta cliente 1----------------------------------------"
		 * ); ClienteDAO.deletar(cliente1); ClienteDAO.pesquisar();
		 * 
		 * 
		 * // Funcionários System.out.println(
		 * "-----------------------------------------Funcionarios------------------------------------------"
		 * ); System.out.println("\nFuncionario\n");
		 * FuncionarioDAO.cadastrar(funcionario1); FuncionarioDAO.pesquisar();
		 * 
		 * System.out.println(); System.out.println(
		 * "---------------------------------------Altera funcionario 1----------------------------------------"
		 * );
		 * 
		 * funcionario1.setEndereco(enderecos); FuncionarioDAO.alterar(funcionario1);
		 * FuncionarioDAO.pesquisar();
		 * 
		 * /* System.out.println(); System.out.
		 * println("---------------------------------------Deleta funcionario 1----------------------------------------"
		 * ); FuncionarioDAO.deletar(funcionario1); FuncionarioDAO.pesquisar();
		 * 
		 * 
		 * // Gerentes System.out.println(
		 * "-------------------------------------------Gerentes--------------------------------------------"
		 * ); System.out.println("\nGerente\n"); GerenteDAO.cadastrar(gerente1);
		 * GerenteDAO.pesquisar();
		 * 
		 * System.out.println(); System.out.println(
		 * "---------------------------------------Altera gerente 1----------------------------------------"
		 * ); gerente1.setEndereco(enderecos); GerenteDAO.alterar(gerente1);
		 * GerenteDAO.pesquisar();
		 * 
		 * /* System.out.println(); System.out.
		 * println("---------------------------------------Deleta gerente 1----------------------------------------"
		 * ); GerenteDAO.deletar(gerente1); GerenteDAO.pesquisar();
		 * 
		 * 
		 * // Empréstimos // https://www.tutorialspoint.com/java/util/calendar_setfield4
		 * // create a calendar Calendar cal = Calendar.getInstance(); // set the
		 * year,month, day, hour, minute, second // the month starts with 0 and goes to
		 * 11 cal.set(2021, 3, 04, 04, 15, 20);
		 * 
		 * System.out.println(
		 * "------------------------------------------Emprestimos------------------------------------------"
		 * ); System.out.println("\nEmprestimo\n");
		 * EmprestimoDAO.cadastrar(emprestimo1); EmprestimoDAO.pesquisar();
		 * 
		 * System.out.println(); System.out.println(
		 * "---------------------------------------Altera emprestimo 1----------------------------------------"
		 * ); emprestimo1.setDataEmprestimo(cal); EmprestimoDAO.alterar(emprestimo1);
		 * EmprestimoDAO.pesquisar();
		 * 
		 * /* System.out.println(); System.out.
		 * println("---------------------------------------Deleta emprestimo 1----------------------------------------"
		 * ); EmprestimoDAO.deletar(emprestimo1); EmprestimoDAO.pesquisar();
		 * 
		 */
	}
}
