package ar.com.fiuba.tpprof.hubin.dto;

import ar.com.fiuba.tpprof.hubin.model.Objetivo;

public class ObjetivoResponseDTO {

    private String id;
    private String nombre;
    private String descripcion;
    private String tipo;


    public ObjetivoResponseDTO(Objetivo objetivo) {
        id = String.valueOf(objetivo.getId());
        nombre = objetivo.getNombre();
        descripcion = objetivo.getDescripcion();
        tipo = objetivo.getTipo();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
