package com.udemy.curso.springboot.cursomc.services.exceptions;

//Classe foi criada com a necessidade de uma exception no CategoriaService no método de DELETE
public class DataIntegrityException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public DataIntegrityException (String msg) {
		super (msg);
	}
	
	public DataIntegrityException (String msg, Throwable cause) {
		super (msg, cause);
	}
}
