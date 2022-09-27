package pe.edu.upc.spring.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sun.el.parser.ParseException;

import pe.edu.upc.spring.model.Aviso;
import pe.edu.upc.spring.model.Estudiante;
import pe.edu.upc.spring.model.Universidad;
import pe.edu.upc.spring.service.IAvisoService;
import pe.edu.upc.spring.service.IEstudianteService;
import pe.edu.upc.spring.service.IUniversidadService;

@Controller
@RequestMapping("/estudiante")
public class EstudianteController {
	@Autowired
	private IEstudianteService eService;
	
	@Autowired
	private IUniversidadService uService;
	
	@Autowired
	private IAvisoService aService;

	@GetMapping("/irlogin")
	public String login(Model model) {
		model.addAttribute("estudiante", new Estudiante());
		return "login";
	}
	
	@RequestMapping("/irRegistrar")
	public String irPaginaRegistrar(Model model) {
		model.addAttribute("Universidad", new Universidad());
		model.addAttribute("estudiante", new Estudiante());
		
		model.addAttribute("listaUniversidades", uService.listar());	
		return "/estudiante/estudiante";
	}
	
	@RequestMapping("/registrar")
	public String registrar(@ModelAttribute Estudiante objEstudiante, BindingResult binRes, Model model)
			throws ParseException
	{
		if (binRes.hasErrors())
			return "/estudiante/estudiante";
		else {
			boolean flag = eService.registrar(objEstudiante);
			if (flag)
				return "redirect:/estudiante/listar";
			else {
				model.addAttribute("mensaje", "Ocurrio un error");
				return "redirect:/estudiante/registrar";
			}
		}
	}
	

	@RequestMapping("/modificar/{id}")
	public String modificar(@PathVariable int id, Model model, RedirectAttributes objRedir)
		throws ParseException 
	{
		Optional<Estudiante> objEstudiante = eService.buscarId(id);
		if (objEstudiante == null) {
			objRedir.addFlashAttribute("mensaje", "Ocurrio un error");
			return "redirect:/estudiante/listar";
		}
		else {
			model.addAttribute("listaUniversidades", uService.listar());	
		
			if (objEstudiante.isPresent())
				objEstudiante.ifPresent(o -> model.addAttribute("estudiante", o));
			
			return "/estudiante/estudiante";
		}
	}

	
	@RequestMapping("/listar")
	public String listar(Map<String, Object> model) {
		model.put("listaEstudiantes", eService.listar());
		return "/estudiante/listEstudiante";
	}
	
	@RequestMapping("/listaestudiante")
	public String listaestudiante(Map<String, Object> model) {
		model.put("listaAvisos", aService.listar());
		return "/estudiante/listAvisoE";
	}
	
	@RequestMapping("/filtrar")
	public String irBuscarE(Model model) 
	{
		model.addAttribute("aviso", new Aviso());
		return "/estudiante/filtro";
	}
	
	@RequestMapping("/filtro")
	public String buscar(Map<String, Object> model, @ModelAttribute Aviso aviso)
			throws ParseException
	{
		List<Aviso> listaAvisos; 
		aviso.setRangoPrecio(aviso.getRangoPrecio());
        listaAvisos = aService.buscarPrecio(aviso.getRangoPrecio());
       
        if(listaAvisos.isEmpty()) {
            listaAvisos=aService.buscarDistrito(aviso.getRangoPrecio());
        }
        
        
        
        if(listaAvisos.isEmpty()) {
            model.put("mensaje", "No existen coincidencias");
        }
        model.put("listaAvisos", listaAvisos);
        return "/estudiante/filtro";
	}	
}
