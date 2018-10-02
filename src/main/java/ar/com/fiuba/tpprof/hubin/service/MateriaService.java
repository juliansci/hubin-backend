package ar.com.fiuba.tpprof.hubin.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.fiuba.tpprof.hubin.dto.MateriaResponseDTO;
import ar.com.fiuba.tpprof.hubin.exception.InvalidMateriaException;
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

	public List<MateriaResponseDTO> getAllMaterias() {
		List<Materia> materias = (List<Materia>) materiaDao.findAll();
		List<MateriaResponseDTO> materiasDTO = new ArrayList<MateriaResponseDTO>();
		for (Materia materia : materias) {
			materiasDTO.add(new MateriaResponseDTO(materia));
		}
		return materiasDTO;
	}

	public MateriaResponseDTO getMateria(int id) throws InvalidMateriaException {
		Materia materia = materiaDao.findOne(id);
		if (materia == null)
			throw new InvalidMateriaException("La materia no existe");
		// TODO Auto-generated method stub
		return new MateriaResponseDTO(materia);
	}

}
