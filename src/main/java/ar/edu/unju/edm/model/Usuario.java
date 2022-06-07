package ar.edu.unju.edm.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

@Component
@Entity
public class Usuario {
    
    private String nombre;
	@NotEmpty(message = "Este campo no puede estar vacio")
	private String apellido;
	@NotEmpty(message = "La contrasela no puede estar vacia")
	private String contrasena;
	private String mail;
	@Max (value = 99999999, message="DNI NO VALIDO: {x/x ∈ Z 1.000.000 < x < 99.999.999}")
	@Min (value = 1000000, message="DNI NO VALIDO: {x/x ∈ Z 1.000.000 < x < 99.999.999}")
	@Id
	private Integer dni;
	private Boolean estado;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate fechanac;

    public Usuario() {
		// TODO Auto-generated constructor stub
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public Integer getDni() {
		return dni;
	}

	public void setDni(Integer dni) {
		this.dni = dni;
	}

	public Boolean getEstado() {
		return estado;
	}

	public void setEstado(Boolean estado) {
		this.estado = estado;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public LocalDate getFechanac() {
		return fechanac;
	}

	public void setFechanac(LocalDate fechanac) {
		this.fechanac = fechanac;
	}	
}