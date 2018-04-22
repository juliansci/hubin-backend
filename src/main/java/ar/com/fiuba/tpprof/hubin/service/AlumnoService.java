package ar.com.fiuba.tpprof.hubin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.fiuba.tpprof.hubin.exception.InvalidAlumnoException;
import ar.com.fiuba.tpprof.hubin.model.Alumno;
import ar.com.fiuba.tpprof.hubin.repository.AlumnoDao;

@Service
public class AlumnoService {

	@Autowired
	private AlumnoDao alumnoDao;

	public Alumno crearAlumno(Alumno alumno) throws InvalidAlumnoException {
		if (alumno.getUsername() == null || alumno.getPassword() == null) {
			throw new InvalidAlumnoException("Datos incompletos");
		}
		if (alumnoDao.findByUsername(alumno.getUsername()) != null) {
			throw new InvalidAlumnoException("El nombre de usuario ya existe");
		}
		Alumno alumnoNuevo = new Alumno();
		alumnoNuevo.setDni(alumno.getDni());
		alumnoNuevo.setFechaNac(alumno.getFechaNac());
		alumnoNuevo.setEmail(alumno.getEmail());
		alumnoNuevo.setFoto(alumno.getFoto());
		alumnoNuevo.setPassword(alumno.getPassword());
		alumnoNuevo.setUsername(alumno.getUsername());
		alumnoDao.save(alumnoNuevo);
		return alumnoNuevo;
	}
	
	public Alumno getAlumno(String username) throws InvalidAlumnoException {
		Alumno alumno = alumnoDao.findByUsername(username);
		if (alumno == null) {
			throw new InvalidAlumnoException("El nombre de usuario no existe");
		}
		return alumno;
	}
	
	public Alumno getAlumno(int id) throws InvalidAlumnoException {
		Alumno alumno = alumnoDao.findOne(id);
		if (alumno == null) {
			throw new InvalidAlumnoException("El nombre de usuario no existe");
		}
		return alumno;
	}

}
