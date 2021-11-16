package br.edu.ufersa.ropke.locadoramaven.model.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.edu.ufersa.ropke.locadoramaven.model.VO.ClienteVO;
import br.edu.ufersa.ropke.locadoramaven.model.VO.DiscoVO;
import br.edu.ufersa.ropke.locadoramaven.model.VO.EmprestimoVO;
import br.edu.ufersa.ropke.locadoramaven.model.VO.EnderecoVO;
import br.edu.ufersa.ropke.locadoramaven.model.VO.FuncionarioVO;
import br.edu.ufersa.ropke.locadoramaven.model.VO.GerenteVO;
import br.edu.ufersa.ropke.locadoramaven.model.VO.LivroVO;
import br.edu.ufersa.ropke.locadoramaven.model.VO.ObjetoEmprestadoVO;
import br.edu.ufersa.ropke.locadoramaven.model.VO.TelefoneVO;

public class MainDAO {
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

		// Cria objetos do tipo DAO
		// -----------------------------------------------------------------------------
		GerenteDAO gerenteDAO = new GerenteDAO();
		FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
		ClienteDAO clienteDAO = new ClienteDAO();
		LivroDAO livroDAO = new LivroDAO();
		DiscoDAO discoDAO = new DiscoDAO();
		EmprestimoDAO emprestimoDAO = new EmprestimoDAO();

		// Pessoas
		// -----------------------------------------------------------------------------
		// Gerentes
		System.out.println("----------------------------------------Gerentes----------------------------------------");
		// gerenteDAO.cadastrar(gerente1);
		gerente1.setId(1);
		gerente1.setIdUsuario(1);
		gerente1.setIdPessoa(1);

		System.out.println("============================================");
		System.out.println("Listar");
		System.out.println("============================================");
		imprimirGerentes(gerenteDAO.listar());

		telefonesGerente1.set(0, new TelefoneVO("55", "9 3316-6382"));
		gerenteDAO.alterar(gerente1);

		System.out.println("============================================");
		System.out.println("Pesquisar usando ID");
		System.out.println("============================================");
		imprimirGerentes(gerenteDAO.pesquisarId(gerente1.getId()));
		System.out.println("============================================");
		System.out.println("Pesquisar usando nome");
		System.out.println("============================================");
		imprimirGerentes(gerenteDAO.pesquisarNome("%a%"));
		System.out.println("============================================");
		System.out.println("Pesquisar usando CPF");
		System.out.println("============================================");
		imprimirGerentes(gerenteDAO.pesquisarCpf("32788672039"));
		System.out.println("============================================");
		System.out.println("Pesquisar usando login");
		System.out.println("============================================");
		imprimirGerentes(gerenteDAO.pesquisarLogin("gen"));

		// gerenteDAO.deletar(gerente1.getIdPessoa());
		// System.out.println("============================================");
		// System.out.println("Listar");
		// System.out.println("============================================");
		// imprimirGerentes(gerenteDAO.listar());

		// Funcionários
		System.out.println("--------------------------------------Funcionarios--------------------------------------");
		// funcionarioDAO.cadastrar(funcionario1);
		funcionario1.setId(1);
		funcionario1.setIdUsuario(2);
		funcionario1.setIdPessoa(2);

		System.out.println("============================================");
		System.out.println("Listar");
		System.out.println("============================================");
		imprimirFuncionarios(funcionarioDAO.listar());

		telefonesFuncionario1.set(0, new TelefoneVO("55", "3316-6382"));
		funcionarioDAO.alterar(funcionario1);

		System.out.println("============================================");
		System.out.println("Pesquisar usando ID");
		System.out.println("============================================");
		imprimirFuncionarios(funcionarioDAO.pesquisarId(funcionario1.getId()));
		System.out.println("============================================");
		System.out.println("Pesquisar usando nome");
		System.out.println("============================================");
		imprimirFuncionarios(funcionarioDAO.pesquisarNome("Menino"));
		System.out.println("============================================");
		System.out.println("Pesquisar usando CPF");
		System.out.println("============================================");
		imprimirFuncionarios(funcionarioDAO.pesquisarCpf("07232548001"));
		System.out.println("============================================");
		System.out.println("Pesquisar usando login");
		System.out.println("============================================");
		imprimirFuncionarios(funcionarioDAO.pesquisarLogin("a"));

