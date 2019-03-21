package ar.com.fiuba.tpprof.hubin.repository;

import ar.com.fiuba.tpprof.hubin.model.Alumno;
import ar.com.fiuba.tpprof.hubin.model.AlumnoDocumentoObservable;
import ar.com.fiuba.tpprof.hubin.model.Documento;
import org.springframework.data.repository.CrudRepository;

import javax.print.Doc;
import java.util.List;

public interface AlumnoDocumentoObservableDao extends CrudRepository<AlumnoDocumentoObservable, Integer> {
    List<AlumnoDocumentoObservable> getAllByDocumento(Documento documento);
    AlumnoDocumentoObservable getAlumnoDocumentoObservableByAlumnoAndDocumento(Alumno alumno, Documento documento);
}
