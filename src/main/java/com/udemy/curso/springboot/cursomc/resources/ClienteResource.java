package com.udemy.curso.springboot.cursomc.resources;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.udemy.curso.springboot.cursomc.domain.Cliente;
import com.udemy.curso.springboot.cursomc.dto.ClienteDTO;
import com.udemy.curso.springboot.cursomc.services.ClienteService;

@RestController
@RequestMapping("/clientes")
public class ClienteResource {

	@Autowired
	private ClienteService clienteService;

	// Médodo GET - devolve um cliente de acordo com o id solicitado
	@GetMapping("/{id}")
	public ResponseEntity<Cliente> find(@PathVariable Integer id) {
		Cliente obj = clienteService.find(id);
		return ResponseEntity.ok().body(obj);
	}

//Método PUT - Altera o conteudo de uma ID 
	
//	//Funciona como uma "mistura" do GET e POST, ele recebe o o objeto e o parametro no URL
//	@PutMapping ("/{id}")
	
	@PutMapping ("/{id}")
	public ResponseEntity<Void> update (@Valid @RequestBody ClienteDTO objDto , @PathVariable Integer id){
		Cliente obj = clienteService.fromDTO(objDto);
		obj.setId(id);
		obj = clienteService.update(obj);
		return ResponseEntity.noContent().build();
	}
	
	//Médodo DELETE - Deleta uma Cliente

	@DeleteMapping ("/{id}")
	public ResponseEntity<Void> delete (@PathVariable Integer id){
		clienteService.delete(id);
		return ResponseEntity.noContent().build();	
	}
	
	// Médodo GET - devolve todas as cadegorias
		
	@GetMapping
	public ResponseEntity<List<ClienteDTO>> findAll() {
		//Assim preciso converter um Lista de Clientes para ClienteDTO
		//Essa pratica é feita no DTO
		List<Cliente> list = clienteService.findAll();
		//Assim percorro a lista e para cada elemento dessa lista (cod acima)
		//Instancio o DTO correspondente (cod  abaixo)
		List<ClienteDTO> listDto = list.stream()
				.map(obj -> new ClienteDTO(obj))
				.collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
		//.stream() percorre a lista
		//.map(obj -> new ClienteDTO(obj)) efetua uma operação para cada 
		//elemento da lista apelidado aqui como obj , assim crio uma
		//nova lista DTO passando "obj" como argumento
		//.collect(Collectors.toList()); preciso voltar o stream para o
		//tipo list por isso o Collectors.toList
	}
	
	// Método paginação
	
	@GetMapping ("/page")
	public ResponseEntity<Page<ClienteDTO>> findpage(
			@RequestParam(value="page",defaultValue="0") Integer page,
			@RequestParam(value="linesPerPage",defaultValue="24")Integer linesPerPage,
			@RequestParam(value="orderBy",defaultValue="nome")String orderBy,
			@RequestParam(value="direction",defaultValue="ASC")String direction) {//DESC para decrescente
		Page<Cliente> list = clienteService.findPage(page,linesPerPage,orderBy,direction);
		Page<ClienteDTO> listDto = list.map(obj -> new ClienteDTO(obj));
		return ResponseEntity.ok().body(listDto);
		
		//Integer linesPerPage é uma boa prática deixar 24 pois ele é multiplo de 1, 2 , 3 e 4
		//assim facilita a visualizaçao
	}
	
}

