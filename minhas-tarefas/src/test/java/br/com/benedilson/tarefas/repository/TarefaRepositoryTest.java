package br.com.benedilson.tarefas.repository;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.benedilson.tarefas.model.Tarefa;

@SpringBootTest
public class TarefaRepositoryTest {

	@Autowired
	private TarefaRepository tarefaRepository;
	
	@Test
	void testFindByNomeCategoria() {
		List<Tarefa> tarefas = tarefaRepository.findByNomeCategoria("Estudos");
		Assertions.assertEquals(2, tarefas.size());
	}
	
}
