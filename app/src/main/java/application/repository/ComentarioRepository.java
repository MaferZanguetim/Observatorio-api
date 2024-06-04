package application.repository;

import org.springframework.data.repository.CrudRepository;

import application.model.Comentarios;

public interface ComentarioRepository extends CrudRepository<Comentarios, Long>{
    
}
