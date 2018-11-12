package ar.com.fiuba.tpprof.hubin.dto;

public class DenunciaRequestDTO {
	
	private String mensaje;
	
	private String idDocumento;

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	
	public String getIdDocumento() {
		return idDocumento;
	}

	public void setIdDocumento(String idDocumento) {
		this.idDocumento = idDocumento;
	}

	public boolean isValid() {
		return (!mensaje.isEmpty() && !idDocumento.isEmpty());
	}

}
