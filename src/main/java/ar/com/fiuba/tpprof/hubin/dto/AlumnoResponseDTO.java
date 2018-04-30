package ar.com.fiuba.tpprof.hubin.dto;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import ar.com.fiuba.tpprof.hubin.model.Alumno;
import ar.com.fiuba.tpprof.hubin.model.Documento;

public class AlumnoResponseDTO {

	private String id;

	private String username;

	private String dni;

	private String email;

	private String foto;

	private String fechaNac;
	
	@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
	@JsonIdentityReference(alwaysAsId = true)
	private List<Documento> documentosCreados;
	
	@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
	@JsonIdentityReference(alwaysAsId = true)
	private List<Documento> documentosConAcceso;

	public AlumnoResponseDTO(Alumno alumno) {
		id = String.valueOf(alumno.getId());
		username = alumno.getUsername();
		dni = String.valueOf(alumno.getDni());
		email = alumno.getEmail();
		foto = new String(alumno.getFoto());
		DateFormat format = new SimpleDateFormat("dd/MM/yyyy-HH:mm:ss");
		fechaNac = format.format(alumno.getFechaNac());
		documentosCreados = new ArrayList<Documento>(alumno.getDocumentosCreados());
		documentosConAcceso = new ArrayList<Documento>(alumno.getDocumentosConAcceso());
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public String getFechaNac() {
		return fechaNac;
	}

	public void setFechaNac(String fechaNac) {
		this.fechaNac = fechaNac;
	}

	public List<Documento> getDocumentosCreados() {
		return documentosCreados;
	}

	public void setDocumentosCreados(List<Documento> documentosCreados) {
		this.documentosCreados = documentosCreados;
	}

	public List<Documento> getDocumentosConAcceso() {
		return documentosConAcceso;
	}

	public void setDocumentosConAcceso(List<Documento> documentosConAcceso) {
		this.documentosConAcceso = documentosConAcceso;
	}

}