		// funcionarioDAO.deletar(funcionario1.getIdPessoa());
		// System.out.println("============================================");
		// System.out.println("Listar");
		// System.out.println("============================================");
		// imprimirFuncionarios(funcionarioDAO.listar());

		// Clientes
		System.out.println("----------------------------------------Clientes----------------------------------------");
		// clienteDAO.cadastrar(cliente1);
		cliente1.setId(1);
		cliente1.setIdPessoa(3);

		System.out.println("============================================");
		System.out.println("Listar");
		System.out.println("============================================");
		imprimirClientes(clienteDAO.listar());

		enderecosCliente1.set(0, new EnderecoVO("Rua longe do local", "111", null, "Do lado do mercado sem nome",
				"Baixo de Cima", "Mossoro", "RN", "12345678"));
		cliente1.setEnderecos(enderecosCliente1);
		clienteDAO.alterar(cliente1);

		System.out.println("============================================");
		System.out.println("Pesquisar usando ID");
		System.out.println("============================================");
		imprimirClientes(clienteDAO.pesquisarId(cliente1.getId()));
		System.out.println("============================================");
		System.out.println("Pesquisar usando nome");
		System.out.println("============================================");
		imprimirClientes(clienteDAO.pesquisarNome("%ao%"));
		System.out.println("============================================");
		System.out.println("Pesquisar usando CPF");
		System.out.println("============================================");
		imprimirClientes(clienteDAO.pesquisarCpf("19315322061"));

		// clienteDAO.deletar(cliente1.getIdPessoa());
		// System.out.println("============================================");
		// System.out.println("Listar");
		// System.out.println("============================================");
		// imprimirClientes(clienteDAO.listar());

		// Emprestáveis
		// -----------------------------------------------------------------------------
		// Livros
		System.out.println("-----------------------------------------Livros-----------------------------------------");
		// livroDAO.cadastrar(livro1);
		livro1.setIdEmprestavel(1);
		livro1.setId(1);
		// livroDAO.cadastrar(livro2);
		livro2.setIdEmprestavel(2);
		livro2.setId(2);

		System.out.println("============================================");
		System.out.println("Listar");
		System.out.println("============================================");
		imprimirLivros(livroDAO.listar());

		livro1.setNumeroPaginas(140);
		livroDAO.alterar(livro1);

		System.out.println("============================================");
		System.out.println("Pesquisar usando ID");
		System.out.println("============================================");
		imprimirLivros(livroDAO.pesquisarId(livro1.getId()));
		System.out.println("============================================");
		System.out.println("Pesquisar usando titulo");
		System.out.println("============================================");
		imprimirLivros(livroDAO.pesquisarTitulo("%o%"));
		System.out.println("============================================");
		System.out.println("Pesquisar usando ano de lancamento");
		System.out.println("============================================");
		imprimirLivros(livroDAO.pesquisarAnoLancamento(2014));
		System.out.println("============================================");
		System.out.println("Pesquisar usando genero");
		System.out.println("============================================");
		imprimirLivros(livroDAO.pesquisarGenero("acao"));
		System.out.println("============================================");
		System.out.println("Pesquisar usando numero de paginas");
		System.out.println("============================================");
		imprimirLivros(livroDAO.pesquisarNumeroPaginas(140));

		livro2.setAnoLancamento(1999);
		livroDAO.alterar(livro2);
		System.out.println("============================================");
		System.out.println("Pesquisar usando ID");
		System.out.println("============================================");
		imprimirLivros(livroDAO.pesquisarId(livro2.getId()));

		// livroDAO.deletar(livro1.getIdEmprestavel());
		// System.out.println("============================================");
		// System.out.println("Listar");
		// System.out.println("============================================");
		// imprimirLivros(livroDAO.listar());

		// Discos
		System.out.println("-----------------------------------------Discos-----------------------------------------");
		// discoDAO.cadastrar(disco1);
		disco1.setIdEmprestavel(3);
		disco1.setId(1);

		System.out.println("============================================");
		System.out.println("Listar");
		System.out.println("============================================");
		imprimirDiscos(discoDAO.listar());

