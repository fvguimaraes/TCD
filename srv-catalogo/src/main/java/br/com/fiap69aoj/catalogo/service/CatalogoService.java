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
import br.com.fiap69aoj.catalogo.exception.FilmeNaoEncontradoException;
import br.com.fiap69aoj.catalogo.exception.SerieNaoEncontradaException;
import br.com.fiap69aoj.catalogo.model.Filme;
import br.com.fiap69aoj.catalogo.model.Serie;

@Service
@Transactional
public class CatalogoService {

	@Autowired
	private FilmeRepository filmeRepo;

	@Autowired
	private SerieRepository serieRepo;

	public void inserirFilme(Filme filme) {
		filmeRepo.save(this.builFilmeEntityFromModel(filme));
	}

	public void inserirSerie(Serie serie) {
		serieRepo.save(this.buildSerieEntityFromModel(serie));
	}

	public List<Filme> obterFilmePorGenero(String genero) {
		List<FilmeEntity> filmesEntity = filmeRepo.findAllByGenero(genero);
		if (!filmesEntity.isEmpty()) {
			List<Filme> retorno = new ArrayList<Filme>();
			for (FilmeEntity filme : filmesEntity) {
				retorno.add(this.buildFilmeModelFromEntity(filme));
			}
			return retorno;
		}
		throw new FilmeNaoEncontradoException(genero);
	}

	public List<Serie> obterSeriePorGenero(String genero) {
		List<SerieEntity> seriesEntity = serieRepo.findAllByGenero(genero);
		if (!seriesEntity.isEmpty()) {
			List<Serie> retorno = new ArrayList<Serie>();
			for (SerieEntity serie : seriesEntity) {
				retorno.add(this.buildSerieModelFromEntity(serie));
			}
			return retorno;
		}
		throw new SerieNaoEncontradaException(genero);
	}

	public Filme obterDetalheFilme(String nomeFilme) {
		Optional<FilmeEntity> filme = filmeRepo.findByNome(nomeFilme);
		if (filme.isPresent()) {
			return this.buildFilmeModelFromEntity(filme.get());
		}
		throw new FilmeNaoEncontradoException(nomeFilme);
	}


	public Serie obterDetalheSerie(String nomeSerie) {
		Optional<SerieEntity> serie = serieRepo.findByNome(nomeSerie);
		if(serie.isPresent()) {
			return this.buildSerieModelFromEntity(serie.get());
		}
		throw new SerieNaoEncontradaException(nomeSerie);
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
