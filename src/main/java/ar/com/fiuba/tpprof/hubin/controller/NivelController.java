package ar.com.fiuba.tpprof.hubin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import ar.com.fiuba.tpprof.hubin.model.Nivel;
import ar.com.fiuba.tpprof.hubin.service.NivelService;

@Controller
@RequestMapping(value = "/nivel", consumes = "application/json", produces = "application/json")
public class NivelController {
	
	@Autowired
	private NivelService nivelService;
	
	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public List<Nivel> getAllNiveles() {
		return nivelService.getAllNiveles();
	}

}
