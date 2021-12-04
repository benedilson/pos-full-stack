package br.com.benedilson.tarefas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.benedilson.tarefas.controller.request.LoginRequest;
import br.com.benedilson.tarefas.controller.response.JwtResponse;
import br.com.benedilson.tarefas.services.UsuarioService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

	@Autowired
	private UsuarioService usuarioService;
	
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
		JwtResponse jwtResponse =  usuarioService
								   .autenticaUsuario(loginRequest.getNome(), loginRequest.getSenha());
		
		return ResponseEntity.ok(jwtResponse);
	}
	
}
