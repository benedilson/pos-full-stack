package br.com.benedilson.tarefas.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import br.com.benedilson.tarefas.controller.response.UsuarioResponse;
import br.com.benedilson.tarefas.model.Usuario;
import br.com.benedilson.tarefas.services.UsuarioService;

public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private ModelMapper mapper;
	
	@GetMapping("/{id}")
	public UsuarioResponse umUsuario(@PathVariable Integer id) {
		Usuario usuario = usuarioService.getUsuarioPorId(id);
		UsuarioResponse usuarioResponse = mapper.map(usuario, UsuarioResponse.class);
		return usuarioResponse;
	}
	
}