package br.com.fiap69aoj.catalogo.model;

public class Serie {

	private String nome;
	private String genero;
	private String descricao;
	private String autor;
	
	public Serie() {}
	
	public static class BuildSerie{
		
		private String nome;
		private String genero;
		private String descricao;
		private String autor;

		public BuildSerie comNome(String nome) {
			this.nome = nome;
			return this;
		}

		public BuildSerie doGenero(String genero) {
			this.genero = genero;
			return this;
		}

		public BuildSerie comDescricao(String descricao) {
			this.descricao = descricao;
			return this;
		}

		public BuildSerie doAutor(String autor) {
			this.autor = autor;
			return this;
		}
		
		public Serie build() {
			return new Serie(this);
		}
	}
	
	private Serie(BuildSerie builder) {
		nome = builder.nome;
		genero = builder.genero;
		descricao = builder.descricao;
		autor = builder.autor;
	}
	
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getAutor() {
		return autor;
	}
	public void setAutor(String autor) {
		this.autor = autor;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getGenero() {
		return genero;
	}
	public void setGenero(String genero) {
		this.genero = genero;
	}
	
}