		disco1.setNumeroExemplares(80);
		discoDAO.alterar(disco1);

		System.out.println("============================================");
		System.out.println("Pesquisar usando ID");
		System.out.println("============================================");
		imprimirDiscos(discoDAO.pesquisarId(disco1.getId()));
		System.out.println("============================================");
		System.out.println("Pesquisar usando titulo");
		System.out.println("============================================");
		imprimirDiscos(discoDAO.pesquisarTitulo("%l%"));
		System.out.println("============================================");
		System.out.println("Pesquisar usando ano de lancamento");
		System.out.println("============================================");
		imprimirDiscos(discoDAO.pesquisarAnoLancamento(2018));
		System.out.println("============================================");
		System.out.println("Pesquisar usando banda");
		System.out.println("============================================");
		imprimirDiscos(discoDAO.pesquisarBanda("%s%"));
		System.out.println("============================================");
		System.out.println("Pesquisar usando estilo");
		System.out.println("============================================");
		imprimirDiscos(discoDAO.pesquisarEstilo("pagode"));

		// discoDAO.deletar(disco1.getIdEmprestavel());
		// System.out.println("============================================");
		// System.out.println("Listar");
		// System.out.println("============================================");
		// imprimirDiscos(discoDAO.listar());

		// Empréstimos
		// -----------------------------------------------------------------------------
		// Empréstimo
		System.out.println("---------------------------------------Emprestimos--------------------------------------");
		emprestimoDAO.cadastrar(emprestimo1);
		emprestimo1.setId(0);

		System.out.println("============================================");
		System.out.println("Listar");
		System.out.println("============================================");
		// imprimirEmprestimos(emprestimoDAO.listar());

		// Devolução
		List<Integer> quantidadesDevolucao = new ArrayList<>();

		quantidadesDevolucao.add(1);
		quantidadesDevolucao.add(2);

		emprestimo1.devolver(objetosEmprestados, quantidadesDevolucao);
		emprestimoDAO.alterar(emprestimo1);
		System.out.println("============================================");
		System.out.println("Listar");
		System.out.println("============================================");
		// imprimirEmprestimos(emprestimoDAO.listar());

