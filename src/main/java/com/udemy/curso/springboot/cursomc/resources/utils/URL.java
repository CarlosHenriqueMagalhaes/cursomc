package com.udemy.curso.springboot.cursomc.resources.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

//Classe de Auxiliar a ProdutoResourse
public class URL {
	
	//Método que pega a String do url http://localhost:8099/produtos/page/?nome=ou&categorias=1,4
	//exemplo no  1 , 4 ele quebra cada string separada pela "," e converte em uma lista de números inteiros
	//Por isso usamos vetor e split (s seria o 1 e o 4 nesse exemplo). O Spli é uma 
	//função que pega o string e recorta ele em pedacinhos baseados no caractere que passei (,).
	//for percorre o vetor e para cada posição ele converte para inteiro e joga para a lista de numeros inteiros
	
	public static List<Integer> decodeIntList (String s){
		String[]vet = s.split(",");
		List<Integer> list = new ArrayList<>();
		for (int i=0; i<vet.length;i++) {
			list.add(Integer.parseInt(vet[i]));
		}
		return list;
		//Essa linha abaixo faz o mesmo código acima decodeIntList
		//return Arrays.asList(s.split(",")).stream().map(x -> Integer.parseInt(x)).collect(Collectors.toList());
	}
	
	
	// Método para descodificar um parametro
	
	public static String decodeParam(String s) {
		try {
			return URLDecoder.decode(s,"UTF-8");
		} catch (UnsupportedEncodingException e) {
			return "";
		}
	}
	
}
