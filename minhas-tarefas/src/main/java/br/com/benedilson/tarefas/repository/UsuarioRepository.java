package br.com.benedilson.tarefas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.benedilson.tarefas.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

}
