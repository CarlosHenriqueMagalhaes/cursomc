package com.udemy.curso.springboot.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.udemy.curso.springboot.cursomc.domain.Categoria;
import com.udemy.curso.springboot.cursomc.domain.Produto;
import com.udemy.curso.springboot.cursomc.repositories.CategoriaRepository;
import com.udemy.curso.springboot.cursomc.repositories.ProdutoRepository;
import com.udemy.curso.springboot.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private CategoriaRepository categoriaRepository;

	// buscar para o ID (GET do controller)
	
	public Produto find(Integer id) {
		Optional<Produto> obj = produtoRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Produto.class.getName()));
		// Retorna mensagem/ lança uma exceção quando o Id não for existir,
		// foi criado uma classe para esse método: ObjectNotFoundException
	}
	
		// Método busca páginada
	
	public Page<Produto> search (String nome, List<Integer>ids, Integer page,
			Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of (page, linesPerPage,Direction.valueOf(direction),orderBy);
	    List<Categoria>categorias = categoriaRepository.findAllById(ids);
		return produtoRepository.findDistinctyByNomeContainingAndCategoriasIn (nome, categorias, pageRequest);
	}
		

}
