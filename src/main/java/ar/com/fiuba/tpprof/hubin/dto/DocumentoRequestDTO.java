package ar.com.fiuba.tpprof.hubin.dto;

public class DocumentoRequestDTO {

    private String nombre;

    private String descripcion;

    private String fechaCreacion;

    private String fechaUltModificacion;

    private String idIdioma;

    private String idNivel;

    private String idMateria;

    private String idCreador;

    private String idEntidad;

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

    public String getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(String fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getFechaUltModificacion() {
        return fechaUltModificacion;
    }

    public void setFechaUltModificacion(String fechaUltModificacion) {
        this.fechaUltModificacion = fechaUltModificacion;
    }

    public String getIdIdioma() {
        return idIdioma;
    }

    public void setIdIdioma(String idIdioma) {
        this.idIdioma = idIdioma;
    }

    public String getIdNivel() {
        return idNivel;
    }

    public void setIdNivel(String idNivel) {
        this.idNivel = idNivel;
    }

    public String getIdMateria() {
        return idMateria;
    }

    public void setIdMateria(String idMateria) {
        this.idMateria = idMateria;
    }

    public String getIdCreador() {
        return idCreador;
    }

    public void setIdCreador(String idCreador) {
        this.idCreador = idCreador;
    }

    public String getIdEntidad() {
        return idEntidad;
    }

    public void setIdEntidad(String idEntidad) {
        this.idEntidad = idEntidad;
    }

    public boolean isValid() {
        if (nombre == null || nombre.isEmpty()) {
            return false;
        }
        if (descripcion == null || descripcion.isEmpty()) {
            return false;
        }
        if (idIdioma == null || idIdioma.isEmpty()) {
            return false;
        }
        if (idNivel == null || idNivel.isEmpty()) {
            return false;
        }
        if (idMateria == null || idMateria.isEmpty()) {
            return false;
        }
        if (idEntidad == null || idEntidad.isEmpty()) {
            return false;
        }
        return true;
    }

}
