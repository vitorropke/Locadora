package br.edu.ufersa.ropke.exception;

public class IncorrectPasswordException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public IncorrectPasswordException() {
		super("Usuario nao encontrado");
	}
}