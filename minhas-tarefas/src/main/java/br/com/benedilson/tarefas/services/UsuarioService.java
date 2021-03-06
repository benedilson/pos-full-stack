package br.com.benedilson.tarefas.services;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.benedilson.tarefas.controller.response.JwtResponse;
import br.com.benedilson.tarefas.model.Role;
import br.com.benedilson.tarefas.model.Usuario;
import br.com.benedilson.tarefas.repository.RoleRepository;
import br.com.benedilson.tarefas.repository.UsuarioRepository;
import br.com.benedilson.tarefas.security.JwtUtils;
import br.com.benedilson.tarefas.security.UserDetailsImpl;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.List;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	JwtUtils jwtUtils;
	
	public Usuario getUsuarioPorId(Integer id) {
		return usuarioRepository.findById(id).orElseThrow(
				() -> new EntityNotFoundException()
				);
	}

	public Usuario salvar(Usuario usuario) {
		Set<Role> roles = getRoles(usuario);
		usuario.setRoles(roles);
		usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
		return usuarioRepository.save(usuario);
	}
	
	public Usuario atualizar(Integer id, Usuario usuario) {
		Optional<Usuario> usuarioBanco = usuarioRepository.findById(id);
		
		if(!usuarioBanco.isPresent())
			throw new EntityNotFoundException();
		
		usuario.setId(id);
		return salvar(usuario);
	}
	
	public void deleteById(Integer id) {
		usuarioRepository.deleteById(id);
	}
	
	
	private Set<Role> getRoles(Usuario usuario) {
		Set<Role> rolesBanco = usuario.getRoles().stream().map(role -> 
			roleRepository.findByName(role.getName())
				).collect(Collectors.toSet());
		
		return rolesBanco;
	}

	public JwtResponse autenticaUsuario(String nome, String senha) {
		Authentication authentication = authenticationManager
		.authenticate(new UsernamePasswordAuthenticationToken(nome, senha));
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		String jwt = jwtUtils.generateJwtToken(authentication);
		
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
		
		List<String> roles = userDetails.getAuthorities().stream()
				.map(item -> item.getAuthority())
				.collect(Collectors.toList());
		
		return new JwtResponse(jwt, userDetails.getId(), userDetails.getUsername(), roles);
	}
	
}
