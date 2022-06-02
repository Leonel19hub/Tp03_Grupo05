package ar.edu.unju.edm.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import ar.edu.unju.edm.model.Curso;

@Component
public class ListadoCursos {

    private static List<Curso> listadoCurso = new ArrayList<>();

    public ListadoCursos() {
    }

    public List<Curso> getListadoCurso() {
        return listadoCurso;
    }

    public void setListadoCurso(List<Curso> listadoCurso) {
        this.listadoCurso = listadoCurso;
    }

}
