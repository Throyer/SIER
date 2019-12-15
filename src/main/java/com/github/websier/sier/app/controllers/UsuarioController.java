package com.github.websier.sier.app.controllers;

import static com.github.websier.sier.app.utils.FormUtils.validarUnicidadeDoEmail;
import static com.github.websier.sier.app.utils.FormUtils.addNotificacao;
import static com.github.websier.sier.app.utils.PageSettings.of;
import static com.github.websier.sier.app.utils.Templates.USUARIO.FORMULARIO;
import static com.github.websier.sier.app.utils.Templates.USUARIO.INDEX;

import java.util.Objects;
import java.util.Optional;

import javax.validation.Valid;

import com.github.websier.sier.app.domain.models.Usuario;
import com.github.websier.sier.app.domain.repositories.CargoRepository;
import com.github.websier.sier.app.services.UsuarioService;
import com.github.websier.sier.app.utils.TipoAlerta;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * UsuarioController
 */
@Controller
@PreAuthorize("hasAnyAuthority('ADMINISTRADOR')")
public class UsuarioController {

    @Autowired
    private CargoRepository cargos;

    @Autowired
    private UsuarioService service;

    @ModelAttribute
    public void addAttributes(Model model) {
        model.addAttribute("usuarios", "active");
    }

    private static final String REDIRECT_LISTAGEM = "redirect:/usuarios";
    private static final String CARGOS = "cargos";
    private static final String PAGINA = "pagina";
    private static final String ENTIDADE = "usuario";

    @GetMapping("/usuarios")
    public String index(
        @RequestParam Optional<Integer> page,
        @RequestParam Optional<Integer> size,
        @RequestParam Optional<String> cargo,
        @RequestParam Optional<String> nome,
        @RequestParam Optional<String> apelido,
        @RequestParam Optional<String> email,
        @RequestParam Optional<Boolean> situacao,
        Model model
    ) {
        var pageable = of(page, size, Direction.DESC, "id");
        var pagina =  service.obterTodos(cargo, Optional.empty(), nome, apelido, email, situacao, model, pageable);
        model.addAttribute(CARGOS, cargos.findAll());
        model.addAttribute(PAGINA, pagina);
        return INDEX;
    }

    @GetMapping("/usuarios/formulario")
    public String formulario(Model model) {
        model.addAttribute(CARGOS, cargos.findAll());
        model.addAttribute(ENTIDADE, new Usuario());
        return FORMULARIO;
    }

    @GetMapping("/usuarios/formulario/{id}")
    public String formulario(@PathVariable Long id, Model model) {
        model.addAttribute(CARGOS, cargos.findAll());
        model.addAttribute(ENTIDADE, service.obterPorId(id));
        return FORMULARIO;
    }

    @PostMapping("/usuarios/formulario")
    public String salvar(
        @Valid Usuario usuario,
        BindingResult result,
        RedirectAttributes redirect,
        Model model
    ) {
        var novoRegistro = Objects.isNull(usuario.getId());

        var usuarioId = Optional.ofNullable(usuario.getId());
        var email = Optional.ofNullable(usuario.getEmail());

        validarUnicidadeDoEmail(result, email, usuarioId);

        if (result.hasErrors()) {
            model.addAttribute(CARGOS, cargos.findAll());
            model.addAttribute(ENTIDADE, usuario);
            return FORMULARIO;
        }

        return salvar(usuario, novoRegistro, redirect);
    }

    private String salvar(
        Usuario usuario,
        Boolean novoRegistro,
        RedirectAttributes redirect
    ) {
        if (novoRegistro) {
            var novo = service.persistir(usuario);
            addNotificacao(redirect, TipoAlerta.NOVO, novo);
            return REDIRECT_LISTAGEM;
        }
        var atualizado = service.atualizar(usuario);
        addNotificacao(redirect, TipoAlerta.ATUALIZADO, atualizado);
        return REDIRECT_LISTAGEM;
    }

    @PostMapping("/usuarios/alternarAtivoOuInativo/{id}")
    public String alternarAtivoOuInativo(
        @PathVariable Long id,
        RedirectAttributes redirect
    ) {
        var usuario = service.obterPorId(id);
        var ativo = service.alternarAtivoOuInativo(usuario);
        
        var tipo = ativo ? TipoAlerta.DESBLOQUEADO : TipoAlerta.BLOQUEADO;

        addNotificacao(redirect, tipo, usuario);
        return REDIRECT_LISTAGEM;
    }

    @PostMapping("/usuarios/deletar/{id}")
    public String deletar(
        @PathVariable Long id,
        RedirectAttributes redirect
    ) {
        var usuario = service.obterPorId(id);
        addNotificacao(redirect, TipoAlerta.DELETADO, usuario);
        service.deletar(usuario);
        return REDIRECT_LISTAGEM;
    }

}