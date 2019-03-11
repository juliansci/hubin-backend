package ar.com.fiuba.tpprof.hubin.repository;

import java.util.List;

import ar.com.fiuba.tpprof.hubin.model.Alumno;
import org.springframework.data.repository.CrudRepository;

import ar.com.fiuba.tpprof.hubin.model.Comentario;
import ar.com.fiuba.tpprof.hubin.model.Documento;

public interface ComentarioDao extends CrudRepository<Comentario, Integer> {

	List<Comentario> findByDocumentoOrderByIdDesc(Documento documento);
	List<Comentario> findByCreador(Alumno alumno);
}
