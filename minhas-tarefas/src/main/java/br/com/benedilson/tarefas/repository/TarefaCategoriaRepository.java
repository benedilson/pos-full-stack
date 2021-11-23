package br.com.benedilson.tarefas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.benedilson.tarefas.model.TarefaCategoria;

public interface TarefaCategoriaRepository extends JpaRepository<TarefaCategoria, Integer> {

	public List<TarefaCategoria> findByNome(String nome);
	
	public List<TarefaCategoria> findByNomeLike(String nome);
	
}
