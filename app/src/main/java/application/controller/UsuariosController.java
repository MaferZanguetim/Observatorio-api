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

import application.model.Usuario;
import application.repository.UsuarioRepository;

@RestController
@RequestMapping("/usuarios")
public class UsuariosController {
    @Autowired
    private UsuarioRepository usuarioRepo;

    @GetMapping
    public Iterable<Usuario>getAll(){
        return usuarioRepo.findAll();
    }

    @GetMapping("/{id}")
    public Usuario getOne(@PathVariable long id) {
        Optional<Usuario> result = usuarioRepo.findById(id);
        if(result.isEmpty()) {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Usuario Não Encontrado"
            );
        }
        return result.get();
    }

    @PostMapping
     private Usuario post(@RequestBody Usuario usuario) {
        return usuarioRepo.save(usuario);
    }

    @PutMapping("/{id}")
    private Usuario put(@RequestBody Usuario usuario, @PathVariable long id) {
        Optional<Usuario> result = usuarioRepo.findById(id);

        if(result.isEmpty()) {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Usuario Não Encontrado"
            );
        }

        result.get().setNome(usuario.getNome());
        result.get().setNome_exibicao(usuario.getNome_exibicao());
        return usuarioRepo.save(result.get());
    }

    @DeleteMapping("/{id}")
    private void delete(@PathVariable long id) {
        if(usuarioRepo.existsById(id)) {
            usuarioRepo.deleteById(id);
        } else {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Usuario Não Encontrado"
            );
        }
    }
    
}
