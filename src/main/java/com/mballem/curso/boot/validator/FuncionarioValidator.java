package com.mballem.curso.boot.validator;

import java.time.LocalDate;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.mballem.curso.boot.domain.Funcionario;

//classe que trabalha com o spring validator
public class FuncionarioValidator implements Validator {// precisamos implementar a interface validator
//a interface pede que implementemos dois métodos
	@Override
	public boolean supports(Class<?> clazz) {
		// método responsavel por validar o objecto que estamos a enviar a partir
		// do formulario com objecto que a classe deve validar
		return Funcionario.class.equals(clazz); // teste para fazer a validacao clazz=objecto que temos no formulario
	}

	@Override
	public void validate(Object object, Errors errors) {// object e o objecto que estamos a receber do formulario, e o
														// errors e o objecto que estamos a lidar com a validacao

		// para termos o acesso ao objecto formulario atribuimos a f o object
		Funcionario f = (Funcionario) object;

		// logica de validacao
		LocalDate entrada = f.getDataEntrada();
		// teste condicional para saber se a data de saida está vazia ou não
		if (f.getDataSaida() != null) {
			// testa se o valor da data de saida e anterior da data de entrada
			if (f.getDataSaida().isBefore(entrada)) {
				//se sim entao teremos um problema com a validaca
				//passamos dois valores
				//1º nome do campo que estamos a validar
				//2º a mensagem de validacao, usaremos mensagem em forma de chave do arquivo: messages.properties
				errors.rejectValue("dataSaida", "PosteriorDataEntrada.funcionario.dataSaida");
			}
			

		}

	}

}
