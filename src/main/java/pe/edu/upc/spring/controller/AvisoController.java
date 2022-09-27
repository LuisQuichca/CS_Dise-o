package pe.edu.upc.spring.controller;


import java.net.MalformedURLException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.multipart.MultipartException;

import com.sun.el.parser.ParseException;

import pe.edu.upc.spring.model.Habitacion;
import pe.edu.upc.spring.model.Aviso;

import pe.edu.upc.spring.service.IHabitacionService;
import pe.edu.upc.spring.service.IUploadFileService;
import pe.edu.upc.spring.service.IAvisoService;


@Controller
@RequestMapping("/aviso")
public class AvisoController {
	
	@Autowired
	private IHabitacionService pService;
	
	@Autowired
	private IAvisoService aService;
	
	@Autowired
	private IUploadFileService uService;
	
	@RequestMapping("/bienvenido")
	public String irPaginaBienvenida() {
		return "bienvenido";
	}
		
	/*@RequestMapping("/")
	public String irPaginaListadoAvisos(Map<String, Object> model) {
		model.put("listaAvisos", aService.listar());
		return "/aviso/listAviso";
	}*/
	
	@GetMapping(value = "/uploads/{filename}")
	public ResponseEntity<Resource> irPaginaListadoAvisos(@PathVariable String filename, Map<String, Object> model) {
		model.put("listaAvisos", aService.listar());
		Resource resource = null;
		try {
			resource = uService.load(filename);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
				.body(resource);
	}
	
	@RequestMapping("/irRegistrar")
	public String irPaginaRegistrar(Model model) {	

		model.addAttribute("habitacion", new Habitacion());
		model.addAttribute("aviso", new Aviso());
		
		model.addAttribute("listaHabitaciones", pService.listar());
		
		return "/aviso/aviso";
	}
	/*
	@RequestMapping("/registrar")
	public String registrar(@ModelAttribute Aviso objAviso, BindingResult binRes, Model model)
			throws ParseException
	{
		if (binRes.hasErrors()) 
			{
				model.addAttribute("listaHabitaciones", pService.listar());
				
				return "/aviso/aviso";
			}
		else {
			boolean flag = aService.registrar(objAviso);
			if (flag)
				return "redirect:/aviso/listar";
			else {
				model.addAttribute("mensaje", "Ocurrio un error");
				return "redirect:/aviso/irRegistrar";
			}
		}
	}*/
	
	@RequestMapping("/registrar")
	public String registrar(@Validated @ModelAttribute("foto") Aviso aviso, BindingResult result, Model model,
			@RequestParam("file") MultipartFile foto, RedirectAttributes flash, SessionStatus status) 
			throws Exception{
		if (result.hasErrors()) {
			System.out.println(result.getFieldError());
			return "/aviso/aviso";
		} else {
			if (!foto.isEmpty()) {
				if(aviso.getIdAviso() > 0 && aviso.getFoto() !=null && aviso.getFoto().length() > 0) {
					uService.delete(aviso.getFoto());
				}
				String uniqueFileName = uService.copy(foto);
				aviso.setFoto(uniqueFileName);
 			}
			aService.registrar(aviso);
			status.setComplete();
		}
		
		return "redirect:/aviso/listar";
	}
	
	@Secured("ROLE_ARRENDADOR")
	@RequestMapping("/modificar/{id}")
	public String modificar(@PathVariable int id, Model model, RedirectAttributes objRedir)
		throws ParseException 
	{
		Optional<Aviso> objAviso = aService.buscarId(id);
		if (objAviso == null) {
			objRedir.addFlashAttribute("mensaje", "Ocurrio un error");
			return "redirect:/aviso/listar";
		}
		else {
			model.addAttribute("listaHabitaciones", pService.listar());
					
			if (objAviso.isPresent())
				objAviso.ifPresent(o -> model.addAttribute("aviso", o));
			
			return "/aviso/aviso";
		}
	}
	@Secured("ROLE_ARRENDADOR")
	@RequestMapping("/eliminar")
	public String eliminar(Map<String, Object> model, @RequestParam(value="id") Integer id) {
		try {
			if (id!=null && id>0) {
				pService.eliminar(id);
				model.put("listaAvisos", aService.listar());
			}
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
			model.put("mensaje","Ocurrio un roche");
			model.put("listaAvisos", aService.listar());
			
		}
		return "/aviso/listAviso";
	}
	
	@RequestMapping("/listar")
	public String listar(Map<String, Object> model) {
		model.put("listaAvisos", aService.listar());
		
		return "/aviso/listAviso";
	}
	
	@RequestMapping("/listarId")
	public String listarId(Map<String, Object> model, @ModelAttribute Aviso aviso) 
	throws ParseException
	{
		aService.listarId(aviso.getIdAviso());
		return "/aviso/listAviso";
	}	
	
	@RequestMapping("/listaestudiante")
	public String listaestudiante(Map<String, Object> model) {
		model.put("listaAvisos", aService.listar());
		return "/estudiante/listAvisoE";
	}
	
	@RequestMapping("/irBuscar")
	public String irBuscar(Model model) 
	{
		model.addAttribute("aviso", new Aviso());
		return "/aviso/buscarAviso";
	}
	
	@RequestMapping("/buscar")
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
        return "/aviso/buscarAviso";
	}		
}
