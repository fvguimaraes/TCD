package br.com.fiap69aoj.chamados.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.fiap69aoj.chamados.dao.entity.ChamadoEntity;

@Repository
public interface ChamadoRepository extends JpaRepository<ChamadoEntity, Long> {
	

}
