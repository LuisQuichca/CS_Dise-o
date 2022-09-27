package pe.edu.upc.spring.service;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.spring.model.Arrendador;


public interface IArrendadorService {
	public boolean registrar(Arrendador arrendador);
	public void eliminar(int idArrendador);
	public Optional<Arrendador> listarId(int idArrendador);
	public Arrendador findByUsername(String username);
	public Arrendador registrarS(Arrendador arrendador);
	List<Arrendador> listar();
	List<Arrendador> buscarNombre(String nomArrendador); 

}
