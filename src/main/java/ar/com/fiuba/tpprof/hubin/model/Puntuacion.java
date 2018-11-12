package ar.com.fiuba.tpprof.hubin.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import ar.com.fiuba.tpprof.hubin.dto.PuntuacionRequestDTO;

@Entity
@Table(name = "puntuacion")
public class Puntuacion {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private Integer puntuacion;
	
	private Date fecha;
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "alumno_id")
    private Alumno alumno;
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "documento_id")
    private Documento documento;

	public Puntuacion() {
	}
	
	public Puntuacion(PuntuacionRequestDTO puntuacionRequestDTO) {
		this.puntuacion = Integer.parseInt(puntuacionRequestDTO.getPuntuacion());
		this.fecha = new Date();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getPuntuacion() {
		return puntuacion;
	}

	public void setPuntuacion(Integer puntuacion) {
		this.puntuacion = puntuacion;
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

	public void setAlummno(Alumno alummno) {
		this.alumno = alummno;
	}

	public Documento getDocumento() {
		return documento;
	}

	public void setDocumento(Documento documento) {
		this.documento = documento;
	}	

}
