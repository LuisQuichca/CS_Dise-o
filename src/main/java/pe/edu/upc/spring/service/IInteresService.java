package pe.edu.upc.spring.service;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.spring.model.Interes;


public interface IInteresService {
	public boolean registrar(Interes interes);
	public void eliminar(int idInteres);
	public Optional<Interes> listarId(int idInteres);
	List<Interes> listar();
	
}
