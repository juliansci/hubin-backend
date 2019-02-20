package ar.com.fiuba.tpprof.hubin.dto;

import ar.com.fiuba.tpprof.hubin.model.File;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class FileMetadataResponseDTO {

    private String id;

    private String fecha;


    public FileMetadataResponseDTO(File file) {
        id = file.getId().toString();
        DateFormat format = new SimpleDateFormat("dd/MM/yyyy-HH:mm:ss");
        fecha = format.format(file.getFecha());
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
}
