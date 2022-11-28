package com.udemy.curso.springboot.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.udemy.curso.springboot.cursomc.domain.Cliente;
import com.udemy.curso.springboot.cursomc.dto.ClienteDTO;
import com.udemy.curso.springboot.cursomc.repositories.ClienteRepository;
import com.udemy.curso.springboot.cursomc.services.exceptions.DataIntegrityException;
import com.udemy.curso.springboot.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;

	// buscar para o ID (GET do controller)
	public Cliente find(Integer id) {
		Optional<Cliente> obj = clienteRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));
		// Retorna mensagem/ lança uma exceção quando o Id não for existir,
		// foi criado uma classe para esse método: ObjectNotFoundException
		// Além disso temos que mudar na camada de recurso para ela receber essa
		// exceção, go to ClienteResource
	}

	// Método para alterar um objeto na Cliente, o PUT no EndPoint

	//Nesse caso vou instanciar um cliente a partir do banco de dados
	public Cliente update(Cliente obj) {
//		find(obj.getId());
		Cliente newObj = find(obj.getId());
		// chamo find porque ele ja busca o objeto no
		// banco e caso o Id não exista ele lança uma exceção
		// esse métod esta no GET acima, só aproveitei ele!
		//Preciso utilizar o método auxiliar abaixo para atualizar os dados
		//desse newObj com base no obj que veio como argumento
		updateData(newObj,obj);
		return clienteRepository.save(newObj);
	}
	
	//Método auxiliar PUT
	
	private void updateData (Cliente newObj, Cliente obj) {
		newObj.setNome(obj.getNome());
		newObj.setEmail(obj.getEmail());
	}

	// Método para deletar uma Cliente, o Delete no EndPoint

	public void delete(Integer id) {
		find(id);
		// Essa excecao (DataIntegrityViolationException) foi vista ao
		// relizar no postman um delete com uma categoria que ja possuia produtos
		// tratamos ela com o try catch!
		try {
			clienteRepository.deleteById(id);// o return comum
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir um cliente pois"
					+ " ele possui entidades relacionadas!");
			// vai lançar minha exception DataIntegrityException
			// (foi criado no pacote com.udemy.curso.springboot.cursomc.services.exceptions)
			// Feito isso temos que lançar na camada no ResourceExceptionHandler
		}

	}
	// buscar todas as Clientes(GET do controller)

	public List<Cliente> findAll() {
		return clienteRepository.findAll();
	}

	// Método paginação
	
	//Esse método é a listagem com paginação, o que acontece? Quando possuo uma
	//quantidade muito grande de registro, se eu usar o GET ele trará todos
	//os registros salvos! Assim terei um consumo grande de memória, processamento...
	//Assim fazemos a paginação para buscar de maneira controlada esses objetos
	//tipo de 20 em 20 , de 100 em 100, etc.

	//O Page ira encapsular informações e operações sobre a paginação
	public Page <Cliente> findPage (Integer page, Integer linesPerPage, String orderBy, String direction){
    PageRequest pageRequest = PageRequest.of (page, linesPerPage,Direction.valueOf(direction),orderBy);
    return clienteRepository.findAll(pageRequest);
}
	
	//Método para transformar Classe em DTO
	
	//Foi feito junto ao endpoint POST e PUT com DTO na classe ClienteResourse
	//A partir de um DTO vou construir um obj Cliente
	//Esse é um método auxiliar que instancia uma categoria a partir de
	//um DTO
	
	public Cliente fromDTO(ClienteDTO objDto) {
		return new Cliente(objDto.getId(),objDto.getNome(),objDto.getEmail(),null,null);
	}

}