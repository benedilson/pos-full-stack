package br.com.benedilson.tarefas.config;

import java.time.LocalDate;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;

import br.com.benedilson.tarefas.model.ERole;
import br.com.benedilson.tarefas.model.Role;
import br.com.benedilson.tarefas.model.Tarefa;
import br.com.benedilson.tarefas.model.TarefaCategoria;
import br.com.benedilson.tarefas.model.TarefaStatus;
import br.com.benedilson.tarefas.model.Usuario;
import br.com.benedilson.tarefas.repository.RoleRepository;
import br.com.benedilson.tarefas.repository.TarefaCategoriaRepository;
import br.com.benedilson.tarefas.repository.TarefaRepository;
import br.com.benedilson.tarefas.repository.UsuarioRepository;

@Configuration
@Profile("dev")
public class CarregaBaseDeDados {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private TarefaCategoriaRepository tarefaCategoriaRepository;
	
	@Autowired
	private TarefaRepository tarefaRepository;
	
	@Autowired
	private PasswordEncoder encoder;
	
	@Bean
	CommandLineRunner executar() {
		return args -> {
			
			Role roleAdmin = new Role(ERole.ROLE_ADMIN);
			roleAdmin = roleRepository.save(roleAdmin);
			
			
			Usuario usuario = new Usuario();
			usuario.setNome("Admin");
			usuario.setSenha(encoder.encode("123456"));
			usuario.setRoles(Set.of(roleAdmin));
			
			usuarioRepository.save(usuario);
			
			TarefaCategoria tarefaCategoria = new TarefaCategoria();
			tarefaCategoria.setNome("Estudos");
			
			tarefaCategoriaRepository.save(tarefaCategoria);
			
			Tarefa tarefa = new Tarefa();
			tarefa.setDescricao("Aprender Spring Boot");
			tarefa.setDataEntrega(LocalDate.now().plusDays(1));
			tarefa.setStatus(TarefaStatus.ABERTO);
			tarefa.setVisivel(true);
			tarefa.setCategoria(tarefaCategoria);
			tarefa.setUsuario(usuario);
			
			tarefaRepository.save(tarefa);
		};
	}
}
