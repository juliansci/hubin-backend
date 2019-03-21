package ar.com.fiuba.tpprof.hubin.dto;

public class AlumnoNotificacionRequestDTO {

    private String documentoId;

    private String materiaId;


    public String getDocumentoId() {
        return documentoId;
    }

    public void setDocumentoId(String documentoId) {
        this.documentoId = documentoId;
    }

    public String getMateriaId() {
        return materiaId;
    }

    public void setMateriaId(String materiaId) {
        this.materiaId = materiaId;
    }
}
