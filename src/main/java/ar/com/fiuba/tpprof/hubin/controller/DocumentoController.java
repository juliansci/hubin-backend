package ar.com.fiuba.tpprof.hubin.controller;

import java.util.List;

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

import ar.com.fiuba.tpprof.hubin.dto.DocumentoRequestDTO;
import ar.com.fiuba.tpprof.hubin.dto.DocumentoResponseDTO;
import ar.com.fiuba.tpprof.hubin.dto.DocumentoUpdateRequestDTO;
import ar.com.fiuba.tpprof.hubin.dto.FileResponseDTO;
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
	public DocumentoResponseDTO updateDocumento(@PathVariable("id") int id, @RequestBody DocumentoUpdateRequestDTO documentoUpdateRequestDTO) throws InvalidDocumentoException {
		return documentoService.updateDocumento(id, documentoUpdateRequestDTO);
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
	public List<DocumentoResponseDTO> getDocumentos(@RequestParam(value = "nombre", required = false) String nombre,
			@RequestParam(value = "idEntidad", required = false) List<Integer> idEntidad,
			@RequestParam(value = "idMateria", required = false) List<Integer> idMateria,
			@RequestParam(value = "idIdioma", required = false) List<Integer> idIdioma,
			@RequestParam(value = "idNivel", required = false) List<Integer> idNivel) throws InvalidDocumentoException {
		return documentoService.getDocumentos(nombre, idEntidad, idMateria, idIdioma, idNivel);
	}

}
