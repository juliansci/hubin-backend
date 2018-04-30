package ar.com.fiuba.tpprof.hubin.dto;

public class DocumentoRequestDTO {
	
	private String nombre;
	
	private String extension;
	
	private String descripcion;
	
	private boolean publico;
	
	private String fechaUltModificacion;
	
	private String idioma;
	
	private String nivel;
	
	private String data;
	
	private String idMateria;
	
	private String idCreador;

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getExtension() {
		return extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public boolean isPublico() {
		return publico;
	}

	public void setPublico(boolean publico) {
		this.publico = publico;
	}

	public String getFechaUltModificacion() {
		return fechaUltModificacion;
	}

	public void setFechaUltModificacion(String fechaUltModificacion) {
		this.fechaUltModificacion = fechaUltModificacion;
	}

	public String getIdioma() {
		return idioma;
	}

	public void setIdioma(String idioma) {
		this.idioma = idioma;
	}

	public String getNivel() {
		return nivel;
	}

	public void setNivel(String nivel) {
		this.nivel = nivel;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getIdMateria() {
		return idMateria;
	}

	public void setIdMateria(String idMateria) {
		this.idMateria = idMateria;
	}

	public String getIdCreador() {
		return idCreador;
	}

	public void setIdCreador(String idCreador) {
		this.idCreador = idCreador;
	}
	
	public boolean isValid() {
		return (!nombre.isEmpty() && !extension.isEmpty() && !fechaUltModificacion.isEmpty() && !data.isEmpty() && !idMateria.isEmpty() && !idCreador.isEmpty());
	}

}
