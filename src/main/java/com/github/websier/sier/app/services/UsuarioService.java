package com.github.websier.sier.app.services;

import static com.github.websier.sier.app.domain.specifications.UsuarioSpecification.where;

import java.util.Optional;

import com.github.websier.sier.app.domain.models.Usuario;
import com.github.websier.sier.app.domain.repositories.UsuarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.server.ResponseStatusException;

/**
 * UsuarioService
 */
@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    private static final String SENHA_PADRAO = "mudar123";

    public Page<Usuario> obterTodos(
        Optional<String> cargo,
        Optional<String> turma,
        Optional<String> nome,
        Optional<String> apelido,
        Optional<String> email,
        Optional<Boolean> situacao,
        Model model,
        Pageable pageable
    ) {
        var specification = where(cargo, turma, nome, apelido, email, situacao, model);
        return repository.findAll(specification, pageable);
    }

    public Usuario obterPorId(Long id) {
        return repository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR));
    }

    public Usuario persistir(Usuario usuario) {
        usuario.setSenha(SENHA_PADRAO);
        var novo = repository.save(usuario);
        return novo;
    }

    public Usuario atualizar(Usuario usuario) {
        var entidade = obterPorId(usuario.getId());
        return repository.save(atualizarCamposDoUsuario(usuario, entidade));
    }

    public Boolean alternarAtivoOuInativo(Usuario usuario) {
        var novaSituacao = !usuario.isAtivo();
        usuario.setAtivo(novaSituacao);
        repository.save(usuario);
        return novaSituacao;
    }

    private Usuario atualizarCamposDoUsuario(Usuario fonte, Usuario destino) {
        destino.setNome(fonte.getNome());
        destino.setApelido(fonte.getApelido());
        destino.setEmail(fonte.getEmail());
        destino.setCargo(fonte.getCargo());
        destino.setTurma(fonte.getTurma());
        return destino;
    }
}