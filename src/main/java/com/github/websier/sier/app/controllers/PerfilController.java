package com.github.websier.sier.app.controllers;

import static com.github.websier.sier.app.utils.Templates.MAIN.PERFIL;

import com.github.websier.sier.app.configuration.security.Autenticado;
import com.github.websier.sier.app.domain.dtos.PasswordDTO;
import com.github.websier.sier.app.services.UsuarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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
            Autenticado principal = (Autenticado) authentication.getPrincipal();
            var usuario = service.obterPorId(principal.getId());
            model.addAttribute("usuario", usuario);
            model.addAttribute("passwordDTO", new PasswordDTO());
            return PERFIL;
        } catch (Exception exception) {
            exception.printStackTrace();
            return REDIRECT;
        }
    } 
}