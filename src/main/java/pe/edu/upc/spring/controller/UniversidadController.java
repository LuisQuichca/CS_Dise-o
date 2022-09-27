package pe.edu.upc.spring.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sun.el.parser.ParseException;

import pe.edu.upc.spring.model.Universidad;
import pe.edu.upc.spring.service.IUniversidadService;


@Controller
@RequestMapping("/universidad")
public class UniversidadController {
	
	@Autowired
	private IUniversidadService uService;
	
	@RequestMapping("/bienvenido")
	public String irPaginaBienvenida() {
		return "bienvenido";
	}
		
	@RequestMapping("/")
	public String irPaginaListadoUniversidades(Map<String, Object> model) {
		model.put("listaUniversidades", uService.listar());
		return "listUniversidad";
	}
	
	@RequestMapping("/listar")
	public String listar(Map<String, Object> model) {
		model.put("listaUniversidades", uService.listar());
		return "listUniverdad";
	}
	
	@RequestMapping("/listarId")
	public String listarId(Map<String, Object> model, @ModelAttribute Universidad universidad) 
	throws ParseException
	{
		uService.listarId(universidad.getIdUniversidad());
		return "listUniversidad";
	}	
			
}