package ar.com.fiuba.tpprof.hubin.dto;

import ar.com.fiuba.tpprof.hubin.model.File;

import java.util.Base64;

public class FileResponseDTO {

    private String data;

    private String extension;

    private String base64Src;

    public FileResponseDTO(File file) {
        data = Base64.getEncoder().encodeToString(file.getData());
        extension = file.getExtension();
        String extensionSrc = "";
        if (extension.equals("jpg") || extension.equals("JPG") || extension.equals("jpeg") || extension.equals("JPEG")) {
            extensionSrc = "image/jpeg";
        } else if (extension.equals("png") || extension.equals("PNG")) {
            extensionSrc = "image/png";
        }
        base64Src = "data:" + extensionSrc + ";base64, " + data;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public String getBase64Src() {
        return base64Src;
    }

    public void setBase64Src(String base64Src) {
        this.base64Src = base64Src;
    }
}
