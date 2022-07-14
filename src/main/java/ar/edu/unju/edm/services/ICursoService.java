package ar.edu.unju.edm.services;

import java.util.List;

import org.springframework.stereotype.Service;

import ar.edu.unju.edm.model.Curso;

@Service
public interface ICursoService {
    
    public void guardarCurso(Curso curso);
    public void eliminarCurso(Integer id) throws Exception;
    public void modficarCurso(Curso curso);
    public List<Curso> listarCursos();
    public Curso buscarCurso(Integer id) throws Exception;
}