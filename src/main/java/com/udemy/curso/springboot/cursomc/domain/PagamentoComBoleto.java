package com.udemy.curso.springboot.cursomc.domain;

import java.util.Date;

import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.udemy.curso.springboot.cursomc.domain.enums.EstadoPagamento;


@Entity
//@JsonTypeInfo Checklist para permitir a instanciação de subclasses a partir de dados JSON:
//Vide classe Pagamento
@JsonTypeName("pagamentoComBoleto")
public class PagamentoComBoleto extends Pagamento {
	private static final long serialVersionUID = 1L;
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date dataVencimento;
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date dataPagamento;

	// Construtores

	public PagamentoComBoleto() {
		super();
	}

	// Adicionamos os construtores da superclass
	public PagamentoComBoleto(Integer id, EstadoPagamento estado, Pedido pedido, Date dataVencimento,
			Date dataPagamento) {
		super(id, estado, pedido);
		this.dataVencimento = dataVencimento;
		this.dataPagamento = dataPagamento;
	}
	
	//Associações

	public Date getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public Date getDataPagamento() {
		return dataPagamento;
	}

	public void setDataPagamento(Date dataPagamento) {
		this.dataPagamento = dataPagamento;
	}

}
