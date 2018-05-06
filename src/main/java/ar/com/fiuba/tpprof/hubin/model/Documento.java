package ar.com.fiuba.tpprof.hubin.model;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import ar.com.fiuba.tpprof.hubin.dto.DocumentoRequestDTO;
import ar.com.fiuba.tpprof.hubin.dto.DocumentoUpdateRequestDTO;

@Entity
@Table(name = "documento")
public class Documento {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;	
	
	private String nombre;
	
	private String extension;
	
	private String descripcion;
	
	private boolean eliminado;
	
	private boolean publico;
	
	private Date fechaUltModificacion;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "idioma_id")
	private Idioma idioma;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "nivel_id")
	private Nivel nivel;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "materia_id")
	private Materia materia;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "entidad_id")
	private Entidad entidad;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "alumno_id")
	private Alumno creador;
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "documento", cascade = CascadeType.ALL, orphanRemoval=true)
	private List<Version> versiones = new ArrayList<Version>();	
	
	@ManyToMany
	@JoinTable(name="compartido", joinColumns=@JoinColumn(name="documento_id", referencedColumnName="id"), inverseJoinColumns=@JoinColumn(name="alumno_id", referencedColumnName="id"))
	private List<Alumno> compartidos = new ArrayList<Alumno>();	

	public Documento() {
	}
	
	public Documento(DocumentoRequestDTO documentoRequestDTO) throws ParseException {
		this.nombre = documentoRequestDTO.getNombre();
		this.extension = documentoRequestDTO.getExtension();
		this.descripcion = documentoRequestDTO.getDescripcion();
		this.eliminado = false;
		this.publico = documentoRequestDTO.isPublico();		
		DateFormat format = new SimpleDateFormat("dd/MM/yyyy-HH:mm:ss");
		this.fechaUltModificacion = format.parse(documentoRequestDTO.getFechaUltModificacion());
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

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

	public Date getFechaUltModificacion() {
		return fechaUltModificacion;
	}

	public void setFechaUltModificacion(Date fechaUltModificacion) {
		this.fechaUltModificacion = fechaUltModificacion;
	}
	
	public void setFechaUltModificacion(String fechaUltModificacion) throws ParseException {
		DateFormat format = new SimpleDateFormat("dd/MM/yyyy-HH:mm:ss");
		this.fechaUltModificacion = format.parse(fechaUltModificacion);		
	}
	
	public Idioma getIdioma() {
		return idioma;
	}

	public void setIdioma(Idioma idioma) {
		this.idioma = idioma;
	}

	public Nivel getNivel() {
		return nivel;
	}

	public void setNivel(Nivel nivel) {
		this.nivel = nivel;
	}

	public Materia getMateria() {
		return materia;
	}

	public void setMateria(Materia materia) {
		this.materia = materia;
	}

	public Entidad getEntidad() {
		return entidad;
	}

	public void setEntidad(Entidad entidad) {
		this.entidad = entidad;
	}

	public Alumno getCreador() {
		return creador;
	}

	public void setCreador(Alumno creador) {
		this.creador = creador;
	}

	public List<Version> getVersiones() {
		return versiones;
	}

	public void setVersiones(List<Version> versiones) {
		this.versiones = versiones;
	}
	
	public void addVersion(Version version) {
		versiones.add(version);
	}

	public List<Alumno> getCompartidos() {
		return compartidos;
	}

	public void setCompartidos(List<Alumno> compartidos) {
		this.compartidos = compartidos;
	}
	
	public void addCompartido(Alumno alumno) {
		compartidos.add(alumno);
	}

	public void update(DocumentoUpdateRequestDTO documentoUpdateRequestDTO) {		
		this.nombre = documentoUpdateRequestDTO.getNombre();
		this.extension = documentoUpdateRequestDTO.getExtension();
		this.descripcion = documentoUpdateRequestDTO.getDescripcion();
		this.eliminado = documentoUpdateRequestDTO.isEliminado();
		this.publico = documentoUpdateRequestDTO.isPublico();
	}

}
