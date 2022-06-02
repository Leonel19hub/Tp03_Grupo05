package ar.edu.unju.edm.model;

import java.time.LocalDate;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

@Component
public class Usuario {

    private String nombre;
    private String apellido;
    private String email;
    @NotEmpty
    private String contrasenia;
    private Boolean estado;
    @NotNull
    @Min(value=10000000, message="DNI NO VALIDO: {x/x ∈ Z x > 10.000.000}")
    @Max(value=100000000, message="DNI NO VALIDO: {x/x ∈ Z x < 99.999.999}")
    private Long dni;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechanac;

    public Usuario() {
    }

    public Usuario(String nombre, String apellido, String email, @NotEmpty String contrasenia, Boolean estado,
            @Min(value = 10000000, message = "DNI NO VALIDO: {x/x ∈ Z x > 10.000.000}") @Max(value = 99999999, message = "DNI NO VALIDO: {x/x ∈ Z x < 99.999.999}") Long dni,
            LocalDate fechanac) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.contrasenia = contrasenia;
        this.estado = estado;
        this.dni = dni;
        this.fechanac = fechanac;
    }



    public Usuario(String nombre, String apellido, String email, String contrasenia) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.contrasenia = contrasenia;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }



    public Boolean getEstado() {
        return estado;
    }



    public void setEstado(Boolean estado) {
        this.estado = estado;
    }



    public Long getDni() {
        return dni;
    }

    public void setDni(Long dni) {
        this.dni = dni;
    }

    public LocalDate getFechanac() {
        return fechanac;
    }

    public void setFechanac(LocalDate fechanac) {
        this.fechanac = fechanac;
    }    
}
