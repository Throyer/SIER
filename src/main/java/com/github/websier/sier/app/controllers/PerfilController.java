package com.github.websier.sier.app.controllers;

import static com.github.websier.sier.app.utils.FormUtils.validarUnicidadeDoEmail;
import static com.github.websier.sier.app.utils.Templates.MAIN.PERFIL;

import javax.validation.Valid;

import com.github.websier.sier.app.domain.dtos.perfil.EmailDTO;
import com.github.websier.sier.app.domain.dtos.perfil.NomeApelidoDTO;
import com.github.websier.sier.app.domain.dtos.perfil.PasswordDTO;
import com.github.websier.sier.app.domain.models.Usuario;
import com.github.websier.sier.app.services.UsuarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * PerfilController
 */
@Controller
public class PerfilController {

    private static final String REDIRECT_ERROR = "redirect:/?erro=true";
    private static final String REDIRECT_PERFIL = "redirect:/perfil";

    private static final String MENSAGEM_SUCESSO = "Seu email e-mail foi atualizado com <strong>sucesso</strong>.";

    private static final String VIEW_ATRIBUTE_ERROS = "erros";
    private static final String VIEW_ATRIBUTE_MENSAGEM = "mensagem";

    private static final String VIEW_ATRIBUTE_NOME = "nomeDTO";
    private static final String VIEW_ATRIBUTE_EMAIL = "emailDTO";
    private static final String VIEW_ATRIBUTE_PASSOWORD = "passwordDTO";

    @Autowired
    private UsuarioService service;

    @GetMapping("/perfil")
    public String perfil(Model model) {

        var optionalUsuarioLogado = service.getUsuarioLogado();

        if (optionalUsuarioLogado.isEmpty()) {
            return REDIRECT_ERROR;
        }
        
        var usuarioLogado = optionalUsuarioLogado.get();

        model = popularModel(model, usuarioLogado);

        return PERFIL;
    }

    @PostMapping("/perfil/nome")
    public String nome(
        @Valid @ModelAttribute(VIEW_ATRIBUTE_NOME) NomeApelidoDTO nomeApelidoDTO,
        BindingResult result,
        RedirectAttributes redirect,
        Model model
    ) {
        return REDIRECT_PERFIL;
    }

    @PostMapping("/perfil/email")
    public String email(
        @Valid @ModelAttribute(VIEW_ATRIBUTE_EMAIL) EmailDTO emailDTO,
        BindingResult result,
        RedirectAttributes redirect,
        Model model
    ) {

        var usuarioLogado = service.getUsuarioLogado()
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR));

        validarUnicidadeDoEmail(result, emailDTO, usuarioLogado);

        if (result.hasErrors()) {
            redirect.addFlashAttribute(VIEW_ATRIBUTE_ERROS, result.getAllErrors());
            return REDIRECT_PERFIL;
        }
        
        service.mudarEmail(usuarioLogado.getId(), emailDTO.getEmail());

        redirect.addFlashAttribute(VIEW_ATRIBUTE_MENSAGEM, MENSAGEM_SUCESSO);

        return REDIRECT_PERFIL;
    }

    @PostMapping("/perfil/senha")
    public String nome(
        @Valid @ModelAttribute(VIEW_ATRIBUTE_PASSOWORD) PasswordDTO passwordDTO,
        BindingResult result,
        Model model
    ) {
        return REDIRECT_PERFIL;
    }

    private Model popularModel(Model model, Usuario usuario) {
        model.addAttribute(VIEW_ATRIBUTE_NOME, new NomeApelidoDTO(usuario));
        model.addAttribute(VIEW_ATRIBUTE_EMAIL, new EmailDTO(usuario));
        model.addAttribute(VIEW_ATRIBUTE_PASSOWORD, new PasswordDTO());
        return model;
    }
}