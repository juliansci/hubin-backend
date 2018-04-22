package ar.com.fiuba.tpprof.hubin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.fiuba.tpprof.hubin.exception.InvalidAreaException;
import ar.com.fiuba.tpprof.hubin.exception.InvalidEntidadException;
import ar.com.fiuba.tpprof.hubin.exception.InvalidMateriaException;
import ar.com.fiuba.tpprof.hubin.model.Area;
import ar.com.fiuba.tpprof.hubin.model.Entidad;
import ar.com.fiuba.tpprof.hubin.model.Materia;
import ar.com.fiuba.tpprof.hubin.repository.EntidadDao;

@Service
public class EntidadService {
	
	@Autowired
	private EntidadDao entidadDao;
	
	public Entidad crearEntidad(Entidad e) {
		return entidadDao.save(e);
	}
	
	public Entidad updateEntidad(int id, Entidad e) throws InvalidEntidadException {
		Entidad entidad = entidadDao.findOne(id);
		if (entidad == null)
			throw new InvalidEntidadException("Entidad desconocida");
		entidad.setCuit(e.getCuit());
		entidad.setNombre(e.getNombre());
		entidad.setDireccion(e.getDireccion());
		entidad.setTelefono(e.getTelefono());
		entidad.setEmail(e.getEmail());
		entidad.setActiva(e.isActiva());
		return entidadDao.save(entidad);
	}
	
	public void deleteEntidad(int id) throws InvalidEntidadException {
		Entidad entidad = entidadDao.findOne(id);
		if (entidad == null)
			throw new InvalidEntidadException("Entidad desconocida");
		entidadDao.delete(entidad);
	}
	
	public Entidad getEntidad(int id) throws InvalidEntidadException {
		Entidad entidad = entidadDao.findOne(id);
		if (entidad == null)
			throw new InvalidEntidadException("Entidad desconocida");
		return entidad;
	}
	
	public Entidad crearArea(int idEntidad, Area area) throws InvalidEntidadException {
		Entidad entidad = entidadDao.findOne(idEntidad);
		if (entidad == null)
			throw new InvalidEntidadException("Entidad desconocida");
		area.setEntidad(entidad);
		entidad.addArea(area);		
		return entidadDao.save(entidad);
	}
	
	public void deleteArea(int idEntidad, int idArea) throws InvalidEntidadException, InvalidAreaException {
		Entidad entidad = entidadDao.findOne(idEntidad);
		if (entidad == null)
			throw new InvalidEntidadException("Entidad desconocida");
		List<Area> areas = entidad.getAreas();
		for(Area area : areas) {
			if (area.getId().equals(idArea)) {
				areas.remove(area);
				entidadDao.save(entidad);
				return;
			}
		}
		throw new InvalidAreaException("Area desconocida");
	}
	
	public Entidad updateArea(int idEntidad, int idArea, Area area) throws InvalidEntidadException, InvalidAreaException {
		Entidad entidad = entidadDao.findOne(idEntidad);
		if (entidad == null)
			throw new InvalidEntidadException("Entidad desconocida");
		List<Area> areas = entidad.getAreas();
		for(Area a : areas) {
			if (a.getId().equals(idArea)) {
				a.setNombre(area.getNombre());
				return entidadDao.save(entidad);
			}
		}
		throw new InvalidAreaException("Area desconocida");
	}
	
	public Entidad crearMateria(int idEntidad, int idArea, Materia materia) throws InvalidEntidadException, InvalidAreaException {
		Entidad entidad = entidadDao.findOne(idEntidad);
		if (entidad == null)
			throw new InvalidEntidadException("Entidad desconocida");		
		List<Area> areas = entidad.getAreas();
		for(Area area : areas) {
			if (area.getId().equals(idArea)) {
				materia.setArea(area);
				area.addMateria(materia);
				return entidadDao.save(entidad);
			}
		}
		throw new InvalidAreaException("Area desconocida");
	}
	
	public void deleteMateria(int idEntidad, int idArea, int idMateria) throws InvalidEntidadException, InvalidAreaException, InvalidMateriaException {
		Entidad entidad = entidadDao.findOne(idEntidad);
		if (entidad == null)
			throw new InvalidEntidadException("Entidad desconocida");		
		List<Area> areas = entidad.getAreas();
		for(Area area : areas) {
			if (area.getId().equals(idArea)) {				
				List<Materia> materias = area.getMaterias();
				for(Materia materia : materias) {
					if (materia.getId().equals(idMateria)) {
						materias.remove(materia);
						entidadDao.save(entidad);
						return;
					}					
				}
				throw new InvalidMateriaException("Materia desconocida");
			}
		}
		throw new InvalidAreaException("Area desconocida");
	}
	
	public Entidad updateMateria(int idEntidad, int idArea, int idMateria, Materia materia) throws InvalidEntidadException, InvalidAreaException, InvalidMateriaException {
		Entidad entidad = entidadDao.findOne(idEntidad);
		if (entidad == null)
			throw new InvalidEntidadException("Entidad desconocida");		
		List<Area> areas = entidad.getAreas();
		for(Area area : areas) {
			if (area.getId().equals(idArea)) {				
				List<Materia> materias = area.getMaterias();
				for(Materia m : materias) {
					if (m.getId().equals(idMateria)) {
						m.setCodigo(materia.getCodigo());
						m.setNombre(materia.getNombre());
						return entidadDao.save(entidad);
					}					
				}
				throw new InvalidMateriaException("Materia desconocida");
			}
		}
		throw new InvalidAreaException("Area desconocida");
	}

}
