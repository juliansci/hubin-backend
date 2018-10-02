package ar.com.fiuba.tpprof.hubin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.fiuba.tpprof.hubin.exception.InvalidEntidadException;
import ar.com.fiuba.tpprof.hubin.model.Entidad;
import ar.com.fiuba.tpprof.hubin.repository.EntidadDao;

@Service
public class EntidadService {
	
	@Autowired
	private EntidadDao entidadDao;
	
	public List<Entidad> getAllEntidades() {
		return (List<Entidad>) entidadDao.findAll();
	}

	public Entidad getEntidad(int id) throws InvalidEntidadException {
		Entidad entidad = entidadDao.findOne(id);
		if (entidad == null)
			throw new InvalidEntidadException("La entidad no existe");
		return entidad;
	}

}
