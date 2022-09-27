package pe.edu.upc.spring.service;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.spring.model.Habitacion;

public interface IHabitacionService {
	public boolean registrar(Habitacion habitacion);
	public void eliminar(int idHabitacion);
	public Optional<Habitacion> listarId(int idHabitacion);
	public Optional<Habitacion> buscarId(int idHabitacion);
	List<Habitacion> listar();
	List<Habitacion> buscarDescripcion(String desHabitacion);
	List<Habitacion> buscarDistrito(String nomDistrito);
	List<Habitacion> buscarArrendador(String nameArrendador);
	
}
