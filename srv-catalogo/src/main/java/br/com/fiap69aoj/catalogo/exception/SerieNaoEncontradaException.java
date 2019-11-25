package br.com.fiap69aoj.catalogo.exception;

public class SerieNaoEncontradaException extends RuntimeException {

	private static final long serialVersionUID = -6785606808360870039L;

	public SerieNaoEncontradaException(String serie) {
		super("Filme nao entrado " + serie);
	}

}
