package ar.com.fiuba.tpprof.hubin.dto;

public class ComentarioEntidadRequestDTO {

    private String mensaje;

    private String idEntidad;

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getIdEntidad() {
        return idEntidad;
    }

    public void setIdEntidad(String idEntidad) {
        this.idEntidad = idEntidad;
    }

    public boolean isValid() {
        return (!mensaje.isEmpty() && !idEntidad.isEmpty());
    }

}
