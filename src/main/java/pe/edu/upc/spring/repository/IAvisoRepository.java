package pe.edu.upc.spring.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.spring.model.Aviso;;

@Repository
public interface IAvisoRepository extends JpaRepository<Aviso, Integer> {
	@Query("from Aviso a where a.rangoPrecio like %:rangoPrecio%")
	List<Aviso> buscarPrecio(@Param("rangoPrecio") String rangoPrecio);	
	
	@Query("from Aviso a where a.habitacion.distrito.nomDistrito like %:nomDistrito%")
	List<Aviso> buscarDistrito(@Param("nomDistrito") String nomDistrito);
	
	List<Aviso> findByFechaPublicacion(Date fechaPublicacion);
	
}
