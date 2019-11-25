package br.com.fiap69aoj.chamados.service;

import br.com.fiap69aoj.chamados.clietnt.model.Usuario;
import br.com.fiap69aoj.chamados.clietnt.model.UsuarioClient;
import br.com.fiap69aoj.chamados.dao.entity.ChamadoEntity;
import br.com.fiap69aoj.chamados.dao.repository.ChamadoRepository;
import br.com.fiap69aoj.chamados.exception.ChamadoNaoEncontradoException;
import br.com.fiap69aoj.chamados.exception.UsuarioNaoEncontradoException;
import br.com.fiap69aoj.chamados.model.Chamado;
import br.com.fiap69aoj.chamados.model.StatusEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ChamadoService {

	@Autowired
	private ChamadoRepository chamadoRepository;

	@Autowired
	private UsuarioClient usuarioClient;

	public void incluirChamado(Chamado chamado) {

		Optional<Usuario> usuario = usuarioClient.obterDadosUsuario(chamado.getIdAcessoUsuario());

		if (usuario.isPresent()) {
			ChamadoEntity entity = new ChamadoEntity();
			entity.setIdAcessoUsuario(chamado.getIdAcessoUsuario());
			entity.setComentario(chamado.getComentario());
			entity.setDataCriacao(LocalDate.now());
			entity.setStatus(StatusEnum.ABERTO.getDescricao());
			chamadoRepository.saveAndFlush(entity);
		} else {
			throw new UsuarioNaoEncontradoException(chamado.getIdAcessoUsuario());
		}

	}

	public List<Chamado> listarChamados() {
		List<ChamadoEntity> consulta = chamadoRepository.findAll();
		List<Chamado> retorno = new ArrayList<Chamado>();

		for (ChamadoEntity chamadoRetorno : consulta) {
			retorno.add(buildChamadoFromEntity(chamadoRetorno));
		}
		return retorno;
	}

	public Chamado listarChamadosPorId(long id) {
		Optional<ChamadoEntity> chamado = chamadoRepository.findById(id);
		if (chamado.isPresent()) {
			return this.buildChamadoFromEntity(chamado.get());
		}
		throw new ChamadoNaoEncontradoException(id);
	}

	public void removerChamadosPorId(long id) {
		chamadoRepository.deleteById(id);
	}

	public void atualizarChamadoChamado(Chamado chamado) {
		Optional<Usuario> usuario = usuarioClient.obterDadosUsuario(chamado.getIdAcessoUsuario());

		if (usuario.isPresent()) {
			ChamadoEntity entity = new ChamadoEntity();
			entity.setIdAcessoUsuario(chamado.getIdAcessoUsuario());
			entity.setComentario(chamado.getComentario());
			entity.setDataCriacao(LocalDate.now());
			entity.setStatus(StatusEnum.ABERTO.getDescricao());
			chamadoRepository.saveAndFlush(entity);
		} else {
			throw new UsuarioNaoEncontradoException(chamado.getIdAcessoUsuario());
		}

	}

	private Chamado buildChamadoFromEntity(ChamadoEntity entity) {
		return new Chamado.BuildChamado().doUsuario(entity.getIdAcessoUsuario()).criadoEm(entity.getDataCriacao())
				.comComentario(entity.getComentario()).deStatus(entity.getStatus()).deNumero(entity.getId()).build();
	}


}
