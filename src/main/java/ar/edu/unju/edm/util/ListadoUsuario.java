package ar.edu.unju.edm.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import ar.edu.unju.edm.model.Usuario;

@Component
public class ListadoUsuario {
    private List<Usuario> listado = new ArrayList<>();

    public ListadoUsuario() {
    }

    public ListadoUsuario(List<Usuario> listado) {
        this.listado = listado;
    }

    public List<Usuario> getListado() {
        return listado;
    }

    public void setListado(List<Usuario> listado) {
        this.listado = listado;
    }

    
}
