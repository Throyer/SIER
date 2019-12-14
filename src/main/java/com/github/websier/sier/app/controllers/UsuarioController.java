package com.github.websier.sier.app.controllers;

import static com.github.websier.sier.app.utils.PageSettings.of;
import static com.github.websier.sier.app.utils.Templates.USUARIO.INDEX;

import java.util.Optional;

import com.github.websier.sier.app.domain.repositories.UsuarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * UsuarioController
 */
@Controller
@PreAuthorize("hasAnyAuthority('ADMINISTRADOR')")
public class UsuarioController {

    @Autowired
    private UsuarioRepository repository;

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
}