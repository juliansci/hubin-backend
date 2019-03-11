package ar.com.fiuba.tpprof.hubin.repository;

import org.springframework.data.repository.CrudRepository;

import ar.com.fiuba.tpprof.hubin.model.Objetivo;

import java.util.List;

public interface ObjetivoDao extends CrudRepository<Objetivo, Integer> {
    public List<Objetivo> findAllByOrderByOrdenAsc();
    public Objetivo findByTipo(String type);

}
