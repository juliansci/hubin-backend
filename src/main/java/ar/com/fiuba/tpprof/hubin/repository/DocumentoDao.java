package ar.com.fiuba.tpprof.hubin.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import ar.com.fiuba.tpprof.hubin.model.Documento;

public interface DocumentoDao extends CrudRepository<Documento, Integer> {
	
	@Query("select d from Documento d inner join d.materia m inner join m.area a inner join a.entidad e join d.idioma i join d.nivel n"
			+ " where "
			+"(:materia is null or m.nombre like %:materia%) and "
			+"(:nombre is null or d.nombre like %:nombre%) and"
			+"(:idioma is null or i.code like %:idioma%) and"
			+"(:nivel is null or n.code like %:nivel%)")
	List<Documento> buscarDocumentos(@Param("nombre") String nombre, @Param("materia") String materia, @Param("idioma") String idioma, @Param("nivel") String nivel);

}
