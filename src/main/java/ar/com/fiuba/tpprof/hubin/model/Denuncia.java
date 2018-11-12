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

import ar.com.fiuba.tpprof.hubin.dto.DenunciaRequestDTO;

@Entity
@Table(name = "denuncia")
public class Denuncia {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String mensaje;
	
	private Date fecha;
	
	private boolean resuelto;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "alumno_id")
	private Alumno creador;
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "documento_id")
    private Documento documento;

	public Denuncia() {
	}
	
	public Denuncia(DenunciaRequestDTO denunciaRequestDTO) {
		this.mensaje = denunciaRequestDTO.getMensaje();
		this.fecha = new Date();
		this.resuelto = false;
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

	public boolean isResuelto() {
		return resuelto;
	}

	public void setResuelto(boolean resuelto) {
		this.resuelto = resuelto;
	}

	public Alumno getCreador() {
		return creador;
	}

	public void setCreador(Alumno creador) {
		this.creador = creador;
	}

	public Documento getDocumento() {
		return documento;
	}

	public void setDocumento(Documento documento) {
		this.documento = documento;
	}

}
