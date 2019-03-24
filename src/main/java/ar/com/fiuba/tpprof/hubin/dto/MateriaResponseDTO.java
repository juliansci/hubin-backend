package ar.com.fiuba.tpprof.hubin.dto;

import java.util.ArrayList;
import java.util.List;

import ar.com.fiuba.tpprof.hubin.model.ComentarioMateria;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import ar.com.fiuba.tpprof.hubin.model.Documento;
import ar.com.fiuba.tpprof.hubin.model.Materia;

public class MateriaResponseDTO {

    private String id;

    private String nombre;
    private String descripcion;

    private String code;

    private FileResponseDTO foto;

    private List<ComentarioMateriaResponseDTO> comentarios;

    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    private List<Documento> documentos;

    public MateriaResponseDTO(Materia materia) {
        id = String.valueOf(materia.getId());
        nombre = materia.getNombre();
        code = materia.getCode();
        if (materia.getFoto() != null) {
            foto = new FileResponseDTO(materia.getFoto());
        }
        documentos = new ArrayList<Documento>(materia.getDocumentos());
        comentarios = new ArrayList<>();
        for (int i = 0; i < materia.getComentarios().size(); i++) {
            ComentarioMateria comentario = materia.getComentarios().get(i);
            comentarios.add(new ComentarioMateriaResponseDTO(comentario));
        }
        descripcion = materia.getDescripcion();
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

    public FileResponseDTO getFoto() {
        return foto;
    }

    public void setFoto(FileResponseDTO foto) {
        this.foto = foto;
    }

    public List<Documento> getDocumentos() {
        return documentos;
    }

    public void setDocumentos(List<Documento> documentos) {
        this.documentos = documentos;
    }

    public List<ComentarioMateriaResponseDTO> getComentarios() {
        return comentarios;
    }

    public void setComentarios(List<ComentarioMateriaResponseDTO> comentarios) {
        this.comentarios = comentarios;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
