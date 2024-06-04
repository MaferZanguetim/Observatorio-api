package application.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import application.model.Comentarios;
import application.repository.ComentarioRepository;

@RestController
@RequestMapping("/comentario")
public class ComentariosController {
    @Autowired
    private ComentarioRepository comentarioRepo;

    @GetMapping
    public Iterable<Comentarios>getAll(){
        return comentarioRepo.findAll();
    }

    @GetMapping("/{id}")
    public Comentarios getOne(@PathVariable long id) {
        Optional<Comentarios> result = comentarioRepo.findById(id);
        if(result.isEmpty()) {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Comentarios Não Encontrado"
            );
        }
        return result.get();
    }

    @PostMapping
     private Comentarios post(@RequestBody Comentarios comentario) {
        return comentarioRepo.save(comentario);
    }

    @PutMapping("/{id}")
    private Comentarios put(@RequestBody Comentarios comentario, @PathVariable long id) {
        Optional<Comentarios> result = comentarioRepo.findById(id);

        if(result.isEmpty()) {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Comentario Não Encontrado"
            );
        }

        result.get().setTexto(comentario.getTexto());
        result.get().setTitulo(comentario.getTitulo());
        return comentarioRepo.save(result.get());
    }

    @DeleteMapping("/{id}")
    private void delete(@PathVariable long id) {
        if(comentarioRepo.existsById(id)) {
            comentarioRepo.deleteById(id);
        } else {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Comentario Não Encontrado"
            );
        }
    }
    
}
