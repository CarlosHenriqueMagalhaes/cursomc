package com.udemy.curso.springboot.cursomc.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Produto implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String nome;

	private double preco;

	// do "outro lado" já busca os objetos, então eu não busco mais
//	@JsonBackReference
	@JsonIgnore
	@ManyToMany
	@JoinTable(name = "Produto_Categoria", joinColumns = @JoinColumn(name = "produto_id"), inverseJoinColumns = @JoinColumn(name = "categoria_id"))
	private List<Categoria> categorias = new ArrayList<>();
	//Explicando cada etapa do código acima:
	//@JoinTable(name = "Produto_Categoria",// define qual vai ser a tabela que vai fazer o muitos para muitos no banco
	// de dados relacional e o nome da terceira tabela)
	//joinColumns = @JoinColumn(name = "produto_id"), // nome do campo de chave estrangeira para código do produto
	// na "terceira"tabela
	//inverseJoinColumns = @JoinColumn(name = "categoria_id")) // nome do campo chave estrangeira para categoria na
	//"terceira tabela
	// não incluimos construtor para coleções ou seja categoria
	
	// O Set garante que a própria linguagem Java nos ajude de não ter
	// item repetido no mesmo pedido
	@JsonIgnore
	@OneToMany (mappedBy = "id.produto")
	private Set<ItemPedido> itens = new HashSet<>();
	
	//Metodos
	
	@JsonIgnore
	public List<Pedido> getPedidos(){
		List<Pedido>lista = new ArrayList<>();
		for (ItemPedido x : itens) {
			lista.add(x.getPedido());
		}
		return lista;
	}

	// Construtores

	public Produto() {
		super();
	}

	public Produto(Integer id, String nome, double preco) {
		super();
		this.id = id;
		this.nome = nome;
		this.preco = preco;
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

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}

	@JsonIgnore
	public List<Categoria> getCategorias() {
		return categorias;
	}

	public void setCategorias(List<Categoria> categorias) {
		this.categorias = categorias;
	}
	
	public Set<ItemPedido> getItens() {
		return itens;
	}

	public void setItens(Set<ItemPedido> itens) {
		this.itens = itens;
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
		Produto other = (Produto) obj;
		return Objects.equals(id, other.id);
	}

}
