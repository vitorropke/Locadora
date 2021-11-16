package br.edu.ufersa.ropke.locadoramaven.model.BO;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.edu.ufersa.ropke.locadoramaven.model.VO.ClienteVO;
import br.edu.ufersa.ropke.locadoramaven.model.VO.DiscoVO;
import br.edu.ufersa.ropke.locadoramaven.model.VO.EmprestavelVO;
import br.edu.ufersa.ropke.locadoramaven.model.VO.EmprestimoVO;
import br.edu.ufersa.ropke.locadoramaven.model.VO.EnderecoVO;
import br.edu.ufersa.ropke.locadoramaven.model.VO.FuncionarioVO;
import br.edu.ufersa.ropke.locadoramaven.model.VO.GerenteVO;
import br.edu.ufersa.ropke.locadoramaven.model.VO.LivroVO;
import br.edu.ufersa.ropke.locadoramaven.model.VO.ObjetoEmprestadoVO;
import br.edu.ufersa.ropke.locadoramaven.model.VO.TelefoneVO;

public class MainBO {
	public static void main(String[] args) {
		// Dados de pessoas
		// -----------------------------------------------------------------------------
		// logradouro, número, complemento, referência, bairro, cidade, estado, cep
		List<EnderecoVO> enderecosGerente1 = new ArrayList<EnderecoVO>();
		// e-mail
		List<String> emailsGerente1 = new ArrayList<String>();
		// ddd, telefone
		List<TelefoneVO> telefonesGerente1 = new ArrayList<TelefoneVO>();

		List<EnderecoVO> enderecosFuncionario1 = new ArrayList<EnderecoVO>();
		List<String> emailsFuncionario1 = new ArrayList<String>();
		List<TelefoneVO> telefonesFuncionario1 = new ArrayList<TelefoneVO>();

		List<EnderecoVO> enderecosCliente1 = new ArrayList<EnderecoVO>();
		List<String> emailsCliente1 = new ArrayList<String>();
		List<TelefoneVO> telefonesCliente1 = new ArrayList<TelefoneVO>();

		// -----------------------------------------------------------------------------
		enderecosGerente1.add(new EnderecoVO("Avenida Desconhecida", "99", "1º andar", "Em frente a lugar nenhum",
				"Feio Horizonte", "Moscow City", "RN", "59600-485"));

		emailsGerente1.add("abc@hotmail.com");
		emailsGerente1.add("comercial@hotmail.com");

		telefonesGerente1.add(new TelefoneVO("084", "9999-9999"));

		// --------------------------------------
		enderecosFuncionario1.add(new EnderecoVO("Rua Sem Nome", "52", null,
				"Do lado esquerdo da casa que ta no lado direito", "Permanecencias", "Sao Paulo", "SP", "25849-285"));
		enderecosFuncionario1.add(new EnderecoVO("Avenida 11", "1", "Em baixo da parte de cima",
				"Na frente da parte de tras", "Outro lado do mundo", "Papibaquigrafo", "RS", "23.545-484"));

		emailsFuncionario1.add("a@hotmail.com");

		telefonesFuncionario1.add(new TelefoneVO("(84)", "0000-0000"));
		telefonesFuncionario1.add(new TelefoneVO("(84)", "5000-1200"));

		// --------------------------------------
		enderecosCliente1.add(new EnderecoVO("Avenida de Baixo", "111", "", "Do lado do mercado sem nome",
				"Baixo de Cima", "Mossoro", "RN", "12345678"));

		emailsCliente1.add("meuemail@hotmail.com");

		telefonesCliente1.add(new TelefoneVO("(84)", "90000-9000"));

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
		LivroVO livro2 = new LivroVO("em", "acao", 220, 10, 0, 0, 2018, 26f);

		// titulo, banda, estilo, numeroExemplares, numeroEmprestimos,
		// numeroDiasAlugado, anoLancamento, valorAluguel
		DiscoVO disco1 = new DiscoVO("ola", "ssd", "pagode", 10, 0, 0, 2018, 8.80f);

		// Dados de empréstimos
		// -----------------------------------------------------------------------------
		List<ObjetoEmprestadoVO> objetosEmprestados = new ArrayList<ObjetoEmprestadoVO>();

		// https://www.tutorialspoint.com/java/util/calendar_setfield4
		// create a calendar
		Calendar cal = Calendar.getInstance();
		// set the year, month, day, hour, minute, second
		// the month starts with 0 and goes to 11
		cal.set(2021, 11, 29, 04, 15, 20);

		objetosEmprestados.add(new ObjetoEmprestadoVO(disco1, cal, 2));
		objetosEmprestados.add(new ObjetoEmprestadoVO(livro1, cal, 1));

		// cliente, objeto
		EmprestimoVO emprestimo1 = new EmprestimoVO(cliente1, objetosEmprestados);

		// Cria objetos do tipo BO
		// -----------------------------------------------------------------------------
		GerenteBO gerenteBO = new GerenteBO();
		FuncionarioBO funcionarioBO = new FuncionarioBO();
		ClienteBO clienteBO = new ClienteBO();
		LivroBO livroBO = new LivroBO();
		DiscoBO discoBO = new DiscoBO();
		EmprestimoBO emprestimoBO = new EmprestimoBO();

		// Pessoas
		// -----------------------------------------------------------------------------
		// Gerentes
		System.out.println("----------------------------------------Gerentes----------------------------------------");
		gerenteBO.cadastrar(gerente1);
		gerente1.setId(1);
		gerente1.setIdUsuario(1);
		gerente1.setIdPessoa(1);

		System.out.println("============================================");
		System.out.println("Listar");
		System.out.println("============================================");
		System.out.println(gerenteBO.listar());

		telefonesGerente1.set(0, new TelefoneVO("55", "9 3316-6382"));
		gerenteBO.alterar(gerente1);

		System.out.println("============================================");
		System.out.println("Pesquisar usando ID");
		System.out.println("============================================");
		System.out.println(gerenteBO.pesquisarId(gerente1.getId()));
		System.out.println("============================================");
		System.out.println("Pesquisar usando nome");
		System.out.println("============================================");
		System.out.println(gerenteBO.pesquisarNome("a"));
		System.out.println("============================================");
		System.out.println("Pesquisar usando CPF");
		System.out.println("============================================");
		System.out.println(gerenteBO.pesquisarCpf("32788672039"));
		System.out.println("============================================");
		System.out.println("Pesquisar usando login");
		System.out.println("============================================");
		System.out.println(gerenteBO.pesquisarLogin("gen"));

		// gerenteBO.deletar(gerente1.getIdPessoa());
		// System.out.println("============================================");
		// System.out.println("Listar");
		// System.out.println("============================================");
		// System.out.println(gerenteBO.listar());

		// Funcionários
		System.out.println("--------------------------------------Funcionarios--------------------------------------");
		funcionarioBO.cadastrar(funcionario1);
		funcionario1.setId(1);
		funcionario1.setIdUsuario(2);
		funcionario1.setIdPessoa(2);

		System.out.println("============================================");
		System.out.println("Listar");
		System.out.println("============================================");
		System.out.println(funcionarioBO.listar());

		telefonesFuncionario1.set(0, new TelefoneVO("55", "3316-6382"));
		funcionarioBO.alterar(funcionario1);

		System.out.println("============================================");
		System.out.println("Pesquisar usando ID");
		System.out.println("============================================");
		System.out.println(funcionarioBO.pesquisarId(funcionario1.getId()));
		System.out.println("============================================");
		System.out.println("Pesquisar usando nome");
		System.out.println("============================================");
		System.out.println(funcionarioBO.pesquisarNome("Menino"));
		System.out.println("============================================");
		System.out.println("Pesquisar usando CPF");
		System.out.println("============================================");
		System.out.println(funcionarioBO.pesquisarCpf("07232548001"));
		System.out.println("============================================");
		System.out.println("Pesquisar usando login");
		System.out.println("============================================");
		System.out.println(funcionarioBO.pesquisarLogin("a"));

		// funcionarioBO.deletar(funcionario1.getIdPessoa());
		// System.out.println("============================================");
		// System.out.println("Listar");
		// System.out.println("============================================");
		// System.out.println(funcionarioBO.listar());

		// Clientes
		System.out.println("----------------------------------------Clientes----------------------------------------");
		clienteBO.cadastrar(cliente1);
		cliente1.setId(1);
		cliente1.setIdPessoa(3);

		System.out.println("============================================");
		System.out.println("Listar");
		System.out.println("============================================");
		System.out.println(clienteBO.listar());

		enderecosCliente1.set(0, new EnderecoVO("Rua longe do local", "111", null, "Do lado do mercado sem nome",
				"Baixo de Cima", "Mossoro", "RN", "12345678"));
		cliente1.setEnderecos(enderecosCliente1);
		clienteBO.alterar(cliente1);

		System.out.println("============================================");
		System.out.println("Pesquisar usando ID");
		System.out.println("============================================");
		System.out.println(clienteBO.pesquisarId(cliente1.getId()));
		System.out.println("============================================");
		System.out.println("Pesquisar usando nome");
		System.out.println("============================================");
		System.out.println(clienteBO.pesquisarNome("ao"));
		System.out.println("============================================");
		System.out.println("Pesquisar usando CPF");
		System.out.println("============================================");
		System.out.println(clienteBO.pesquisarCpf("19315322061"));

		// clienteBO.deletar(cliente1.getIdPessoa());
		// System.out.println("============================================");
		// System.out.println("Listar");
		// System.out.println("============================================");
		// System.out.println(clienteBO.listar());

		// Emprestáveis
		// -----------------------------------------------------------------------------
		// Livros
		System.out.println("-----------------------------------------Livros-----------------------------------------");
		livroBO.cadastrar(livro1);
		livro1.setIdEmprestavel(1);
		livro1.setId(1);
		livroBO.cadastrar(livro2);
		livro2.setIdEmprestavel(2);
		livro2.setId(2);

		System.out.println("============================================");
		System.out.println("Listar");
		System.out.println("============================================");
		System.out.println(livroBO.listar());

		livro1.setNumeroPaginas(140);
		livroBO.alterar(livro1);

		System.out.println("============================================");
		System.out.println("Pesquisar usando ID");
		System.out.println("============================================");
		System.out.println(livroBO.pesquisarId(livro1.getId()));
		System.out.println("============================================");
		System.out.println("Pesquisar usando titulo");
		System.out.println("============================================");
		System.out.println(livroBO.pesquisarTitulo("o"));
		System.out.println("============================================");
		System.out.println("Pesquisar usando ano de lancamento");
		System.out.println("============================================");
		System.out.println(livroBO.pesquisarAnoLancamento(2014));
		System.out.println("============================================");
		System.out.println("Pesquisar usando genero");
		System.out.println("============================================");
		System.out.println(livroBO.pesquisarGenero("acao"));
		System.out.println("============================================");
		System.out.println("Pesquisar usando numero de paginas");
		System.out.println("============================================");
		System.out.println(livroBO.pesquisarNumeroPaginas(140));

		livro2.setAnoLancamento(1999);
		livroBO.alterar(livro2);
		System.out.println("============================================");
		System.out.println("Pesquisar usando ID");
		System.out.println("============================================");
		System.out.println(livroBO.pesquisarId(livro2.getId()));

		// livroDAO.deletar(livro1.getIdEmprestavel());
		// System.out.println("============================================");
		// System.out.println("Listar");
		// System.out.println("============================================");
		// System.out.println(livroBO.listar());

		// Discos
		System.out.println("-----------------------------------------Discos-----------------------------------------");
		discoBO.cadastrar(disco1);
		disco1.setIdEmprestavel(3);
		disco1.setId(1);

		System.out.println("============================================");
		System.out.println("Listar");
		System.out.println("============================================");
		System.out.println(discoBO.listar());

		disco1.setNumeroExemplares(80);
		discoBO.alterar(disco1);

		System.out.println("============================================");
		System.out.println("Pesquisar usando ID");
		System.out.println("============================================");
		System.out.println(discoBO.pesquisarId(disco1.getId()));
		System.out.println("============================================");
		System.out.println("Pesquisar usando titulo");
		System.out.println("============================================");
		System.out.println(discoBO.pesquisarTitulo("l"));
		System.out.println("============================================");
		System.out.println("Pesquisar usando ano de lancamento");
		System.out.println("============================================");
		System.out.println(discoBO.pesquisarAnoLancamento(2018));
		System.out.println("============================================");
		System.out.println("Pesquisar usando banda");
		System.out.println("============================================");
		System.out.println(discoBO.pesquisarBanda("s"));
		System.out.println("============================================");
		System.out.println("Pesquisar usando estilo");
		System.out.println("============================================");
		System.out.println(discoBO.pesquisarEstilo("pagode"));

		// discoBO.deletar(disco1.getIdEmprestavel());
		// System.out.println("============================================");
		// System.out.println("Listar");
		// System.out.println("============================================");
		// System.out.println(discoBO.listar());
		/*
		 * // Empréstimos //
		 * -----------------------------------------------------------------------------
		 * // Empréstimo System.out.println(
		 * "---------------------------------------Emprestimos--------------------------------------"
		 * ); emprestimoDAO.cadastrar(emprestimo1); emprestimoDAO.pesquisar();
		 * 
		 * // Devolução List<Integer> quantidadesDevolucao = new ArrayList<>();
		 * 
		 * quantidadesDevolucao.add(1); quantidadesDevolucao.add(2);
		 * 
		 * emprestimo1.devolver(objetosEmprestados, quantidadesDevolucao);
		 * emprestimoDAO.alterar(emprestimo1); emprestimoDAO.pesquisar();
		 * 
		 * // emprestimoDAO.deletar(emprestimo1); // emprestimoDAO.pesquisar();
		 */
	}
}
