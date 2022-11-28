package com.udemy.curso.springboot.cursomc.resources.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.udemy.curso.springboot.cursomc.services.exceptions.DataIntegrityException;
import com.udemy.curso.springboot.cursomc.services.exceptions.ObjectNotFoundException;

//Tratamento de exception
@ControllerAdvice
public class ResourceExceptionHandler {

	//Para objeto não emcontrado:
	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException e, HttpServletRequest request) {
		StandardError err = new StandardError(HttpStatus.NOT_FOUND.value(), e.getMessage(), System.currentTimeMillis());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
	}

	//Para o uso no método DELETE (presente na CategoriaService e CategoriaResource):
	@ExceptionHandler(DataIntegrityException.class)
	public ResponseEntity<StandardError> dataIntegrity(DataIntegrityException e, HttpServletRequest request) {
		StandardError err = new StandardError(HttpStatus.BAD_REQUEST.value(), e.getMessage(), System.currentTimeMillis());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
	}

	//Para exception ao dar erro de validação 
	//Fiz as classes auxiliares FieldMessage e ValidationError
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<StandardError> validation(MethodArgumentNotValidException e, HttpServletRequest request) {
		ValidationError err = new ValidationError(HttpStatus.BAD_REQUEST.value(), "Erro de validação", System.currentTimeMillis());
		for(FieldError x : e.getBindingResult().getFieldErrors()) {
			err.addError(x.getField(),x.getDefaultMessage());
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
		
		//for aqui: percorre a lista de erros que ja tem na exceção MethodArgumentNotValidException
		//chamada "e" e para cada erro na listinha de erros dessa exceção MethodArgumentNotValidException
		//ela gera o objeto FieldMessage ( e.getBindingResult e getFieldErrors) com isso acessamos
		//todos erros de campos que aconteceram nessa exceção
		//para cada fieldError x nessa lista eu vou fazer o objeto pegar o nome do campo
		//e depois a mensagem (err.addError(x.getField(),x.getDefaultMessage());)
	}
	
}

