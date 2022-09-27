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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sun.el.parser.ParseException;

import pe.edu.upc.spring.model.Arrendador;
import pe.edu.upc.spring.service.IArrendadorService;


@Controller 
@RequestMapping("/arrendador") 
public class ArrendadorController {
	@Autowired
	private IArrendadorService rService;
	
	@RequestMapping("/bienvenido")
	public String irPaginaBienvenida() {
		return "bienvenido";
	}
	
	@GetMapping("/irlogin")
	public String login(Model model) {
		model.addAttribute("arrendador", new Arrendador());
		return "login";
	}
	
	@RequestMapping("/")
	public String irPaginaListadoHabitaciones(Map<String, Object> model) {
		model.put("listaArrendadores", rService.listar());
		return "/arrendador/listArrendador";
	}
	
	@RequestMapping("/irRegistrar")
	public String irPaginaRegistrar(Model model) {
		model.addAttribute("arrendador", new Arrendador());
		return "/arrendador/arrendador";
	}
	
	@RequestMapping("/registrar")
	public String registrar(@ModelAttribute Arrendador objArrendador, BindingResult binRes, Model model)
			throws ParseException
	{
		if (binRes.hasErrors())
			return "/arrendador/arrendador";
		else {
			boolean flag = rService.registrar(objArrendador);
			if (flag)
				return "redirect:/arrendador/listar";
			else {
				model.addAttribute("mensaje", "Ocurrio un error");
				return "redirect:/arrendador/irRegistrar";
			}
		}
	}
	
	
	@RequestMapping("/modificar/{id}")
	public String modificar(@PathVariable int id, Model model, RedirectAttributes objRedir)
		throws ParseException 
	{
		Optional<Arrendador> objArrendador = rService.listarId(id);
		if (objArrendador == null) {
			objRedir.addFlashAttribute("mensaje", "Ocurrio un error");
			return "redirect:/arrendador/listar";
		}
		else {
			model.addAttribute("arrendador", objArrendador);
			return "/arrendador/arrendador";
		}
	}
	
	@RequestMapping("/eliminar")
	public String eliminar(Map<String, Object> model, @RequestParam(value="id") Integer id) {
		try {
			if (id!=null && id>0) {
				rService.eliminar(id);
				model.put("listaArrendadores", rService.listar());
			}
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
			model.put("mensaje","Ocurrio un roche");
			model.put("listaArrendadores", rService.listar());
			
		}
		return "/arrendador/listArrendador";
	}
	
	@RequestMapping("/listar")
	public String listar(Map<String, Object> model) {
		model.put("listaArrendadores", rService.listar());
		return "/arrendador/listArrendador";
	}
	
	@RequestMapping("/irBuscar")
	public String irBuscar(Model model)  
	{
		model.addAttribute("arrendador", new Arrendador());
		return "/arrendador/buscarArrendador";
	}
	
	@RequestMapping("/buscar")
	public String buscar(Map<String, Object> model, @ModelAttribute Arrendador arrendador) throws ParseException{
		
		List<Arrendador> listaArrendadores; 
		arrendador.setNomArrendador(arrendador.getNomArrendador());
        listaArrendadores = rService.buscarNombre(arrendador.getNomArrendador());
        
        if(listaArrendadores.isEmpty()) {
            model.put("mensaje", "No existen coincidencias");
        }
        model.put("listaArrendadores", listaArrendadores);
        return "/arrendador/buscarArrendador";
	}		
}