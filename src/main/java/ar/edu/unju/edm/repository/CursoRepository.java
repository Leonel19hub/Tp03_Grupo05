package ar.edu.unju.edm.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ar.edu.unju.edm.model.Curso;

@Repository
public interface CursoRepository extends CrudRepository<Curso, Integer>{
    
}