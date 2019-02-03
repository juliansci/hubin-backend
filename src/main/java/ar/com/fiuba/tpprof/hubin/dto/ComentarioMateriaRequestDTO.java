package ar.com.fiuba.tpprof.hubin.dto;

public class ComentarioMateriaRequestDTO {

    private String mensaje;

    private String idMateria;

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getIdMateria() {
        return idMateria;
    }

    public void setIdMateria(String idMateria) {
        this.idMateria = idMateria;
    }

    public boolean isValid() {
        return (!mensaje.isEmpty() && !idMateria.isEmpty());
    }

}
