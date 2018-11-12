package ar.com.fiuba.tpprof.hubin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import ar.com.fiuba.tpprof.hubin.dto.PuntuacionRequestDTO;
import ar.com.fiuba.tpprof.hubin.exception.InvalidPuntuacionException;
import ar.com.fiuba.tpprof.hubin.service.PuntuacionService;

@Controller
@CrossOrigin
@RequestMapping(value = "/puntuacion", consumes = "application/json", produces = "application/json")
public class PuntuacionController {
	
	@Autowired
	private PuntuacionService puntuacionService;
	
	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	public void createPuntuacion(@RequestBody PuntuacionRequestDTO puntuacionRequestDTO) throws InvalidPuntuacionException {
		puntuacionService.crearPuntuacion(puntuacionRequestDTO);
	}

}
