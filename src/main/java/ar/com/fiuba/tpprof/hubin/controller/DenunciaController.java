package ar.com.fiuba.tpprof.hubin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import ar.com.fiuba.tpprof.hubin.dto.DenunciaRequestDTO;
import ar.com.fiuba.tpprof.hubin.exception.InvalidDenunciaException;
import ar.com.fiuba.tpprof.hubin.service.DenunciaService;

@Controller
@CrossOrigin
@RequestMapping(value = "/denuncia", consumes = "application/json", produces = "application/json")
public class DenunciaController {
	
	@Autowired
	private DenunciaService denunciaService;
	
	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	public void createDocumento(@RequestBody DenunciaRequestDTO denunciaRequestDTO) throws InvalidDenunciaException {
		denunciaService.crearDenuncia(denunciaRequestDTO);
	}

}
