package ar.com.fiuba.tpprof.hubin.model;

import java.nio.charset.StandardCharsets;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import ar.com.fiuba.tpprof.hubin.dto.AlumnoRequestDTO;
import ar.com.fiuba.tpprof.hubin.dto.AlumnoUpdateRequestDTO;

@Entity
@Table(name = "alumno")
public class Alumno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String username;

    private String password;

    private Integer dni;

    private String email;

    private String nombre;

    private String presentacion;

    @OneToOne(fetch = FetchType.EAGER, cascade = {javax.persistence.CascadeType.MERGE})
    @JoinColumn(name = "foto")
    private File foto;

    private Date fechaNac;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "creador", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Documento> documentosCreados = new ArrayList<Documento>();

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "compartidos", cascade = CascadeType.ALL)
    private List<Documento> documentosConAcceso = new ArrayList<Documento>();

    public Alumno() {
    }

    public Alumno(AlumnoRequestDTO alumnoRequestDTO) throws ParseException {
        email = alumnoRequestDTO.getEmail();
        username = alumnoRequestDTO.getUsername();
        setPassword(alumnoRequestDTO.getPassword());
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        byte[] passwordBytes = Base64.getDecoder().decode(password);
        String decodedPassword = new String(passwordBytes, StandardCharsets.UTF_8);
        return decodedPassword;
    }

    public void setPassword(String password) {
        byte[] passwordBytes = password.getBytes(StandardCharsets.UTF_8);
        String encodedPassword = Base64.getEncoder().encodeToString(passwordBytes);
        this.password = encodedPassword;
    }

    public Integer getDni() {
        return dni;
    }

    public void setDni(Integer dni) {
        this.dni = dni;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPresentacion() {
        return presentacion;
    }

    public void setPresentacion(String presentacion) {
        this.presentacion = presentacion;
    }

    public File getFoto() {
        return foto;
    }

    public void setFoto(File foto) {
        this.foto = foto;
    }

    public Date getFechaNac() {
        return fechaNac;
    }

    public void setFechaNac(Date fechaNac) {
        this.fechaNac = fechaNac;
    }

    public List<Documento> getDocumentosCreados() {
        return documentosCreados;
    }

    public void setDocumentosCreados(List<Documento> documentosCreados) {
        this.documentosCreados = documentosCreados;
    }

    public void addDocumentoCreado(Documento documento) {
        documentosCreados.add(documento);
    }

    public List<Documento> getDocumentosConAcceso() {
        return documentosConAcceso;
    }

    public void setDocumentosConAcceso(List<Documento> documentosConAcceso) {
        this.documentosConAcceso = documentosConAcceso;
    }

    public void addDocumentoConAcceso(Documento documento) {
        documentosConAcceso.add(documento);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Alumno other = (Alumno) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    public void update(AlumnoUpdateRequestDTO alumnoUpdateRequestDTO) throws ParseException {
        if (alumnoUpdateRequestDTO.getPassword() != null)
            setPassword(alumnoUpdateRequestDTO.getPassword());
        if (alumnoUpdateRequestDTO.getEmail() != null)
            email = alumnoUpdateRequestDTO.getEmail();
        if (alumnoUpdateRequestDTO.getNombre() != null)
            nombre = alumnoUpdateRequestDTO.getNombre();
        if (alumnoUpdateRequestDTO.getPresentacion() != null)
            presentacion = alumnoUpdateRequestDTO.getPresentacion();
        if (alumnoUpdateRequestDTO.getDni() != null)
            dni = Integer.parseInt(alumnoUpdateRequestDTO.getDni());
        if (alumnoUpdateRequestDTO.getFechaNac() != null) {
            DateFormat format = new SimpleDateFormat("dd/MM/yyyy-HH:mm:ss");
            fechaNac = format.parse(alumnoUpdateRequestDTO.getFechaNac());
        }
    }

}
