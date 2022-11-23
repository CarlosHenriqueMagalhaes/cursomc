package com.udemy.curso.springboot.cursomc.domain;

import java.util.Date;

import javax.persistence.Entity;


public class PagamentoComBoleto extends Pagamento{
	
	private Date dataVencimento;
	private Date dataPagamento;
	
	//Construtores
	public PagamentoComBoleto() {
		super();
	}
	
	
	

}
