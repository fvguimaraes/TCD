package br.com.fiap69aoj.estatisticas.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fiap69aoj.estatisticas.dao.entity.EstatisticaEntity;
import br.com.fiap69aoj.estatisticas.dao.repository.EstatisticaRepository;
import br.com.fiap69aoj.estatisticas.exception.RegistroNaoEncontradoException;
import br.com.fiap69aoj.estatisticas.model.Conteudo;
import br.com.fiap69aoj.estatisticas.model.ConteudoRatting;

@Service
@Transactional
public class EstatisticasService {

	@Autowired
	private EstatisticaRepository repository;

	public List<Conteudo> assistidos(String catalogo, String genero) {
		List<EstatisticaEntity> retorno =  repository.findMaisAssistidos(catalogo, genero);
		if (!retorno.isEmpty()) {
			List<Conteudo> conteudos = new ArrayList<Conteudo>();
			for (EstatisticaEntity entity : retorno) {
				Conteudo c = new Conteudo();
				c.setId(entity.getIdConteudo());
				c.setTipo(entity.getTipoConteudo());
				conteudos.add(c);
			}
			return conteudos;
		}
		throw new RegistroNaoEncontradoException();
	}
	
	public void inserirRatting(ConteudoRatting ratting) {
		EstatisticaEntity entity = new EstatisticaEntity();
		entity.setGenero(ratting.getGenero());
		entity.setIdConteudo(ratting.getIdConteudo());
		entity.setNota(ratting.getNota());
		entity.setTipoConteudo(ratting.getTipoConteudo());
		repository.save(entity);
	}
	

	public List<Conteudo> obterItensAssistidosDoUsuario(Long idUsuario) {
		List<EstatisticaEntity> retorno = repository.findByIdUsuario(idUsuario);

		if (!retorno.isEmpty()) {
			List<Conteudo> conteudos = new ArrayList<Conteudo>();
			for (EstatisticaEntity entity : retorno) {
				Conteudo c = new Conteudo();
				c.setId(entity.getIdConteudo());
				c.setTipo(entity.getTipoConteudo());
				conteudos.add(c);
			}
			return conteudos;
		}

		throw new RegistroNaoEncontradoException();

	}
}
