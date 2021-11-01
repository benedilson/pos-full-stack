package br.com.benedilson.tarefas.services;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.benedilson.tarefas.exception.TarefaStatusException;
import br.com.benedilson.tarefas.model.Tarefa;
import br.com.benedilson.tarefas.model.TarefaStatus;
import br.com.benedilson.tarefas.repository.TarefaRepository;

@Service
public class TarefaService {
	@Autowired
	private TarefaRepository tarefaRepository;
	
	public List<Tarefa> getTodasTarefas() {
		return tarefaRepository.findAll();
	}
	
	public List<Tarefa> getTarefasPorDescricao(String descricao) {
		return tarefaRepository.findByDescricaoLike("%" + descricao + "%");
	}
	
	public Tarefa getTarefaPorId(Integer id) {
		return tarefaRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());
	}
	
	public Tarefa salvarTarefa(Tarefa tarefa) {
		return tarefaRepository.save(tarefa);
	}
	
	public void deleteById(Integer id) {
		tarefaRepository.deleteById(id);
	}
	
	public Tarefa iniciarTarefaPorId(Integer id) {
		Tarefa tarefa = getTarefaPorId(id);
		
		if(!TarefaStatus.ABERTO.equals(tarefa.getStatus())) 
			throw new TarefaStatusException();
		
		tarefa.setStatus(TarefaStatus.EM_ANDAMENTO);
		tarefaRepository.save(tarefa);
		return tarefa;
	}
	
	public Tarefa concluirTarefaPorId(Integer id) {
		Tarefa tarefa = getTarefaPorId(id);
		
		if(TarefaStatus.CANCELADA.equals(tarefa.getStatus())) 
			throw new TarefaStatusException();
		
		tarefa.setStatus(TarefaStatus.CONCLUIDA);
		tarefaRepository.save(tarefa);
		return tarefa;
	}
	
	public Tarefa cancelarTarefaPorId(Integer id) {
		Tarefa tarefa = getTarefaPorId(id);
		
		if(TarefaStatus.CONCLUIDA.equals(tarefa.getStatus())) 
			throw new TarefaStatusException();
		
		tarefa.setStatus(TarefaStatus.CANCELADA);
		tarefaRepository.save(tarefa);
		return tarefa;
	}
}
