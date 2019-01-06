package ar.com.fiuba.tpprof.hubin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import ar.com.fiuba.tpprof.hubin.dto.ComentarioRequestDTO;
import ar.com.fiuba.tpprof.hubin.exception.InvalidComentarioException;
import ar.com.fiuba.tpprof.hubin.service.ComentarioService;

@Controller
@CrossOrigin
@RequestMapping(value = "/comentario", consumes = "application/json", produces = "application/json")
public class ComentarioController {
	
	@Autowired
	private ComentarioService comentarioService;
	
	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	public void createComentario(@RequestBody ComentarioRequestDTO comentarioRequestDTO) throws InvalidComentarioException {
		comentarioService.crearComentario(comentarioRequestDTO);
	}

}
