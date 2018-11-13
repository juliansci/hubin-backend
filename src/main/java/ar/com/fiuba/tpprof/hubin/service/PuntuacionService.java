package ar.com.fiuba.tpprof.hubin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import ar.com.fiuba.tpprof.hubin.dto.PuntuacionRequestDTO;
import ar.com.fiuba.tpprof.hubin.exception.InvalidPuntuacionException;
import ar.com.fiuba.tpprof.hubin.model.Alumno;
import ar.com.fiuba.tpprof.hubin.model.Documento;
import ar.com.fiuba.tpprof.hubin.model.Puntuacion;
import ar.com.fiuba.tpprof.hubin.repository.AlumnoDao;
import ar.com.fiuba.tpprof.hubin.repository.DocumentoDao;
import ar.com.fiuba.tpprof.hubin.repository.PuntuacionDao;

@Service
public class PuntuacionService {
	
	@Autowired
	private PuntuacionDao puntuacionDao;
	
	@Autowired
	private AlumnoDao alumnoDao;
	
	@Autowired
    private DocumentoDao documentoDao;

	public void crearPuntuacion(PuntuacionRequestDTO puntuacionRequestDTO) throws InvalidPuntuacionException {
		
		if (!puntuacionRequestDTO.isValid())
			throw new InvalidPuntuacionException("Datos incompletos");
		
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Alumno alumno = alumnoDao.findByUsername(userDetails.getUsername());
		if (alumno == null)
			throw new InvalidPuntuacionException("Alumno desconocido");
		
		Documento documento = documentoDao.findOne(Integer.parseInt(puntuacionRequestDTO.getIdDocumento()));
		if (documento == null)
			throw new InvalidPuntuacionException("Documento desconocido");


		Puntuacion puntuacionGuardada = puntuacionDao.findByDocumentoAndAlumno(documento, alumno);
		if(puntuacionGuardada != null){
			puntuacionGuardada.setPuntuacion(Integer.parseInt(puntuacionRequestDTO.getPuntuacion()));
			puntuacionDao.save(puntuacionGuardada);
		}else{
			Puntuacion puntuacion = new Puntuacion(puntuacionRequestDTO);
			puntuacion.setAlummno(alumno);
			puntuacion.setDocumento(documento);
			puntuacionDao.save(puntuacion);
		}
		List<Puntuacion> puntuaciones = puntuacionDao.findByDocumento(documento);
		int totalAcumulado = puntuaciones.stream().mapToInt(Puntuacion::getPuntuacion).sum();
		Double totalPuntuacion = totalAcumulado / new Double(puntuaciones.size());
		documento.setPuntuacion(totalPuntuacion);
		documento.setPuntuacionCantidad(puntuaciones.size());
		documentoDao.save(documento);
		
	}

}
