package ar.edu.unju.edm.model;

import org.springframework.stereotype.Component;

@Component
public class Usaurio {

    private String email;
    private String contraseña;
    public Usaurio() {
    }
    public Usaurio(String email, String contraseña) {
        this.email = email;
        this.contraseña = contraseña;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getContraseña() {
        return contraseña;
    }
    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    
    
}
