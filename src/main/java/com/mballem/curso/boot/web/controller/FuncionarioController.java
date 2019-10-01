package com.mballem.curso.boot.web.controller;

import java.time.LocalDate;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.mballem.curso.boot.domain.Cargo;
import com.mballem.curso.boot.domain.Funcionario;
import com.mballem.curso.boot.domain.UF;
import com.mballem.curso.boot.service.CargoService;
import com.mballem.curso.boot.service.FuncionarioService;
import com.mballem.curso.boot.validator.FuncionarioValidator;

@Controller
@RequestMapping("/funcionarios")
public class FuncionarioController {
	
	//dependencias
	@Autowired
	private FuncionarioService funcionarioService;
	
	@Autowired
	private CargoService cargoService; //sera necessario para popular a lista de cargos no registo de funcionarios
	
	 
	@InitBinder//anotacao que vai fazer com que seja o primeiro metodo dentro do controller a ser inicializado
	public void initBinder(WebDataBinder binder) {//assim ira activar a validacao
		binder.addValidators(new FuncionarioValidator());
	}
	
	@RequestMapping("/cadastrar")
	public String cadastrar(Funcionario funcionario) {
		return "funcionario/cadastro";
	}

	@RequestMapping("/listar")
	public String listar(ModelMap model) {
		model.addAttribute("funcionarios", funcionarioService.buscarTodos());
		return "funcionario/lista";
	}
	
	@PostMapping("/salvar")
	public String salvar(@Valid Funcionario funcionario, BindingResult result, RedirectAttributes attr) {
		
		if (result.hasErrors()) {
			return "funcionario/cadastro";
		}
		
		funcionarioService.salvar(funcionario);
		attr.addFlashAttribute("success", "Funcionário inserido com sucesso");
		return "redirect:/funcionarios/cadastrar";
	}
	
	@GetMapping("/editar/{id}")
	public String preEditar(@PathVariable("id") Long id, ModelMap model) {
		model.addAttribute("funcionario", funcionarioService.buscarPorId(id));
		return ("funcionario/cadastro");
	}
	
	@PostMapping("/editar")
	public String editar(@Valid Funcionario funcionario, BindingResult result, RedirectAttributes attr) {
	if (result.hasErrors()) {
		return "funcionario/cadastro";
	}	
	
	funcionarioService.editar(funcionario);
	attr.addFlashAttribute("success", "Funcionário editado com sucesso!");
	return("redirect:/funcionarios/cadastrar");
	}
	
	@GetMapping("/excluir/{id}")
	public String excluir(@PathVariable ("id") Long id, RedirectAttributes attr ) {
		funcionarioService.excluir(id);
		attr.addFlashAttribute("success", "Funcionário removido com sucesso.");
		return "redirect:/funcionarios/listar";
		
	}
	
	@GetMapping("/buscar/nome")//estamos a usar o requestParam em inves de pathvariable porque o valor do nome nao chegara como um path mas sim como um atribute de request 
	public String getPorNome(@RequestParam ("nome") String nome, ModelMap model) {
		model.addAttribute("funcionarios", funcionarioService.buscarPorNome(nome));
		return "funcionario/lista";
	}
	
	@GetMapping("/buscar/cargo")
	public String getPorCargo(@RequestParam("id") Long id, ModelMap model) {
		model.addAttribute("funcionarios", funcionarioService.buscaPorCargo(id));
		return "funcionario/lista";
	}
	
	@GetMapping("/buscar/data")
	public String getPorDatas(@RequestParam("entrada")@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate entrada,
							@RequestParam("saida")@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate saida,
							ModelMap model) {
		model.addAttribute("funcionarios", funcionarioService.buscarPorDatas(entrada, saida));
		return "funcionario/lista";
	}
			
	
	@ModelAttribute("cargos")//variavel cargos
	public List<Cargo> getCargos(){ //metodo responsavel por enviar registos de cargos disponiveis
		return cargoService.buscarTodos(); 
	}
	
	@ModelAttribute("ufs")
	public UF[] getUFs() { //metodo responsavel por enviar registos de cargos disponiveis
		return UF.values();
	}
}
