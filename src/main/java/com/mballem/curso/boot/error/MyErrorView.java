package com.mballem.curso.boot.error;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.autoconfigure.web.servlet.error.ErrorViewResolver;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

@Component//para transformar em um bean do spring
public class MyErrorView implements ErrorViewResolver {//interface do spring boot responsavel por tratar os erros para view

	@Override
	//request - terá os dados referentes ao request caso haja necessidade
	//status - tem os dados relacionados ao erro do status que ocorreu
	//map -  teremos as mensagens actuais relacioanadas ao erro que ocorreu
	public ModelAndView resolveErrorView(HttpServletRequest request, HttpStatus status, Map<String, Object> map) {
		
		//pega no erro vindo do navegador e poem na console map.forEach((k,v) -> System.out.println(k + ":" + v + "\n"));
		
		ModelAndView model = new ModelAndView("error");//pagina que queremos abrir
		model.addObject("status", status.value());//ira recuperar o valor do status e enviar para a view atrves do objecto status
		
		switch (status.value()) {//ira avaliar o valor do status
		case 404://caso o valor seja 404
			model.addObject("error", "Página não encontrada.");//ira por na varivel error uma mensagem e enviar para a view
			model.addObject("message", "A url para a página '" + map.get("path")+"' não existe.");//ira por na varivel message uma mensagem e enviar para a view
			break;
			
		case 500://caso o valor seja 500
			model.addObject("error", "Ocorreu um erro interno no servidor");//ira por na varivel error uma mensagem e enviar para a view
			model.addObject("message", "Ocorreu um erro inesperado, tente mais tarde.");//ira por na varivel message uma mensagem e enviar para a view
			break;

		default://outro statu s
			model.addObject("error", map.get("error"));
			model.addObject("message", map.get("message"));
			break;
		}
		
		return model;
	}

}
 