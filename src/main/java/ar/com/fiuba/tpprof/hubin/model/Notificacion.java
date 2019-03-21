package ar.com.fiuba.tpprof.hubin.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "notification")
public class Notificacion {

    public static final String EVENT_COMMENT = "event.comment";
    public static final String EVENT_UPDATE = "event.update";
    public static final String EVENT_NEW_DOCUMENT = "event.new_document";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "alumno_id")
    private Alumno alumno;

    private Date fecha;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "documento_id")
    private Documento documentoRelacionado;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "materia_id")
    private Materia materiaRelacionada;

    private String evento;
    private boolean leido;

    public Notificacion() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Alumno getAlumno() {
        return alumno;
    }

    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Documento getDocumentoRelacionado() {
        return documentoRelacionado;
    }

    public void setDocumentoRelacionado(Documento documentoRelacionado) {
        this.documentoRelacionado = documentoRelacionado;
    }

    public Materia getMateriaRelacionada() {
        return materiaRelacionada;
    }

    public void setMateriaRelacionada(Materia materiaRelacionada) {
        this.materiaRelacionada = materiaRelacionada;
    }

    public String getEvento() {
        return evento;
    }

    public void setEvento(String evento) {
        this.evento = evento;
    }

    public boolean isLeido() {
        return leido;
    }

    public void setLeido(boolean leido) {
        this.leido = leido;
    }
}
