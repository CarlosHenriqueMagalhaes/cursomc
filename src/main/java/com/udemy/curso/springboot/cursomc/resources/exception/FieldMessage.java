package com.udemy.curso.springboot.cursomc.resources.exception;

import java.io.Serializable;

//Esse é um método/classe auxiliar para o tratamento de exception referente 
//a validação @ExceptionHandler(MethodArgumentNotValidException)
public class FieldMessage implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String fieldName;
	private String message;
	
	//Construtores
	
	public FieldMessage() {
		super();
	}
	public FieldMessage(String fieldName, String message) {
		super();
		this.fieldName = fieldName;
		this.message = message;
	}
	
	//Acessores
	
	public String getFieldName() {
		return fieldName;
	}
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	

}
