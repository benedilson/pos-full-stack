package br.com.benedilson.tarefas.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Tarefa {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Column(name = "ds_tarefa", nullable = false, length = 150)
	private String descricao;
	
	@Enumerated(EnumType.STRING)
	private TarefaStatus status;
	
	private LocalDate dataEntrega;
    
	private boolean visivel;
    
	@ManyToOne
	@JoinColumn(nullable = false)
	private TarefaCategoria categoria;
    
	@ManyToOne
	@JoinColumn(nullable = false)
	private Usuario usuario;
    
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public TarefaCategoria getCategoria() {
		return categoria;
	}
	public void setCategoria(TarefaCategoria categoria) {
		this.categoria = categoria;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public TarefaStatus getStatus() {
		return status;
	}
	public void setStatus(TarefaStatus status) {
		this.status = status;
	}
	public LocalDate getDataEntrega() {
		return dataEntrega;
	}
	public void setDataEntrega(LocalDate dataEntrega) {
		this.dataEntrega = dataEntrega;
	}
	public boolean isVisivel() {
		return visivel;
	}
	public void setVisivel(boolean visivel) {
		this.visivel = visivel;
	}
    
    
}
