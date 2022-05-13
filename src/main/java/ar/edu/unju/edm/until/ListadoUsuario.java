package ar.edu.unju.edm.until;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import ar.edu.unju.edm.model.Usaurio;

@Component
public class ListadoUsuario {
    private List<Usaurio> listado = new ArrayList<>();

    public ListadoUsuario() {
    }

    public ListadoUsuario(List<Usaurio> listado) {
        this.listado = listado;
    }

    public List<Usaurio> getListado() {
        return listado;
    }

    public void setListado(List<Usaurio> listado) {
        this.listado = listado;
    }

    
}
