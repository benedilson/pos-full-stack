package br.com.benedilson.tarefas.controller.assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.HashMap;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import br.com.benedilson.tarefas.controller.TarefaCategoriaController;
import br.com.benedilson.tarefas.controller.TarefaController;
import br.com.benedilson.tarefas.controller.UsuarioController;
import br.com.benedilson.tarefas.controller.response.TarefaResponse;
import br.com.benedilson.tarefas.model.Tarefa;
import br.com.benedilson.tarefas.model.TarefaStatus;

@Component
public class TarefaModelAssembler implements RepresentationModelAssembler<Tarefa, EntityModel<TarefaResponse>> {

	@Autowired
	private ModelMapper mapper;
	
	@Override
	public EntityModel<TarefaResponse> toModel(Tarefa tarefa) {
		TarefaResponse tarefaResponse = mapper.map(tarefa, TarefaResponse.class);
		
		EntityModel<TarefaResponse> tarefaModel = EntityModel.of(tarefaResponse, 
				linkTo(methodOn(TarefaController.class).umaTarefa(tarefaResponse.getId())).withSelfRel(),
				linkTo(methodOn(TarefaController.class).todasTarefas(new HashMap<>())).withRel("tarefas"),
				linkTo(methodOn(TarefaCategoriaController.class).umaCategoria(tarefaResponse.getCategoriaId())).withRel("categoria"),
				linkTo(methodOn(UsuarioController.class).umUsuario(tarefaResponse.getUsuarioId())).withRel("usuario"));
		
		if(TarefaStatus.EM_ANDAMENTO.equals(tarefa.getStatus())) {
			tarefaModel.add(
				linkTo(methodOn(TarefaController.class).iniciarTarefa(tarefa.getId())).withRel("concluir"),
				linkTo(methodOn(TarefaController.class).cancelarTarefa(tarefa.getId())).withRel("cancelar"));
		}
		
		if(TarefaStatus.ABERTO.equals(tarefa.getStatus())) {
			tarefaModel.add(
					linkTo(methodOn(TarefaController.class).iniciarTarefa(tarefa.getId())).withRel("iniciar"));
		}
		
		return tarefaModel;
	}

}
