package ar.com.fiuba.tpprof.hubin.controller;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
@RequestMapping(value="/admin", consumes="application/json", produces="application/json")
public class AdminController {
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.OK)
	public void login() {
	}
	
	@RequestMapping(value="/logout", method=RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.OK)
	public void logout() {
	}

}
