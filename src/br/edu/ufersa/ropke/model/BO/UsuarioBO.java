package br.edu.ufersa.ropke.model.BO;

import java.io.File;

import br.edu.ufersa.ropke.model.VO.UsuarioVO;

public abstract class UsuarioBO<VO extends UsuarioVO> extends PessoaBO<VO> {
	@Override
	public boolean isNull(VO usuario, File arquivo) {
		// Verifica se a entrada de argumentos não é nula
		if ((usuario != null) && (arquivo != null)) {
			// Verifica se parâmetros importantes não são nulos
			if ((usuario.getLogin() != null) && (usuario.getSenha() != null)) {
				return false;
			} else {
				return true;
			}
		} else {
			return true;
		}
	}

	@Override
	public void cadastrar(VO usuario, File arquivo) {
		// Verifica se a entrada de argumentos não é nula
		if (!isNull(usuario, arquivo)) {
			super.cadastrar(usuario, arquivo);
		}
	}

	@Override
	public void alterar(VO usuario, File arquivo) {
		// Verifica se a entrada de argumentos não é nula
		if (!isNull(usuario, arquivo)) {
			super.alterar(usuario, arquivo);
		}
	}

	@Override
	public void deletar(VO usuario, File arquivo) {
		// Verifica se a entrada de argumentos não é nula
		if (!isNull(usuario, arquivo)) {
			super.deletar(usuario, arquivo);
		}

	}

	@Override
	public void pesquisar(File arquivo) {
		if (arquivo != null) {
			super.pesquisar(arquivo);
		}
	}

	@Override
	public VO pesquisar(VO usuario, File arquivo) {
		// Verifica se a entrada de argumentos não é nula
		if (!isNull(usuario, arquivo)) {
			return super.pesquisar(usuario, arquivo);
		} else {
			return null;
		}
	}

	public boolean autenticar(VO usuario, File arquivo, String login, String senha) {
		if ((usuario != null) && (arquivo != null) && (login != null) && (senha != null)) {
			if ((login != "") && (senha != "")) {
				VO usuarioEncontrado = super.pesquisar(usuario, arquivo);

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
