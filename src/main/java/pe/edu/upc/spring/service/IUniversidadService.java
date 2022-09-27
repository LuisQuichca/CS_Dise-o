package pe.edu.upc.spring.service;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.spring.model.Universidad;


public interface IUniversidadService {
	public boolean registrar(Universidad universidad);
	public void eliminar(int idUniversidad);
	public Optional<Universidad> listarId(int idUniversidadr);
	List<Universidad> listar();
	
}
