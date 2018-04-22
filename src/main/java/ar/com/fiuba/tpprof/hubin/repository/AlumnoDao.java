package ar.com.fiuba.tpprof.hubin.repository;

import org.springframework.data.repository.CrudRepository;

import ar.com.fiuba.tpprof.hubin.model.Alumno;

public interface AlumnoDao extends CrudRepository<Alumno, Integer> {
	
	Alumno findByUsername(String username);

}
