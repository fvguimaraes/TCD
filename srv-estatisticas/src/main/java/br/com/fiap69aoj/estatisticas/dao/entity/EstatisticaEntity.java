package br.com.fiap69aoj.estatisticas.dao.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "dados")
public class EstatisticaEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@NotNull
	private Long idUsuario;
	@NotNull
	private Long idConteudo;
	@NotNull
	private String tipoConteudo;
	@NotNull
	private String genero;
	@NotNull
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

}
