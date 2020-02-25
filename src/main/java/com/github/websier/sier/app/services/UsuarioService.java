package com.github.websier.sier.app.services;

import static com.github.websier.sier.app.domain.specifications.UsuarioSpecification.where;
import static com.github.websier.sier.app.utils.FormUtils.addNotificacao;
import static java.util.Objects.isNull;

import java.util.Optional;

import com.github.websier.sier.app.configuration.security.SecurityService;
import com.github.websier.sier.app.domain.models.Usuario;
import com.github.websier.sier.app.domain.repositories.UsuarioRepository;
import com.github.websier.sier.app.utils.TipoAlerta;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * UsuarioService
 */
@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private SecurityService securityService;

    private static final String SENHA_PADRAO = "mudar123";

    public Optional<Usuario> getUsuarioLogado() {
        return securityService.getAutenticado()
            .map(auth -> obterPorId(auth.getId()));
    }

    public void mudarEmail(Long usuarioId, String novoEmail) {
        var usuario = obterPorId(usuarioId);
        usuario.setEmail(novoEmail);
        repository.save(usuario);
    }

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

    public void salvar(Usuario usuario, RedirectAttributes redirect) {
        if (isNull(usuario.getId())) {
            addNotificacao(redirect, TipoAlerta.NOVO, persistir(usuario));
        } else {
            addNotificacao(redirect, TipoAlerta.ATUALIZADO, atualizar(usuario));
        }
    }

    public Usuario persistir(Usuario usuario) {
        usuario.setSenha(SENHA_PADRAO);
        var novo = repository.save(usuario);
        return novo;
    }

    public Usuario atualizar(Usuario usuario) {

        var persistir = obterPorId(usuario.getId());

        BeanUtils.copyProperties(
            usuario,
            persistir,
            "id",
            "senha",
            "ativo",
            "createdAt",
            "updatedAt",
            "lastLogin"
        );
        
        return repository.save(persistir);
    }

    public Boolean alternarAtivoOuInativo(Usuario usuario) {
        var novaSituacao = !usuario.isAtivo();
        usuario.setAtivo(novaSituacao);
        repository.save(usuario);
        return novaSituacao;
    }

    public void deletar(Usuario usuario) {
        repository.delete(usuario);
    }
}