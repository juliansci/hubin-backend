package ar.com.fiuba.tpprof.hubin.service;

import ar.com.fiuba.tpprof.hubin.dto.AlumnoNotificacionRequestDTO;
import ar.com.fiuba.tpprof.hubin.dto.NotificacionResponseDTO;
import ar.com.fiuba.tpprof.hubin.exception.InvalidNotificacionException;
import ar.com.fiuba.tpprof.hubin.model.*;
import ar.com.fiuba.tpprof.hubin.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NotificacionService {

    @Autowired
    private NotificacionDao notificacionDao;
    @Autowired
    private DocumentoDao documentoDao;
    @Autowired
    private MateriaDao materiaDao;
    @Autowired
    private AlumnoDao alumnoDao;
    @Autowired
    private AlumnoDocumentoObservableDao alumnoDocumentoObservableDao;

    @Autowired
    private AlumnoMateriaObservableDao alumnoMateriaObservableDao;

    public List<NotificacionResponseDTO> getAll() throws InvalidNotificacionException {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Alumno alumno = alumnoDao.findByUsername(userDetails.getUsername());
        if (alumno == null)
            throw new InvalidNotificacionException("Alumno desconocido");
        List<Notificacion> notificaciones = notificacionDao.getNotificacionByAlumnoOrderByFechaDesc(alumno);
        List<NotificacionResponseDTO> response = new ArrayList<>();
        for (Notificacion notificacion : notificaciones) {
            NotificacionResponseDTO notificationResponse = new NotificacionResponseDTO(notificacion);
            response.add(notificationResponse);
        }
        return response;
    }

    public void marcarComoLeida(int id) throws InvalidNotificacionException {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Alumno alumno = alumnoDao.findByUsername(userDetails.getUsername());
        if (alumno == null)
            throw new InvalidNotificacionException("Alumno desconocido");
        Notificacion notificacion = notificacionDao.findOne(id);
        if (notificacion == null)
            throw new InvalidNotificacionException("Notificacion no encontrada");
        if (notificacion.getAlumno().getId() != alumno.getId())
            throw new InvalidNotificacionException("Notificacion no encontrada");
        notificacion.setLeido(true);
        notificacionDao.save(notificacion);
    }

    public void notificarNuevoComentario(Documento documento) {
        for (AlumnoDocumentoObservable alumnoDocObservable : alumnoDocumentoObservableDao.getAllByDocumento(documento)) {
            Alumno alumno = alumnoDocObservable.getAlumno();
            Notificacion notificacion = new Notificacion();
            notificacion.setAlumno(alumno);
            notificacion.setDocumentoRelacionado(documento);
            notificacion.setEvento(Notificacion.EVENT_COMMENT);
            notificacionDao.save(notificacion);
        }
    }

    public void notificarActualizacion(Documento documento) {
        for (AlumnoDocumentoObservable alumnoDocObservable : alumnoDocumentoObservableDao.getAllByDocumento(documento)) {
            Alumno alumno = alumnoDocObservable.getAlumno();
            Notificacion notificacion = new Notificacion();
            notificacion.setAlumno(alumno);
            notificacion.setDocumentoRelacionado(documento);
            notificacion.setEvento(Notificacion.EVENT_UPDATE);
            notificacionDao.save(notificacion);
        }
    }

    public void notificarNuevoComentario(Materia materia) {
        for (AlumnoMateriaObservable alumnoMatObservable : alumnoMateriaObservableDao.getAllByMateria(materia)) {
            Alumno alumno = alumnoMatObservable.getAlumno();
            Notificacion notificacion = new Notificacion();
            notificacion.setAlumno(alumno);
            notificacion.setMateriaRelacionada(materia);
            notificacion.setEvento(Notificacion.EVENT_COMMENT);
            notificacionDao.save(notificacion);
        }
    }

    public void notificarNuevoDocumento(Materia materia) {
        for (AlumnoMateriaObservable alumnoMatObservable : alumnoMateriaObservableDao.getAllByMateria(materia)) {
            Alumno alumno = alumnoMatObservable.getAlumno();
            Notificacion notificacion = new Notificacion();
            notificacion.setAlumno(alumno);
            notificacion.setMateriaRelacionada(materia);
            notificacion.setEvento(Notificacion.EVENT_NEW_DOCUMENT);
            notificacionDao.save(notificacion);
        }
    }

    public void suscribirse(AlumnoNotificacionRequestDTO alumnoNotificacionRequestDTO) throws InvalidNotificacionException {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Alumno alumno = alumnoDao.findByUsername(userDetails.getUsername());
        if (alumno == null)
            throw new InvalidNotificacionException("Alumno desconocido");

        String documentoId = alumnoNotificacionRequestDTO.getDocumentoId();
        String materiaId = alumnoNotificacionRequestDTO.getMateriaId();
        if ((documentoId == null || documentoId.isEmpty()) && (materiaId == null || materiaId.isEmpty())) {
            throw new InvalidNotificacionException("Parametros invalidos");
        }
        if (documentoId != null && !documentoId.isEmpty()) {
            Documento documento = documentoDao.findOne(Integer.getInteger(documentoId));
            AlumnoDocumentoObservable alumnoDocumentoObservable = alumnoDocumentoObservableDao.getAlumnoDocumentoObservableByAlumnoAndDocumento(alumno, documento);
            if (alumnoDocumentoObservable == null) {
                alumnoDocumentoObservable = new AlumnoDocumentoObservable();
                alumnoDocumentoObservable.setAlumno(alumno);
                alumnoDocumentoObservable.setDocumento(documento);
                alumnoDocumentoObservableDao.save(alumnoDocumentoObservable);
            }
        }
        if (materiaId != null && !materiaId.isEmpty()) {
            Materia materia = materiaDao.findOne(Integer.getInteger(materiaId));
            AlumnoMateriaObservable alumnoMateriaObservable = alumnoMateriaObservableDao.getAlumnoMateriaObservableByAlumnoAndMateria(alumno, materia);
            if (alumnoMateriaObservable == null) {
                alumnoMateriaObservable = new AlumnoMateriaObservable();
                alumnoMateriaObservable.setAlumno(alumno);
                alumnoMateriaObservable.setMateria(materia);
                alumnoMateriaObservableDao.save(alumnoMateriaObservable);
            }
        }
    }

    public void desuscribirse(AlumnoNotificacionRequestDTO alumnoNotificacionRequestDTO) throws InvalidNotificacionException {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Alumno alumno = alumnoDao.findByUsername(userDetails.getUsername());
        if (alumno == null)
            throw new InvalidNotificacionException("Alumno desconocido");

        String documentoId = alumnoNotificacionRequestDTO.getDocumentoId();
        String materiaId = alumnoNotificacionRequestDTO.getMateriaId();
        if ((documentoId == null || documentoId.isEmpty()) && (materiaId == null || materiaId.isEmpty())) {
            throw new InvalidNotificacionException("Parametros invalidos");
        }
        if (documentoId != null && !documentoId.isEmpty()) {
            Documento documento = documentoDao.findOne(Integer.getInteger(documentoId));
            AlumnoDocumentoObservable alumnoDocumentoObservable = alumnoDocumentoObservableDao.getAlumnoDocumentoObservableByAlumnoAndDocumento(alumno, documento);
            if (alumnoDocumentoObservable != null) {
                alumnoDocumentoObservableDao.delete(alumnoDocumentoObservable);
            }
        }
        if (materiaId != null && !materiaId.isEmpty()) {
            Materia materia = materiaDao.findOne(Integer.getInteger(materiaId));
            AlumnoMateriaObservable alumnoMateriaObservable = alumnoMateriaObservableDao.getAlumnoMateriaObservableByAlumnoAndMateria(alumno, materia);
            if (alumnoMateriaObservable != null) {
                alumnoMateriaObservableDao.delete(alumnoMateriaObservable);
            }
        }
    }

}
