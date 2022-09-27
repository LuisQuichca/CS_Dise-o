package pe.edu.upc.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.spring.model.Distrito;


@Repository
public interface IDistritoRepository extends JpaRepository<Distrito, Integer> {
	@Query("from Distrito h where h.nomDistrito like %:nomDistrito%")
	List<Distrito> buscarDistrito(@Param("nomDistrito") String nomDistrito);

}
