package com.udemy.curso.springboot.cursomc.services;

import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.udemy.curso.springboot.cursomc.domain.PagamentoComBoleto;

@Service
public class BoletoService {
	
	//Definimos na requisição que a data de pagamento será 7 dias após a data de lançamento do boleto:
	public void preencherPagamentoComBoleto(PagamentoComBoleto pagto, Date instanteDoPedido) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(instanteDoPedido);
		cal.add(Calendar.DAY_OF_MONTH, 7);
		pagto.setDataPagamento(cal.getTime());
		
	}

}
