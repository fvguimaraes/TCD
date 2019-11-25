package br.com.fiap69aoj.catalogo.exception;

public class FilmeNaoEncontradoException extends RuntimeException {

	private static final long serialVersionUID = 8945326609228092346L;

	public FilmeNaoEncontradoException(String filme) {
		super("Filme nao entrado " + filme);
	}

}
