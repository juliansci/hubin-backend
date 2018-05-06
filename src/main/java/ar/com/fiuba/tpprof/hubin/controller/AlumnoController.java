package ar.com.fiuba.tpprof.hubin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import ar.com.fiuba.tpprof.hubin.dto.AlumnoDocumentosResponseDTO;
import ar.com.fiuba.tpprof.hubin.dto.AlumnoRequestDTO;
import ar.com.fiuba.tpprof.hubin.dto.AlumnoResponseDTO;
import ar.com.fiuba.tpprof.hubin.dto.AlumnoUpdateRequestDTO;
import ar.com.fiuba.tpprof.hubin.exception.InvalidAlumnoException;
import ar.com.fiuba.tpprof.hubin.service.AlumnoService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@CrossOrigin
@RequestMapping(value = "/alumno", consumes = "application/json", produces = "application/json")
public class AlumnoController {

	@Autowired
	private AlumnoService alumnoService;	

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseBody
	public AlumnoResponseDTO login() throws InvalidAlumnoException {		
		UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = userDetails.getUsername();
		return alumnoService.getAlumno(username);
		
	}

	@RequestMapping(value = "/logout", method = RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.OK)
	public void logout() {
	}

	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	public AlumnoResponseDTO createAlumno(@RequestBody AlumnoRequestDTO alumnoRequestDTO) throws InvalidAlumnoException {
		return alumnoService.crearAlumno(alumnoRequestDTO);
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	@ResponseBody
	public AlumnoResponseDTO getAlumno(@PathVariable("id") int id) throws InvalidAlumnoException {
		return alumnoService.getAlumno(id);
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public AlumnoResponseDTO updateDocumento(@PathVariable("id") int id, @RequestBody AlumnoUpdateRequestDTO alumnoUpdateRequestDTO) throws InvalidAlumnoException {
		return alumnoService.updateAlumno(id, alumnoUpdateRequestDTO);
	}
	
	@RequestMapping(value="/{id}/documentos", method = RequestMethod.GET)
	@ResponseBody
	public AlumnoDocumentosResponseDTO getDocumentos(@PathVariable("id") int id) throws InvalidAlumnoException {
		return alumnoService.getDocumentos(id);
	}

}
