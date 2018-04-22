package ar.com.fiuba.tpprof.hubin.model;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;
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

}
