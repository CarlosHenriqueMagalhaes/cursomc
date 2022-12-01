package com.udemy.curso.springboot.cursomc.domain;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.udemy.curso.springboot.cursomc.domain.enums.EstadoPagamento;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
//@JsonTypeInfo Checklist para permitir a instanciação de subclasses a partir de dados JSON:
// Na superclasse abstrata, defina que haverá um campo adicional @type:
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "@type")
//também devo adicionar anotação @JsonTypeName nas subclasses (PagamentoComBoleto e PagamentoComCartao)
public abstract class Pagamento implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Integer id;

	// quando é um para um o Id é o mesmo de pedido e pagamento (@OneToOne)
    //@JsonBackReference
	@JsonIgnore
	@OneToOne
	@JoinColumn (name = "pedido_id")
	@MapsId
	private Pedido pedido;
	
	private Integer estado;//Tratamento do ENUM

	// Construtores

	public Pagamento() {
		super();
	}

	public Pagamento(Integer id, EstadoPagamento estado, Pedido pedido) {
		super();
		this.id = id;
//		this.estado = estado.getCod();//Tratamento do ENUM
		//Para podermos utilizar um valor nulo, mudamos para o código:
		this.estado = (estado==null) ? null : estado.getCod();
		this.pedido = pedido;
	}
	
	//Associações

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	//Tratamento do ENUM
	public EstadoPagamento getEstado() {
		return EstadoPagamento.toEnum(estado);
	}

	//Tratamento do ENUM
	public void setEstado(EstadoPagamento estado) {
		this.estado = estado.getCod();
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	// as subclasses não precisam do hashCode equals, pois o id esta aqui!!
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
