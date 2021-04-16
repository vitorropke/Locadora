package br.edu.ufersa.ropke.model.BO;

import java.util.Calendar;

import br.edu.ufersa.ropke.model.VO.ClienteVO;
import br.edu.ufersa.ropke.model.VO.DiscoVO;
import br.edu.ufersa.ropke.model.VO.EmprestimoVO;
import br.edu.ufersa.ropke.model.VO.LivroVO;

public class MainBO {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EmprestimoVO emprestimoVO = new EmprestimoVO();

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
		cal.set(2021, 3, 26, 04, 15, 20);
		cal0.set(2021, 4, 1, 04, 15, 20);
		Calendar[] datas = { cal, cal0 };
		EmprestimoBO.alugarLivro(emprestimoVO, livros, quantidades, datas, cliente1);

		Calendar[] datas0 = { cal };
		int[] quantidades0 = { 3 };
		EmprestimoBO.alugarDisco(emprestimoVO, discos, quantidades0, datas0, cliente1);

		// System.out.println(emprestimo);
		System.out.println(emprestimoVO);

		System.out.println("----------------------------------------Devolucao-------------------------------------");
		LivroVO[] livros0 = { livro1, livro2 };
		quantidades[0] = 3;
		quantidades[1] = 1;
		EmprestimoBO.devolverLivro(emprestimoVO, livros0, quantidades, datas);
		
		
		EmprestimoBO.devolverDisco(emprestimoVO, discos, quantidades0, datas0);
		
		System.out.println(emprestimoVO);
	}
}
