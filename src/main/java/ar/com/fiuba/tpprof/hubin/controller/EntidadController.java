package ar.com.fiuba.tpprof.hubin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import ar.com.fiuba.tpprof.hubin.exception.InvalidAreaException;
import ar.com.fiuba.tpprof.hubin.exception.InvalidEntidadException;
import ar.com.fiuba.tpprof.hubin.exception.InvalidMateriaException;
import ar.com.fiuba.tpprof.hubin.model.Area;
import ar.com.fiuba.tpprof.hubin.model.Entidad;
import ar.com.fiuba.tpprof.hubin.model.Materia;
import ar.com.fiuba.tpprof.hubin.service.EntidadService;

@Controller
@RequestMapping(value = "/entidad", consumes = "application/json", produces = "application/json")
public class EntidadController {
	
	@Autowired
	private EntidadService entidadService;
	
	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	public Entidad crearEntidad(@RequestBody Entidad entidad) {
		return entidadService.crearEntidad(entidad);
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public Entidad updateEntidad(@PathVariable("id") int id, @RequestBody Entidad entidad) throws InvalidEntidadException {
		return entidadService.updateEntidad(id, entidad);
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	@ResponseBody
	public Entidad getEntidad(@PathVariable("id") int id) throws InvalidEntidadException {
		return entidadService.getEntidad(id);
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public void deleteEntidad(@PathVariable("id") int id) throws InvalidEntidadException {
		entidadService.deleteEntidad(id);
	}
	
	@RequestMapping(value="/{id}/area", method = RequestMethod.POST)
	@ResponseBody
	public Entidad crearArea(@PathVariable("id") int id, @RequestBody Area area) throws InvalidEntidadException {
		return entidadService.crearArea(id, area);
	}
	
	@RequestMapping(value="/{idEntidad}/area/{idArea}", method = RequestMethod.PUT)
	@ResponseBody
	public Entidad updateArea(@PathVariable("idEntidad") int idEntidad, @PathVariable("idArea") int idArea, @RequestBody Area area) throws InvalidEntidadException, InvalidAreaException {
		return entidadService.updateArea(idEntidad, idArea, area);
	}
	
	@RequestMapping(value="/{idEntidad}/area/{idArea}", method = RequestMethod.DELETE)
	@ResponseBody
	public void deleteArea(@PathVariable("idEntidad") int idEntidad, @PathVariable("idArea") int idArea) throws InvalidEntidadException, InvalidAreaException {
		entidadService.deleteArea(idEntidad, idArea);
	}
	
	@RequestMapping(value="/{idEntidad}/area/{idArea}/materia", method = RequestMethod.POST)
	@ResponseBody
	public Entidad crearMateria(@PathVariable("idEntidad") int idEntidad, @PathVariable("idArea") int idArea, @RequestBody Materia materia) throws InvalidEntidadException, InvalidAreaException {
		return entidadService.crearMateria(idEntidad, idArea, materia);
	}
	
	@RequestMapping(value="/{idEntidad}/area/{idArea}/materia/{idMateria}", method = RequestMethod.PUT)
	@ResponseBody
	public Entidad updateMateria(@PathVariable("idEntidad") int idEntidad, @PathVariable("idArea") int idArea, @PathVariable("idMateria") int idMateria, @RequestBody Materia materia) throws InvalidEntidadException, InvalidAreaException, InvalidMateriaException {
		return entidadService.updateMateria(idEntidad, idArea, idMateria, materia);
	}
	
	@RequestMapping(value="/{idEntidad}/area/{idArea}/materia/{idMateria}", method = RequestMethod.DELETE)
	@ResponseBody
	public void deleteMateria(@PathVariable("idEntidad") int idEntidad, @PathVariable("idArea") int idArea, @PathVariable("idMateria") int idMateria) throws InvalidEntidadException, InvalidAreaException, InvalidMateriaException {
		entidadService.deleteMateria(idEntidad, idArea, idMateria);
	}

}
