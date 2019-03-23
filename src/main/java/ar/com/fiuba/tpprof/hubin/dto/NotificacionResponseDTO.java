package ar.com.fiuba.tpprof.hubin.dto;

import ar.com.fiuba.tpprof.hubin.model.Notificacion;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class NotificacionResponseDTO {

    private String id;
    private String idDocumento;
    private String idMateria;
    private String evento;
    private Boolean leido;
    private String fecha;


    public NotificacionResponseDTO(Notificacion notificacion) {
        id = String.valueOf(notificacion.getId());
        if (notificacion.getDocumentoRelacionado() != null) {
            idDocumento = String.valueOf(notificacion.getDocumentoRelacionado().getId());
        }
        if (notificacion.getMateriaRelacionada() != null) {
            idMateria = String.valueOf(notificacion.getMateriaRelacionada().getId());
        }
        evento = notificacion.getEvento();
        if (notificacion.getFecha() != null) {
            DateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            fecha = format.format(notificacion.getFecha());
        }
        leido = notificacion.isLeido();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdDocumento() {
        return idDocumento;
    }

    public void setIdDocumento(String idDocumento) {
        this.idDocumento = idDocumento;
    }

    public String getIdMateria() {
        return idMateria;
    }

    public void setIdMateria(String idMateria) {
        this.idMateria = idMateria;
    }

    public String getEvento() {
        return evento;
    }

    public void setEvento(String evento) {
        this.evento = evento;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public Boolean getLeido() {
        return leido;
    }

    public void setLeido(Boolean leido) {
        this.leido = leido;
    }
}
