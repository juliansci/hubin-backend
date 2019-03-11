package ar.com.fiuba.tpprof.hubin.repository;

import ar.com.fiuba.tpprof.hubin.model.Alumno;
import ar.com.fiuba.tpprof.hubin.model.ComentarioEntidad;
import ar.com.fiuba.tpprof.hubin.model.ComentarioMateria;
import ar.com.fiuba.tpprof.hubin.model.Materia;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ComentarioMateriaDao extends CrudRepository<ComentarioMateria, Integer> {

    List<ComentarioMateria> findByMateriaOrderByIdDesc(Materia materia);

    List<ComentarioMateria> findByCreador(Alumno alumno);

}
