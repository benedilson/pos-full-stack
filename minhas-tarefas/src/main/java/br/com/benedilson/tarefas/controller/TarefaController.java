package br.com.benedilson.tarefas.controller;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.benedilson.tarefas.model.Tarefa;
import br.com.benedilson.tarefas.services.TarefaService;

@RestController
public class TarefaController {

	@Autowired
	private TarefaService tarefaService;
	
	@GetMapping("/tarefa")
	public List<Tarefa> todasTarefas(@RequestParam Map<String, String> parametros) {
		if(parametros.isEmpty())
		   return tarefaService.getTodasTarefas();
		
		String descricao = parametros.get("descricao");
		return tarefaService.getTarefasPorDescricao(descricao);
	}
	
	@GetMapping("/tarefa/{id}")
	public Tarefa umaTarefa(@PathVariable Integer id) {
		return tarefaService.getTarefaPorId(id);
	}
	
	@PostMapping("/tarefa")
	public Tarefa salvarTarefa(@Valid @RequestBody Tarefa tarefa) {
		return tarefaService.salvarTarefa(tarefa);
	}
	
	@DeleteMapping("/tarefa/{id}")
	public void excluirTarefa(@PathVariable Integer id) {
		tarefaService.deleteById(id);
	}
}
