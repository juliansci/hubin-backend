package ar.com.fiuba.tpprof.hubin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import ar.com.fiuba.tpprof.hubin.dto.ComentarioRequestDTO;
import ar.com.fiuba.tpprof.hubin.exception.InvalidComentarioException;
import ar.com.fiuba.tpprof.hubin.model.Alumno;
import ar.com.fiuba.tpprof.hubin.model.Comentario;
import ar.com.fiuba.tpprof.hubin.model.Documento;
import ar.com.fiuba.tpprof.hubin.repository.AlumnoDao;
import ar.com.fiuba.tpprof.hubin.repository.ComentarioDao;
import ar.com.fiuba.tpprof.hubin.repository.DocumentoDao;

@Service
public class ComentarioService {
	
	@Autowired
    private ComentarioDao comentarioDao;
	
	@Autowired
	private AlumnoDao alumnoDao;
	
	@Autowired
    private DocumentoDao documentoDao;

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
		
	}

	
	
}
