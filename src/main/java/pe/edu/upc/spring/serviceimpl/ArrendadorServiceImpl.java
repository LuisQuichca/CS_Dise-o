package pe.edu.upc.spring.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.spring.model.Arrendador;
import pe.edu.upc.spring.repository.IArrendadorRepository;
import pe.edu.upc.spring.service.IArrendadorService;

@Service
public class ArrendadorServiceImpl implements IArrendadorService {

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private IArrendadorRepository dArrendador;
	
	@Override
	@Transactional
	public boolean registrar(Arrendador arrendador) {
		Arrendador objArrendador = dArrendador.save(arrendador);
		if (objArrendador == null)
			return false;
		else
			return true;
	}

	@Override
	@Transactional
	public void eliminar(int idArrendador) {
		dArrendador.deleteById(idArrendador);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Arrendador> listarId(int idArrendador) {
		return dArrendador.findById(idArrendador);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Arrendador> buscarNombre(String nomArrendador) {
		return dArrendador.buscarNombre(nomArrendador);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Arrendador> listar() {
		return dArrendador.findAll();
	}

	@Override
	public Arrendador findByUsername(String username) {
		
		return dArrendador.findByUsername(username);
	}
	
	@Override
	public Arrendador registrarS(Arrendador arrendador) {
		arrendador.setPassword(passwordEncoder.encode(arrendador.getPassword()));
		return dArrendador.save(arrendador);
	}

}
