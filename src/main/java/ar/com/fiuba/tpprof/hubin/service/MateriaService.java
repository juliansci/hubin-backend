package ar.com.fiuba.tpprof.hubin.service;

import java.util.ArrayList;
import java.util.List;

import ar.com.fiuba.tpprof.hubin.exception.InvalidNotificacionException;
import ar.com.fiuba.tpprof.hubin.model.Alumno;
import ar.com.fiuba.tpprof.hubin.model.AlumnoMateriaObservable;
import ar.com.fiuba.tpprof.hubin.repository.AlumnoDao;
import ar.com.fiuba.tpprof.hubin.repository.AlumnoMateriaObservableDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import ar.com.fiuba.tpprof.hubin.dto.MateriaResponseDTO;
import ar.com.fiuba.tpprof.hubin.exception.InvalidMateriaException;
import ar.com.fiuba.tpprof.hubin.model.Materia;
import ar.com.fiuba.tpprof.hubin.repository.MateriaDao;

@Service
public class MateriaService {

	@Autowired
	private MateriaDao materiaDao;

	@Autowired
	private AlumnoDao alumnoDao;

	@Autowired
	private AlumnoMateriaObservableDao alumnoMateriaObservableDao;

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

	public boolean checkFollow(int id) throws InvalidMateriaException{
		Materia materia = materiaDao.findOne(id);
		if (materia == null)
			throw new InvalidMateriaException("La materia no existe");
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Alumno alumno = alumnoDao.findByUsername(userDetails.getUsername());
		if (alumno == null)
			throw new InvalidMateriaException("Alumno desconocido");
		AlumnoMateriaObservable alumnoMateriaObservable = alumnoMateriaObservableDao.getAlumnoMateriaObservableByAlumnoAndMateria(alumno, materia);
		return (alumnoMateriaObservable != null);
	}
}
