package br.com.benedilson.tarefas.services;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.benedilson.tarefas.model.Usuario;
import br.com.benedilson.tarefas.repository.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	public Usuario getUsuarioPorId(Integer id) {
		return usuarioRepository.findById(id).orElseThrow(
				() -> new EntityNotFoundException()
				);
	}
	
}
