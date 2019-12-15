package com.github.websier.sier.app.services;

import static com.github.websier.sier.app.domain.specifications.UsuarioSpecification.where;

import java.util.Optional;

import com.github.websier.sier.app.domain.models.Usuario;
import com.github.websier.sier.app.domain.repositories.UsuarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

/**
 * UsuarioService
 */
@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    public Page<Usuario> obterTodos(
        Optional<String> cargo,
        Optional<String> nome,
        Optional<String> apelido,
        Optional<String> email,
        Optional<Boolean> situacao,
        Model model,
        Pageable pageable
    ) {
        var specification = where(cargo, nome, apelido, email, situacao, model);
        return repository.findAll(specification, pageable);
    }

    public Boolean alternarAtivoOuInativo(Usuario usuario) {
        var novaSituacao = !usuario.isAtivo();
        usuario.setAtivo(novaSituacao);
        repository.save(usuario);
        return novaSituacao;    
    }
}