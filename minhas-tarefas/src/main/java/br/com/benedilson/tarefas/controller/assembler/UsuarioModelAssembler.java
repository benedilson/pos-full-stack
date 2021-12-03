package br.com.benedilson.tarefas.controller.assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import br.com.benedilson.tarefas.controller.UsuarioController;
import br.com.benedilson.tarefas.controller.response.UsuarioResponse;
import br.com.benedilson.tarefas.model.Usuario;

@Component
public class UsuarioModelAssembler implements RepresentationModelAssembler<Usuario, EntityModel<UsuarioResponse>> {

	@Autowired
	private ModelMapper mapper;
	
	@Override
	public EntityModel<UsuarioResponse> toModel(Usuario usuario) {
		UsuarioResponse usuarioResponse = mapper.map(usuario, UsuarioResponse.class);
		
		EntityModel<UsuarioResponse> usuarioModel = EntityModel.of(usuarioResponse, 
				linkTo(methodOn(UsuarioController.class).umUsuario(usuarioResponse.getId())).withSelfRel());
		
		return usuarioModel;
	}

}
