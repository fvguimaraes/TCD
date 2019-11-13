package br.com.fiap69aoj.chamados.exception;

public class UsuarioNaoEncontradoException  extends RuntimeException {

	private static final long serialVersionUID = 6747078479139465427L;

	public UsuarioNaoEncontradoException(String idAcessoUsuario) {
        super("Nao foi possivel registrar chamado. Usuario nao encontrado " + idAcessoUsuario);
    }


}
