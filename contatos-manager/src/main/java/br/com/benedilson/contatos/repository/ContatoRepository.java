package br.com.benedilson.contatos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.benedilson.contatos.model.Contato;

public interface ContatoRepository extends JpaRepository<Contato, Integer> {

}
