package com.udemy.curso.springboot.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.udemy.curso.springboot.cursomc.domain.Categoria;
import com.udemy.curso.springboot.cursomc.repositories.CategoriaRepository;
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
	
	//Método para salvar um novo objeto na Categoria, o POST e seu EndPoint.
	public Categoria insert (Categoria obj) {
		obj.setId(null);
		//é null pois ele vai "buscar o primeiro id nulo para setar
		//sem o null vai ser considerado uma atualização e não uma inserção
		return categoriaRepository.save(obj);
	}

}
