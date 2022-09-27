package pe.edu.upc.spring.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.spring.model.Habitacion;
import pe.edu.upc.spring.repository.IHabitacionRepository;
import pe.edu.upc.spring.service.IHabitacionService;

@Service
public class HabitacionServiceImpl implements IHabitacionService {

	@Autowired
	private IHabitacionRepository dHabitacion;
	
	@Override
	@Transactional
	public boolean registrar(Habitacion habitacion) {
		Habitacion objHabitacion = dHabitacion.save(habitacion);
		if (objHabitacion == null)
			return false;
		else
			return true;
	}

	@Override
	@Transactional
	public void eliminar(int idHabitacion) {
		dHabitacion.deleteById(idHabitacion);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Habitacion> listarId(int idHabitacion) {
		return dHabitacion.findById(idHabitacion);
	}
	
	@Override
	@Transactional(readOnly = true)
	public Optional<Habitacion> buscarId(int idHabitacion) {
		return dHabitacion.findById(idHabitacion);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Habitacion> listar() {
		return dHabitacion.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public List<Habitacion> buscarDescripcion(String desHabitacion) {
		return dHabitacion.buscarDescripcion(desHabitacion);
	}	
	
	@Override
	@Transactional(readOnly = true)
	public List<Habitacion> buscarArrendador(String nomArrendador) {
		return dHabitacion.buscarArrendador(nomArrendador);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Habitacion> buscarDistrito(String nomDistrito) {
		return dHabitacion.buscarDistrito(nomDistrito);
	}

}
