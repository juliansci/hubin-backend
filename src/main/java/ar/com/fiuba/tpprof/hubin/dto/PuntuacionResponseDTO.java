package ar.com.fiuba.tpprof.hubin.dto;

import ar.com.fiuba.tpprof.hubin.model.Puntuacion;

public class PuntuacionResponseDTO {
	
	private String idDocumento;
	
	private String puntuacion;

	public PuntuacionResponseDTO(Puntuacion puntuacion) {
		this.idDocumento = String.valueOf(puntuacion.getDocumento().getId());
		this.puntuacion = String.valueOf(puntuacion.getPuntuacion());
	}

	public String getIdDocumento() {
		return idDocumento;
	}

	public void setIdDocumento(String idDocumento) {
		this.idDocumento = idDocumento;
	}

	public String getPuntuacion() {
		return puntuacion;
	}

	public void setPuntuacion(String puntuacion) {
		this.puntuacion = puntuacion;
	}

}
