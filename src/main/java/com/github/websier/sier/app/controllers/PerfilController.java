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

    private static final String MENSAGEM_SUCESSO_EMAIL = "Seu email e-mail foi atualizado com <strong>sucesso</strong>.";

    private static final String VIEW_ATRIBUTE_ERROS = "erros";
    private static final String VIEW_ATRIBUTE_MENSAGEM = "mensagem";

    private static final String VIEW_ATRIBUTE_NOME = "nomeDTO";
    private static final String VIEW_ATRIBUTE_EMAIL = "emailDTO";
    private static final String VIEW_ATRIBUTE_PASSOWORD = "passwordDTO";

    @Autowired
    private UsuarioService service;

    @GetMapping("/perfil")
    public String perfil(Model model) {
        return service.getUsuarioLogado()
            .map(usuario -> {
                popularModel(model, usuario);
                return PERFIL;
            })
            .orElseGet(() -> REDIRECT_ERROR);
    }

    @PostMapping("/perfil/nome")
    public String nome(
        @Valid @ModelAttribute(VIEW_ATRIBUTE_NOME) NomeApelidoDTO nomeApelidoDTO,
        BindingResult result,
        RedirectAttributes redirect,
        Model model
    ) {
        if (result.hasErrors()) {
            redirect.addFlashAttribute(VIEW_ATRIBUTE_ERROS, result.getAllErrors());
            return REDIRECT_PERFIL;
        }
        var usuarioLogado = getUsuarioLogado();
        usuarioLogado.setNomeEApelido(nomeApelidoDTO);
        service.atualizar(usuarioLogado);
        redirect.addFlashAttribute(VIEW_ATRIBUTE_MENSAGEM, "Nome e Apelido atualizados com <strong>sucesso</strong>.");
        return REDIRECT_PERFIL;
    }

    @PostMapping("/perfil/email")
    public String email(
        @Valid @ModelAttribute(VIEW_ATRIBUTE_EMAIL) EmailDTO emailDTO,
        BindingResult result,
        RedirectAttributes redirect,
        Model model
    ) {

        var usuarioLogado = getUsuarioLogado();

        validarUnicidadeDoEmail(result, emailDTO, usuarioLogado);

        if (result.hasErrors()) {
            redirect.addFlashAttribute(VIEW_ATRIBUTE_ERROS, result.getAllErrors());
            return REDIRECT_PERFIL;
        }
        
        service.mudarEmail(usuarioLogado.getId(), emailDTO.getEmail());

        redirect.addFlashAttribute(VIEW_ATRIBUTE_MENSAGEM, MENSAGEM_SUCESSO_EMAIL);

        return REDIRECT_PERFIL;
    }

    @PostMapping("/perfil/senha")
    public String senha(
        @Valid @ModelAttribute(VIEW_ATRIBUTE_PASSOWORD) PasswordDTO passwordDTO,
        BindingResult result,
        RedirectAttributes redirect
    ) {

        var usuario = getUsuarioLogado();

        passwordDTO.validate(usuario, result);

        if (result.hasErrors()) {
            redirect.addFlashAttribute(VIEW_ATRIBUTE_ERROS, result.getAllErrors());
            return REDIRECT_PERFIL;
        }

        usuario.atualizarSenha(passwordDTO);
        service.atualizar(usuario);
        redirect.addFlashAttribute(VIEW_ATRIBUTE_MENSAGEM, "Senha atualizada com <strong>sucesso</strong>.");
        return REDIRECT_PERFIL;
    }

    private Model popularModel(Model model, Usuario usuario) {
        model.addAttribute(VIEW_ATRIBUTE_NOME, new NomeApelidoDTO(usuario));
        model.addAttribute(VIEW_ATRIBUTE_EMAIL, new EmailDTO(usuario));
        return model;
    }

    private Usuario getUsuarioLogado() {
        return service.getUsuarioLogado()
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR));
    }
}