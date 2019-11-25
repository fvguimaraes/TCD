package br.com.fiap69aoj.usuario.service;

import br.com.fiap69aoj.usuario.dao.entity.ConteudoEntity;
import br.com.fiap69aoj.usuario.dao.entity.UsuarioEntity;
import br.com.fiap69aoj.usuario.dao.repository.ConteudoRepository;
import br.com.fiap69aoj.usuario.dao.repository.UsuarioRepository;
import br.com.fiap69aoj.usuario.exception.UsuarioNaoEncontradoExcption;
import br.com.fiap69aoj.usuario.model.Conteudo;
import br.com.fiap69aoj.usuario.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	@Autowired
	private ConteudoRepository conteudoRepository;

	public Usuario obterDadosUsuarioPorId(String idAcesso) {
		Optional<UsuarioEntity> usuario = usuarioRepository.findByIdAcesso(idAcesso);
		if(usuario.isPresent()) {
			return this.buildUsuarioModelo(usuario.get());
		}
		throw new UsuarioNaoEncontradoExcption(idAcesso);
	}
	
	public void incluirUsuario(Usuario usuario) {
		UsuarioEntity entity = new UsuarioEntity();
		entity.setNome(usuario.getNome());
		entity.setEmail(usuario.getEmail());
		entity.setIdAcesso(usuario.getIdAcesso());
		usuarioRepository.save(entity);		
	}

	public void removerUsuario(String idAcesso) {
		usuarioRepository.deleteByIdAcesso(idAcesso);		
	}
	
	public void atualizarUsuario(Usuario usuario) {
		UsuarioEntity entity = new UsuarioEntity();
		entity.setNome(usuario.getNome());
		entity.setEmail(usuario.getEmail());
		entity.setIdAcesso(usuario.getIdAcesso());
		usuarioRepository.save(entity);			
	}
		
	private Usuario buildUsuarioModelo(UsuarioEntity usuarioEntity) {
		return new Usuario.BuildUsuario()
				.comNome(usuarioEntity.getNome())
				.comEmail(usuarioEntity.getEmail())
				.comIdAcesso(usuarioEntity.getIdAcesso())
				.build();
	}

	public void adicionaConteudoNaLista(Conteudo conteudo) {
		ConteudoEntity entity = new ConteudoEntity();
		entity.setId(conteudo.getId());
		this.conteudoRepository.save(entity);
	}
}
