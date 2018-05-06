package ar.com.fiuba.tpprof.hubin.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.fiuba.tpprof.hubin.dto.MateriaResponseDTO;
import ar.com.fiuba.tpprof.hubin.model.Materia;
import ar.com.fiuba.tpprof.hubin.repository.MateriaDao;

@Service
public class MateriaService {
	
	@Autowired
	private MateriaDao materiaDao;

	public List<MateriaResponseDTO> getMateriasDestacadas() {
		List<Materia> materiasDestacadas = materiaDao.findTop8ByDestacadaTrue();
		List<MateriaResponseDTO> materiasDestacadasResponseDTO = new ArrayList<MateriaResponseDTO>();
		for (Materia materia : materiasDestacadas) {
			materiasDestacadasResponseDTO.add(new MateriaResponseDTO(materia));
		}
		return materiasDestacadasResponseDTO;
	}

}
