package br.com.fiap69aoj.catalogo.dao.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.fiap69aoj.catalogo.dao.entity.FilmeEntity;

@Repository
public interface FilmeRepository extends JpaRepository<FilmeEntity, Long> {
	
	List<FilmeEntity> findAllByGenero(String genero);
	
	Optional<FilmeEntity> findByNome(String nome);
}
