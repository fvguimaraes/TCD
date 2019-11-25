package br.com.fiap69aoj.estatisticas.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.fiap69aoj.estatisticas.dao.entity.EstatisticaEntity;

@Repository
public interface EstatisticaRepository extends JpaRepository<EstatisticaEntity, Long> {

}
