package com.udemy.curso.springboot.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.udemy.curso.springboot.cursomc.domain.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer>{

	//método "findBy" automaticamente o Spring Data vai detectar buscar e implementar
	//o método, nesse caso para o Email
	@Transactional (readOnly=true)
	Cliente  findByEmail(String email);
}
