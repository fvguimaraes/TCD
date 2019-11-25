package br.com.fiap69aoj.catalogo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap69aoj.catalogo.model.Filme;
import br.com.fiap69aoj.catalogo.model.Serie;
import br.com.fiap69aoj.catalogo.service.CatalogoService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/")
public class CatalogoController {

	@Autowired
	CatalogoService catalogoService;
	
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation(value = "Cadastrar um filme", notes = "Cadastrar um filme")
	@PutMapping("/filmes/")
	public ResponseEntity<HttpStatus> inserirFilme(@RequestBody Filme filme) {
		catalogoService.inserirFilme(filme);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation(value = "Cadastrar uma série", notes = "Cadastrar uma série")
	@PutMapping("/series/")
	public ResponseEntity<HttpStatus> inserirSerie(@RequestBody Serie serie) {
		catalogoService.inserirSerie(serie);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	
	@ApiOperation(value = "Obtem filme por determindo genero", notes = "Obter filme por determinado genero", response = Filme.class)
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Filmes listados com sucesso"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
		    @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
		    @ApiResponse(code = 404, message = "Nenhum filme encontrado") })
	@GetMapping("/filmes/")
	public ResponseEntity<List<Filme>> listarFilmesPorGenero(@RequestParam String genero) {
		return ResponseEntity.ok(catalogoService.obterFilmePorGenero(genero));
	}
	
	@ApiOperation(value = "Obtem serie por determindo genero", notes = "Obter serie por determinado genero", response = Serie.class)
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Series listadas com sucesso"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
		    @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
		    @ApiResponse(code = 404, message = "Nenhuma serie encontrado") })
	@GetMapping("/series/")
	public ResponseEntity<List<Serie>> listarSeriesPorGenero(@RequestParam String genero) {
		return ResponseEntity.ok(catalogoService.obterSeriePorGenero(genero));
	}
	
	@ApiOperation(value = "Obtem detalhe de um filme", notes = "Obtem detalhe de um filme", response = Filme.class)
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Filme encontrado com sucesso"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
		    @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
		    @ApiResponse(code = 404, message = "Nenhum filme encontrado") })
	@GetMapping("/filmes/{nomeFilme}/detalhes")
	public ResponseEntity<Filme> obterDetalheFilme(@PathVariable String nomeFilme) {
		return ResponseEntity.ok(catalogoService.obterDetalheFilme(nomeFilme));
	}
	
	@ApiOperation(value = "Obtem detalhe de uma serie", notes = "Obtem detalhe de uma serie", response = Serie.class)
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Serie encontrada com sucesso"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
		    @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
		    @ApiResponse(code = 404, message = "Nenhuma serie encontrada") })
	@GetMapping("/series/{nomeSerie}/detalhes")
	public ResponseEntity<Serie> obterDetalheSerie(@PathVariable String nomeSerie) {
		return ResponseEntity.ok(catalogoService.obterDetalheSerie(nomeSerie));
	}

}
