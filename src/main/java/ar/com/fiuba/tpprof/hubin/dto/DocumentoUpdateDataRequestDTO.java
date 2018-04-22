package ar.com.fiuba.tpprof.hubin.dto;

public class DocumentoUpdateDataRequestDTO {
	
	private String fechaUltModificacion;
	
	private String data;

	public String getFechaUltModificacion() {
		return fechaUltModificacion;
	}

	public void setFechaUltModificacion(String fechaUltModificacion) {
		this.fechaUltModificacion = fechaUltModificacion;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}
	
	public boolean isValid() {
		return (!fechaUltModificacion.isEmpty() && !data.isEmpty());
	}

}
