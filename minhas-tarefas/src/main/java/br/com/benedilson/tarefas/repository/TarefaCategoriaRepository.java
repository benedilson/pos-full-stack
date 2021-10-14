package br.com.benedilson.tarefas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.benedilson.tarefas.model.TarefaCategoria;

public interface TarefaCategoriaRepository extends JpaRepository<TarefaCategoria, Integer> {

}
