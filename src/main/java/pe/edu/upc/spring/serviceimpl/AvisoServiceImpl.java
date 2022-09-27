package pe.edu.upc.spring.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.spring.model.Aviso;
import pe.edu.upc.spring.repository.IAvisoRepository;
import pe.edu.upc.spring.service.IAvisoService;


@Service
public class AvisoServiceImpl implements IAvisoService {

	@Autowired
	private IAvisoRepository aAviso;
	
	@Override
	@Transactional
	public boolean registrar(Aviso aviso) {
		Aviso objAviso = aAviso.save(aviso);
		if (objAviso == null)
			return false;
		else
			return true;
	}

	@Override
	@Transactional
	public void eliminar(int idAviso) {
		aAviso.deleteById(idAviso);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Aviso> listarId(int idAviso) {
		return aAviso.findById(idAviso);
	}
	
	@Override
	@Transactional(readOnly = true)
	public Optional<Aviso> buscarId(int idAviso) {
		return aAviso.findById(idAviso);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Aviso> listar() {
		return aAviso.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public List<Aviso> buscarPrecio(String rangoPrecio) {
		return aAviso.buscarPrecio(rangoPrecio);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Aviso> buscarDistrito(String nomDistrito) {
		return aAviso.buscarDistrito(nomDistrito);
	}
	
}
