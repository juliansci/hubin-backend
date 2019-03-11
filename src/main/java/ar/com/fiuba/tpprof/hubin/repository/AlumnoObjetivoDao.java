package ar.com.fiuba.tpprof.hubin.repository;

import ar.com.fiuba.tpprof.hubin.model.*;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AlumnoObjetivoDao extends CrudRepository<AlumnoObjetivo, Integer> {
	List<AlumnoObjetivo> findByAlumnoOrderByFechaDesc(Alumno alumno);
	AlumnoObjetivo findByAlumnoAndObjetivo(Alumno alumno, Objetivo objetivo);

}
