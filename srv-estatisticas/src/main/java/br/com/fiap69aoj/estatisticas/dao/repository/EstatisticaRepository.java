package br.com.fiap69aoj.estatisticas.dao.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.fiap69aoj.estatisticas.dao.entity.EstatisticaEntity;

@Repository
public interface EstatisticaRepository extends JpaRepository<EstatisticaEntity, Long> {

	@Query(value = "SELECT * FROM dados WHERE tipo_conteudo = ?1 and genero = ?2 group by id_conteudo ORDER BY COUNT(*) desc",
			nativeQuery = true)
	List<EstatisticaEntity> findMaisAssistidos(String tipoConteudo, String genero);

	List<EstatisticaEntity> findByIdUsuario(long idUsuario);

}
