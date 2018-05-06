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

import ar.com.fiuba.tpprof.hubin.dto.DocumentoRequestDTO;
import ar.com.fiuba.tpprof.hubin.dto.DocumentoResponseDTO;
import ar.com.fiuba.tpprof.hubin.dto.DocumentoUpdateDataRequestDTO;
import ar.com.fiuba.tpprof.hubin.dto.DocumentoUpdateRequestDTO;
import ar.com.fiuba.tpprof.hubin.dto.VersionResponseDTO;
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
	
	@RequestMapping(value="/{id}/version", method = RequestMethod.PUT)
	@ResponseBody
	public DocumentoResponseDTO updateDocumento(@PathVariable("id") int id, @RequestBody DocumentoUpdateDataRequestDTO documentoUpdateDataRequestDTO) throws InvalidDocumentoException {
		return documentoService.updateDocumento(id, documentoUpdateDataRequestDTO);
	}
	
	@RequestMapping(value="/{idDocumento}", method = RequestMethod.GET)
	@ResponseBody
	public DocumentoResponseDTO getDocumento(@PathVariable("idDocumento") int idDocumento) throws InvalidDocumentoException {
		return documentoService.getDocumento(idDocumento);
	}
	
	@RequestMapping(value="/{idDocumento}/version/{idVersion}", method = RequestMethod.GET)
	@ResponseBody
	public VersionResponseDTO getDocumento(@PathVariable("idDocumento") int idDocumento, @PathVariable("idVersion") int idVersion) throws InvalidDocumentoException {
		return documentoService.getDocumento(idDocumento, idVersion);
	}
	
	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public List<DocumentoResponseDTO> getDocumentos(@RequestParam(value = "nombre", required = false) String nombre,
			@RequestParam(value = "materia", required = false) String materia,
			@RequestParam(value = "idioma", required = false) String idioma,
			@RequestParam(value = "nivel", required = false) String nivel) throws InvalidDocumentoException {
		return documentoService.getDocumentos(nombre, materia, idioma, nivel);
	}

}
