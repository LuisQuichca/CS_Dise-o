package pe.edu.upc.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.spring.model.Arrendador;

@Repository
public interface IArrendadorRepository extends JpaRepository<Arrendador, Integer> {
	@Query("from Arrendador a where a.nomArrendador like %:nomArrendador%")
	List<Arrendador> buscarNombre(@Param("nomArrendador") String nomArrendador);

	
	public Arrendador findByUsername(String username);
	

}
