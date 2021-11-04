package br.edu.ufersa.ropke.locadoramaven.model.VO;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MainVO {
	public static void main(String[] args) {
		// Dados de pessoas
		// -----------------------------------------------------------------------------
		// logradouro, número, complemento, bairro, cidade, estado, cep
		List<Endereco> enderecosGerente1 = new ArrayList<Endereco>();
		// e-mail
		List<String> emailsGerente1 = new ArrayList<String>();
		// ddd, telefone
		List<Telefone> telefonesGerente1 = new ArrayList<Telefone>();

		List<Endereco> enderecosFuncionario1 = new ArrayList<Endereco>();
		List<String> emailsFuncionario1 = new ArrayList<String>();
		List<Telefone> telefonesFuncionario1 = new ArrayList<Telefone>();

		List<Endereco> enderecosCliente1 = new ArrayList<Endereco>();
		List<String> emailsCliente1 = new ArrayList<String>();
		List<Telefone> telefonesCliente1 = new ArrayList<Telefone>();

		// -----------------------------------------------------------------------------
		enderecosGerente1.add(new Endereco("Avenida Desconhecida", "99", "Em frente a lugar nenhum", "Feio Horizonte",
				"Moscow City", "RN", "59600-485"));

		emailsGerente1.add("abc@hotmail.com");
		emailsGerente1.add("comercial@hotmail.com");

		telefonesGerente1.add(new Telefone("084", "9999-9999"));

		// --------------------------------------
		enderecosFuncionario1.add(new Endereco("Rua Sem Nome", "52", "Do lado esquerdo da casa que ta no lado direito",
				"Permanecencias", "Sao Paulo", "SP", "25849-285"));
		enderecosFuncionario1.add(new Endereco("Avenida de Baixo", "111", "Do lado do mercado sem nome",
				"Baixo de Cima", "Mossoro", "RN", "12345678"));

		emailsFuncionario1.add("a@hotmail.com");

		telefonesFuncionario1.add(new Telefone("(84)", "0000-0000"));
		telefonesFuncionario1.add(new Telefone("(84)", "5000-1200"));

		// --------------------------------------
		enderecosCliente1.add(new Endereco("Avenida de Baixo", "111", "Do lado do mercado sem nome", "Baixo de Cima",
				"Mossoro", "RN", "12345678"));

		emailsCliente1.add("meuemail@hotmail.com");

		telefonesCliente1.add(new Telefone("(84)", "90000-9000"));

		// -----------------------------------------------------------------------------
		// login, senha, nome, cpf, endereco, email, telefone
		GerenteVO gerente1 = new GerenteVO("gen", "asds", "aaaa", "327.886.720-39", enderecosGerente1, emailsGerente1,
				telefonesGerente1);
		FuncionarioVO funcionario1 = new FuncionarioVO("a", "vcdf", "Menino", "072.325.480-01", enderecosFuncionario1,
				emailsFuncionario1, telefonesFuncionario1);
		// nome, cpf, endereco, email, telefone
		ClienteVO cliente1 = new ClienteVO("joao", "19315322061", enderecosCliente1, emailsCliente1, telefonesCliente1);

		// Dados de emprestáveis
		// -----------------------------------------------------------------------------
		// titulo, genero, numeroPaginas, numeroExemplares, numeroEmprestimos,
		// numeroDiasAlugado, anoLancamento, valorAluguel
		LivroVO livro1 = new LivroVO("oi", "romance", 150, 10, 0, 0, 2014, 5.4f);
		livro1.setId(0);

		LivroVO livro2 = new LivroVO("em", "acao", 220, 10, 0, 0, 2018, 26f);
		livro2.setId(1);

		// titulo, banda, estilo, numeroExemplares, numeroEmprestimos,
		// numeroDiasAlugado, anoLancamento, valorAluguel
		DiscoVO disco1 = new DiscoVO("ola", "ssd", "pagode", 10, 0, 0, 2018, 8.80f);
		disco1.setId(0);

		// Dados de empréstimos
		// -----------------------------------------------------------------------------
		List<ObjetoEmprestado> objetosEmprestados = new ArrayList<ObjetoEmprestado>();

		// https://www.tutorialspoint.com/java/util/calendar_setfield4
		// create a calendar
		Calendar cal = Calendar.getInstance();
		// set the year, month, day, hour, minute, second
		// the month starts with 0 and goes to 11
		cal.set(2021, 11, 29, 04, 15, 20);

		objetosEmprestados.add(new ObjetoEmprestado(disco1, cal, 2));
		objetosEmprestados.add(new ObjetoEmprestado(livro1, cal, 1));

		// cliente, objeto
		EmprestimoVO emprestimo1 = new EmprestimoVO(cliente1, objetosEmprestados);

		// Pessoas
		// -----------------------------------------------------------------------------
		// Gerentes
		System.out.println("----------------------------------------Gerentes----------------------------------------");
		System.out.println(gerente1);

		// Funcionários
		System.out.println("--------------------------------------Funcionarios--------------------------------------");
		System.out.println(funcionario1);

		// Clientes
		System.out.println("----------------------------------------Clientes----------------------------------------");
		System.out.println(cliente1);

		// Emprestáveis
		// -----------------------------------------------------------------------------
		// Livros
		System.out.println("-----------------------------------------Livros-----------------------------------------");
		System.out.println(livro1);
		System.out.println(livro2);

		// Discos
		System.out.println("-----------------------------------------Discos-----------------------------------------");
		System.out.println(disco1);

		// Empréstimos
		// -----------------------------------------------------------------------------
		// Empréstimo
		System.out.println("---------------------------------------Emprestimos--------------------------------------");
		System.out.println(emprestimo1);
	}
}
