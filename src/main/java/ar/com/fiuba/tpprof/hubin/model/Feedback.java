package ar.com.fiuba.tpprof.hubin.model;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import ar.com.fiuba.tpprof.hubin.dto.FeedbackRequestDTO;

@Entity
@Table(name = "feedback")
public class Feedback {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String tipo;

	private String mensaje;
	
	private Date fecha;
	
	private boolean leido;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "alumno_id")
	private Alumno creador;
	
	public Feedback() {
	}
	
	public Feedback(FeedbackRequestDTO feedbackRequestDTO) throws ParseException {
		this.tipo = feedbackRequestDTO.getTipo();
		this.mensaje = feedbackRequestDTO.getMensaje();
		DateFormat format = new SimpleDateFormat("dd/MM/yyyy-HH:mm:ss");
		this.fecha = format.parse(feedbackRequestDTO.getFecha());
		this.leido = false;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
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

	public boolean isLeido() {
		return leido;
	}

	public void setLeido(boolean leido) {
		this.leido = leido;
	}

	public Alumno getCreador() {
		return creador;
	}

	public void setCreador(Alumno creador) {
		this.creador = creador;
	}	

}
