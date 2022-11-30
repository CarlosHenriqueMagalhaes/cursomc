package com.udemy.curso.springboot.cursomc.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.udemy.curso.springboot.cursomc.domain.enums.TipoCliente;
import com.udemy.curso.springboot.cursomc.dto.ClienteNewDTO;
import com.udemy.curso.springboot.cursomc.resources.exception.FieldMessage;
import com.udemy.curso.springboot.cursomc.services.validation.utils.BR;

//Classe Validador da Anotação:

public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClienteNewDTO> {

	@Override
	public void initialize(ClienteInsert ann) {
		// nesse campo vazio posso adicionar alguma configuração de inicialização, nesse
		// caso não vai ser necessário
	}

	// isValid é um método da ConstraintValidator, que verifica se nosso tipo (o
	// ClienteNewDTO) no
	// caso se ele vai ser válido ou não!(ele retorna true ou false por isso ele é
	// um
	// boolean
	@Override
	public boolean isValid(ClienteNewDTO objDto, ConstraintValidatorContext context) {
		List<FieldMessage> list = new ArrayList<>();
		// Uma lista vazia é instanciada de objetos do tipo FieldMessage (verifique essa
		// classe)
		// que foi criada no resource exption para carregar o nome do campo e a mensagem
		// de erro
		// desse campo

		// inclua os testes aqui, inserindo erros na lista
		// validando apartir do código da classe BR:

		if (objDto.getTipo().equals(TipoCliente.PESSOAFISICA.getCod()) && !BR.isValidCPF(objDto.getCpfOuCnpj())) {
			list.add(new FieldMessage("cpfOuCnpj", "CPF inválido"));
		}
		if (objDto.getTipo().equals(TipoCliente.PESSOAJURIDICA.getCod()) && !BR.isValidCNPJ(objDto.getCpfOuCnpj())) {
			list.add(new FieldMessage("cpfOuCnpj", "CNPJ inválido"));
		}

		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
		// O método isValid retorna verdadeiro, porém se houver algum erro essa lista
		// não vai
		// estar vazia e o meu pétodo vai retornar Falso!
		// O for: é para percorrer minha lista de FieldMessage e para cada objeto na
		// minha lista,
		// eu vou adicionar um erro correspondente em minha lista de erros do framework
		// que
		// são os comandos context.disableDefaultConstraintViolation(); e
		// context.buildConstraintViolationWithTemplate, então esses dois comandos me
		// permite
		// transportar os meus erros personalizados para a lista de erros do framework
		// Essa lista do framework é tratada e mostrada na classe
		// ResourceExceptionHandler
		// no método MethodArgumentNotValidException

	}
}