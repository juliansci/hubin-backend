package ar.com.fiuba.tpprof.hubin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import ar.com.fiuba.tpprof.hubin.dto.MateriaResponseDTO;
import ar.com.fiuba.tpprof.hubin.exception.InvalidMateriaException;
import ar.com.fiuba.tpprof.hubin.service.MateriaService;

@Controller
@CrossOrigin
@RequestMapping(value = "/materia", consumes = "application/json", produces = "application/json")
public class MateriaController {
	
	@Autowired
	private MateriaService materiaService;
	
	@RequestMapping(value="/destacadas", method = RequestMethod.GET)
	@ResponseBody
	public List<MateriaResponseDTO> getMateriasDestacadas() {
		return materiaService.getMateriasDestacadas();
	}
	
	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public List<MateriaResponseDTO> getAllMaterias() {
		return materiaService.getAllMaterias();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public MateriaResponseDTO getMateria(@PathVariable("id") int id) throws InvalidMateriaException {
		return materiaService.getMateria(id);
	}

}
