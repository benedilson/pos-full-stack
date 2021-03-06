package br.com.benedilson.tarefas.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.benedilson.tarefas.model.TarefaCategoria;
import br.com.benedilson.tarefas.services.TarefaCategoriaService;

@RestController
@RequestMapping("/categoria")
public class TarefaCategoriaController {

	@Autowired
	private TarefaCategoriaService tarefaCategoriaService;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@GetMapping
	public List<TarefaCategoria> todasCategorias() {
		List<TarefaCategoria> categorias = tarefaCategoriaService.getTodasAsCategorias();
		return categorias
				.stream()
				.map(categoria -> modelMapper.map(categoria, TarefaCategoria.class))
				.collect(Collectors.toList());
	}
	
	@GetMapping("/{id}")
	public TarefaCategoria umaCategoria(@PathVariable Integer id) {
		return modelMapper.map(
				tarefaCategoriaService.getTarefaCategoriaPorId(id), 
				TarefaCategoria.class);
	}
	
	@PostMapping
	public TarefaCategoria salvarCategoria(@RequestBody TarefaCategoria tarefaCategoria) {
		return tarefaCategoriaService.salvarTarefaCategoria(tarefaCategoria);
	}
	
	@DeleteMapping("/{id}")
	public void excluirCategoria(@PathVariable Integer id) {
		tarefaCategoriaService.deleteById(id);
	}
}
