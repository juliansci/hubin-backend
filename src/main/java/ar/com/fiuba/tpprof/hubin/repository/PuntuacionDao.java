package ar.com.fiuba.tpprof.hubin.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ar.com.fiuba.tpprof.hubin.model.Alumno;
import ar.com.fiuba.tpprof.hubin.model.Documento;
import ar.com.fiuba.tpprof.hubin.model.Puntuacion;

public interface PuntuacionDao extends CrudRepository<Puntuacion, Integer> {
	
	List<Puntuacion> findByAlumno(Alumno alumno);
	
	List<Puntuacion> findByDocumento(Documento documento);

	Puntuacion findByDocumentoAndAlumno(Documento documento, Alumno alumno);


}
