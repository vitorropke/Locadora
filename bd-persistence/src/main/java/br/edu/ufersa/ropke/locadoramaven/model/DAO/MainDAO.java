package br.edu.ufersa.ropke.locadoramaven.model.DAO;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.edu.ufersa.ropke.locadoramaven.model.VO.ClienteVO;
import br.edu.ufersa.ropke.locadoramaven.model.VO.DiscoVO;
import br.edu.ufersa.ropke.locadoramaven.model.VO.EmprestimoVO;
import br.edu.ufersa.ropke.locadoramaven.model.VO.Endereco;
import br.edu.ufersa.ropke.locadoramaven.model.VO.FuncionarioVO;
import br.edu.ufersa.ropke.locadoramaven.model.VO.GerenteVO;
import br.edu.ufersa.ropke.locadoramaven.model.VO.LivroVO;
import br.edu.ufersa.ropke.locadoramaven.model.VO.ObjetoEmprestado;
import br.edu.ufersa.ropke.locadoramaven.model.VO.Telefone;

public class MainDAO {
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

		// Cria objetos do tipo DAO
		LivroDAO livroDAO = new LivroDAO();
		DiscoDAO discoDAO = new DiscoDAO();
		ClienteDAO clienteDAO = new ClienteDAO();
		FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
		GerenteDAO gerenteDAO = new GerenteDAO();
		EmprestimoDAO emprestimoDAO = new EmprestimoDAO();
		
		// Pessoas
		// -----------------------------------------------------------------------------
		// Gerentes
		System.out.println("----------------------------------------Gerentes----------------------------------------");
		//gerenteDAO.cadastrar(gerente1);
		//gerenteDAO.pesquisar();

		//telefonesGerente1.set(0, new Telefone("55", "9 3316-6382"));
		//gerente1.setTelefones(telefonesGerente1);
		//gerenteDAO.alterar(gerente1);
		//gerenteDAO.pesquisar();

		// gerenteDAO.deletar(funcionario1);
		// gerenteDAO.pesquisar();

		// Funcionários
		System.out.println("--------------------------------------Funcionarios--------------------------------------");
		//funcionarioDAO.cadastrar(funcionario1);
		//funcionarioDAO.pesquisar();
		try {
			ResultSet rs = funcionarioDAO.listar();
			
			if (rs.next()) {
				List<Endereco> enderecos = new ArrayList<Endereco>();
				List<String> emails = new ArrayList<String>();
				List<Telefone> telefones = new ArrayList<Telefone>();
				
				FuncionarioVO funcionario = new FuncionarioVO(rs.getString("login"), rs.getString("senha"), rs.getString("nome"), rs.getString("cpf"), enderecos, emails, telefones);
				funcionario.setId(rs.getLong("id"));
				funcionario.setIdUsuario(rs.getLong("id_usuario"));
				funcionario.setIdPessoa(rs.getLong("id_pessoa"));
				
				System.out.println(funcionario);
			}
		} catch (Exception e) {}

		//telefonesFuncionario1.set(0, new Telefone("55", "3316-6382"));
		//funcionario1.setTelefones(telefonesFuncionario1);
		//funcionarioDAO.alterar(funcionario1);
		//funcionarioDAO.pesquisar();

		// funcionarioDAO.deletar(funcionario1);
		// funcionarioDAO.pesquisar();
		
