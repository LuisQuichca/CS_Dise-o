package pe.edu.upc.spring.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.spring.model.Estudiante;
import pe.edu.upc.spring.repository.IEstudianteRepository;
import pe.edu.upc.spring.service.IEstudianteService;

@Service
public class EstudianteServiceImpl implements IEstudianteService {

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private IEstudianteRepository eEstudiante;
	
	@Override
	@Transactional
	public boolean registrar(Estudiante estudiante) {
		Estudiante objEstudiante = eEstudiante.save(estudiante);
		if (objEstudiante == null)
			return false;
		else
			return true;
	}
	
	@Override
	@Transactional
	public Estudiante findByUsername(String username) {
		return eEstudiante.findByUsername(username);
	}
	
	@Override
	@Transactional
	public void eliminar(int idEstudiante) {
		eEstudiante.deleteById(idEstudiante);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Estudiante> listarId(int idEstudiante) {
		return eEstudiante.findById(idEstudiante);
	}
	
	@Override
	@Transactional(readOnly = true)
	public Optional<Estudiante> buscarId(int idEstudiante) {
		return eEstudiante.findById(idEstudiante);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Estudiante> listar() {
		return eEstudiante.findAll();
	}

	@Override
	public Estudiante registrarS(Estudiante estudiante) {
		estudiante.setPassword(passwordEncoder.encode(estudiante.getPassword()));
		return eEstudiante.save(estudiante);
	}

}
