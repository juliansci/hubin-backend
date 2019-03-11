package ar.com.fiuba.tpprof.hubin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.fiuba.tpprof.hubin.exception.InvalidObjetivoException;
import ar.com.fiuba.tpprof.hubin.exception.InvalidProveedorException;
import ar.com.fiuba.tpprof.hubin.exception.InvalidPublicidadException;
import ar.com.fiuba.tpprof.hubin.model.Objetivo;
import ar.com.fiuba.tpprof.hubin.model.Proveedor;
import ar.com.fiuba.tpprof.hubin.model.Publicidad;
import ar.com.fiuba.tpprof.hubin.repository.ObjetivoDao;
import ar.com.fiuba.tpprof.hubin.repository.ProveedorDao;
import ar.com.fiuba.tpprof.hubin.repository.PublicidadDao;

@Service
public class ProveedorService {

	@Autowired
	private ProveedorDao proveedorDao;

	@Autowired
	private PublicidadDao publicidadDao;

	@Autowired
	private ObjetivoDao objetivoDao;

	public Proveedor crearProveedor(Proveedor proveedor){
		return proveedorDao.save(proveedor);
	}

	public Proveedor updateProveedor(int id, Proveedor p) throws InvalidProveedorException {
		Proveedor proveedor = proveedorDao.findOne(id);
		if (proveedor == null)
			throw new InvalidProveedorException("Prvoeedor desconocido");
		proveedor.setCuit(p.getCuit());
		proveedor.setRazonSocial(p.getRazonSocial());
		proveedor.setDireccion(p.getDireccion());
		proveedor.setTelefono(p.getTelefono());
		proveedor.setEmail(p.getEmail());
		return proveedorDao.save(proveedor);
	}

	public void deleteProveedor(int id) throws InvalidProveedorException {
		Proveedor proveedor = proveedorDao.findOne(id);
		if (proveedor == null)
			throw new InvalidProveedorException("Proveedor desconocido");
		proveedorDao.delete(proveedor);
	}

	public Proveedor getProveedor(int id) throws InvalidProveedorException {
		Proveedor proveedor = proveedorDao.findOne(id);
		if (proveedor == null)
			throw new InvalidProveedorException("Proveedor desconocido");
		return proveedor;
	}

	public Publicidad crearPublicidad(int id, Publicidad publicidad) throws InvalidProveedorException {
		Proveedor proveedor = proveedorDao.findOne(id);
		if (proveedor == null)
			throw new InvalidProveedorException("Proveedor desconocido");
		publicidad.setProveedor(proveedor);
		return publicidadDao.save(publicidad);
	}

	public Publicidad getPublicidad(int idProveedor, int idPublicidad) throws InvalidProveedorException,  InvalidPublicidadException{
		Proveedor proveedor = proveedorDao.findOne(idProveedor);
		if (proveedor == null)
			throw new InvalidProveedorException("Proveedor desconocido");
		List<Publicidad> publicidades = proveedor.getPublicidades();
		for(Publicidad publicidad : publicidades) {
			if (publicidad.getId().equals(idPublicidad)) {
				return publicidad;
			}
		}
		throw new InvalidPublicidadException("Publicidad desconocida");
	}

	public Publicidad updatePublicidad(int idProveedor, int idPublicidad, Publicidad p) throws InvalidProveedorException,  InvalidPublicidadException{
		Proveedor proveedor = proveedorDao.findOne(idProveedor);
		if (proveedor == null)
			throw new InvalidProveedorException("Proveedor desconocido");
		List<Publicidad> publicidades = proveedor.getPublicidades();
		for(Publicidad publicidad : publicidades) {
			if (publicidad.getId().equals(idPublicidad)) {
				publicidad.setActiva(p.isActiva());
				publicidad.setData(p.getData());
				publicidad.setFechaAlta(p.getFechaAlta());
				publicidad.setPrioridad(p.getId());
				return publicidadDao.save(publicidad);
			}
		}
		throw new InvalidPublicidadException("Publicidad desconocida");
	}

	public void deletetPublicidad(int idProveedor, int idPublicidad) throws InvalidProveedorException,  InvalidPublicidadException{
		Proveedor proveedor = proveedorDao.findOne(idProveedor);
		if (proveedor == null)
			throw new InvalidProveedorException("Proveedor desconocido");
		List<Publicidad> publicidades = proveedor.getPublicidades();
		for(Publicidad publicidad : publicidades) {
			if (publicidad.getId().equals(idPublicidad)) {
				publicidades.remove(publicidad);
				proveedorDao.save(proveedor);
				return;
			}
		}
		throw new InvalidPublicidadException("Publicidad desconocida");
	}

	public Objetivo crearObjetivo(int id, Objetivo objetivo) throws InvalidProveedorException {
		Proveedor proveedor = proveedorDao.findOne(id);
		if (proveedor == null)
			throw new InvalidProveedorException("Proveedor desconocido");
		objetivo.setProveedor(proveedor);
		return objetivoDao.save(objetivo);
	}

	public Objetivo getObjetivo(int idProveedor, int idObjetivo) throws InvalidProveedorException,  InvalidObjetivoException{
		Proveedor proveedor = proveedorDao.findOne(idProveedor);
		if (proveedor == null)
			throw new InvalidProveedorException("Proveedor desconocido");
		List<Objetivo> objetivos = proveedor.getObjetivos();
		for(Objetivo objetivo : objetivos) {
			if (objetivo.getId().equals(idObjetivo)) {
				return objetivo;
			}
		}
		throw new InvalidObjetivoException("Objetivo desconocido");
	}

	public Objetivo updateObjetivo(int idProveedor, int idObjetivo, Objetivo o) throws InvalidProveedorException, InvalidObjetivoException{
		Proveedor proveedor = proveedorDao.findOne(idProveedor);
		if (proveedor == null)
			throw new InvalidProveedorException("Proveedor desconocido");
		List<Objetivo> objetivos = proveedor.getObjetivos();
		for(Objetivo objetivo : objetivos) {
			if (objetivo.getId().equals(idObjetivo)) {
				objetivo.setNombre(o.getNombre());
				objetivo.setActivo(o.isActivo());
				objetivo.setDescripcion(o.getDescripcion());
				return objetivoDao.save(objetivo);
			}
		}
		throw new InvalidObjetivoException("Objetivo desconocido");
	}

	public void deleteObjetivo(int idProveedor, int idObjetivo) throws InvalidProveedorException, InvalidObjetivoException{
		Proveedor proveedor = proveedorDao.findOne(idProveedor);
		if (proveedor == null)
			throw new InvalidProveedorException("Proveedor desconocido");
		List<Objetivo> objetivos = proveedor.getObjetivos();
		for(Objetivo objetivo : objetivos) {
			if (objetivo.getId().equals(idObjetivo)) {
				objetivos.remove(objetivo);
				objetivoDao.save(objetivo);
				return;
			}
		}
		throw new InvalidObjetivoException("Objetivo desconocido");
	}

}
