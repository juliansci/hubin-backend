package ar.com.fiuba.tpprof.hubin.service;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.fiuba.tpprof.hubin.dto.AlumnoDocumentosResponseDTO;
import ar.com.fiuba.tpprof.hubin.dto.AlumnoRequestDTO;
import ar.com.fiuba.tpprof.hubin.dto.AlumnoResponseDTO;
import ar.com.fiuba.tpprof.hubin.dto.AlumnoUpdateRequestDTO;
import ar.com.fiuba.tpprof.hubin.exception.InvalidAlumnoException;
import ar.com.fiuba.tpprof.hubin.model.Alumno;
import ar.com.fiuba.tpprof.hubin.repository.AlumnoDao;

@Service
public class AlumnoService {

	@Autowired
	private AlumnoDao alumnoDao;

	public AlumnoResponseDTO crearAlumno(AlumnoRequestDTO alumnoRequestDTO) throws InvalidAlumnoException {
		if (alumnoRequestDTO.getUsername() == null || alumnoRequestDTO.getPassword() == null) {
			throw new InvalidAlumnoException("Datos incompletos");
		}
		if (alumnoDao.findByUsername(alumnoRequestDTO.getUsername()) != null) {
			throw new InvalidAlumnoException("El nombre de usuario ya existe");
		}
		Alumno alumno = null;
		try {
			alumno = new Alumno(alumnoRequestDTO);
		} catch (ParseException e) {
			throw new InvalidAlumnoException("Formato de fecha incorrecto");
		}
		alumnoDao.save(alumno);
		return new AlumnoResponseDTO(alumno);
	}
	
	public AlumnoResponseDTO getAlumno(String username) throws InvalidAlumnoException {
		Alumno alumno = alumnoDao.findByUsername(username);
		if (alumno == null) {
			throw new InvalidAlumnoException("El nombre de usuario no existe");
		}
		return new AlumnoResponseDTO(alumno);
	}
	
	public AlumnoResponseDTO getAlumno(int id) throws InvalidAlumnoException {
		Alumno alumno = alumnoDao.findOne(id);
		if (alumno == null) {
			throw new InvalidAlumnoException("El nombre de usuario no existe");
		}
		return new AlumnoResponseDTO(alumno);
	}

	public AlumnoResponseDTO updateAlumno(int id, AlumnoUpdateRequestDTO alumnoUpdateRequestDTO) throws InvalidAlumnoException {
		if (!alumnoUpdateRequestDTO.isValid())
			throw new InvalidAlumnoException("Datos incompletos");		
		Alumno alumno = alumnoDao.findOne(id);
		if (alumno == null) {
			throw new InvalidAlumnoException("El nombre de usuario no existe");
		}		
		try {
			alumno.update(alumnoUpdateRequestDTO);
		} catch (ParseException e) {
			throw new InvalidAlumnoException("Formato de fecha incorrecto");
		}		
		alumnoDao.save(alumno);		
		return new AlumnoResponseDTO(alumno);
	}

	public AlumnoDocumentosResponseDTO getDocumentos(int id) throws InvalidAlumnoException {
		Alumno alumno = alumnoDao.findOne(id);
		if (alumno == null) {
			throw new InvalidAlumnoException("El nombre de usuario no existe");
		}		
		return new AlumnoDocumentosResponseDTO(alumno);
	}

}
