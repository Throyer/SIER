package com.github.websier.sier.app.controllers;

import static com.github.websier.sier.app.domain.specifications.AlunoSpecification.where;
import static com.github.websier.sier.app.utils.Templates.ALUNO.INDEX;
import static com.github.websier.sier.app.utils.Templates.ALUNO.FORMULARIO;
import static com.github.websier.sier.app.utils.PageSettings.of;

import java.util.Optional;

import com.github.websier.sier.app.domain.models.Aluno;
import com.github.websier.sier.app.domain.repositories.AlunoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

/**
 * AlunoController
 */
@Controller
public class AlunoController {

    @Autowired
    private AlunoRepository repository;

    @ModelAttribute
    public void addAttributes(Model model) {
        model.addAttribute("alunos", "active");
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

    @GetMapping("/alunos/formulario")
    public String formulario(Model model) {
        model.addAttribute("aluno", new Aluno());
        return FORMULARIO;
    }

    @GetMapping("/alunos/formulario/{id}")
    public String formulario(@PathVariable Long id, Model model) {
        var aluno = repository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        model.addAttribute("aluno", aluno);
        return FORMULARIO;
    }
}