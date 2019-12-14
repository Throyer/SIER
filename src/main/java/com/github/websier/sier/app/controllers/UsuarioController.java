package com.github.websier.sier.app.controllers;

import static com.github.websier.sier.app.utils.PageSettings.of;
import static com.github.websier.sier.app.utils.Templates.USUARIO.INDEX;

import java.util.Optional;

import com.github.websier.sier.app.domain.dtos.Alerta;
import com.github.websier.sier.app.domain.repositories.UsuarioRepository;
import com.github.websier.sier.app.services.UsuarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * UsuarioController
 */
@Controller
@PreAuthorize("hasAnyAuthority('ADMINISTRADOR')")
public class UsuarioController {

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private UsuarioService service;

    @ModelAttribute
    public void addAttributes(Model model) {
        model.addAttribute("usuarios", "active");
    }

    private static final String REDIRECT_LISTAGEM = "redirect:/usuarios";

    @GetMapping("/usuarios")
    public String index(
        @RequestParam Optional<Integer> page,
        @RequestParam Optional<Integer> size,
        Model model
    ) {
        var pageable = of(page, size, Direction.DESC, "id");
        var pagina =  repository.findAll(pageable);
        model.addAttribute("pagina", pagina);
        return INDEX;
    }

    @PostMapping("/usuarios/alternarAtivoOuInativo/{id}")
    public String alternarAtivoOuInativo(
        @PathVariable Long id,
        RedirectAttributes redirect
    ) {
        var usuario = repository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR));
        var ativo = service.alternarAtivoOuInativo(usuario);
        var alerta = new Alerta(usuario.getNome(), "Usuario", usuario.getId());
        var tipo = ativo ? "desbloqueado" : "bloqueado";
        redirect.addFlashAttribute(tipo, alerta);
        return REDIRECT_LISTAGEM;
    }
}