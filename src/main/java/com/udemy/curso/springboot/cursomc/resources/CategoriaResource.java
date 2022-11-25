package com.udemy.curso.springboot.cursomc.resources;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
	public ResponseEntity<?> find(@PathVariable Integer id) {
		Categoria obj = categoriaService.find(id);
		return ResponseEntity.ok().body(obj);
	}
	
	//Lembrando que o Método para salvar fica na classe CategoriaService:
	//O @RequestBody faz o Json ser convertido para o objeto java automáticamente
	//(sem ele o retornava null o nome inserido no postman)
	//@RequestMapping(method=RequestMethod.POST)
	@PostMapping
	public ResponseEntity<Void> insert(@RequestBody Categoria obj) {
		obj = categoriaService.insert(obj);// essa linha garande que sera salvo o próximo id disponivel na Categoria
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		//no return usamos o .created pois ele retorna o "201" que é o correto para essa aplicação
		//basta pesquisar http status code que veremos uma lista com todos os códigos adequados
		return ResponseEntity.created(uri).build();
	}

}
