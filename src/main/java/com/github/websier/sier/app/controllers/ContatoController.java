package com.github.websier.sier.app.controllers;

import com.github.websier.sier.app.utils.Templates.MAIN;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

/**
 * ContatoController
 */
@Controller
public class ContatoController {

    @ModelAttribute
    public void addAttributes(Model model) {
        model.addAttribute("contato", "active");
    }

    @GetMapping("/contato")
    public String index() {
        return MAIN.CONTATO;
    }

}