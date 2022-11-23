package com.udemy.curso.springboot.cursomc.domain;

import java.util.Date;
import java.util.Objects;

import javax.persistence.Entity;


public class Pedido {

	private Integer id;
	private Date instante;

	// Construtores

	public Pedido() {
		super();
	}

	public Pedido(Integer id, Date instante) {
		super();
		this.id = id;
		this.instante = instante;
	}

	// Acessores

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getInstante() {
		return instante;
	}

	public void setInstante(Date instante) {
		this.instante = instante;
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
		Pedido other = (Pedido) obj;
		return Objects.equals(id, other.id);
	}

}
