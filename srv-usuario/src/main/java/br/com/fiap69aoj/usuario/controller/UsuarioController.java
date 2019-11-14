package br.com.fiap69aoj.usuario.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap69aoj.usuario.model.Usuario;
import br.com.fiap69aoj.usuario.service.UsuarioService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController()
@RequestMapping("/")
public class UsuarioController {
		
	@Autowired
	private UsuarioService service;	
	
	@ApiOperation(value = "Obtem usuário por id de acesso", notes = "Obter usuário por id de acesso", response = Usuario.class)
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Usuário listado com sucesso"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
		    @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
		    @ApiResponse(code = 404, message = "Nenhum usuário encontrado") })
	@GetMapping("/usuarios/{idAcesso}")
	public ResponseEntity<Usuario> listarUsuarioPorId(@PathVariable String idAcesso) {
		return ResponseEntity.ok(service.obterDadosUsuarioPorId(idAcesso));
	}
	
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation(value = "Insere um usuário", notes = "Insere um usuário" )
	@ApiResponses({
        @ApiResponse(code = 201, message = "Inclusão com sucesso de um usuário.")
    })
	@PostMapping("/usuarios/")
	public ResponseEntity<HttpStatus> incluirUsuario(@RequestBody Usuario usuario) {
		service.incluirUsuario(usuario);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ApiOperation(value = "Remove um usuário", notes = "Remove um usuário" )
	@DeleteMapping("/usuarios/{idAcesso}")
	public ResponseEntity<HttpStatus> deleterUsuario(@PathVariable String idAcesso) {
		service.removerUsuario(idAcesso);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
	
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Atualiza um usuário", notes = "Atualiza um usuário" )
	@ApiResponses({
        @ApiResponse(code = 200, message = "Atualização com sucesso de um usuário.")
    })
	@PutMapping("/usuarios/")
	public ResponseEntity<HttpStatus> atualizarUsuario(@RequestBody Usuario usuario) {
		service.atualizarUsuario(usuario);
		return ResponseEntity.status(HttpStatus.OK).build();
	}
}
