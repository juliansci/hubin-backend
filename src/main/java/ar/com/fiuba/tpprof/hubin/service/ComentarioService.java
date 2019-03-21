package ar.com.fiuba.tpprof.hubin.service;

import ar.com.fiuba.tpprof.hubin.dto.ComentarioEntidadRequestDTO;
import ar.com.fiuba.tpprof.hubin.dto.ComentarioMateriaRequestDTO;
import ar.com.fiuba.tpprof.hubin.model.*;
import ar.com.fiuba.tpprof.hubin.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import ar.com.fiuba.tpprof.hubin.dto.ComentarioRequestDTO;
import ar.com.fiuba.tpprof.hubin.exception.InvalidComentarioException;

@Service
public class ComentarioService {

	@Autowired
    private ComentarioDao comentarioDao;
	@Autowired
	private ComentarioEntidadDao comentarioEntidadDao;
	@Autowired
	private ComentarioMateriaDao comentarioMateriaDao;
	@Autowired
	private AlumnoDao alumnoDao;

	@Autowired
    private DocumentoDao documentoDao;

	@Autowired
	private EntidadDao entidadDao;

	@Autowired
	private MateriaDao materiaDao;

	@Autowired
	private ObjetivoAlumnoService objetivoAlumnoService;

	@Autowired
	private NotificacionService notificacionService;
	public void crearComentario(ComentarioRequestDTO comentarioRequestDTO) throws InvalidComentarioException {

		if (!comentarioRequestDTO.isValid())
			throw new InvalidComentarioException("Datos incompletos");

		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Alumno alumno = alumnoDao.findByUsername(userDetails.getUsername());
		if (alumno == null)
			throw new InvalidComentarioException("Alumno desconocido");

		Documento documento = documentoDao.findOne(Integer.parseInt(comentarioRequestDTO.getIdDocumento()));
		if (documento == null)
			throw new InvalidComentarioException("Documento desconocido");

		Comentario comentario = new Comentario(comentarioRequestDTO);
		comentario.setCreador(alumno);
		comentario.setDocumento(documento);
		comentarioDao.save(comentario);
		objetivoAlumnoService.checkComments(alumno);
		notificacionService.notificarNuevoComentario(documento);
	}

	public void crearComentario(ComentarioEntidadRequestDTO comentarioRequestDTO) throws InvalidComentarioException {

		if (!comentarioRequestDTO.isValid())
			throw new InvalidComentarioException("Datos incompletos");

		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Alumno alumno = alumnoDao.findByUsername(userDetails.getUsername());
		if (alumno == null)
			throw new InvalidComentarioException("Alumno desconocido");

		Entidad entidad = entidadDao.findOne(Integer.parseInt(comentarioRequestDTO.getIdEntidad()));
		if (entidad == null)
			throw new InvalidComentarioException("Entidad desconocida");

		ComentarioEntidad comentario = new ComentarioEntidad(comentarioRequestDTO);
		comentario.setCreador(alumno);
		comentario.setEntidad(entidad);
		comentarioEntidadDao.save(comentario);
		objetivoAlumnoService.checkComments(alumno);
	}

	public void crearComentario(ComentarioMateriaRequestDTO comentarioRequestDTO) throws InvalidComentarioException {

		if (!comentarioRequestDTO.isValid())
			throw new InvalidComentarioException("Datos incompletos");

		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Alumno alumno = alumnoDao.findByUsername(userDetails.getUsername());
		if (alumno == null)
			throw new InvalidComentarioException("Alumno desconocido");

		Materia materia = materiaDao.findOne(Integer.parseInt(comentarioRequestDTO.getIdMateria()));
		if (materia == null)
			throw new InvalidComentarioException("Materia desconocida");

		ComentarioMateria comentario = new ComentarioMateria(comentarioRequestDTO);
		comentario.setCreador(alumno);
		comentario.setMateria(materia);
		comentarioMateriaDao.save(comentario);
		objetivoAlumnoService.checkComments(alumno);
		notificacionService.notificarNuevoComentario(materia);

	}
}
