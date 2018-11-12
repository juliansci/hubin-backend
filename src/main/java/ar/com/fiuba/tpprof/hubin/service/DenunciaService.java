package ar.com.fiuba.tpprof.hubin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import ar.com.fiuba.tpprof.hubin.dto.DenunciaRequestDTO;
import ar.com.fiuba.tpprof.hubin.exception.InvalidDenunciaException;
import ar.com.fiuba.tpprof.hubin.model.Alumno;
import ar.com.fiuba.tpprof.hubin.model.Denuncia;
import ar.com.fiuba.tpprof.hubin.model.Documento;
import ar.com.fiuba.tpprof.hubin.repository.AlumnoDao;
import ar.com.fiuba.tpprof.hubin.repository.DenunciaDao;
import ar.com.fiuba.tpprof.hubin.repository.DocumentoDao;

@Service
public class DenunciaService {
	
	@Autowired
    private DenunciaDao denunciaDao;
	
	@Autowired
	private AlumnoDao alumnoDao;
	
	@Autowired
    private DocumentoDao documentoDao;

	public void crearDenuncia(DenunciaRequestDTO denunciaRequestDTO) throws InvalidDenunciaException {

		if (!denunciaRequestDTO.isValid())
			throw new InvalidDenunciaException("Datos incompletos");
		
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Alumno alumno = alumnoDao.findByUsername(userDetails.getUsername());
		if (alumno == null)
			throw new InvalidDenunciaException("Alumno desconocido");
		
		Documento documento = documentoDao.findOne(Integer.parseInt(denunciaRequestDTO.getIdDocumento()));
		if (documento == null)
			throw new InvalidDenunciaException("Documento desconocido");
		
		Denuncia denuncia = new Denuncia(denunciaRequestDTO);
		denuncia.setCreador(alumno);
		denuncia.setDocumento(documento);
		
		denunciaDao.save(denuncia);		
	}

}
