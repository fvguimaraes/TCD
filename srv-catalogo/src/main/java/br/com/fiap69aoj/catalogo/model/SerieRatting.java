package br.com.fiap69aoj.catalogo.model;

import com.google.gson.Gson;

public class SerieRatting {

	private Long idConteudo;

	private String tipoConteudo;

	private String genero;

	private Long nota;

	public Long getIdConteudo() {
		return idConteudo;
	}

	public void setIdConteudo(Long idConteudo) {
		this.idConteudo = idConteudo;
	}

	public String getTipoConteudo() {
		return tipoConteudo;
	}

	public void setTipoConteudo(String tipoConteudo) {
		this.tipoConteudo = tipoConteudo;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public Long getNota() {
		return nota;
	}

	public void setNota(Long nota) {
		this.nota = nota;
	}
	
	public String toJson() {
		return new Gson().toJson(this);		
	}

}
