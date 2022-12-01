package com.udemy.curso.springboot.cursomc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.udemy.curso.springboot.cursomc.domain.PagamentoComBoleto;
import com.udemy.curso.springboot.cursomc.domain.PagamentoComCartao;

//Classe criada para auxilio de:
//@JsonTypeInfo Checklist para permitir a instanciação de subclasses a partir de dados JSON:
//Vide classe Pagamento
//vide PDF 4 - Operações de CRUD e casos de uso pág 11 para mais informações

@Configuration
public class JacksonConfig {
// https://stackoverflow.com/questions/41452598/overcome-can-not-construct-instance-ofinterfaceclass-without-hinting-the-pare

	@Bean
	public Jackson2ObjectMapperBuilder objectMapperBuilder() {
		Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder() {
			public void configure(ObjectMapper objectMapper) {
				objectMapper.registerSubtypes(PagamentoComCartao.class);//subclasse de Pagamento
				objectMapper.registerSubtypes(PagamentoComBoleto.class);//subclasse de Pagamento
				super.configure(objectMapper);
			};
		};
		return builder;
	}
}
