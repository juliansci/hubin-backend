package ar.com.fiuba.tpprof.hubin.dto;

import ar.com.fiuba.tpprof.hubin.model.*;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import java.util.ArrayList;
import java.util.List;

public class EntidadResponseDTO {
    private String id;
    private String cuit;
    private String nombre;
    private String descripcion;
    private String nombreCorto;
    private String code;
    private String direccion;
    private String telefono;
    private String email;
    private List<ComentarioEntidadResponseDTO> comentarios;
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    private List<Documento> documentos;

    public EntidadResponseDTO(Entidad entidad) {

        id = String.valueOf(entidad.getId());
        cuit = entidad.getCuit();
        nombre = entidad.getNombre();
        code = entidad.getCode();
        descripcion = entidad.getDescripcion();
        nombreCorto = entidad.getNombreCorto();
        direccion = entidad.getDireccion();
        telefono = entidad.getTelefono();
        email = entidad.getEmail();
        documentos = new ArrayList<>(entidad.getDocumentos());
        comentarios = new ArrayList<>();
        for (int i = 0; i < entidad.getComentarios().size(); i++) {
            ComentarioEntidad comentario = entidad.getComentarios().get(i);
            comentarios.add(new ComentarioEntidadResponseDTO(comentario));
        }

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCuit() {
        return cuit;
    }

    public void setCuit(String cuit) {
        this.cuit = cuit;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getNombreCorto() {
        return nombreCorto;
    }

    public void setNombreCorto(String nombreCorto) {
        this.nombreCorto = nombreCorto;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<ComentarioEntidadResponseDTO> getComentarios() {
        return comentarios;
    }

    public void setComentarios(List<ComentarioEntidadResponseDTO> comentarios) {
        this.comentarios = comentarios;
    }

    public List<Documento> getDocumentos() {
        return documentos;
    }

    public void setDocumentos(List<Documento> documentos) {
        this.documentos = documentos;
    }
}
