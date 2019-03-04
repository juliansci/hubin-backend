package ar.com.fiuba.tpprof.hubin.dto;

import ar.com.fiuba.tpprof.hubin.model.Alumno;

public class AlumnoCompartirResponseDTO {

    private String id;

    private String username;
    private String email;


    public AlumnoCompartirResponseDTO(Alumno alumno) {
        id = String.valueOf(alumno.getId());
        username = alumno.getUsername();
        email = alumno.getEmail();
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
