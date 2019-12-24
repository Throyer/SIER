package com.github.websier.sier.app.controllers;

import static com.github.websier.sier.app.utils.FormUtils.validarUnicidadeDoEmail;
import static com.github.websier.sier.app.utils.Templates.MAIN.PERFIL;

import java.util.Optional;

import javax.validation.Valid;

import com.github.websier.sier.app.domain.dtos.perfil.NomeApelidoDTO;
import com.github.websier.sier.app.domain.dtos.perfil.PasswordDTO;
import com.github.websier.sier.app.domain.models.Usuario;
import com.github.websier.sier.app.domain.dtos.perfil.EmailDTO;
import com.github.websier.sier.app.services.UsuarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * PerfilController
 */
@Controller
public class PerfilController {

    @Autowired
    private UsuarioService service;

    private static final String REDIRECT_ERROR = "redirect:/?erro=true";
    private static final String REDIRECT_PERFIL = "redirect:/perfil";

    @GetMapping("/perfil")
    public String perfil(Model model) {

        var optionalUsuarioLogado = service.getUsuarioLogado();

        if (optionalUsuarioLogado.isEmpty()) {
            return REDIRECT_ERROR;
        }
        
        var usuarioLogado = optionalUsuarioLogado.get();

        popularModel(model, usuarioLogado);

        return PERFIL;
    }

    @PostMapping("/perfil/nome")
    public String nome(
        @Valid @ModelAttribute("nomeDTO") NomeApelidoDTO nomeApelidoDTO,
        BindingResult result,
        RedirectAttributes redirect,
        Model model
    ) {
        return REDIRECT_PERFIL;
    }

    @PostMapping("/perfil/email")
    public String email(
        @Valid @ModelAttribute("EmailDTO") EmailDTO emailDTO,
        BindingResult result,
        RedirectAttributes redirect,
        Model model
    ) {

        var optionalUsuarioLogado = service.getUsuarioLogado();

        if (optionalUsuarioLogado.isEmpty()) {
            return REDIRECT_ERROR;
        }

        var usuarioLogado = optionalUsuarioLogado.get();

        var email = Optional.of(emailDTO.getEmail());
        var id = Optional.of(usuarioLogado.getId());

        validarUnicidadeDoEmail(result, email, id);

        if (result.hasErrors()) {
            redirect.addFlashAttribute("erros", result.getAllErrors());
            return REDIRECT_PERFIL;
        }
        
        service.mudarEmail(id.get(), email.get());

        redirect.addFlashAttribute("mensagem", "Seu email e-mail foi atualizado com <strong>sucesso</strong>.");

        return REDIRECT_PERFIL;
    }

    @PostMapping("/perfil/senha")
    public String nome(
        @Valid @ModelAttribute("PasswordDTO") PasswordDTO passwordDTO,
        BindingResult result,
        Model model
    ) {
        return REDIRECT_PERFIL;
    }

    private void popularModel(Model model, Usuario usuario) {
        model.addAttribute("nomeDTO", new NomeApelidoDTO(usuario));
        model.addAttribute("emailDTO", new EmailDTO(usuario));
        model.addAttribute("passwordDTO", new PasswordDTO());
    }
}