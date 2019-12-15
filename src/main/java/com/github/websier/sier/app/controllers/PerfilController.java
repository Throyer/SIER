package com.github.websier.sier.app.controllers;

import static com.github.websier.sier.app.utils.Templates.MAIN.PERFIL;

import java.util.Optional;

import javax.validation.Valid;

import com.github.websier.sier.app.configuration.security.Autenticado;
import com.github.websier.sier.app.domain.dtos.NomeApelidoDTO;
import com.github.websier.sier.app.domain.dtos.PasswordDTO;
import com.github.websier.sier.app.services.UsuarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * PerfilController
 */
@Controller
public class PerfilController {

    @Autowired
    private UsuarioService service;

    private static final String REDIRECT = "redirect:/?erro=true";
    
    @GetMapping("/perfil")
    public String perfil(Model model, Authentication authentication) {
        try {
            var usuario = service.obterPorId(getPrincipal(authentication).getId());
            model.addAttribute("usuario", usuario);
            model.addAttribute("passwordDTO", new PasswordDTO());
            return PERFIL;
        } catch (Exception exception) {
            exception.printStackTrace();
            return REDIRECT;
        }
    }

    @PostMapping("/perfil/nome")
    public String nome(
        @Valid NomeApelidoDTO nomeApelidoDTO,
        BindingResult result,
        Model model,
        Authentication authentication
    ) {
        var usuario = service.obterPorId(getPrincipal(authentication).getId());
        model.addAttribute("usuario", usuario);
        model.addAttribute("passwordDTO", new PasswordDTO());
        return PERFIL;
    }

    @PostMapping("/perfil/email")
    public String email(
        @RequestParam Optional<String> email,
        Model model, Authentication authentication
    ) {
        var usuario = service.obterPorId(getPrincipal(authentication).getId());
        model.addAttribute("usuario", usuario);
        model.addAttribute("passwordDTO", new PasswordDTO());
        return PERFIL;
    }

    @PostMapping("/perfil/senha")
    public String nome(
        @Valid PasswordDTO passwordDTO,
        BindingResult result,
        Model model,
        Authentication authentication
    ) {
        var usuario = service.obterPorId(getPrincipal(authentication).getId());
        model.addAttribute("usuario", usuario);
        model.addAttribute("passwordDTO", new PasswordDTO());
        return PERFIL;
    }

    private Autenticado getPrincipal(Authentication authentication) {
        return (Autenticado) authentication.getPrincipal();
    }
}