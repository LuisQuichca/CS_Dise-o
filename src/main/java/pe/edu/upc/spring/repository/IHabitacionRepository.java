package pe.edu.upc.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.spring.model.Habitacion;

@Repository
public interface IHabitacionRepository extends JpaRepository<Habitacion, Integer> {
	@Query("from Habitacion h where h.desHabitacion like %:desHabitacion%")
	List<Habitacion> buscarDescripcion(@Param("desHabitacion") String desHabitacion);
	
	@Query("from Habitacion h where h.arrendador.nomArrendador like %:nomArrendador%")
	List<Habitacion> buscarArrendador(@Param("nomArrendador") String nomArrendador);	
	
	@Query("from Habitacion p where p.distrito.nomDistrito like %:nomDistrito%")
	List<Habitacion> buscarDistrito(@Param("nomDistrito") String nomDistrito); 

}
