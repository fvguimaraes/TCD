package br.com.fiap69aoj.usuario.dao.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.fiap69aoj.usuario.dao.entity.UsuarioEntity;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Long> {
	
	Optional<UsuarioEntity> findByIdAcesso(String idAcesso);
	
	void deleteByIdAcesso(String idAcesso);

}
