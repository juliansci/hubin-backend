package ar.com.fiuba.tpprof.hubin.dto;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import ar.com.fiuba.tpprof.hubin.model.Comentario;

public class ComentarioResponseDTO {

    private String mensaje;

    private String fecha;

    private AlumnoResponseDTO creador;

    public ComentarioResponseDTO(Comentario comentario) {
        this.mensaje = comentario.getMensaje();
        if (comentario.getFecha() != null) {
            DateFormat format = new SimpleDateFormat("dd/MM/yyyy-HH:mm:ss");
            this.fecha = format.format(comentario.getFecha());
        }
        this.creador = new AlumnoResponseDTO(comentario.getCreador());
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public AlumnoResponseDTO getCreador() {
        return creador;
    }

    public void setCreador(AlumnoResponseDTO creador) {
        this.creador = creador;
    }
}
