package br.com.benedilson.tarefas.services;

import br.com.benedilson.tarefas.repository.TarefaCategoriaRepository;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.benedilson.tarefas.model.TarefaCategoria;

@Service
public class TarefaCategoriaService {

	@Autowired
	private TarefaCategoriaRepository tarefaCategoriaRepository;
	
	public List<TarefaCategoria> getTodasAsCategorias() {
		return tarefaCategoriaRepository.findAll();
	}
	
	public List<TarefaCategoria> getTarefaCategoriaPorNome(String nome) {
		return tarefaCategoriaRepository.findByNomeLike("%" + nome + "%");
	}
	
	public TarefaCategoria getTarefaCategoriaPorId(Integer id) {
		return tarefaCategoriaRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());
	}
	
	public TarefaCategoria salvarTarefaCategoria(TarefaCategoria tarefaCategoria) {
		return tarefaCategoriaRepository.save(tarefaCategoria);
	}
	
	public void deleteById(Integer id) {
		tarefaCategoriaRepository.deleteById(id);
	}
}
