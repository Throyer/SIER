package com.github.websier.sier.app.controllers;

import static com.github.websier.sier.app.domain.specifications.AlunoSpecification.where;
import static com.github.websier.sier.app.utils.Templates.ALUNO.INDEX;
import static com.github.websier.sier.app.utils.PageSettings.of;

import java.util.Optional;

import com.github.websier.sier.app.domain.repositories.AlunoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * AlunoController
 */
@Controller
public class AlunoController {

    @Autowired
    private AlunoRepository repository;

    @ModelAttribute
    public void addAttributes(Model model) {
        model.addAttribute("edificios", "active");
    }

    @RequestMapping("/alunos")
    public String index(
        @RequestParam Optional<Integer> page,
        @RequestParam Optional<Integer> size,
        @RequestParam Optional<String> nome,
        @RequestParam Optional<String> turma,
        Model model
    ) {
        var specification = where(nome, turma, model);
        var pagina = repository.findAll(specification, of(page, size));
        model.addAttribute("pagina", pagina);
        return INDEX;
    }
}