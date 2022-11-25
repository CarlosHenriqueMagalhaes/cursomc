package com.udemy.curso.springboot.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.udemy.curso.springboot.cursomc.domain.Pedido;
import com.udemy.curso.springboot.cursomc.repositories.PedidoRepository;
import com.udemy.curso.springboot.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository pedidoRepository;

	// buscar para o ID (GET do controller)
	public Pedido find(Integer id) {
		Optional<Pedido> obj = pedidoRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Pedido.class.getName()));
		// Retorna mensagem/ lança uma exceção quando o Id não for existir,
		// foi criado uma classe para esse método: ObjectNotFoundException
		// Além disso temos que mudar na camada de recurso para ela receber essa
		// exceção, go to PedidoResource
	}

}
