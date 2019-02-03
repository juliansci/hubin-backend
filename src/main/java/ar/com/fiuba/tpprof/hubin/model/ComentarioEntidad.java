package ar.com.fiuba.tpprof.hubin.model;


import ar.com.fiuba.tpprof.hubin.dto.ComentarioEntidadRequestDTO;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "comentario_entidad")
public class ComentarioEntidad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String mensaje;

    private Date fecha;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "alumno_id")
    private Alumno creador;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "entidad_id")
    private Entidad entidad;

    public ComentarioEntidad() {
    }

    public ComentarioEntidad(ComentarioEntidadRequestDTO comentarioRequestDTO) {
        this.mensaje = comentarioRequestDTO.getMensaje();
        this.fecha = new Date();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Alumno getCreador() {
        return creador;
    }

    public void setCreador(Alumno creador) {
        this.creador = creador;
    }


    public Entidad getEntidad() {
        return entidad;
    }

    public void setEntidad(Entidad entidad) {
        this.entidad = entidad;
    }
}
