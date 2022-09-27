package pe.edu.upc.spring.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sun.el.parser.ParseException;

import pe.edu.upc.spring.model.Interes;
import pe.edu.upc.spring.model.Aviso;
import pe.edu.upc.spring.model.Estudiante;
import pe.edu.upc.spring.service.IInteresService;
import pe.edu.upc.spring.service.IAvisoService;
import pe.edu.upc.spring.service.IEstudianteService;


@Controller
@RequestMapping("/interes")
public class InteresController {
	
	@Autowired
	private IEstudianteService dService;
	
	@Autowired
	private IAvisoService aService;
	
	@Autowired
	private IInteresService iService;
	
	@RequestMapping("/bienvenido")
	public String irPaginaBienvenida() {
		return "bienvenido";
	}
		
	@RequestMapping("/")
	public String irPaginaListadoIntereses(Map<String, Object> model) {
		model.put("listaIntereses", iService.listar());
		return "/interes/listInteres";
	}
	
	@RequestMapping("/irRegistrar")
	public String irPaginaRegistrar(Model model) {	

		model.addAttribute("Estudiante", new Estudiante());
		model.addAttribute("Aviso", new Aviso());
		model.addAttribute("Interes", new Interes());
		
		model.addAttribute("listaEstudiantes", dService.listar());	
		model.addAttribute("listaAvisos", aService.listar());	
		
		return "/interes/interes";
	}
	
	@RequestMapping("/registrar")
	public String registrar(@ModelAttribute Interes objInteres, BindingResult binRes, Model model)
			throws ParseException
	{
		if (binRes.hasErrors()) 
			{
				model.addAttribute("listaEstudiantes", dService.listar());
				model.addAttribute("listaAvisos", aService.listar());
				return "/interes/interes";
			}
		else {
			boolean flag = iService.registrar(objInteres);
			if (flag)
				return "redirect:/interes/listar";
			else {
				model.addAttribute("mensaje", "Ocurrio un error");
				return "redirect:/interes/irRegistrar";
			}
		}
	}
	
	
	@RequestMapping("/eliminar")
	public String eliminar(Map<String, Object> model, @RequestParam(value="id") Integer id) {
		try {
			if (id!=null && id>0) {
				iService.eliminar(id);
				model.put("listaIntereses", iService.listar());
			}
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
			model.put("mensaje","Ocurrio un roche");
			model.put("listaIntereses", iService.listar());
			
		}
		return "/interes/listInteres";
	}
	
	@RequestMapping("/listar")
	public String listar(Map<String, Object> model) {
		model.put("listaIntereses", iService.listar());
		return "/interes/listInteres";
	}
	
	@RequestMapping("/listarId")
	public String listarId(Map<String, Object> model, @ModelAttribute Interes interes) 
	throws ParseException
	{
		iService.listarId(interes.getIdInteres());
		return "/interes/listInteres";
	}	
			
}
