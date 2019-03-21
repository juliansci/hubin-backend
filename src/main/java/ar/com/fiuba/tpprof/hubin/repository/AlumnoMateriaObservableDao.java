package ar.com.fiuba.tpprof.hubin.repository;

import ar.com.fiuba.tpprof.hubin.model.*;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AlumnoMateriaObservableDao extends CrudRepository<AlumnoMateriaObservable, Integer> {
    List<AlumnoMateriaObservable> getAllByMateria(Materia materia);
    AlumnoMateriaObservable getAlumnoMateriaObservableByAlumnoAndMateria(Alumno alumno, Materia materia);
}
