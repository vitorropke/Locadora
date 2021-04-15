package br.edu.ufersa.ropke.model.BO;

import java.util.Calendar;

import br.edu.ufersa.ropke.model.VO.ClienteVO;
import br.edu.ufersa.ropke.model.VO.EmprestimoVO;
import br.edu.ufersa.ropke.model.VO.LivroVO;

public class MainBO {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EmprestimoBO emprestimo = new EmprestimoBO();
		EmprestimoVO emprestimoVO = new EmprestimoVO();

		String[] enderecos = { "rua 1" };
		String[] emails = { "123abc@gmail.com" };
		String[] telefones = { "(84) 3000-0000" };

		// titulo, genero, numeroPaginas, 'numeroExemplares', 'numeroEmprestimos',
		// 'numeroDiasAlugado', anoLancamento, valorAluguel
		LivroVO livro1 = new LivroVO("oi", "romance", 150, 4, 2010, 12);
		LivroVO livro2 = new LivroVO("em", "acao", 220, 1, 2018, 26);

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
		cal.set(2021, 6, 04, 04, 15, 20);
		cal0.set(2021, 4, 2, 04, 15, 20);
		Calendar[] datas = { cal, cal0 };

		emprestimo.alugarLivro(emprestimoVO, livros, quantidades, datas, cliente1);

		// System.out.println(emprestimo);
		System.out.println(emprestimoVO);
	}
}
