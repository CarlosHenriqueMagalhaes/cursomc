package com.udemy.curso.springboot.cursomc.resources.exception;

import java.util.ArrayList;
import java.util.List;

//Esse é uma classe auxiliar para o tratamento de exception referente 
//a validação @ExceptionHandler(MethodArgumentNotValidException)
public class ValidationError extends StandardError {
	private static final long serialVersionUID = 1L;

	private List<FieldMessage>errors = new ArrayList<>();
	
	//Construtores
	
	public ValidationError(Integer status, String msg, Long timeStamp) {
		super(status, msg, timeStamp);
	}

	//Acessores
	
	public List<FieldMessage> getErrors() {
		return errors;
	}

	//trocado setter:
	//foi trocado pois não queremos acrescentar uma lista inteira
	//de uma vez e sim um erro de cada vez
	public void addError(String fieldName, String messagem) {
		errors.add(new FieldMessage(fieldName,messagem));
	}
	
	
	
	
	

}
