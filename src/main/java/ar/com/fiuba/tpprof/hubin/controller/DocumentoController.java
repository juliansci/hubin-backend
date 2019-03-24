package ar.com.fiuba.tpprof.hubin.controller;

import java.util.List;

import ar.com.fiuba.tpprof.hubin.dto.*;
import ar.com.fiuba.tpprof.hubin.exception.InvalidMateriaException;
import ar.com.fiuba.tpprof.hubin.exception.InvalidProveedorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import ar.com.fiuba.tpprof.hubin.exception.InvalidDocumentoException;
import ar.com.fiuba.tpprof.hubin.service.DocumentoService;

@Controller
@CrossOrigin
@RequestMapping(value = "/documento", consumes = "application/json", produces = "application/json")
public class DocumentoController {

	@Autowired
	private DocumentoService documentoService;

	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	public DocumentoResponseDTO createDocumento(@RequestBody DocumentoRequestDTO documentoRequestDTO) throws InvalidDocumentoException {
		return documentoService.crearDocumento(documentoRequestDTO);
	}

	@RequestMapping(value="/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public DocumentoResponseDTO updateDocumento(@PathVariable("id") int id, @RequestBody DocumentoRequestDTO documentoRequestDTO) throws InvalidDocumentoException {
		return documentoService.updateDocumento(id, documentoRequestDTO);
	}

	@RequestMapping(value="/{id}/version", method = RequestMethod.POST, consumes = {"multipart/form-data"})
	@ResponseBody
	public DocumentoResponseDTO updateDocumento(@PathVariable("id") int id, @RequestParam("file") MultipartFile file) throws InvalidDocumentoException {
		return documentoService.crearVersion(id, file);
	}

	@RequestMapping(value="/{idDocumento}", method = RequestMethod.GET)
	@ResponseBody
	public DocumentoResponseDTO getDocumento(@PathVariable("idDocumento") int idDocumento) throws InvalidDocumentoException {
		return documentoService.getDocumento(idDocumento);
	}

	@RequestMapping(value="/{idDocumento}/version/{idVersion}", method = RequestMethod.GET)
	@ResponseBody
	public FileResponseDTO getDocumento(@PathVariable("idDocumento") int idDocumento, @PathVariable("idVersion") int idVersion) throws InvalidDocumentoException {
		return documentoService.getDocumento(idDocumento, idVersion);
	}

	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public List<DocumentoResponseDTO> getDocumentos(@RequestParam(value = "name", required = false) String nombre,
			@RequestParam(value = "entity", required = false) List<Integer> idEntidad,
			@RequestParam(value = "subject", required = false) List<Integer> idMateria,
			@RequestParam(value = "language", required = false) List<Integer> idIdioma,
			@RequestParam(value = "level", required = false) List<Integer> idNivel) throws InvalidDocumentoException {
		return documentoService.getDocumentos(nombre, idEntidad, idMateria, idIdioma, idNivel);
	}

	@RequestMapping(value="/{idDocumento}/comentarios", method = RequestMethod.GET)
	@ResponseBody
	public List<ComentarioResponseDTO> getComentarios(@PathVariable("idDocumento") int idDocumento) throws InvalidDocumentoException {
		return documentoService.getComentarios(idDocumento);
	}

	@RequestMapping(value="/{idDocumento}/relacionados", method = RequestMethod.GET)
	@ResponseBody
	public List<DocumentoResponseDTO> getDocumentosRelacionados(@PathVariable("idDocumento") int idDocumento) throws InvalidDocumentoException {
		return documentoService.getDocumentosRelacionados(idDocumento);
	}

	@RequestMapping(value="/{idDocumento}", method = RequestMethod.DELETE)
	@ResponseBody
	public void deleteDocumento(@PathVariable("idDocumento") int id) throws InvalidDocumentoException {
		documentoService.deleteDocumento(id);
	}


	@RequestMapping(value="/restore/{idDocumento}", method = RequestMethod.POST)
	@ResponseBody
	public void restoreDocumento(@PathVariable("idDocumento") int id) throws InvalidDocumentoException {
		documentoService.restoreDocumento(id);
	}
	@RequestMapping(value="/{idDocumento}/version/{idVersion}", method = RequestMethod.DELETE)
	@ResponseBody
	public void removeVersionDocumento(@PathVariable("idDocumento") int idDocumento, @PathVariable("idVersion") int idVersion) throws InvalidDocumentoException {
		documentoService.removeVersionDocumento(idDocumento, idVersion);
	}

	@RequestMapping(value="/compartir/alumnos/{idDocumento}", method = RequestMethod.GET)
	@ResponseBody
	public List<AlumnoCompartirResponseDTO> getAlumnosCompartirDocumento(@PathVariable("idDocumento") int idDocumento) throws InvalidDocumentoException {
		return documentoService.getAlumnosCompartir(idDocumento);
	}


	@RequestMapping(value="/shared/{idDocumento}/alumno/{idAlumno}", method = RequestMethod.POST)
	@ResponseBody
	public DocumentoResponseDTO addAlumnoSharedDocumento(@PathVariable("idDocumento") int idDocumento, @PathVariable("idAlumno") int idAlumno) throws InvalidDocumentoException {
		return documentoService.addAlumnoShared(idDocumento, idAlumno);
	}

	@RequestMapping(value="/shared/{idDocumento}/alumno/{idAlumno}", method = RequestMethod.DELETE)
	@ResponseBody
	public DocumentoResponseDTO removeAlumnoSharedDocumento(@PathVariable("idDocumento") int idDocumento, @PathVariable("idAlumno") int idAlumno) throws InvalidDocumentoException {
		return documentoService.removeAlumnoShared(idDocumento, idAlumno);
	}


    @RequestMapping(value = "/follow/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Boolean checkFollowMateria(@PathVariable("id") int id) throws InvalidDocumentoException {
        return documentoService.checkFollow(id);
    }
}

