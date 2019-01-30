package ar.com.fiuba.tpprof.hubin.dto;

import ar.com.fiuba.tpprof.hubin.model.Alumno;

public class CreadorDocumentoResponseDTO {

    private String id;

    private String username;

    public CreadorDocumentoResponseDTO(Alumno alumno) {
        id = String.valueOf(alumno.getId());
        username = alumno.getUsername();
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

}
