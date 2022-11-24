package com.udemy.curso.springboot.cursomc.domain.enums;

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
