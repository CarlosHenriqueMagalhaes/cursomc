package com.udemy.curso.springboot.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.udemy.curso.springboot.cursomc.domain.Categoria;
import com.udemy.curso.springboot.cursomc.repositories.CategoriaRepository;
import com.udemy.curso.springboot.cursomc.services.exceptions.DataIntegrityException;
import com.udemy.curso.springboot.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository categoriaRepository;

	// buscar para o ID (GET do controller)

	public Categoria find(Integer id) {
		Optional<Categoria> obj = categoriaRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName()));
		// Retorna mensagem/ lança uma exceção quando o Id não for existir,
		// foi criado uma classe para esse método: ObjectNotFoundException
		// Além disso temos que mudar na camada de recurso para ela receber essa
		// exceção, go to CategoriaResource
	}

	// Método para salvar um novo objeto na Categoria, o POST no EndPoint.

	public Categoria insert(Categoria obj) {
		obj.setId(null);
		// é null pois ele vai "buscar o primeiro id nulo para setar
		// sem o null vai ser considerado uma atualização e não uma inserção
		return categoriaRepository.save(obj);
	}

	// Método para alterar um objeto na Categoria, o PUT no EndPoint

	// O mesmo método de inserir/salvar, a diferença é a ausencia
	// da linha "obj.setId(null);
	public Categoria update(Categoria obj) {
		find(obj.getId());
		// chamo find porque ele ja busca o objeto no
		// banco e caso o Id não exista ele lança uma exceção
		// esse métod esta no GET acima, só aproveitei ele!
		return categoriaRepository.save(obj);
	}

	// Método para deletar uma Categoria, o Delete no EndPoint

	public void delete(Integer id) {
		find(id);
		// Essa excecao (DataIntegrityViolationException) foi vista ao
		// relizar no postman um delete com uma categoria que ja possuia produtos
		// tratamos ela com o try catch!
		try {
			categoriaRepository.deleteById(id);// o return comum
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir uma categoria que possui produtos cadastrados!");
			// vai lançar minha exception DataIntegrityException
			// (foi criado no pacote com.udemy.curso.springboot.cursomc.services.exceptions)
			// Feito isso temos que lançar na camada no ResourceExceptionHandler
		}

	}
	// buscar todas as Categorias(GET do controller)

	public List<Categoria> findAll() {
		return categoriaRepository.findAll();
	}

	// Método paginação
	
	//Esse método é a listagem com paginação, o que acontece? Quando possuo uma
	//quantidade muito grande de registro, se eu usar o GET ele trará todos
	//os registros salvos! Assim terei um consumo grande de memória, processamento...
	//Assim fazemos a paginação para buscar de maneira controlada esses objetos
	//tipo de 20 em 20 , de 100 em 100, etc.

	//O Page ira encapsular informações e operações sobre a paginação
	public Page <Categoria> findPage (Integer page, Integer linesPerPage, String orderBy, String direction){
//	PageRequest pageRequest = new PageRequest (page, linesPerPage,Direction.valueOf(direction),orderBy);
    PageRequest pageRequest = PageRequest.of (page, linesPerPage,Direction.valueOf(direction),orderBy);
    return categoriaRepository.findAll(pageRequest);
}

}