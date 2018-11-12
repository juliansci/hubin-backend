package ar.com.fiuba.tpprof.hubin.dto;

public class FeedbackRequestDTO {
	
	private String tipo;

	private String mensaje;

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
	
	public boolean isValid() {
		return (!tipo.isEmpty() && !mensaje.isEmpty());
	}

}
