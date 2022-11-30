package com.udemy.curso.springboot.cursomc.services.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

	//Classe Anotação:

	//Essa classe é para uso de anotação para ClienteNewDTO para
	//validação do atributo cpfOuCnpj
    //se fosse um campo especifico apenas para CPF ou CNPJ, bastaria
	//adicionar a anotação @CPF e/ou @CNPJ para validar, porém cpfOuCnpj
	//depende do campo tipo(ENUM)! assim criamos essa classe para anotação 

@Constraint(validatedBy = ClienteInsertValidator.class)
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface ClienteInsert {
	String message() default "Erro de validação";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}