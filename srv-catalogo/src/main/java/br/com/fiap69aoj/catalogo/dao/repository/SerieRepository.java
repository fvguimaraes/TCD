package br.com.fiap69aoj.catalogo.dao.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.fiap69aoj.catalogo.dao.entity.SerieEntity;

@Repository
public interface SerieRepository extends JpaRepository<SerieEntity, Long> {

	List<SerieEntity> findAllByGenero(String genero);

	Optional<SerieEntity> findByNome(String nomeSerie);
	
	List<SerieEntity> findByNomeContainingIgnoreCase(String nome);
	
	@Query("SELECT s FROM SerieEntity s where s.nome LIKE %?1% and f.genero = ?2")
	List<SerieEntity> findCustom(String chave, String genero);
}
