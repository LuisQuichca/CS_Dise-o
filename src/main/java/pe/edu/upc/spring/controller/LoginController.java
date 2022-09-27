package pe.edu.upc.spring.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sun.el.parser.ParseException;

import pe.edu.upc.spring.model.Arrendador;
import pe.edu.upc.spring.model.Estudiante;
import pe.edu.upc.spring.model.Universidad;
import pe.edu.upc.spring.service.IArrendadorService;
import pe.edu.upc.spring.service.IEstudianteService;
import pe.edu.upc.spring.service.IUniversidadService;

@Controller
@RequestMapping
public class LoginController {
	
	@Autowired
	private IUniversidadService uService;
	
	@Autowired
	private IEstudianteService eService;
	
	@Autowired
	private IArrendadorService aService;
	

	
	@GetMapping("/auth/login")
	public String loginA(Model model) {
		model.addAttribute("estudiante", new Estudiante());
		model.addAttribute("arrendador", new Arrendador());
		return "login";
	}
	
	@GetMapping("/auth/registroE")
	public String registroForm(Model model) {
		model.addAttribute("Universidad", new Universidad());
		model.addAttribute("estudiante", new Estudiante());
		
		model.addAttribute("listaUniversidades", uService.listar());
		return "registroE";
		
	}
	
	@PostMapping("/auth/registroE")
	public String registro(@Valid @ModelAttribute Estudiante estudiante, BindingResult result, Model model) throws ParseException {
		if(result.hasErrors()) {
			return "redirect:/auth/registroE";
		}
		else {
			model.addAttribute("estudiante", eService.registrarS(estudiante));
		}
		return "redirect:/auth/login";
	}
	
	@GetMapping("/auth/registroA")
	public String registroFormA(Model model) {
		model.addAttribute("arrendador", new Arrendador());
		
		return "registroA";
		
	}
	
	@PostMapping("/auth/registroA")
	public String registroA(@Valid @ModelAttribute Arrendador arrendador, BindingResult result, Model model) throws ParseException {
		if(result.hasErrors()) {
			return "redirect:/auth/registroA";
		}
		else {
			model.addAttribute("arrendador", aService.registrarS(arrendador));
		}
		return "redirect:/auth/login";
	}

}

