package ar.com.fiuba.tpprof.hubin.service;

import java.util.ArrayList;
import java.util.List;

import ar.com.fiuba.tpprof.hubin.dto.EntidadResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.fiuba.tpprof.hubin.exception.InvalidEntidadException;
import ar.com.fiuba.tpprof.hubin.model.Entidad;
import ar.com.fiuba.tpprof.hubin.repository.EntidadDao;

@Service
public class EntidadService {

	@Autowired
	private EntidadDao entidadDao;

	public List<EntidadResponseDTO> getAllEntidades() {
		List<Entidad> entidades = (List) entidadDao.findAll();
		List<EntidadResponseDTO> entidadesDTO = new ArrayList<>();
		for (Entidad entidad : entidades) {
			entidadesDTO.add(new EntidadResponseDTO(entidad));
		}
		return entidadesDTO;
	}

	public EntidadResponseDTO getEntidad(int id) throws InvalidEntidadException {
		Entidad entidad = entidadDao.findOne(id);
		if (entidad == null)
			throw new InvalidEntidadException("La entidad no existe");
		return new EntidadResponseDTO(entidad);
	}

}
