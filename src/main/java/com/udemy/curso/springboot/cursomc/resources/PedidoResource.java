package com.udemy.curso.springboot.cursomc.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.udemy.curso.springboot.cursomc.domain.Pedido;
import com.udemy.curso.springboot.cursomc.services.PedidoService;

@RestController
@RequestMapping("/pedidos")
public class PedidoResource {

	@Autowired
	private PedidoService pedidoService;

	// Médodo GET - devolve um pedido de acordo com o id solicitado
	@GetMapping("/{id}")
	public ResponseEntity<?> find(@PathVariable Integer id) {
		Pedido obj = pedidoService.find(id);
		return ResponseEntity.ok().body(obj);
	}

}
