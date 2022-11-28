package com.udemy.curso.springboot.cursomc.dto;

import java.io.Serializable;

import com.udemy.curso.springboot.cursomc.domain.Categoria;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

public class CategoriaDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;

	// @NotEmpty e @ Length são anotações de validação sintatica( foi necessário
	// adicionar dependencias no pom.xml
	@NotEmpty(message = "Preenchimento obrigatório")
	@Length(min = 5, max = 80, message = "O tamanho deve ser entre 5 e 80 caracteres")
	private String nome;

	// Construtores

	public CategoriaDTO() {
		super();
	}

	// Construtor recebe objeto correspondente ao da classe de dominio
	// Então esse contrutor vai instanciar a CategoriaDTO
	// a partir da Categoria
	public CategoriaDTO(Categoria obj) {
		super();
		id = obj.getId();
		nome = obj.getNome();

	}

	// Acessores

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
