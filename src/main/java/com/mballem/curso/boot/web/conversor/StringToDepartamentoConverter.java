package com.mballem.curso.boot.web.conversor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.mballem.curso.boot.domain.Departamento;
import com.mballem.curso.boot.service.DepartamentoService;

@Component
public class StringToDepartamentoConverter implements Converter<String, Departamento> {

	@Autowired
	private DepartamentoService service;//precisaremos de fazer uma consulta para retornar para o controller
		
	@Override
	public Departamento convert(String text) {//neste metodo será imlementado o codigo responsavel pela conversao
		if(text.isEmpty()) { //teste condicional se a variavel esta vazia ou nao, se a variavel estiver vazia teremos uma string e quando estiver a fazer a conversao de string para long teremos uma exceção garantindo assim quando a variavel estiver vazia ela não seja convertida porque essa classe será chamada sempre que a classe cargo controller for utilizada mesmo em listar
			return null;
		}
		
		
		//o id vem da variavel text entao sera declara um id e sera convertido de string para long
		Long id = Long.valueOf(text);
		return service.buscarPorId(id);
	}

		
	
}
 