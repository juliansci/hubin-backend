package ar.com.fiuba.tpprof.hubin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import ar.com.fiuba.tpprof.hubin.model.Entidad;
import ar.com.fiuba.tpprof.hubin.service.EntidadService;

@Controller
@RequestMapping(value = "/entidad", consumes = "application/json", produces = "application/json")
public class EntidadController {
	
	@Autowired
	private EntidadService entidadService;
	
	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public List<Entidad> getAllEntidades() {
		return entidadService.getAllEntidades();
	}

}
