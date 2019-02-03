package ar.com.fiuba.tpprof.hubin.repository;

import ar.com.fiuba.tpprof.hubin.model.ComentarioEntidad;
import ar.com.fiuba.tpprof.hubin.model.Entidad;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ComentarioEntidadDao extends CrudRepository<ComentarioEntidad, Integer> {

	List<ComentarioEntidad> findByEntidadOrderByIdDesc(Entidad entidad);

}
