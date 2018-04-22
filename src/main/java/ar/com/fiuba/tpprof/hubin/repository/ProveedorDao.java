package ar.com.fiuba.tpprof.hubin.repository;

import org.springframework.data.repository.CrudRepository;

import ar.com.fiuba.tpprof.hubin.model.Proveedor;

public interface ProveedorDao extends CrudRepository<Proveedor, Integer> {
	
	Proveedor findByCuit(String cuit);

}
