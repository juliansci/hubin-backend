package ar.com.fiuba.tpprof.hubin.service;

import ar.com.fiuba.tpprof.hubin.dto.ObjetivoResponseDTO;
import ar.com.fiuba.tpprof.hubin.exception.InvalidObjetivoException;
import ar.com.fiuba.tpprof.hubin.model.Alumno;
import ar.com.fiuba.tpprof.hubin.model.AlumnoObjetivo;
import ar.com.fiuba.tpprof.hubin.model.Objetivo;
import ar.com.fiuba.tpprof.hubin.repository.AlumnoDao;
import ar.com.fiuba.tpprof.hubin.repository.AlumnoObjetivoDao;
import ar.com.fiuba.tpprof.hubin.repository.ObjetivoDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ObjetivoService {

    @Autowired
    private AlumnoDao alumnoDao;

    @Autowired
    private ObjetivoDao objetivoDao;

    @Autowired
    private AlumnoObjetivoDao alumnoObjetivoDao;

    public List<ObjetivoResponseDTO> getAll() throws InvalidObjetivoException {
        List<Objetivo> objetivos = (List) objetivoDao.findAllByOrderByOrdenAsc();
        List<ObjetivoResponseDTO> objetivosResponse = new ArrayList<>();
        for (Objetivo objetivo : objetivos) {
            if (objetivo.isActivo()) {
                ObjetivoResponseDTO objetivoResponseActual = new ObjetivoResponseDTO(objetivo);
                objetivosResponse.add(objetivoResponseActual);
            }
        }
        return objetivosResponse;
    }

    public List<ObjetivoResponseDTO> getAllAlumno(int id) throws InvalidObjetivoException {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Alumno alumnoLogged = alumnoDao.findByUsername(userDetails.getUsername());
        if (alumnoLogged == null)
            throw new InvalidObjetivoException("El usuario no esta loggeado");
        Alumno alumno = alumnoDao.findOne(id);
        if (alumno == null)
            throw new InvalidObjetivoException("El usuario no existe");
        List<AlumnoObjetivo> alumnoObjetivos = (List) alumnoObjetivoDao.findByAlumnoOrderByFechaDesc(alumno);
        List<ObjetivoResponseDTO> objetivosResponse = new ArrayList<>();
        for (AlumnoObjetivo alumnoObjetivo : alumnoObjetivos) {
            Objetivo objetivo = alumnoObjetivo.getObjetivo();
            if (objetivo.isActivo()) {
                ObjetivoResponseDTO objetivoResponseActual = new ObjetivoResponseDTO(objetivo);
                objetivosResponse.add(objetivoResponseActual);
            }
        }
        return objetivosResponse;
    }

}
