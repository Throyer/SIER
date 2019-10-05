package com.github.websier.sier.app.controllers;

import com.github.websier.sier.app.domain.repositories.EdificioRepository;
import com.github.websier.sier.app.utils.Templates.EDIFCIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * EdificioController
 */
@Controller
public class EdificioController {

    @Autowired
    private EdificioRepository repository;

    @ModelAttribute
    public void addAttributes(Model model) {
        model.addAttribute("routeEdificios", "active");
    }

    @RequestMapping("/edificios")
    public String Index(
        @PageableDefault(page = 0, size = 10) Pageable pageable,
        Model model
    ) {
        model.addAttribute("url", "edificios");
        model.addAttribute("pagina", repository.findAll(pageable));
        return EDIFCIO.INDEX;
    }
}