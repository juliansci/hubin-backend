package ar.com.fiuba.tpprof.hubin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.fiuba.tpprof.hubin.model.Nivel;
import ar.com.fiuba.tpprof.hubin.repository.NivelDao;

@Service
public class NivelService {
	
	@Autowired
	private NivelDao nivelDao;

	public List<Nivel> getAllNiveles() {
		return (List<Nivel>) nivelDao.findAll();
	}

}
