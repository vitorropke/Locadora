package br.edu.ufersa.ropke.locadoramaven.exception;

public class AuthenticationException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public AuthenticationException() {
		super("Usuario ou senha incorretos");
	}
}
