package br.com.fiap69aoj.usuario.dao.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "minhalista")
public class ConteudoEntity {

	@Id
	@Column(name = "id_conteudo")
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
