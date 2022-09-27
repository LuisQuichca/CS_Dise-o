package pe.edu.upc.spring.service;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.spring.model.Estudiante;


public interface IEstudianteService {
	public boolean registrar(Estudiante estudiante);
	public void eliminar(int idEstudiante);
	public Optional<Estudiante> listarId(int idEstudiante);
	public Optional<Estudiante> buscarId(int idEstudiante);
	public Estudiante findByUsername(String username);
	public Estudiante registrarS(Estudiante estudiante);
	List<Estudiante> listar();	
}
