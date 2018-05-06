package ar.com.fiuba.tpprof.hubin.service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.fiuba.tpprof.hubin.dto.DocumentoRequestDTO;
import ar.com.fiuba.tpprof.hubin.dto.DocumentoResponseDTO;
import ar.com.fiuba.tpprof.hubin.dto.DocumentoUpdateDataRequestDTO;
import ar.com.fiuba.tpprof.hubin.dto.DocumentoUpdateRequestDTO;
import ar.com.fiuba.tpprof.hubin.dto.VersionResponseDTO;
import ar.com.fiuba.tpprof.hubin.exception.InvalidDocumentoException;
import ar.com.fiuba.tpprof.hubin.model.Alumno;
import ar.com.fiuba.tpprof.hubin.model.Documento;
import ar.com.fiuba.tpprof.hubin.model.Idioma;
import ar.com.fiuba.tpprof.hubin.model.Materia;
import ar.com.fiuba.tpprof.hubin.model.Nivel;
import ar.com.fiuba.tpprof.hubin.model.Version;
import ar.com.fiuba.tpprof.hubin.repository.AlumnoDao;
import ar.com.fiuba.tpprof.hubin.repository.DocumentoDao;
import ar.com.fiuba.tpprof.hubin.repository.IdiomaDao;
import ar.com.fiuba.tpprof.hubin.repository.MateriaDao;
import ar.com.fiuba.tpprof.hubin.repository.NivelDao;

@Service
public class DocumentoService {
	
	@Autowired
	private DocumentoDao documentoDao;
	
	@Autowired
	private AlumnoDao alumnoDao;
	
	@Autowired
	private MateriaDao materiaDao;
	
	@Autowired
	private IdiomaDao idiomaDao;
	
	@Autowired
	private NivelDao nivelDao;

	public DocumentoResponseDTO crearDocumento(DocumentoRequestDTO documentoRequestDTO) throws InvalidDocumentoException {

		if (!documentoRequestDTO.isValid())
			throw new InvalidDocumentoException("Datos incompletos");
		
		Documento documento = null;
		try {
			documento = new Documento(documentoRequestDTO);
		} catch (ParseException e) {
			throw new InvalidDocumentoException("Formato de fecha incorrecto");
		}
		
		Alumno alumno = alumnoDao.findOne(Integer.parseInt(documentoRequestDTO.getIdCreador()));
		if (alumno == null)
			throw new InvalidDocumentoException("Alumno desconocido");
		
		Materia materia = materiaDao.findOne(Integer.parseInt(documentoRequestDTO.getIdMateria()));
		if (materia == null)
			throw new InvalidDocumentoException("Materia desconocida");
		
		Idioma idioma = idiomaDao.findOne(Integer.parseInt(documentoRequestDTO.getIdIdioma()));
		if (idioma == null)
			throw new InvalidDocumentoException("Idioma desconocido");
		
		Nivel nivel = nivelDao.findOne(Integer.parseInt(documentoRequestDTO.getIdNivel()));
		if (nivel == null)
			throw new InvalidDocumentoException("Nivel desconocido");
		
		Version version = new Version();
		version.setData(documentoRequestDTO.getData().getBytes());
		version.setDocumento(documento);
		
		documento.addVersion(version);
		documento.setCreador(alumno);
		documento.setMateria(materia);
		documento.setIdioma(idioma);
		documento.setNivel(nivel);
		
		documentoDao.save(documento);
		
		return new DocumentoResponseDTO(documento);
	}

	public DocumentoResponseDTO updateDocumento(int id, DocumentoUpdateRequestDTO documentoUpdateRequestDTO) throws InvalidDocumentoException {
		
		if (!documentoUpdateRequestDTO.isValid())
			throw new InvalidDocumentoException("Datos incompletos");
		
		Documento documento = documentoDao.findOne(id);
		if (documento == null)
			throw new InvalidDocumentoException("Documento inexistente");
		
		if (!documentoUpdateRequestDTO.getCompartidos().isEmpty()) {
			for (String idAlumno : documentoUpdateRequestDTO.getCompartidos()) {				
				Alumno alumno = alumnoDao.findOne(Integer.parseInt(idAlumno));
				if (alumno == null) {
					throw new InvalidDocumentoException("Alumno a compartir desconocido");
				}
				else { 
					List<Alumno> compartidos = documento.getCompartidos();
					if (!compartidos.contains(alumno)) {
						compartidos.add(alumno);
					}					
				}
			}
		}
		
		Idioma idioma = idiomaDao.findOne(Integer.parseInt(documentoUpdateRequestDTO.getIdIdioma()));
		documento.setIdioma(idioma);
		Nivel nivel = nivelDao.findOne(Integer.parseInt(documentoUpdateRequestDTO.getIdNivel()));
		documento.setNivel(nivel);
		documento.update(documentoUpdateRequestDTO);
		
		documentoDao.save(documento);
		
		return new DocumentoResponseDTO(documento);
	}

	public DocumentoResponseDTO updateDocumento(int id, DocumentoUpdateDataRequestDTO documentoUpdateDataRequestDTO) throws InvalidDocumentoException {
		
		if (!documentoUpdateDataRequestDTO.isValid())
			throw new InvalidDocumentoException("Datos incompletos");
		
		Documento documento = documentoDao.findOne(id);
		if (documento == null)
			throw new InvalidDocumentoException("Documento inexistente");
		
		try {
			documento.setFechaUltModificacion(documentoUpdateDataRequestDTO.getFechaUltModificacion());
		} catch (ParseException e) {
			throw new InvalidDocumentoException("Formato de fecha incorrecto");
		}
		
		Version version = new Version();
		version.setData(documentoUpdateDataRequestDTO.getData().getBytes());
		version.setDocumento(documento);
		
		documento.addVersion(version);
		
		documentoDao.save(documento);
		
		return new DocumentoResponseDTO(documento);
	}
	
	public DocumentoResponseDTO getDocumento(int idDocumento) throws InvalidDocumentoException {		
		Documento documento = documentoDao.findOne(idDocumento);
		if (documento == null)
			throw new InvalidDocumentoException("Documento inexistente");
		return new DocumentoResponseDTO(documento);
	}

	public VersionResponseDTO getDocumento(int idDocumento, int idVersion) throws InvalidDocumentoException {		
		Documento documento = documentoDao.findOne(idDocumento);
		if (documento == null)
			throw new InvalidDocumentoException("Documento inexistente");
		List<Version> versiones = documento.getVersiones();
		for (Version version : versiones) {
			if (version.getId().equals(idVersion)) {
				return new VersionResponseDTO(version);
			}
		}
		throw new InvalidDocumentoException("Version de documento inexistente");
	}

	public List<DocumentoResponseDTO> getDocumentos(String nombre, String materia, String idioma, String nivel) {	
		List<Documento> documentos = documentoDao.buscarDocumentos(nombre, materia, idioma, nivel);
		List<DocumentoResponseDTO> documentosResponse = new ArrayList<DocumentoResponseDTO>();
		for (Documento documento : documentos) {
			documentosResponse.add(new DocumentoResponseDTO(documento));
		}
		return documentosResponse;
	}

}
