package com.github.websier.sier.app.services;

import com.github.websier.sier.app.domain.models.Usuario;
import com.github.websier.sier.app.domain.repositories.UsuarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * UsuarioService
 */
@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    public Boolean alternarAtivoOuInativo(Usuario usuario) {
        var novaSituacao = !usuario.isAtivo();
        usuario.setAtivo(novaSituacao);
        repository.save(usuario);
        return novaSituacao;    
    }
}