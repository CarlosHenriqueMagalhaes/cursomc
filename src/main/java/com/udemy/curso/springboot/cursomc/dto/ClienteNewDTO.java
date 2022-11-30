package com.udemy.curso.springboot.cursomc.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.udemy.curso.springboot.cursomc.services.validation.ClienteInsert;

// Nesse DTO inserimos um novo cliente (que deve ter um telefone e
//um endereço e no endereço uma cidade (consulte o diagrama), assim
//o sistema vai cadastrar o Cliente, o Endereco (com a Cidade) e o Telefone tudo junto!
//Assim esse é um DTO especifico para enviar todos esses dados

//anotação @ClienteInsert para o tipo CPF ou CNPJ vide abaixo
@ClienteInsert
public class ClienteNewDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	// Dados do Cliente:

	@NotEmpty (message = "Preenchimento obrigatório")
	@Length (min = 5, max = 120, message = "O tamanho deve ser entre 5 e 120 caracteres")
	private String nome;
	
	@NotEmpty (message = "Preenchimento obrigatório")
	@Email (message = "Email inválido")
	private String email;
	
	@NotEmpty (message = "Preenchimento obrigatório")
	//se fosse um campo especifico apenas para CPF ou CNPJ, bastaria
	//adicionar a anotação @CPF e/ou @CNPJ para validar, porém esse
	//campo depende do campo tipo! assim criamos uma anotação e os 
	//métodos, presentes nas classes ClienteInsert, ClienteInsertValidator e BR
	private String cpfOuCnpj;
	
	private Integer tipo;
	// para o caso do ENUM vide construtores e acessores

	// Dados do Endereco:

	@NotEmpty (message = "Preenchimento obrigatório")
	private String logradouro;
	
	@NotEmpty (message = "Preenchimento obrigatório")
	private String numero;
	
	private String complemento;
	
	private String bairro;
	
	@NotEmpty (message = "Preenchimento obrigatório")
	private String cep;

	// Para o Telefone:

	@NotEmpty (message = "Preenchimento obrigatório")
	private String telefone1;
	
	private String telefone2;
	
	private String telefone3;
	// o telefone2 e 3 são opcionais

	// Para a Cidade (Pois ela esta atrelada ao Endereco):

	private Integer cidadeId;
	// Estou adicionando somente o númeri inteiro! O Id para ficar mais simples

	// Construtores

	public ClienteNewDTO() {
		super();
	}

	// Acessores

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCpfOuCnpj() {
		return cpfOuCnpj;
	}

	public void setCpfOuCnpj(String cpfOuCnpj) {
		this.cpfOuCnpj = cpfOuCnpj;
	}

	public Integer getTipo() {
		return tipo;
	}

	public void setTipo(Integer tipo) {
		this.tipo = tipo;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getTelefone1() {
		return telefone1;
	}

	public void setTelefone1(String telefone1) {
		this.telefone1 = telefone1;
	}

	public String getTelefone2() {
		return telefone2;
	}

	public void setTelefone2(String telefone2) {
		this.telefone2 = telefone2;
	}

	public String getTelefone3() {
		return telefone3;
	}

	public void setTelefone3(String telefone3) {
		this.telefone3 = telefone3;
	}

	public Integer getCidadeId() {
		return cidadeId;
	}

	public void setCidadeId(Integer cidadeId) {
		this.cidadeId = cidadeId;
	}

}
