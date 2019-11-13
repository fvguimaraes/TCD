package br.com.fiap69aoj.usuario.model;

public class Usuario {

	private String nome;
	private String email;
	private String idAcesso;
	
	public static class BuildUsuario {
		
		private String nome;
		private String email;
		private String idAcesso;
		
		public BuildUsuario comNome(String nome) {
			this.nome = nome;	
			return this;
		}
		
		public BuildUsuario comEmail(String email) {
			this.email = email;	
			return this;
		}	
		
		public BuildUsuario comIdAcesso(String idAcesso) {
			this.idAcesso = idAcesso;	
			return this;
		}	
		
		public Usuario build() {
			return new Usuario(this);
		}
	}

	private Usuario(BuildUsuario builder) {
		nome = builder.nome;
		email = builder.email;
		idAcesso = builder.idAcesso;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getIdAcesso() {
		return idAcesso;
	}

	public void setIdAcesso(String idAcesso) {
		this.idAcesso = idAcesso;
	}
	
	public Usuario() {		
	}
	
}
