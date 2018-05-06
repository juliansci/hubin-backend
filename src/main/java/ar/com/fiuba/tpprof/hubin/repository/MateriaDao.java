package ar.com.fiuba.tpprof.hubin.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ar.com.fiuba.tpprof.hubin.model.Materia;

public interface MateriaDao extends CrudRepository<Materia, Integer> {
	
	List<Materia> findTop8ByDestacadaTrue();

}
