package ar.com.fiuba.tpprof.hubin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.fiuba.tpprof.hubin.model.Idioma;
import ar.com.fiuba.tpprof.hubin.repository.IdiomaDao;

@Service
public class IdiomaService {
	
	@Autowired
	private IdiomaDao idiomaDao;

	public List<Idioma> getAllIdiomas() {
		return (List<Idioma>) idiomaDao.findAll();
	}

}