		// emprestimoDAO.deletar(emprestimo1);
		// System.out.println("============================================");
		// System.out.println("Listar");
		// System.out.println("============================================");
		// imprimirEmprestimos(emprestimoDAO.listar());

	}

	private static void imprimirGerentes(ResultSet rs) {
		try {
			while (rs.next()) {
				List<EnderecoVO> enderecos = new ArrayList<EnderecoVO>();
				List<String> emails = new ArrayList<String>();
				List<TelefoneVO> telefones = new ArrayList<TelefoneVO>();

				long idPessoa = rs.getLong("id_pessoa");

				ResultSet rsEnderecos = EnderecoDAO.listar(idPessoa);
				while (rsEnderecos.next()) {
					enderecos.add(new EnderecoVO(rsEnderecos.getString("logradouro"), rsEnderecos.getString("numero"),
							rsEnderecos.getString("complemento"), rsEnderecos.getString("referencia"),
							rsEnderecos.getString("bairro"), rsEnderecos.getString("cidade"),
							rsEnderecos.getString("estado"), rsEnderecos.getString("cep")));
				}
				ResultSet rsEmails = EmailDAO.listar(idPessoa);
				while (rsEmails.next()) {
					emails.add(new String(rsEmails.getString("email")));
				}
				ResultSet rsTelefones = TelefoneDAO.listar(idPessoa);
				while (rsTelefones.next()) {
					telefones.add(new TelefoneVO(rsTelefones.getString("ddd"), rsTelefones.getString("telefone")));
				}

				GerenteVO gerente = new GerenteVO(rs.getString("login"), rs.getString("senha"), rs.getString("nome"),
						rs.getString("cpf"), enderecos, emails, telefones);
				gerente.setId(rs.getLong("id"));
				gerente.setIdUsuario(rs.getLong("id_usuario"));
				gerente.setIdPessoa(idPessoa);

				System.out.println(gerente);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private static void imprimirFuncionarios(ResultSet rs) {
		try {
			while (rs.next()) {
				List<EnderecoVO> enderecos = new ArrayList<EnderecoVO>();
				List<String> emails = new ArrayList<String>();
				List<TelefoneVO> telefones = new ArrayList<TelefoneVO>();

				long idPessoa = rs.getLong("id_pessoa");

				ResultSet rsEnderecos = EnderecoDAO.listar(idPessoa);
				while (rsEnderecos.next()) {
					enderecos.add(new EnderecoVO(rsEnderecos.getString("logradouro"), rsEnderecos.getString("numero"),
							rsEnderecos.getString("complemento"), rsEnderecos.getString("referencia"),
							rsEnderecos.getString("bairro"), rsEnderecos.getString("cidade"),
							rsEnderecos.getString("estado"), rsEnderecos.getString("cep")));
				}
				ResultSet rsEmails = EmailDAO.listar(idPessoa);
				while (rsEmails.next()) {
					emails.add(new String(rsEmails.getString("email")));
				}
				ResultSet rsTelefones = TelefoneDAO.listar(idPessoa);
				while (rsTelefones.next()) {
					telefones.add(new TelefoneVO(rsTelefones.getString("ddd"), rsTelefones.getString("telefone")));
				}

				FuncionarioVO funcionario = new FuncionarioVO(rs.getString("login"), rs.getString("senha"),
						rs.getString("nome"), rs.getString("cpf"), enderecos, emails, telefones);
				funcionario.setId(rs.getLong("id"));
				funcionario.setIdUsuario(rs.getLong("id_usuario"));
				funcionario.setIdPessoa(idPessoa);

				System.out.println(funcionario);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private static void imprimirClientes(ResultSet rs) {
		try {
			while (rs.next()) {
				List<EnderecoVO> enderecos = new ArrayList<EnderecoVO>();
				List<String> emails = new ArrayList<String>();
				List<TelefoneVO> telefones = new ArrayList<TelefoneVO>();

				long idPessoa = rs.getLong("id_pessoa");

				ResultSet rsEnderecos = EnderecoDAO.listar(idPessoa);
				while (rsEnderecos.next()) {
					enderecos.add(new EnderecoVO(rsEnderecos.getString("logradouro"), rsEnderecos.getString("numero"),
							rsEnderecos.getString("complemento"), rsEnderecos.getString("referencia"),
							rsEnderecos.getString("bairro"), rsEnderecos.getString("cidade"),
							rsEnderecos.getString("estado"), rsEnderecos.getString("cep")));
				}
				ResultSet rsEmails = EmailDAO.listar(idPessoa);
				while (rsEmails.next()) {
					emails.add(new String(rsEmails.getString("email")));
				}
				ResultSet rsTelefones = TelefoneDAO.listar(idPessoa);
				while (rsTelefones.next()) {
					telefones.add(new TelefoneVO(rsTelefones.getString("ddd"), rsTelefones.getString("telefone")));
				}

				ClienteVO cliente = new ClienteVO(rs.getString("nome"), rs.getString("cpf"), enderecos, emails,
						telefones);
				cliente.setId(rs.getLong("id"));
				cliente.setIdPessoa(idPessoa);

				System.out.println(cliente);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private static void imprimirLivros(ResultSet rs) {
		try {
			while (rs.next()) {
				LivroVO livro = new LivroVO(rs.getString("titulo"), rs.getString("genero"), rs.getInt("numero_paginas"),
						rs.getInt("numero_exemplares"), rs.getInt("numero_emprestimos"),
						rs.getInt("numero_dias_alugado"), rs.getInt("ano_lancamento"), rs.getFloat("valor_aluguel"));
				livro.setId(rs.getLong("id"));
				livro.setIdEmprestavel(rs.getLong("id_emprestavel"));

				System.out.println(livro);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private static void imprimirDiscos(ResultSet rs) {
		try {
			while (rs.next()) {
				DiscoVO disco = new DiscoVO(rs.getString("titulo"), rs.getString("banda"), rs.getString("estilo"),
						rs.getInt("numero_exemplares"), rs.getInt("numero_emprestimos"),
						rs.getInt("numero_dias_alugado"), rs.getInt("ano_lancamento"), rs.getFloat("valor_aluguel"));
				disco.setId(rs.getLong("id"));
				disco.setIdEmprestavel(rs.getLong("id_emprestavel"));

				System.out.println(disco);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
