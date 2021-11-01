package br.com.benedilson.tarefas.services;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.benedilson.tarefas.exception.TarefaStatusException;
import br.com.benedilson.tarefas.model.Tarefa;
import br.com.benedilson.tarefas.model.TarefaStatus;
import br.com.benedilson.tarefas.repository.TarefaRepository;

@ExtendWith(MockitoExtension.class)
public class TarefaServiceTest {

	@Mock
	private TarefaRepository repositorio;
	
	@InjectMocks //Indicando que ele injetou um mock dentro do service
	private TarefaService service;
	
	@Test
	void naoDeveConcluirTarefaCancelada() {
		
		Integer idExemplo = 1;
		
		Tarefa tarefa = new Tarefa();
		tarefa.setId(idExemplo);
		tarefa.setDescricao("Teste 01");
		tarefa.setStatus(TarefaStatus.CANCELADA);
		
		Mockito.when(repositorio.findById(idExemplo)).thenReturn(Optional.of(tarefa));
		
		Assertions.assertThrows(TarefaStatusException.class, () -> service.concluirTarefaPorId(idExemplo));
	}
	
	@Test
	void naoDeveCancelarTarefaConcluida() {
		
		Integer idExemplo = 1;
		
		Tarefa tarefa = new Tarefa();
		tarefa.setId(idExemplo);
		tarefa.setDescricao("Teste 01");
		tarefa.setStatus(TarefaStatus.CONCLUIDA);
		
		Mockito.when(repositorio.findById(idExemplo)).thenReturn(Optional.of(tarefa));
		
		Assertions.assertThrows(TarefaStatusException.class, () -> service.cancelarTarefaPorId(idExemplo));
	}
}
