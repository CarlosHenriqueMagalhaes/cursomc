package com.udemy.curso.springboot.cursomc.resources;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.udemy.curso.springboot.cursomc.domain.Categoria;
import com.udemy.curso.springboot.cursomc.services.CategoriaService;

@RestController
@RequestMapping("/categorias")// padrão do ending point no plural
public class CategoriaResource {

	@Autowired
	private CategoriaService categoriaService;

	// Médodo GET - devolve um item da categoria de acordo com o id solicitado
	
	@GetMapping("/{id}")
	public ResponseEntity<Categoria> find(@PathVariable Integer id) {
		Categoria obj = categoriaService.find(id);
		return ResponseEntity.ok().body(obj);
	}
	
	// Médodo POST - insere uma nova Categoria em um novo Id!
	
	//Lembrando que o Método para salvar fica na classe CategoriaService:
	//O @RequestBody faz o Json ser convertido para o objeto java automáticamente
	//(sem ele o retornava null o nome inserido no postman)
	//@RequestMapping(method=RequestMethod.POST)
	@PostMapping
	public ResponseEntity<Void> insert(@RequestBody Categoria obj) {
		obj = categoriaService.insert(obj);// essa linha garande que sera salvo o próximo id disponivel na Categoria
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		//URI é o campo local host http://localhost:8099/categorias
		//fromCurrentRequest() pega a URL que usamos para inserir nesse caso http://localhost:8099/categorias
		//.path("/{id}" para o id a ser criado
		//.buildAndExpand(obj.getId()) para atribuir o valor para ele
		//.toUri() para converter para URI
		//no return usamos o .created pois ele retorna o "201" que é o correto para essa aplicação
		//basta pesquisar http status code que veremos uma lista com todos os códigos adequados
		return ResponseEntity.created(uri).build();
	}
	
	//Método PUT - Altera o conteudo de uma ID 
	
	//Funciona como uma "mistura" do GET e POST, ele recebe o o objeto e o parametro no URL
	@PutMapping ("/{id}")
	public ResponseEntity<Void> update (@RequestBody Categoria obj , @PathVariable Integer id){
		obj.setId(id);
		obj = categoriaService.update(obj);
		return ResponseEntity.noContent().build();
	}
	
	//Médodo DELETE - Deleta uma Categoria

	@DeleteMapping ("/{id}")
	public ResponseEntity<Void> delete (@PathVariable Integer id){
		categoriaService.delete(id);
		return ResponseEntity.noContent().build();	
	}
}
