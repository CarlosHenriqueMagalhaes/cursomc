package com.udemy.curso.springboot.cursomc.domain.enums;

//Essa implementação do tipo enumerado garante controle total do código atribuído a cada valor da enumeração
//No caso o da PESSOA FISICA e da PESSOAJURIDICA
public enum TipoCliente {

	PESSOAFISICA(1, "Pessoa Física"), PESSOAJURIDICA(2, "Pessoa Jurídica");

	private int cod;
	private String descricao;

	// Construtores

	private TipoCliente(int cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}

	// Acessores apenas get para ENUMS
	
	//nos Enuns não fazemos setters os valores são imutaveis
	public int getCod() {
		return cod;
	}

	public String getDescricao() {
		return descricao;
	}

	// Método

	public static TipoCliente toEnum(Integer cod) {

		if (cod == null) {
			return null;
		}

		for (TipoCliente x : TipoCliente.values()) {
			if (cod.equals(x.getCod())) {
				return x;
			}
		}
		throw new IllegalArgumentException("Id inválido " + cod);

	}

}
