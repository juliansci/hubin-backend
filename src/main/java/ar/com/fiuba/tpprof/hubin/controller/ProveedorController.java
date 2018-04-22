package ar.com.fiuba.tpprof.hubin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import ar.com.fiuba.tpprof.hubin.exception.InvalidObjetivoException;
import ar.com.fiuba.tpprof.hubin.exception.InvalidProveedorException;
import ar.com.fiuba.tpprof.hubin.exception.InvalidPublicidadException;
import ar.com.fiuba.tpprof.hubin.model.Objetivo;
import ar.com.fiuba.tpprof.hubin.model.Proveedor;
import ar.com.fiuba.tpprof.hubin.model.Publicidad;
import ar.com.fiuba.tpprof.hubin.service.ProveedorService;

@Controller
@RequestMapping(value = "/proveedor", consumes = "application/json", produces = "application/json")
public class ProveedorController {
	
	@Autowired
	private ProveedorService proveedorService;
	
	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	public Proveedor createProveedor(@RequestBody Proveedor proveedor) {
		Proveedor proveedorCreado = proveedorService.crearProveedor(proveedor);
		return proveedorCreado;
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public Proveedor updateProveedor(@PathVariable("id") int id, @RequestBody Proveedor proveedor) throws InvalidProveedorException {
		return proveedorService.updateProveedor(id, proveedor);
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	@ResponseBody
	public Proveedor getProveedor(@PathVariable("id") int id) throws InvalidProveedorException {
		return proveedorService.getProveedor(id);
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public void deleteProveedor(@PathVariable("id") int id) throws InvalidProveedorException {
		proveedorService.deleteProveedor(id);
	}
	
	@RequestMapping(value="/{id}/publicidad", method = RequestMethod.POST)
	@ResponseBody
	public Publicidad crearPublicidad(@PathVariable("id") int id, @RequestBody Publicidad publicidad) throws InvalidProveedorException {
		return proveedorService.crearPublicidad(id, publicidad);
	}
	
	@RequestMapping(value="/{idProveedor}/publicidad/{idPublicidad}", method = RequestMethod.GET)
	@ResponseBody
	public Publicidad getPublicidad(@PathVariable("idProveedor") int idProveedor, @PathVariable("idPublicidad") int idPublicidad) throws InvalidProveedorException, InvalidPublicidadException {
		return proveedorService.getPublicidad(idProveedor, idPublicidad);
	}
	
	@RequestMapping(value="/{idProveedor}/publicidad/{idPublicidad}", method = RequestMethod.PUT)
	@ResponseBody
	public Publicidad updatePublicidad(@PathVariable("idProveedor") int idProveedor, @PathVariable("idPublicidad") int idPublicidad, @RequestBody Publicidad publicidad) throws InvalidProveedorException, InvalidPublicidadException {
		return proveedorService.updatePublicidad(idProveedor, idPublicidad, publicidad);
	}
	
	@RequestMapping(value="/{idProveedor}/publicidad/{idPublicidad}", method = RequestMethod.DELETE)
	@ResponseBody
	public void deletePublicidad(@PathVariable("idProveedor") int idProveedor, @PathVariable("idPublicidad") int idPublicidad) throws InvalidProveedorException, InvalidPublicidadException {
		proveedorService.deletetPublicidad(idProveedor, idPublicidad);
	}	
	
	@RequestMapping(value="/{id}/objetivo", method = RequestMethod.POST)
	@ResponseBody
	public Objetivo crearObjetivo(@PathVariable("id") int id, @RequestBody Objetivo objetivo) throws InvalidProveedorException {
		return proveedorService.crearObjetivo(id, objetivo);
	}
	
	@RequestMapping(value="/{idProveedor}/objetivo/{idObjetivo}", method = RequestMethod.GET)
	@ResponseBody
	public Objetivo getObjetivo(@PathVariable("idProveedor") int idProveedor, @PathVariable("idObjetivo") int idObjetivo) throws InvalidProveedorException, InvalidObjetivoException {
		return proveedorService.getObjetivo(idProveedor, idObjetivo);
	}
	
	@RequestMapping(value="/{idProveedor}/objetivo/{idObjetivo}", method = RequestMethod.PUT)
	@ResponseBody
	public Objetivo updateObjetivo(@PathVariable("idProveedor") int idProveedor, @PathVariable("idObjetivo") int idObjetivo, @RequestBody Objetivo objetivo) throws InvalidProveedorException, InvalidObjetivoException {
		return proveedorService.updateObjetivo(idProveedor, idObjetivo, objetivo);
	}
	
	@RequestMapping(value="/{idProveedor}/objetivo/{idObjetivo}", method = RequestMethod.DELETE)
	@ResponseBody
	public void deleteObjetivo(@PathVariable("idProveedor") int idProveedor, @PathVariable("idObjetivo") int idObjetivo) throws InvalidProveedorException, InvalidObjetivoException {
		proveedorService.deleteObjetivo(idProveedor, idObjetivo);
	}
	

}
