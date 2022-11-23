package com.udemy.curso.springboot.cursomc.domain;

import java.util.Objects;

import javax.persistence.Entity;


public class Pagamento {

	private Integer id;
	// private EstadoPagamento estado (enum)

	// Construtores

	public Pagamento() {

	}

	public Pagamento(Integer id) {
		super();
		this.id = id;
	}

	// Associações

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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
		Pagamento other = (Pagamento) obj;
		return Objects.equals(id, other.id);
	}

}
