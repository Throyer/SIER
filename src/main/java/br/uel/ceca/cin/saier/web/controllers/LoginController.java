/*
 * S.A.I.E.R 2.0.0 | https://github.com/Throyer/SAIER | 2018.
 */
package br.uel.ceca.cin.saier.web.controllers;

import br.uel.ceca.cin.saier.services.interfaces.UsuarioService;
import br.uel.ceca.cin.saier.enums.TemplatePath;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Controlador web de Login.
 *
 * @author Renato
 * @version (02/06/2018)
 */
@Controller
public class LoginController {

    @Autowired
    private UsuarioService usuarioService;

    /**
     * Login.
     *
     * @return view login.
     */
    @GetMapping(value = {"/", "/login"})
    public String login() {

        if (usuarioService.getUsuarioLogado() != null) {
            return "redirect:" + "/home";
        } else {
            return TemplatePath.LOGIN.getPath();
        }

    }

}
