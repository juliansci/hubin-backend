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
import ar.com.fiuba.tpprof.hubin.model.Entidad;
import ar.com.fiuba.tpprof.hubin.model.File;
import ar.com.fiuba.tpprof.hubin.model.Idioma;
import ar.com.fiuba.tpprof.hubin.model.Materia;
import ar.com.fiuba.tpprof.hubin.model.Nivel;

public class DocumentoResponseDTO {

    private String id;

    private String nombre;

    private String extension;

    private String descripcion;

    private boolean eliminado;

    private boolean publico;

    private String fechaCreacion;

    private String fechaUltModificacion;

    private String puntuacion;

    private Integer puntuacionCantidad;

    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    private Idioma idioma;

    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    private Nivel nivel;

    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    private Materia materia;

    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    private Entidad entidad;

    private CreadorDocumentoResponseDTO creador;

    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    private List<File> versiones = new ArrayList<File>();

    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    private List<Alumno> compartidos = new ArrayList<Alumno>();

    public DocumentoResponseDTO(Documento documento) {
        id = String.valueOf(documento.getId());
        nombre = documento.getNombre();
        extension = documento.getExtension();
        descripcion = documento.getDescripcion();
        eliminado = documento.isEliminado();
        publico = documento.isPublico();
        if (documento.getFechaCreacion() != null) {
            DateFormat format = new SimpleDateFormat("dd/MM/yyyy-HH:mm:ss");
            fechaCreacion = format.format(documento.getFechaCreacion());
        }
        if (documento.getFechaUltModificacion() != null) {
            DateFormat format = new SimpleDateFormat("dd/MM/yyyy-HH:mm:ss");
            fechaUltModificacion = format.format(documento.getFechaUltModificacion());
        }
        puntuacion = String.valueOf(documento.getPuntuacion());
        idioma = documento.getIdioma();
        nivel = documento.getNivel();
        materia = documento.getMateria();
        entidad = documento.getEntidad();
        creador = new CreadorDocumentoResponseDTO(documento.getCreador());
        versiones = documento.getVersiones();
        compartidos = documento.getCompartidos();
        puntuacionCantidad = documento.getPuntuacionCantidad();
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

    public String getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(String fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getFechaUltModificacion() {
        return fechaUltModificacion;
    }

    public void setFechaUltModificacion(String fechaUltModificacion) {
        this.fechaUltModificacion = fechaUltModificacion;
    }

    public String getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(String puntuacion) {
        this.puntuacion = puntuacion;
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

    public CreadorDocumentoResponseDTO getCreador() {
        return creador;
    }

    public void setCreador(CreadorDocumentoResponseDTO creador) {
        this.creador = creador;
    }

    public List<File> getVersiones() {
        return versiones;
    }

    public void setVersiones(List<File> versiones) {
        this.versiones = versiones;
    }

    public List<Alumno> getCompartidos() {
        return compartidos;
    }

    public void setCompartidos(List<Alumno> compartidos) {
        this.compartidos = compartidos;
    }

    public Integer getPuntuacionCantidad() {
        return puntuacionCantidad;
    }

    public void setPuntuacionCantidad(Integer puntuacionCantidad) {
        this.puntuacionCantidad = puntuacionCantidad;
    }
}
