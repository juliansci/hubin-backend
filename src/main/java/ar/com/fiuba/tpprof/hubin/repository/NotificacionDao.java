package ar.com.fiuba.tpprof.hubin.repository;

import ar.com.fiuba.tpprof.hubin.model.Alumno;
import ar.com.fiuba.tpprof.hubin.model.Notificacion;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface NotificacionDao extends CrudRepository<Notificacion, Integer> {

    List<Notificacion> getNotificacionByAlumnoOrderByFechaDesc(Alumno alumno);

}
