package br.com.fiap69aoj.catalogo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fiap69aoj.catalogo.dao.entity.FilmeEntity;
import br.com.fiap69aoj.catalogo.dao.entity.SerieEntity;
import br.com.fiap69aoj.catalogo.dao.repository.FilmeRepository;
import br.com.fiap69aoj.catalogo.dao.repository.SerieRepository;
import br.com.fiap69aoj.catalogo.events.CatalogoProducer;
import br.com.fiap69aoj.catalogo.exception.FilmeNaoEncontradoException;
import br.com.fiap69aoj.catalogo.exception.SerieNaoEncontradaException;
import br.com.fiap69aoj.catalogo.model.Filme;
import br.com.fiap69aoj.catalogo.model.FilmeRatting;
import br.com.fiap69aoj.catalogo.model.Serie;
import br.com.fiap69aoj.catalogo.model.SerieRatting;

@Service
@Transactional
public class CatalogoService {

	@Autowired
	private FilmeRepository filmeRepo;

	@Autowired
	private SerieRepository serieRepo;

	@Autowired
	private CatalogoProducer producer;

	public void inserirFilme(Filme filme) {
		filmeRepo.save(this.builFilmeEntityFromModel(filme));
	}

	public void inserirSerie(Serie serie) {
		serieRepo.save(this.buildSerieEntityFromModel(serie));
	}

	public List<String> obterFilmePorGenero(String genero) {
		List<FilmeEntity> filmesEntity = filmeRepo.findAllByGenero(genero);
		if (!filmesEntity.isEmpty()) {
			List<String> retorno = new ArrayList<String>();
			for (FilmeEntity filme : filmesEntity) {
				retorno.add(this.buildFilmeModelFromEntity(filme).getNome());
			}
			return retorno;
		}
		throw new FilmeNaoEncontradoException(genero);
	}

	public List<String> obterSeriePorGenero(String genero) {
		List<SerieEntity> seriesEntity = serieRepo.findAllByGenero(genero);
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

	public void registraDetlheFilme(Long idFilme, Long nota) {
		Optional<FilmeEntity> filmeEntity = filmeRepo.findById(idFilme);
		if (filmeEntity.isPresent()) {
			FilmeRatting ratting = new FilmeRatting();
			ratting.setGenero(filmeEntity.get().getGenero());
			ratting.setIdConteudo(filmeEntity.get().getId());
			ratting.setNota(nota);
			ratting.setTipoConteudo("FILME");
			producer.send(ratting.toJson());
		}
		throw new FilmeNaoEncontradoException("");
	}

	public void registraDetlheSerie(Long idSerie, Long nota) {
		Optional<SerieEntity> serieEntity = serieRepo.findById(idSerie);
		if (serieEntity.isPresent()) {
			SerieRatting ratting = new SerieRatting();
			ratting.setGenero(serieEntity.get().getGenero());
			ratting.setIdConteudo(serieEntity.get().getId());
			ratting.setNota(nota);
			ratting.setTipoConteudo("SERIE");
			producer.send(ratting.toJson());
		}
		throw new SerieNaoEncontradaException("");
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

}
