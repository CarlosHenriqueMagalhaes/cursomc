package com.udemy.curso.springboot.cursomc.domain;

import java.io.Serializable;
import java.util.Objects;

public class Cidade implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;
	private String nome;

	// Construtores
	public Cidade() {
		super();
	}

	public Cidade(Integer id, String nome) {
		super();
		this.id = id;
		this.nome = nome;
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

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cidade other = (Cidade) obj;
		return Objects.equals(id, other.id);
	}

}
