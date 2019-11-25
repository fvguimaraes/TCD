package br.com.fiap69aoj.estatisticas.model;

public class ConteudoRatting {

	private Long idConteudo;

	private String tipoConteudo;

	private String genero;

	private Long nota;
	
	private long idUsuario;

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

	public long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(long idUsuario) {
		this.idUsuario = idUsuario;
	}
	
}
