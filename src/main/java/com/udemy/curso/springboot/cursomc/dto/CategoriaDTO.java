package com.udemy.curso.springboot.cursomc.dto;

import java.io.Serializable;

import com.udemy.curso.springboot.cursomc.domain.Categoria;

public class CategoriaDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String nome;
	
	//Construtores
	
	public CategoriaDTO() {
		super();
	}
	
	//Construtor recebe objeto correspondente ao da classe de dominio
	//Então esse contrutor vai instanciar a CategoriaDTO
	//a partir da Categoria
	public CategoriaDTO(Categoria obj) {
		super();
		id = obj.getId();
		nome = obj.getNome();
		
	}
	
	
	
	//Acessores

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	
	
	

}
