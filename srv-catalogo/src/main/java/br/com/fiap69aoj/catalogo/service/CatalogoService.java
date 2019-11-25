package br.com.fiap69aoj.catalogo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import br.com.fiap69aoj.catalogo.client.EstatisticasClient;
import br.com.fiap69aoj.catalogo.model.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fiap69aoj.catalogo.dao.entity.FilmeEntity;
import br.com.fiap69aoj.catalogo.dao.entity.SerieEntity;
import br.com.fiap69aoj.catalogo.dao.repository.FilmeRepository;
import br.com.fiap69aoj.catalogo.dao.repository.SerieRepository;
import br.com.fiap69aoj.catalogo.events.CatalogoProducer;
import br.com.fiap69aoj.catalogo.exception.FilmeNaoEncontradoException;
import br.com.fiap69aoj.catalogo.exception.SerieNaoEncontradaException;

@Service
@Transactional
public class CatalogoService {

	@Autowired
	private FilmeRepository filmeRepo;

	@Autowired
	private SerieRepository serieRepo;

	@Autowired
	private CatalogoProducer producer;
	@Autowired
	private EstatisticasClient estatisticas;

	public void inserirFilme(Filme filme) {
		filmeRepo.save(this.builFilmeEntityFromModel(filme));
	}

	public void inserirSerie(Serie serie) {
		serieRepo.save(this.buildSerieEntityFromModel(serie));
	}

	public List<String> obterFilmePorGeneroOuChave(String genero, String palavraChave) {
		
		boolean buscarPorPalavraChave = StringUtils.isNotEmpty(palavraChave);
		boolean buscarPorGenero = StringUtils.isNotEmpty(genero);
		
		List<FilmeEntity> filmesEntity = null;
		
		if (buscarPorGenero && buscarPorPalavraChave) {
			filmesEntity = filmeRepo.findCustom(palavraChave, genero);
		} else if (buscarPorGenero) {
			filmesEntity = filmeRepo.findAllByGenero(genero);
		} else if (buscarPorPalavraChave) {
			filmesEntity = filmeRepo.findByNomeContainingIgnoreCase(palavraChave);
		} else {
			filmesEntity = filmeRepo.findAll();
		}
						
		if (!filmesEntity.isEmpty()) {
			List<String> retorno = new ArrayList<String>();
			for (FilmeEntity filme : filmesEntity) {
				retorno.add(this.buildFilmeModelFromEntity(filme).getNome());
			}
			return retorno;
		}
		throw new FilmeNaoEncontradoException(genero);
	}

	public List<String> obterSeriePorGeneroOuChave(String genero, String palavraChave) {
		
		boolean buscarPorPalavraChave = StringUtils.isNotEmpty(palavraChave);
		boolean buscarPorGenero = StringUtils.isNotEmpty(genero);
		
		List<SerieEntity> seriesEntity = null;
		
		if (buscarPorGenero && buscarPorPalavraChave) {
			seriesEntity = serieRepo.findCustom(palavraChave, genero);
		} else if (buscarPorGenero) {
			seriesEntity = serieRepo.findAllByGenero(genero);
		} else if (buscarPorPalavraChave) {
			seriesEntity = serieRepo.findByNomeContainingIgnoreCase(palavraChave);
		} else {
			seriesEntity = serieRepo.findAll();
		}

		if (!seriesEntity.isEmpty()) {
			List<String> retorno = new ArrayList<String>();
			for (SerieEntity serie : seriesEntity) {
				retorno.add(this.buildSerieModelFromEntity(serie).getNome());
			}
			return retorno;
		}
		throw new SerieNaoEncontradaException(genero);
	}

	public Filme obterDetalheFilme(long idFilme) {
		Optional<FilmeEntity> filme = filmeRepo.findById(idFilme);
		if (filme.isPresent()) {
			return this.buildFilmeModelFromEntity(filme.get());
		}
		throw new FilmeNaoEncontradoException("");
	}

	public Serie obterDetalheSerie(long idSerie) {
		Optional<SerieEntity> serie = serieRepo.findById(idSerie);
		if (serie.isPresent()) {
			return this.buildSerieModelFromEntity(serie.get());
		}
		throw new SerieNaoEncontradaException("");
	}

	public void registraDetlheFilme(Long idFilme, Long nota, Long idUsuario) {
		Optional<FilmeEntity> filmeEntity = filmeRepo.findById(idFilme);
		if (filmeEntity.isPresent()) {
			FilmeRatting ratting = new FilmeRatting();
			ratting.setGenero(filmeEntity.get().getGenero());
			ratting.setIdConteudo(filmeEntity.get().getId());
			ratting.setNota(nota);
			ratting.setTipoConteudo("FILME");
			ratting.setIdUsuario(idUsuario);
			producer.send(ratting.toJson());
		} else {
			throw new FilmeNaoEncontradoException("");
		}
	}

	public void registraDetlheSerie(Long idSerie, Long nota, Long idUsuario) {
		Optional<SerieEntity> serieEntity = serieRepo.findById(idSerie);
		if (serieEntity.isPresent()) {
			SerieRatting ratting = new SerieRatting();
			ratting.setGenero(serieEntity.get().getGenero());
			ratting.setIdConteudo(serieEntity.get().getId());
			ratting.setNota(nota);
			ratting.setTipoConteudo("SERIE");
			ratting.setIdUsuario(idUsuario);
			producer.send(ratting.toJson());
		} else {
			throw new SerieNaoEncontradaException("");
		}
	}

	private Filme buildFilmeModelFromEntity(FilmeEntity filmeEntity) {
		return new Filme.BuildFilme().comNome(filmeEntity.getNome()).comDescricao(filmeEntity.getDescricao())
				.doGenero(filmeEntity.getGenero()).doAutor(filmeEntity.getAutor()).build();
	}

	private Serie buildSerieModelFromEntity(SerieEntity serieEntity) {
		return new Serie.BuildSerie().comNome(serieEntity.getNome()).comDescricao(serieEntity.getDescricao())
				.doGenero(serieEntity.getGenero()).doAutor(serieEntity.getAutor()).build();
	}

	private FilmeEntity builFilmeEntityFromModel(Filme filme) {
		FilmeEntity entity = new FilmeEntity();
		entity.setAutor(filme.getAutor());
		entity.setDescricao(filme.getDescricao());
		entity.setGenero(filme.getGenero());
		entity.setNome(filme.getNome());
		return entity;
	}

	private SerieEntity buildSerieEntityFromModel(Serie serie) {
		SerieEntity entity = new SerieEntity();
		entity.setAutor(serie.getAutor());
		entity.setDescricao(serie.getDescricao());
		entity.setGenero(serie.getGenero());
		entity.setNome(serie.getNome());
		return entity;
	}

	public List<Conteudo> obterAssistidosGenero(String tipo, String genero) {
		return this.estatisticas.assistidos(tipo,genero);
	}

	public List<Conteudo> obterAssistidosGenero(Long id_usuario) {
		return this.estatisticas.assistidosUsuario(id_usuario);
	}
}
