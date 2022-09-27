package pe.edu.upc.spring.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.spring.model.Universidad;
import pe.edu.upc.spring.repository.IUniversidadRepository;
import pe.edu.upc.spring.service.IUniversidadService;

@Service
public class UniversidadServiceImpl implements IUniversidadService {

	@Autowired
	private IUniversidadRepository dUniversidad;
	
	@Override
	@Transactional
	public boolean registrar(Universidad universidad) {
		Universidad objUniversidad = dUniversidad.save(universidad);
		if (objUniversidad == null)
			return false;
		else
			return true;
	}

	@Override
	@Transactional
	public void eliminar(int idUniversidad) {
		dUniversidad.deleteById(idUniversidad);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Universidad> listarId(int idUniversidad) {
		return dUniversidad.findById(idUniversidad);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Universidad> listar() {
		return dUniversidad.findAll();
	}

}
