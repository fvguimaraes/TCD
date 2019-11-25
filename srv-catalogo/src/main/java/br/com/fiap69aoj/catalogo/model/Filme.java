package br.com.fiap69aoj.catalogo.model;

public class Filme {

	private String nome;
	private String genero;
	private String descricao;
	private String autor;

	public static class BuildFilme {

		private String nome;
		private String genero;
		private String descricao;
		private String autor;

		public BuildFilme comNome(String nome) {
			this.nome = nome;
			return this;
		}

		public BuildFilme doGenero(String genero) {
			this.genero = genero;
			return this;
		}

		public BuildFilme comDescricao(String descricao) {
			this.descricao = descricao;
			return this;
		}

		public BuildFilme doAutor(String autor) {
			this.autor = autor;
			return this;
		}

		public Filme build() {
			return new Filme(this);
		}

	}

	private Filme(BuildFilme builder) {
		nome = builder.nome;
		genero = builder.genero;
		descricao = builder.descricao;
		autor = builder.autor;
	}

	public Filme() {
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

}
