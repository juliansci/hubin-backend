package ar.com.fiuba.tpprof.hubin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import ar.com.fiuba.tpprof.hubin.model.Idioma;
import ar.com.fiuba.tpprof.hubin.service.IdiomaService;

@Controller
@RequestMapping(value = "/idioma", consumes = "application/json", produces = "application/json")
public class IdiomaController {
	
	@Autowired
	private IdiomaService idiomaService;
	
	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public List<Idioma> getAllIdiomas() {
		return idiomaService.getAllIdiomas();
	}

}
