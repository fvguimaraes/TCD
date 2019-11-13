package br.com.fiap69aoj.chamados.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap69aoj.chamados.model.Chamado;
import br.com.fiap69aoj.chamados.service.ChamadoService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
public class ChamadosController {
	
	@Autowired
	private ChamadoService chamadoService; 

	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation(value = "Insere um chamado", notes = "Insere um chamado" )
	@ApiResponses({
        @ApiResponse(code = 201, message = "Inclusão com sucesso de um chamado.")
    })
	@PostMapping("/chamados/")
	public ResponseEntity<HttpStatus> incluirChamado(@RequestBody Chamado dadosChamado) {
		chamadoService.incluirChamado(dadosChamado);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	
	@ApiOperation(value = "Lista de todos os chamados", notes = "Lista todos os chamados", response = Chamado.class, responseContainer = "List" )
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Chamados listados com sucesso"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
		    @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
		    @ApiResponse(code = 404, message = "Nenhum chamado encontrado") })
	@GetMapping("/chamados/")
	public ResponseEntity<List<Chamado>> listarChamados() {
		return ResponseEntity.ok(chamadoService.listarChamados());
	}
	
	@ApiOperation(value = "Obtem chamado por ids", notes = "Obter chamado por id", response = Chamado.class)
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Chamado listado com sucesso"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
		    @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
		    @ApiResponse(code = 404, message = "Nenhum chamado encontrado") })
	@GetMapping("/chamados/{id}")
	public ResponseEntity<Chamado> listarChamadoPorId(@PathVariable long id) {
		return ResponseEntity.ok(chamadoService.listarChamadosPorId(id));
	}

	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ApiOperation(value = "Remove um chamado", notes = "Remove um chamado" )
	@DeleteMapping("/chamados/{id}")
	public ResponseEntity<HttpStatus> deleterUsuario(@PathVariable long id) {
		chamadoService.removerChamadosPorId(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
	
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Atualiza um chamado", notes = "Atualiza um chamado" )
	@ApiResponses({
        @ApiResponse(code = 200, message = "Atualização com sucesso de um chamado.")
    })
	@PutMapping("/chamados/")
	public ResponseEntity<HttpStatus> atualizarUsuario(@RequestBody Chamado chamado) {
		chamadoService.atualizarChamadoChamado(chamado);
		return ResponseEntity.status(HttpStatus.OK).build();
	}
}
