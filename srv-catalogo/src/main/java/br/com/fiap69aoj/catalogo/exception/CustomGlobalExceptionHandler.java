package br.com.fiap69aoj.catalogo.exception;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomGlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(FilmeNaoEncontradoException.class)
	public void chamadoNaoEncontrado(HttpServletResponse response) throws IOException {
		response.sendError(HttpStatus.NOT_FOUND.value());
	}

	@ExceptionHandler(SerieNaoEncontradaException.class)
	public void usuarioNaoEncontrado(HttpServletResponse response) throws IOException {
		response.sendError(HttpStatus.NOT_FOUND.value());
	}

}
