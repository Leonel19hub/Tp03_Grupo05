package ar.edu.unju.edm.services.imp;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unju.edm.Controller.CursoController;
import ar.edu.unju.edm.model.Curso;
import ar.edu.unju.edm.services.ICursoService;
import ar.edu.unju.edm.util.ListadoCursos;

@Service
public class ICursoServiceImp implements ICursoService{

    private static final Log GRUPO05 = LogFactory.getLog(CursoController.class);

    @Autowired
    ListadoCursos listCourses;

    @Override
    public void guardarCurso(Curso curso) {
        
        curso.setEstado(true);
        curso.setId(listCourses.getListadoCurso().size()+1);
        listCourses.getListadoCurso().add(curso);
        
    }

    @Override
    public void eliminarCurso(Integer id) {
        
        for (int i = 0; i < listCourses.getListadoCurso().size(); i++) {
            if (listCourses.getListadoCurso().get(i).getId().equals(id)) {
                listCourses.getListadoCurso().get(i).setEstado(false);
            }
        }
        
    }

    @Override
    public void modficarCurso(Curso curso) {
    
        for (int i = 0; i < listCourses.getListadoCurso().size(); i++) {			
			if (listCourses.getListadoCurso().get(i).getId() == curso.getId()) {
				GRUPO05.error("encontrando curso "+curso.getId());
				listCourses.getListadoCurso().set(i, curso);			
			}            
        }
        
    }

    @Override
    public List<Curso> listarCursos() {
        List<Curso> auxiliar = new ArrayList<>();
        GRUPO05.info("Ingresando al metodo: Buscar Curso");
        for (int i = 0; i < listCourses.getListadoCurso().size(); i++) {
            GRUPO05.error("Recorriendo: Listado Cursos id: "+listCourses.getListadoCurso().get(i).getId());

            if (listCourses.getListadoCurso().get(i).getEstado() == true) {
                auxiliar.add(listCourses.getListadoCurso().get(i));
            }
        }
        return auxiliar;
    }

    @Override
    public Curso buscarCurso(Integer id) {
        Curso cursoEncontrado = new Curso();
        for (int i = 0; i < listCourses.getListadoCurso().size(); i++) {
            if (listCourses.getListadoCurso().get(i).getId().equals(id)) {
                cursoEncontrado = listCourses.getListadoCurso().get(i);
            }
        }
        return cursoEncontrado;
    }
    
}
