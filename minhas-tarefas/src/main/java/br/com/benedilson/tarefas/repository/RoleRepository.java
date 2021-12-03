package br.com.benedilson.tarefas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.benedilson.tarefas.model.ERole;
import br.com.benedilson.tarefas.model.Role;

public interface RoleRepository extends JpaRepository<Role, Integer>  {

	Role findByName(ERole name);

}
