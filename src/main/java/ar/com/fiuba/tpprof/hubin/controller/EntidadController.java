package ar.com.fiuba.tpprof.hubin.controller;

import java.util.List;

import ar.com.fiuba.tpprof.hubin.dto.EntidadResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import ar.com.fiuba.tpprof.hubin.exception.InvalidEntidadException;
import ar.com.fiuba.tpprof.hubin.service.EntidadService;

@Controller
@RequestMapping(value = "/entidad", consumes = "application/json", produces = "application/json")
public class EntidadController {

	@Autowired
	private EntidadService entidadService;

	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public List<EntidadResponseDTO> getAllEntidades() {
		return entidadService.getAllEntidades();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public EntidadResponseDTO getEntidad(@PathVariable("id") int id) throws InvalidEntidadException {
		return entidadService.getEntidad(id);
	}

}
