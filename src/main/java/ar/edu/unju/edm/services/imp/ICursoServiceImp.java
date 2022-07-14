package ar.edu.unju.edm.services.imp;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unju.edm.Controller.CursoController;
import ar.edu.unju.edm.model.Curso;
import ar.edu.unju.edm.repository.CursoRepository;
import ar.edu.unju.edm.services.ICursoService;
import ar.edu.unju.edm.util.ListadoCursos;

@Service
public class ICursoServiceImp implements ICursoService{

    private static final Log GRUPO05 = LogFactory.getLog(CursoController.class);

    @Autowired
    ListadoCursos listCourses;

    @Autowired
    CursoRepository cursoRepository;

    @Override
    public void guardarCurso(Curso curso) {

        curso.setEstado(true);
        cursoRepository.save(curso);
        
    }

    @Override
    public void eliminarCurso(Integer id) throws Exception{

        Curso auxiliar = new Curso();
        auxiliar = buscarCurso(id);
        auxiliar.setEstado(false);
        cursoRepository.save(auxiliar);
        
    }

    @Override
    public void modficarCurso(Curso curso) {
    	GRUPO05.info("El curso con id: "+curso.getId()+"sera modificado proximamente");
        cursoRepository.save(curso);
        GRUPO05.info("El curso con id: "+curso.getId()+"se modifico");
        
    }

    @Override
    public List<Curso> listarCursos() {
        List<Curso> auxiliar = new ArrayList<>();
        auxiliar = (List<Curso>) cursoRepository.findAll();
        List<Curso> auxiliar2 = new ArrayList<>();
        for (int i = 0; i < auxiliar.size(); i++) {

            if (auxiliar.get(i).getEstado() == true) {
                auxiliar2.add(auxiliar.get(i));
            }
        }
        return auxiliar2;
    }

    @Override
    public Curso buscarCurso(Integer id) throws Exception {
        Curso cursoEncontrado = new Curso();
        cursoEncontrado = cursoRepository.findById(id).orElseThrow(()-> new Exception("Curso no encontrado"));
        return cursoEncontrado;
    }
    
}