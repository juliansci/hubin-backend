package ar.com.fiuba.tpprof.hubin.dto;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import ar.com.fiuba.tpprof.hubin.model.Area;
import ar.com.fiuba.tpprof.hubin.model.Documento;
import ar.com.fiuba.tpprof.hubin.model.Materia;

public class MateriaResponseDTO {
	
	private String id;

	private String nombre;
	
	private String code;

	private String foto;

	@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "nombre")
	@JsonIdentityReference(alwaysAsId = true)
	private Area area;

	@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
	@JsonIdentityReference(alwaysAsId = true)
	private List<Documento> documentos;
	
	public MateriaResponseDTO(Materia materia) {
		id = String.valueOf(materia.getId());
		nombre = materia.getNombre();
		code = materia.getCode();
		if (materia.getFoto() != null)
			foto = new String(materia.getFoto());
		area = materia.getArea();
		documentos = new ArrayList<Documento>(materia.getDocumentos()); 
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public Area getArea() {
		return area;
	}

	public void setArea(Area area) {
		this.area = area;
	}

	public List<Documento> getDocumentos() {
		return documentos;
	}

	public void setDocumentos(List<Documento> documentos) {
		this.documentos = documentos;
	}	

}
