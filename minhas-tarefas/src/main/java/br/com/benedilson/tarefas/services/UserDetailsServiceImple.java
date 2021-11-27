package br.com.benedilson.tarefas.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.benedilson.tarefas.model.Usuario;
import br.com.benedilson.tarefas.repository.UsuarioRepository;
import br.com.benedilson.tarefas.security.UserDetailsImpl;

@Service
public class UserDetailsServiceImple implements UserDetailsService {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario usuario = usuarioRepository.findByNome(username)
				.orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));
		return UserDetailsImpl.build(usuario);
	}

}