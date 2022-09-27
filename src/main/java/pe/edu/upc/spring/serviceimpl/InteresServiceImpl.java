package pe.edu.upc.spring.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.spring.model.Interes;
import pe.edu.upc.spring.repository.IInteresRepository;
import pe.edu.upc.spring.service.IInteresService;

@Service
public class InteresServiceImpl implements IInteresService {

	@Autowired
	private IInteresRepository dInteres;
	
	@Override
	@Transactional
	public boolean registrar(Interes interes) {
		Interes objInteres = dInteres.save(interes);
		if (objInteres == null)
			return false;
		else
			return true;
	}

	@Override
	@Transactional
	public void eliminar(int idInteres) {
		dInteres.deleteById(idInteres);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Interes> listarId(int idInteres) {
		return dInteres.findById(idInteres);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Interes> listar() {
		return dInteres.findAll();
	}

}
