package br.com.fiap69aoj.usuario.dao.repository;

import br.com.fiap69aoj.usuario.dao.entity.ConteudoEntity;
import br.com.fiap69aoj.usuario.dao.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ConteudoRepository extends JpaRepository<ConteudoEntity, Long> {

}
