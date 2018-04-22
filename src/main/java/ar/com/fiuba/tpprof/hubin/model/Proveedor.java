package ar.com.fiuba.tpprof.hubin.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "proveedor")
public class Proveedor {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String cuit;
	
	private String razonSocial;
	
	private String direccion;
	
	private Integer telefono;
	
	private String email;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "proveedor", cascade = CascadeType.ALL, orphanRemoval=true)
	@JsonManagedReference
	private List<Publicidad> publicidades = new ArrayList<Publicidad>();
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "proveedor", cascade = CascadeType.ALL, orphanRemoval=true)
	@JsonManagedReference
	private List<Objetivo> objetivos = new ArrayList<Objetivo>();
	
	public Proveedor() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCuit() {
		return cuit;
	}

	public void setCuit(String cuit) {
		this.cuit = cuit;
	}

	public String getRazonSocial() {
		return razonSocial;
	}

	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public Integer getTelefono() {
		return telefono;
	}

	public void setTelefono(Integer telefono) {
		this.telefono = telefono;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public List<Publicidad> getPublicidades() {
		return publicidades;
	}

	public void setPublicidades(List<Publicidad> publicidades) {
		this.publicidades = publicidades;
	}

	public void addPublicidad(Publicidad publicidad) {
		publicidades.add(publicidad);
	}

	public List<Objetivo> getObjetivos() {
		return objetivos;
	}

	public void setObjetivos(List<Objetivo> objetivos) {
		this.objetivos = objetivos;
	}
	
	public void addObjetivo(Objetivo objetivo) {
		objetivos.add(objetivo);
	}

}
