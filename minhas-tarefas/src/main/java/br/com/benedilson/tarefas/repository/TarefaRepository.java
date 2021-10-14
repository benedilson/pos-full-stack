package br.com.benedilson.tarefas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.benedilson.tarefas.model.Tarefa;

public interface TarefaRepository extends JpaRepository<Tarefa, Integer> {

}
