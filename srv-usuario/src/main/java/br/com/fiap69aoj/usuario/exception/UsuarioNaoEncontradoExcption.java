package br.com.fiap69aoj.usuario.exception;

public class UsuarioNaoEncontradoExcption extends RuntimeException {

	private static final long serialVersionUID = -3464831381975305812L;

	public UsuarioNaoEncontradoExcption(String idAcesso) {
        super("Usuario nao encontrado : " + idAcesso);
    }

}
