package com.udemy.curso.springboot.cursomc.domain;

import javax.persistence.Entity;

import com.udemy.curso.springboot.cursomc.domain.enums.EstadoPagamento;

@Entity
public class PagamentoComCartao extends Pagamento {
	private static final long serialVersionUID = 1L;
	
	private Integer numeroDeParcelas;
	
	//Construtores

	public PagamentoComCartao() {
		super();
	}

	// Adicionamos os construtores da superclass e o construtor dessa classe no
	// mesmo corpo:
	public PagamentoComCartao(Integer id, EstadoPagamento estado, Pedido pedido,Integer numeroDeParcelas) {
		super(id, estado, pedido);
		this.numeroDeParcelas = numeroDeParcelas;
	}
	
	//Associações

	public Integer getNumeroDeParcelas() {
		return numeroDeParcelas;
	}

	public void setNumeroDeParcelas(Integer numeroDeParcelas) {
		this.numeroDeParcelas = numeroDeParcelas;
	}
	

}
