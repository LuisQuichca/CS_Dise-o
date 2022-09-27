package pe.edu.upc.spring.controller;


import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
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

import pe.edu.upc.spring.model.Habitacion;
import pe.edu.upc.spring.model.Arrendador;
import pe.edu.upc.spring.model.Distrito;

import pe.edu.upc.spring.service.IHabitacionService;
import pe.edu.upc.spring.service.IArrendadorService;
import pe.edu.upc.spring.service.IDistritoService;


@Controller
@RequestMapping("/habitacion")
public class HabitacionController {
	
	@Autowired
	private IArrendadorService dService;
	
	@Autowired
	private IHabitacionService pService;	
	
	@Autowired
	private IDistritoService cService;

	
	@RequestMapping("/bienvenido")
	public String irPaginaBienvenida() {
		return "bienvenido";
	}
		
	@RequestMapping("/")
	public String irPaginaListadoHabitaciones(Map<String, Object> model) {
		model.put("listaHabitaciones", pService.listar());
		return "/habitacion/listHabitacion"; 
	}
	
	@Secured("ROLE_ARRENDADOR")
	@RequestMapping("/irRegistrar")
	public String irPaginaRegistrar(Model model) {	

		model.addAttribute("arrendador", new Arrendador());
		model.addAttribute("distrito", new Distrito());
		model.addAttribute("habitacion", new Habitacion());
		
		model.addAttribute("listaArrendadores", dService.listar());
		model.addAttribute("listaDistritos", cService.listar());
		
		return "/habitacion/habitacion";
	}
	
	@Secured("ROLE_ARRENDADOR")
	@RequestMapping("guardar")
	public String guardar(@ModelAttribute Habitacion objHabitacion, BindingResult binRes, Model model)
			throws ParseException
	{
		if (binRes.hasErrors())
			return "/habitacion/habitacion";
		else {
			boolean flag = pService.registrar(objHabitacion);
			if (flag)
				return "redirect:/habitacion/listar";
			else {
				model.addAttribute("mensaje", "Ocurrio un error");
				return "redirect:/habitacion/irRegistrar";
			}
		}
	}

	
	
	@RequestMapping("/modificar/{id}")
	public String modificar(@PathVariable int id, Model model, RedirectAttributes objRedir)
		throws ParseException 
	{
		Optional<Habitacion> objHabitacion = pService.buscarId(id);
		if (objHabitacion == null) {
			objRedir.addFlashAttribute("mensaje", "Ocurrio un error");
			return "redirect:/habitacion/listar";
		}
		else {
			model.addAttribute("listaArrendadores", dService.listar());	
			model.addAttribute("listaDistritos", cService.listar());
					
			if (objHabitacion.isPresent())
				objHabitacion.ifPresent(o -> model.addAttribute("habitacion", o));
			
			return "/habitacion/habitacion";
		}
	}
	
	@RequestMapping("/eliminar")
	public String eliminar(Map<String, Object> model, @RequestParam(value="id") Integer id) {
		try {
			if (id!=null && id>0) {
				pService.eliminar(id);
				model.put("listaHabitaciones", pService.listar());
			}
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
			model.put("mensaje","Ocurrio un roche");
			model.put("listaHabitaciones", pService.listar());
			
		}
		return "/habitacion/listHabitacion";
	}
	
	@GetMapping("/listar")
	public String listar(Map<String, Object> model) {
		model.put("listaHabitaciones", pService.listar());
		return "/habitacion/listHabitacion";
	}
	
	
	
	@RequestMapping("/listarId")
	public String listarId(Map<String, Object> model, @ModelAttribute Habitacion habitacion) 
	throws ParseException
	{
		pService.listarId(habitacion.getIdHabitacion());
		return "/habitacion/listHabitacion";
	}	
	
	@RequestMapping("/irBuscar")
	public String irBuscar(Model model) 
	{
		model.addAttribute("habitacion", new Habitacion());
		return "/habitacion/buscarHabitacion";
	}
	
	
	
	@RequestMapping("/buscar")
	public String buscar(Map<String, Object> model, @ModelAttribute Habitacion habitacion)
			throws ParseException
	{
		List<Habitacion> listaHabitaciones; 
		habitacion.setDesHabitacion(habitacion.getDesHabitacion());
        listaHabitaciones = pService.buscarDescripcion(habitacion.getDesHabitacion());
       
        if(listaHabitaciones.isEmpty()) {
            listaHabitaciones=pService.buscarDistrito(habitacion.getDesHabitacion());
        }
        if(listaHabitaciones.isEmpty()) {
            listaHabitaciones=pService.buscarArrendador(habitacion.getDesHabitacion());
        }
        if(listaHabitaciones.isEmpty()) {
            model.put("mensaje", "No existen coincidencias");
        }
        model.put("listaHabitaciones", listaHabitaciones);
        return "/habitacion/buscarHabitacion";
	}		
}