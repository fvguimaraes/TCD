package br.com.fiap69aoj.catalogo.dao.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.fiap69aoj.catalogo.dao.entity.FilmeEntity;

@Repository
public interface FilmeRepository extends JpaRepository<FilmeEntity, Long> {
	
	List<FilmeEntity> findAllByGenero(String genero);
	
	Optional<FilmeEntity> findByNome(String nome);
	
	List<FilmeEntity> findByNomeContainingIgnoreCase(String nome);
		
	@Query("SELECT f FROM FilmeEntity f where f.nome LIKE %?1% and f.genero = ?2")
	List<FilmeEntity> findCustom(String chave, String genero);
}
