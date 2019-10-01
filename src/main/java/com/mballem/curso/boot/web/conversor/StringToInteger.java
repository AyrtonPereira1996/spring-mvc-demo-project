package com.mballem.curso.boot.web.conversor;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringToInteger implements Converter<String, Integer> {// no valor do generics o String corresponde ao dado
																	// que vem do formulario e o Integer corresponde ao
																	// para o qual eu quero converter

	@Override
	public Integer convert(String text) {
		text = text.trim();// pegamos a variavel text e atribuir a ela text.trim() remove espcos em branco

		// teste condicional para ver se o valor que estamos a receber da view e um
		// valor que contenha digitos
		if (text.matches("[0-9]+")) {// usaremos expressao regular numa varivel do tipo string podemos trabalhar com
										// expressoes regulares do tipo matches(teremos varias aparições de digitos
										// entre 0 e 9 da variavel text)
			return Integer.valueOf(text);// caso seja verdadeiro é convertido para integer
		}

		return null;// caso nao retorna null, e lancado a mesagem de validacao de null
	}

}
