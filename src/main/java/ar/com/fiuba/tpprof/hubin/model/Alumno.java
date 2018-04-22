package ar.com.fiuba.tpprof.hubin.model;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@Entity
@Table(name = "alumno")
public class Alumno {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String username;

	@JsonProperty(access = Access.WRITE_ONLY)
	private String password;
	
	private Integer dni;
	
	private String email;
	
	private String foto;
	
	private String fechaNac;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "creador", cascade = CascadeType.ALL, orphanRemoval=true)
	@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
	@JsonIdentityReference(alwaysAsId = true)
	private List<Documento> documentosCreados = new ArrayList<Documento>();
	
	@ManyToMany(fetch = FetchType.LAZY, mappedBy="compartidos", cascade = CascadeType.ALL)
	@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
	@JsonIdentityReference(alwaysAsId = true)
	private List<Documento> documentosConAcceso = new ArrayList<Documento>();

	public Alumno() {
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

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public String getFechaNac() {
		return fechaNac;
	}

	public void setFechaNac(String fechaNac) {
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

}
