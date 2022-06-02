package ar.edu.unju.edm.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import ar.edu.unju.edm.model.Usuario;

@Component
public class ListadoUsuario {

    private static List<Usuario> listadoUsuario = new ArrayList<>();

    public ListadoUsuario() {
    }

    public List<Usuario> getListadoUsuario() {
        return listadoUsuario;
    }

    public void setListadoUsuario(List<Usuario> listadoUsuario) {
        this.listadoUsuario = listadoUsuario;
    }

    
    
}
