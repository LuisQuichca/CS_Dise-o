package pe.edu.upc.spring.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import pe.edu.upc.spring.model.Arrendador;
import pe.edu.upc.spring.model.Estudiante;
import pe.edu.upc.spring.service.IArrendadorService;
import pe.edu.upc.spring.service.IEstudianteService;

@Controller
@RequestMapping("/private")
public class PrivateController {
	
	@Autowired
	private IArrendadorService aService;
	
	@Autowired
	private IEstudianteService eService;
	
	
	
	
	
	@GetMapping("/index")
	public String index(Authentication auth, HttpSession session) {
		String username = auth.getName();
		
		if(session.getAttribute("arrendador")==null) {
			Arrendador arrendador = aService.findByUsername(username);
			System.out.println("username"+ username);
			
			session.setAttribute("arrendador", arrendador);
			
			return "bienvenidoA";
	    }
		if(session.getAttribute("estudiante")==null) {
			Estudiante estudiante = eService.findByUsername(username);
			System.out.println("username"+ username);
			
			session.setAttribute("estudiante", estudiante);
			
			return "bienvenidoE";
		}
		
		return "";
		
	}
	

}
