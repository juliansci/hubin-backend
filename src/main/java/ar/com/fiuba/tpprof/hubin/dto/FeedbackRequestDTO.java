package ar.com.fiuba.tpprof.hubin.dto;

public class FeedbackRequestDTO {
	
	private String tipo;

	private String mensaje;
	
	private String fecha;
	
	private String idCreador;

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

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getIdCreador() {
		return idCreador;
	}

	public void setIdCreador(String idCreador) {
		this.idCreador = idCreador;
	}
	
	public boolean isValid() {
		return (!tipo.isEmpty() && !mensaje.isEmpty() && !fecha.isEmpty() && !idCreador.isEmpty());
	}

}
