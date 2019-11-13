package br.com.fiap69aoj.chamados.exception;

public class ChamadoNaoEncontradoException extends RuntimeException {

	private static final long serialVersionUID = 3898223563615967133L;

	public ChamadoNaoEncontradoException(long idAcesso) {
        super("Chamado nao encontrado : " + idAcesso);
    }

}
