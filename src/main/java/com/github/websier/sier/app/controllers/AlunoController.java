package com.github.websier.sier.app.controllers;

import static com.github.websier.sier.app.utils.Cargos.ALUNO;
import static com.github.websier.sier.app.utils.FormUtils.addNotificacao;
import static com.github.websier.sier.app.utils.FormUtils.validarUnicidadeDoEmail;
import static com.github.websier.sier.app.utils.PageSettings.of;
import static com.github.websier.sier.app.utils.Templates.ALUNO.FORMULARIO;
import static com.github.websier.sier.app.utils.Templates.ALUNO.INDEX;

import java.util.Objects;
import java.util.Optional;

import javax.validation.Valid;

import com.github.websier.sier.app.domain.models.Usuario;
import com.github.websier.sier.app.domain.repositories.CargoRepository;
import com.github.websier.sier.app.services.UsuarioService;
import com.github.websier.sier.app.utils.TipoAlerta;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * AlunoController
 */
@Controller
@PreAuthorize("hasAnyAuthority('ADMINISTRADOR', 'PROFESSOR')")
public class AlunoController {

    @Autowired
    private CargoRepository cargos;

    @Autowired
    private UsuarioService service;

    @ModelAttribute
    public void addAttributes(Model model) {
        model.addAttribute("usuarios", "active");
    }

    private static final String REDIRECT_LISTAGEM = "redirect:/alunos";
    private static final String PAGINA = "pagina";
    private static final String ENTIDADE = "aluno";

    @GetMapping("/alunos")
    public String index(
        @RequestParam Optional<Integer> page,
        @RequestParam Optional<Integer> size,
        @RequestParam Optional<String> turma,
        @RequestParam Optional<String> nome,
        @RequestParam Optional<String> apelido,
        @RequestParam Optional<String> email,
        @RequestParam Optional<Boolean> situacao,
        Model model
    ) {
        var pageable = of(page, size, Direction.DESC, "id");
        var pagina =  service.obterTodos(Optional.of(ALUNO), turma, nome, apelido, email, situacao, model, pageable);
        model.addAttribute(PAGINA, pagina);
        return INDEX;
    }

    @GetMapping("/alunos/formulario")
    public String formulario(Model model) {
        var cargo = cargos.findOptionalByNome(ALUNO)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR));

        model.addAttribute(ENTIDADE, new Usuario(cargo));
        return FORMULARIO;
    }

    @GetMapping("/alunos/formulario/{id}")
    public String formulario(@PathVariable Long id, Model model) {
        model.addAttribute(ENTIDADE, service.obterPorId(id));
        return FORMULARIO;
    }

    @PostMapping("/alunos/formulario")
    public String salvar(
        @Valid @ModelAttribute(ENTIDADE) Usuario aluno,
        BindingResult result,
        RedirectAttributes redirect,
        Model model
    ) {
        var novoRegistro = Objects.isNull(aluno.getId());

        var usuarioId = Optional.ofNullable(aluno.getId());
        var email = Optional.ofNullable(aluno.getEmail());

        validarUnicidadeDoEmail(result, email, usuarioId);

        if (result.hasErrors()) {
            model.addAttribute(ENTIDADE, aluno);
            return FORMULARIO;
        }

        return salvar(aluno, novoRegistro, redirect);
    }

    private String salvar(
        Usuario aluno,
        Boolean novoRegistro,
        RedirectAttributes redirect
    ) {
        if (novoRegistro) {
            var novo = service.persistir(aluno);
            addNotificacao(redirect, TipoAlerta.NOVO, novo);
            return REDIRECT_LISTAGEM;
        }
        var atualizado = service.atualizar(aluno);
        addNotificacao(redirect, TipoAlerta.ATUALIZADO, atualizado);
        return REDIRECT_LISTAGEM;
    }

    @PostMapping("/alunos/alternarAtivoOuInativo/{id}")
    public String alternarAtivoOuInativo(
        @PathVariable Long id,
        RedirectAttributes redirect
    ) {
        var usuario = service.obterPorId(id);
        var ativo = service.alternarAtivoOuInativo(usuario);
        
        var tipo = ativo ? TipoAlerta.DESBLOQUEADO : TipoAlerta.BLOQUEADO;

        addNotificacao(redirect, tipo, usuario);
        return REDIRECT_LISTAGEM;
    }
}