		// Clientes
		System.out.println("----------------------------------------Clientes----------------------------------------");
		//clienteDAO.cadastrar(cliente1);
		//System.out.println(clienteDAO.listar());
		try {
			ResultSet rs = clienteDAO.listar();
			
			if (rs.next()) {
				List<Endereco> enderecos = new ArrayList<Endereco>();
				List<String> emails = new ArrayList<String>();
				List<Telefone> telefones = new ArrayList<Telefone>();
				
				ClienteVO cliente = new ClienteVO(rs.getString("nome"), rs.getString("cpf"), enderecos, emails, telefones);
				cliente.setId(rs.getLong("id"));
				
				System.out.println(cliente);
			}
		} catch (Exception e) {}
		/*
		enderecosCliente1.set(0, new Endereco("Rua longe do local", "111", "Do lado do mercado sem nome",
				"Baixo de Cima", "Mossoro", "RN", "12345678"));
		cliente1.setEnderecos(enderecosCliente1);
		clienteDAO.alterar(cliente1);
		clienteDAO.pesquisar();

		List<ClienteVO> clientes = clienteDAO.pesquisarNome("ao");
		for (ClienteVO clienteAtual : clientes) {
			System.out.println(clienteAtual);
			System.out.println("==============================================");
		}

		// clienteDAO.deletar(cliente1);
		// clienteDAO.pesquisar();

		// Emprestáveis
		// -----------------------------------------------------------------------------
		// Livros
		System.out.println("-----------------------------------------Livros-----------------------------------------");
		livroDAO.cadastrar(livro1);
		livroDAO.cadastrar(livro2);
		livroDAO.pesquisar();

		livro1.setNumeroPaginas(140);
		livroDAO.alterar(livro1);
		livroDAO.pesquisar();

		livro2.setAnoLancamento(1999);
		livroDAO.alterar(livro2);
		livroDAO.pesquisar();

		List<LivroVO> livros = livroDAO.pesquisarTitulo("em");

		for (LivroVO livroAtual : livros) {
			System.out.println(livroAtual);
			System.out.println("========================================");
		}

		livros = livroDAO.pesquisarGenero("acao");

		for (LivroVO livroAtual : livros) {
			System.out.println(livroAtual);
			System.out.println("========================================");
		}

		livros = livroDAO.pesquisarAnoLancamento(2001);

		for (LivroVO livroAtual : livros) {
			System.out.println(livroAtual);
			System.out.println("========================================");
		}

		// livroDAO.deletar(livro1);
		// livroDAO.pesquisar();

		// Discos
		System.out.println("-----------------------------------------Discos-----------------------------------------");
		discoDAO.cadastrar(disco1);
		discoDAO.pesquisar();

		disco1.setNumeroExemplares(80);
		discoDAO.alterar(disco1);
		discoDAO.pesquisar();

		List<DiscoVO> discos = discoDAO.pesquisarTitulo("em");

		for (DiscoVO discoAtual : discos) {
			System.out.println(discoAtual);
			System.out.println("========================================");
		}

		discos = discoDAO.pesquisarBanda("ssd");
		for (DiscoVO discoAtual : discos) {
			System.out.println(discoAtual);
			System.out.println("========================================");
		}

		discos = discoDAO.pesquisarEstilo("pagode");
		for (DiscoVO discoAtual : discos) {
			System.out.println(discoAtual);
			System.out.println("========================================");
		}

		discos = discoDAO.pesquisarAnoLancamento(2018);
		for (DiscoVO discoAtual : discos) {
			System.out.println(discoAtual);
			System.out.println("========================================");
		}

		// discoDAO.deletar(disco1);
		// discoDAO.pesquisar();

		// Empréstimos
		// -----------------------------------------------------------------------------
		// Empréstimo
		System.out.println("---------------------------------------Emprestimos--------------------------------------");
		emprestimoDAO.cadastrar(emprestimo1);
		emprestimoDAO.pesquisar();

		// Devolução
		List<Integer> quantidadesDevolucao = new ArrayList<>();

		quantidadesDevolucao.add(1);
		quantidadesDevolucao.add(2);

		emprestimo1.devolver(objetosEmprestados, quantidadesDevolucao);
		emprestimoDAO.alterar(emprestimo1);
		emprestimoDAO.pesquisar();

		// emprestimoDAO.deletar(emprestimo1);
		// emprestimoDAO.pesquisar();
		*/
	}
}
