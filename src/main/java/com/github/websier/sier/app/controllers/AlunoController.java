package com.github.websier.sier.app.controllers;

import static com.github.websier.sier.app.utils.FormUtils.confirmarSenha;
import static com.github.websier.sier.app.utils.FormUtils.validarUnicidadeDoEmail;
import static com.github.websier.sier.app.utils.PageSettings.of;
import static com.github.websier.sier.app.utils.Templates.ALUNO.FORMULARIO;
import static com.github.websier.sier.app.utils.Templates.ALUNO.INDEX;

import java.util.Objects;
import java.util.Optional;

import javax.validation.Valid;

import com.github.websier.sier.app.domain.dtos.Alerta;
import com.github.websier.sier.app.domain.models.Aluno;
import com.github.websier.sier.app.services.AlunoService;
import com.github.websier.sier.app.services.UsuarioService;

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
 * AlunoController
 */
@Controller
@PreAuthorize("hasAnyAuthority('ADMINISTRADOR', 'PROFESSOR')")
public class AlunoController {
    
    @Autowired
    private UsuarioService usuarioService;
    
    @Autowired
    private AlunoService service;

    @ModelAttribute
    public void addAttributes(Model model) {
        model.addAttribute("alunos", "active");
    }

    private static final String REDIRECT_LISTAGEM = "redirect:/alunos";

    @GetMapping("/alunos")
    public String index(
        @RequestParam Optional<Integer> page,
        @RequestParam Optional<Integer> size,
        @RequestParam Optional<String> nome,
        @RequestParam Optional<String> turma,
        Model model
    ) {
        var pageable = of(page, size, Direction.DESC, "id");
        var pagina = service.obterTodos(nome, turma, model, pageable);
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
        model.addAttribute("aluno", service.obterAlunoPorId(id));
        return FORMULARIO;
    }

    @PostMapping("/alunos/formulario")
    public String salvar(
        @Valid Aluno aluno,
        BindingResult result,
        RedirectAttributes redirect,
        @RequestParam Optional<String> confirmacaoDaSenha,
        Model model
    ) {
        var novoRegistro = Objects.isNull(aluno.getId());

        var usuarioId = Optional.ofNullable(aluno.getUsuario().getId());
        var email = Optional.ofNullable(aluno.getUsuario().getEmail());

        validarUnicidadeDoEmail(result, email, usuarioId);
        
        if (novoRegistro) {
            confirmarSenha(result, confirmacaoDaSenha, Optional.ofNullable(aluno.getSenha()));
        }

        if (result.hasErrors()) {
            model.addAttribute("aluno", aluno);
            return FORMULARIO;
        }

        return salvar(aluno, novoRegistro, redirect);
    }

    private String salvar(
        Aluno aluno,
        Boolean novoRegistro,
        RedirectAttributes redirect
    ) {
        if (novoRegistro) {
            var novo = service.persistir(aluno);
            redirect.addFlashAttribute("novo", new Alerta(novo.getNome(), "Aluno", novo.getId()));
            return REDIRECT_LISTAGEM;
        }
        var atualizado = service.atualizar(aluno);
        redirect.addFlashAttribute("atualizado", new Alerta(atualizado.getNome(), "Aluno", atualizado.getId()));
        return REDIRECT_LISTAGEM;
    }

    @PostMapping("/alunos/alternarAtivoOuInativo/{id}")
    public String alternarAtivoOuInativo(
        @PathVariable Long id,
        RedirectAttributes redirect
    ) {
        var aluno = service.obterAlunoPorId(id);
        var ativo = usuarioService.alternarAtivoOuInativo(aluno.getUsuario());
        var alerta = new Alerta(aluno.getNome(), "Aluno", aluno.getId());
        var tipo = ativo ? "desbloqueado" : "bloqueado";
        redirect.addFlashAttribute(tipo, alerta);
        return REDIRECT_LISTAGEM;
    }
}