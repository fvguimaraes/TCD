package br.com.fiap69aoj.chamados.model;

import java.time.LocalDate;

import io.swagger.annotations.ApiModelProperty;

public class Chamado {
	
	@ApiModelProperty(notes = "ID de acesso do usuário que abriu chamado")	
	private String idAcessoUsuario;
	
	@ApiModelProperty(notes = "Detalhe do problema relatado")
	private String comentario;
	
	@ApiModelProperty(notes = "Data de criação do chamado", hidden = true)
	private LocalDate dataCriacao;
	
	@ApiModelProperty(notes = "Status atual do chamado", hidden = true)
	private String status;
	
	@ApiModelProperty(notes = "ID do chamado", required = true, hidden = true)
	private long idChamado;
	
	public static class BuildChamado {
		
		private String idAcessoUsuario;	
		private String comentario;	
		private LocalDate dataCriacao;	
		private String status;
		private long idChamado;
		
		public BuildChamado doUsuario(String idAcessoUsuario) {
			this.idAcessoUsuario = idAcessoUsuario;	
			return this;
		}
		
		public BuildChamado comComentario(String comentario) {
			this.comentario = comentario;	
			return this;
		}	
		
		public BuildChamado deStatus(String status) {
			this.status = status;	
			return this;
		}	
		
		public BuildChamado criadoEm(LocalDate dataCriacao) {
			this.dataCriacao = dataCriacao;	
			return this;
		}	

		public BuildChamado deNumero(long idChamado) {
			this.idChamado = idChamado;	
			return this;
		}	
		
		public Chamado build() {
			return new Chamado(this);
		}
	}

	private Chamado(BuildChamado builder) {
		idAcessoUsuario = builder.idAcessoUsuario;
		comentario = builder.comentario;
		dataCriacao = builder.dataCriacao;
		status = builder.status;
		idChamado = builder.idChamado;
	}
	
	public Chamado() {}

	public String getIdAcessoUsuario() {
		return idAcessoUsuario;
	}

	public void setIdAcessoUsuario(String idAcessoUsuario) {
		this.idAcessoUsuario = idAcessoUsuario;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public LocalDate getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(LocalDate dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
		
	
	public long getIdChamado() {
		return idChamado;
	}

	public void setIdChamado(long idChamado) {
		this.idChamado = idChamado;
	}
		
}
