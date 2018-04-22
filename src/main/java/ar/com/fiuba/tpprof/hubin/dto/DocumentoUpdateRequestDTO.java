package ar.com.fiuba.tpprof.hubin.dto;

import java.util.ArrayList;
import java.util.List;

public class DocumentoUpdateRequestDTO {
	
	private String nombre;
	
	private String extension;
	
	private String descripcion;
	
	private boolean eliminado;
	
	private boolean publico;
	
	private List<String> compartidos = new ArrayList<String>();

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

	public boolean isEliminado() {
		return eliminado;
	}

	public void setEliminado(boolean eliminado) {
		this.eliminado = eliminado;
	}

	public boolean isPublico() {
		return publico;
	}

	public void setPublico(boolean publico) {
		this.publico = publico;
	}

	public List<String> getCompartidos() {
		return compartidos;
	}

	public void setCompartidos(List<String> compartidos) {
		this.compartidos = compartidos;
	}
	
	public void addCompartido(String compartido) {
		compartidos.add(compartido);
	}

	public boolean isValid() {
		return (!nombre.isEmpty() && !extension.isEmpty());
	}

}
