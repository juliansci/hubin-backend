package ar.com.fiuba.tpprof.hubin.service;

import ar.com.fiuba.tpprof.hubin.model.*;
import ar.com.fiuba.tpprof.hubin.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ObjetivoAlumnoService {

    @Autowired
    private AlumnoObjetivoDao alumnoObjetivoDao;

    @Autowired
    private ObjetivoDao objetivoDao;

    @Autowired
    private DocumentoDao documentoDao;

    @Autowired
    private ComentarioDao comentarioDao;

    @Autowired
    private ComentarioEntidadDao comentarioEntidadDao;

    @Autowired
    private ComentarioMateriaDao comentarioMateriaDao;

    @Autowired
    private PuntuacionDao puntuacionDao;

    public void checkUploads(Alumno alumno) {

        List<Documento> documentosAlumno = documentoDao.findByCreador(alumno);
        if (documentosAlumno.size() >= 1) {
            Objetivo objetivo = objetivoDao.findByTipo(Objetivo.type_upload_1);
            AlumnoObjetivo alumnoObjetivoUpload1 = alumnoObjetivoDao.findByAlumnoAndObjetivo(alumno, objetivo);
            if (alumnoObjetivoUpload1 == null) {
                createAlumnoObjetivo(alumno, objetivo);
            }
        }
        if (documentosAlumno.size() >= 5) {
            Objetivo objetivo = objetivoDao.findByTipo(Objetivo.type_upload_5);
            AlumnoObjetivo alumnoObjetivoUpload5 = alumnoObjetivoDao.findByAlumnoAndObjetivo(alumno, objetivo);
            if (alumnoObjetivoUpload5 == null) {
                createAlumnoObjetivo(alumno, objetivo);
            }
        }
    }

    public void checkComments(Alumno alumno) {
        List<Comentario> comentariosAlumno = comentarioDao.findByCreador(alumno);
        List<ComentarioEntidad> comentariosEntidadAlumno = comentarioEntidadDao.findByCreador(alumno);
        List<ComentarioMateria> comentariosMateriaAlumno = comentarioMateriaDao.findByCreador(alumno);
        if ((comentariosAlumno.size() +
                comentariosEntidadAlumno.size() +
                comentariosMateriaAlumno.size())
                >= 1) {
            Objetivo objetivo = objetivoDao.findByTipo(Objetivo.type_comment_1);
            AlumnoObjetivo alumnoObjetivoComment1 = alumnoObjetivoDao.findByAlumnoAndObjetivo(alumno, objetivo);
            if (alumnoObjetivoComment1 == null) {
                createAlumnoObjetivo(alumno, objetivo);
            }
        }
        if ((comentariosAlumno.size() +
                comentariosEntidadAlumno.size() +
                comentariosMateriaAlumno.size()) >= 5) {
            Objetivo objetivo = objetivoDao.findByTipo(Objetivo.type_comment_5);
            AlumnoObjetivo alumnoObjetivoComment5 = alumnoObjetivoDao.findByAlumnoAndObjetivo(alumno, objetivo);
            if (alumnoObjetivoComment5 == null) {
                createAlumnoObjetivo(alumno, objetivo);
            }
        }
    }

    public void checkScore(Alumno alumno) {
        List<Puntuacion> puntuacionAlumno = puntuacionDao.findByAlumno(alumno);
        if (puntuacionAlumno.size() >= 1) {
            Objetivo objetivo = objetivoDao.findByTipo(Objetivo.type_rate_1);
            AlumnoObjetivo alumnoObjetivoRate1 = alumnoObjetivoDao.findByAlumnoAndObjetivo(alumno, objetivo);
            if (alumnoObjetivoRate1 == null) {
                createAlumnoObjetivo(alumno, objetivo);
            }
        }
        if (puntuacionAlumno.size() >= 5) {
            Objetivo objetivo = objetivoDao.findByTipo(Objetivo.type_rate_5);
            AlumnoObjetivo alumnoObjetivoRate5 = alumnoObjetivoDao.findByAlumnoAndObjetivo(alumno, objetivo);
            if (alumnoObjetivoRate5 == null) {
                createAlumnoObjetivo(alumno, objetivo);
            }
        }
    }

    public void checkProfile(Alumno alumno) {
        Objetivo objetivo = objetivoDao.findByTipo(Objetivo.type_update_profile);
        AlumnoObjetivo alumnoObjetivo = alumnoObjetivoDao.findByAlumnoAndObjetivo(alumno, objetivo);
        if (alumnoObjetivo == null) {
            createAlumnoObjetivo(alumno, objetivo);
        }
    }

    private void createAlumnoObjetivo(Alumno alumno, Objetivo objetivo) {
        AlumnoObjetivo alumnoObjetivo = new AlumnoObjetivo();
        alumnoObjetivo.setAlumno(alumno);
        alumnoObjetivo.setObjetivo(objetivo);
        alumnoObjetivoDao.save(alumnoObjetivo);
    }
}
