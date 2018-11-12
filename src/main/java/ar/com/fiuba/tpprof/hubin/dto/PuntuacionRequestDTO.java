package ar.com.fiuba.tpprof.hubin.dto;

public class PuntuacionRequestDTO {
	
	private String puntuacion;
	
	private String idDocumento;

	public String getPuntuacion() {
		return puntuacion;
	}

	public void setPuntuacion(String puntuacion) {
		this.puntuacion = puntuacion;
	}

	public String getIdDocumento() {
		return idDocumento;
	}

	public void setIdDocumento(String idDocumento) {
		this.idDocumento = idDocumento;
	}
	
	public boolean isValid() {
		return (!puntuacion.isEmpty() && !idDocumento.isEmpty());
	}

}
