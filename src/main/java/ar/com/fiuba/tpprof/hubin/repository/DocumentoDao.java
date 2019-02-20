package ar.com.fiuba.tpprof.hubin.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import ar.com.fiuba.tpprof.hubin.model.Documento;

public interface DocumentoDao extends CrudRepository<Documento, Integer> {

	@Query("select d from Documento d where eliminado = 0 and "
			+"(:nombre is null or d.nombre LIKE CONCAT('%',:nombre,'%')) and"
			+"(:hasIdEntidad is null or d.entidad.id in (:idEntidad)) and "
			+"(:hasIdMateria is null or d.materia.id in (:idMateria)) and "
			+"(:hasIdIdioma is null or d.idioma.id in (:idIdioma)) and"
			+"(:hasIdNivel is null or d.nivel.id in (:idNivel))")
	List<Documento> buscarDocumentos(
			@Param("nombre") String nombre,
			@Param("hasIdEntidad") Boolean hasIdEntidad,
			@Param("idEntidad") List<Integer> idEntidad,
			@Param("hasIdMateria") Boolean hasIdMateria,
			@Param("idMateria") List<Integer> idMateria,
			@Param("hasIdIdioma") Boolean hasIdIdioma,
			@Param("idIdioma") List<Integer> idIdioma,
			@Param("hasIdNivel") Boolean hasIdNivel,
			@Param("idNivel") List<Integer> idNivel);

	@Query("select d from Documento d where eliminado = 0 and d.materia.id in (:idMateria)")
	List<Documento> buscarPorIdMateria(@Param("idMateria") Integer idMateria);

	@Query("select d from Documento d where eliminado = 0 and d.entidad.id in (:idEntidad)")
	List<Documento> buscarPorIdEntidad(@Param("idEntidad") Integer idEntidad);


}
