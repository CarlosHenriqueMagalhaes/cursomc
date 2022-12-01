package com.udemy.curso.springboot.cursomc.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.udemy.curso.springboot.cursomc.domain.Produto;
import com.udemy.curso.springboot.cursomc.dto.ProdutoDTO;
import com.udemy.curso.springboot.cursomc.resources.utils.URL;
import com.udemy.curso.springboot.cursomc.services.ProdutoService;

@RestController
@RequestMapping("/produtos")// padrão do ending point no plural
public class ProdutoResource {

	@Autowired
	private ProdutoService produtoService;

	// Médodo GET - devolve um item da categoria de acordo com o id solicitado
	
	@GetMapping("/{id}")
	public ResponseEntity<Produto> find(@PathVariable Integer id) {
		Produto obj = produtoService.find(id);
		return ResponseEntity.ok().body(obj);
	}
	
	// Método paginação

	@GetMapping ("/page")
	public ResponseEntity<Page<ProdutoDTO>> findpage(
			@RequestParam(value="nome",defaultValue="") String nome,
			@RequestParam(value="categorias",defaultValue="") String categorias,
			@RequestParam(value="page",defaultValue="0") Integer page,
			@RequestParam(value="linesPerPage",defaultValue="24")Integer linesPerPage,
			@RequestParam(value="orderBy",defaultValue="nome")String orderBy,
			@RequestParam(value="direction",defaultValue="ASC")String direction) {//DESC para decrescente
		String nomeDecoded = URL.decodeParam(nome);// Método para descodificar um parametro presente na classe URL
		List<Integer> ids = URL.decodeIntList(categorias);
		Page<Produto> list = produtoService.search(nomeDecoded,ids,page,linesPerPage,orderBy,direction);//para que eu
		//pudesse passar o nome aqui tive que utilizar o método para descodificar um parametro presente na classe URL
		//pois pode ser que o usuário ao entrar "aqui" (http://localhost:8099/produtos/page/?nome)
		//com o nome que ele deseja buscar ele coloca nome com espaços por isso preciso do Encode
		//O ENCODE é converter um string que pode ser um espaço em branco ou com um caracter especial para um
		//um string com caracteres basicos
		Page<ProdutoDTO> listDto = list.map(obj -> new ProdutoDTO(obj));
		return ResponseEntity.ok().body(listDto);
		//Integer linesPerPage é uma boa prática deixar 24 pois ele é multiplo de 1, 2 , 3 e 4
		//assim facilita a visualizaçao
		
		//Interpretação do método:
		//Usando o POSTMAN com:
		//http://localhost:8099/produtos/page/?nome=t&categorias=1,4
		//em ?nome=t&categorias=1,4 no t, ele buscara e mostrara no GET
		//todos os produtos presentes na categoria 1 e 4 (&categorias=1,4) 
		//que tenham t no nome, assim posso mudar as categorias e o nome (sendo inteiro
		//o nome escrito ou só parte dele, para obter os produtos cadastrados
		//Há diferenciação de minusculo e Maisculo no nome a ser buscado
			
	}
				
}