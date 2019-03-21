package ar.com.fiuba.tpprof.hubin.model;


import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "alumno_documento_observable")
public class AlumnoDocumentoObservable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Date fecha;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "alumno_id")
    private Alumno alumno;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "documento_id")
    private Documento documento;

    public AlumnoDocumentoObservable() {
        this.fecha = new Date();

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Alumno getAlumno() {
        return alumno;
    }

    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }

    public Documento getDocumento() {
        return documento;
    }

    public void setDocumento(Documento documento) {
        this.documento = documento;
    }
}
