package br.edu.ufersa.ropke.model.BO;

import java.io.File;

import br.edu.ufersa.ropke.model.VO.UsuarioVO;

public abstract class UsuarioBO extends PessoaBO {
	public static void cadastrar(UsuarioVO usuario, File arquivo) {
		if ((usuario != null) && (arquivo != null)) {
			// Verifica se parâmetros importantes não são nulos
			if ((usuario.getLogin() != null) && (usuario.getSenha() != null)) {
				PessoaBO.cadastrar(usuario, arquivo);
			}
		}
	}

	public static void alterar(UsuarioVO usuario, File arquivo) {
		if ((usuario != null) && (arquivo != null)) {
			// Verifica se parâmetros importantes não são nulos
			if ((usuario.getLogin() != null) && (usuario.getSenha() != null)) {
				PessoaBO.alterar(usuario, arquivo);
			}
		}
	}

	public static void deletar(UsuarioVO usuario, File arquivo) {
		if ((usuario != null) && (arquivo != null)) {
			// Verifica se parâmetros importantes não são nulos
			if ((usuario.getLogin() != null) && (usuario.getSenha() != null)) {
				PessoaBO.deletar(usuario, arquivo);
			}
		}
	}

	public static void pesquisar(File arquivo) {
		if (arquivo != null) {
			PessoaBO.pesquisar(arquivo);
		}
	}

	public static UsuarioVO pesquisar(UsuarioVO usuario, File arquivo) {
		// Verifica se a entrada de argumentos não é nula
		if ((usuario != null) && (arquivo != null)) {
			// Verifica se parâmetros importantes não são nulos
			if ((usuario.getLogin() != null) && (usuario.getSenha() != null)) {
				return (UsuarioVO) PessoaBO.pesquisar(usuario, arquivo);
			} else {
				return null;
			}
		} else {
			return null;
		}
	}

	public static boolean autenticar(UsuarioVO usuario, File arquivo, String login, String senha) {
		if ((usuario != null) && (arquivo != null) && (login != null) && (senha != null)) {
			if ((login != "") && (senha != "")) {
				UsuarioVO usuarioEncontrado = (UsuarioVO) PessoaBO.pesquisar(usuario, arquivo);

				if (usuarioEncontrado == null) {
					return false;
				}
				if (usuarioEncontrado.getLogin().equals(login)) {
					if (usuarioEncontrado.getSenha().equals(senha)) {
						return true;
					} else {
						return false;
					}
				} else {
					return false;
				}
			}

			return false;
		}

		return false;
	}
}
