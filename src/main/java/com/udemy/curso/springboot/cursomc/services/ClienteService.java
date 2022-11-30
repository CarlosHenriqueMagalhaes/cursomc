package com.udemy.curso.springboot.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.udemy.curso.springboot.cursomc.domain.Cidade;
import com.udemy.curso.springboot.cursomc.domain.Cliente;
import com.udemy.curso.springboot.cursomc.domain.Endereco;
import com.udemy.curso.springboot.cursomc.domain.enums.TipoCliente;
import com.udemy.curso.springboot.cursomc.dto.ClienteDTO;
import com.udemy.curso.springboot.cursomc.dto.ClienteNewDTO;
import com.udemy.curso.springboot.cursomc.repositories.ClienteRepository;
import com.udemy.curso.springboot.cursomc.repositories.EnderecoRepository;
import com.udemy.curso.springboot.cursomc.services.exceptions.DataIntegrityException;
import com.udemy.curso.springboot.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private EnderecoRepository enderecoRepository;

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

	// Método para salvar um novo objeto na Categoria, o POST no EndPoint.

	// O @ Transactional é para garantir que sera salvo tanto o cliente quanto os
	// endereços na mesma transação do banco de dados
	@Transactional
	public Cliente insert(Cliente obj) {
		obj.setId(null);
		// é null pois ele vai "buscar o primeiro id nulo para setar
		// sem o null vai ser considerado uma atualização e não uma inserção
		obj = clienteRepository.save(obj);
		enderecoRepository.saveAll(obj.getEnderecos());
		return obj;
	}

	// Método para alterar um objeto na Cliente, o PUT no EndPoint

	// Nesse caso vou instanciar um cliente a partir do banco de dados
	public Cliente update(Cliente obj) {
//		find(obj.getId());
		Cliente newObj = find(obj.getId());
		// chamo find porque ele ja busca o objeto no
		// banco e caso o Id não exista ele lança uma exceção
		// esse métod esta no GET acima, só aproveitei ele!
		// Preciso utilizar o método auxiliar abaixo para atualizar os dados
		// desse newObj com base no obj que veio como argumento
		updateData(newObj, obj);
		return clienteRepository.save(newObj);
	}

	// Método auxiliar PUT

	private void updateData(Cliente newObj, Cliente obj) {
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
			throw new DataIntegrityException(
					"Não é possível excluir um cliente pois ele possui pedidos relacionadas!");
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

	// Esse método é a listagem com paginação, o que acontece? Quando possuo uma
	// quantidade muito grande de registro, se eu usar o GET ele trará todos
	// os registros salvos! Assim terei um consumo grande de memória,
	// processamento...
	// Assim fazemos a paginação para buscar de maneira controlada esses objetos
	// tipo de 20 em 20 , de 100 em 100, etc.

	// O Page ira encapsular informações e operações sobre a paginação
	public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return clienteRepository.findAll(pageRequest);
	}

	// Método para transformar Classe em DTO

	// Foi feito junto ao endpoint POST e PUT com DTO na classe ClienteResourse
	// A partir de um DTO vou construir um obj Cliente
	// Esse é um método auxiliar que instancia uma categoria a partir de
	// um DTO

	public Cliente fromDTO(ClienteDTO objDto) {
		return new Cliente(objDto.getId(), objDto.getNome(), objDto.getEmail(), null, null);
	}

	// Sobrecarga do médoto acima para ClienteNewDTO

	public Cliente fromDTO(ClienteNewDTO objDto) {
		Cliente cli = new Cliente(null, objDto.getNome(), objDto.getEmail(), objDto.getCpfOuCnpj(),
				TipoCliente.toEnum(objDto.getTipo()));
		Cidade cid = new Cidade(objDto.getCidadeId(), null, null);
		Endereco end = new Endereco(null, objDto.getLogradouro(), objDto.getNumero(), objDto.getComplemento(),
				objDto.getBairro(), objDto.getCep(), cli, // endereço já conhece o cliente por aqui!
				cid);
		cli.getEnderecos().add(end);// O cliente conheceu os endereços dele
		cli.getTelefones().add(objDto.getTelefone1());
		if (objDto.getTelefone2() != null) {
			cli.getTelefones().add(objDto.getTelefone2());
		}
		if (objDto.getTelefone3() != null) {
			cli.getTelefones().add(objDto.getTelefone3());
		}
		return cli;
		// Sobre os Ifs: se adicionarmos um numero de telefone a mais (1 e 2), ele
		// cai no método e insere o valor selecionado, se não ele
		// permanece null pois ele não é obrigatório

	}
}