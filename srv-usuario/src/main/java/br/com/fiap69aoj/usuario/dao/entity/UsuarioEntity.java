package br.com.fiap69aoj.usuario.dao.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "cadastro")
public class UsuarioEntity {

	@Id
	@Column(name = "id_acesso")
	private String idAcesso;

	@NotNull
	private String email;

	@NotNull
	private String nome;
	

	public UsuarioEntity() {
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

		
	public String getIdAcesso() {
		return idAcesso;
	}

	public void setIdAcesso(String idAcesso) {
		this.idAcesso = idAcesso;
	}

}
