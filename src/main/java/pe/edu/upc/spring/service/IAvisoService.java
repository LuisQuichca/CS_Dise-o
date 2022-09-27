package pe.edu.upc.spring.service;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.spring.model.Aviso;

public interface IAvisoService {
	public boolean registrar(Aviso aviso);
	public void eliminar(int idAviso);
	public Optional<Aviso> listarId(int idAviso);
	public Optional<Aviso> buscarId(int idAviso);
	List<Aviso> listar();
	List<Aviso> buscarPrecio(String rangoPrecio);
	List<Aviso> buscarDistrito(String nomDistrito);
	
}
