package br.com.fiap69aoj.estatisticas.controller;

import br.com.fiap69aoj.estatisticas.model.Conteudo;
import br.com.fiap69aoj.estatisticas.service.EstatisticasService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
public class EstatisticasController {
	
	@Autowired
	private EstatisticasService service;

	@ResponseStatus(HttpStatus.ACCEPTED)
	@ApiOperation(value = "Consulta os itens assistidos do catalogo")
	@ApiResponses({
			@ApiResponse(code = 200, message = "Itens assitidos.")
	})
	@GetMapping("/assistidos/{catalogo}/{genero}")
	public ResponseEntity<List<Conteudo>> getAssistidos(@PathVariable String catalogo, @PathVariable String genero, @RequestParam String order){
		return ResponseEntity.ok(this.service.assistidos(catalogo, genero, order));
	}

	@ResponseStatus(HttpStatus.ACCEPTED)
	@ApiOperation(value = "Consulta os itens classificados do catalogo")
	@ApiResponses({
			@ApiResponse(code = 200, message = "Estatisiticas dos itens.")
	})
	@GetMapping("/classificacoes/{catalogo}/{genero}")
	public ResponseEntity getClassificacoes(@PathVariable String catalogo, @PathVariable String genero, @RequestParam String order, @RequestParam String usuario){
		return ResponseEntity.ok(this.service.classificacoesPorUsuario(catalogo,genero,order,usuario));
	}
}
