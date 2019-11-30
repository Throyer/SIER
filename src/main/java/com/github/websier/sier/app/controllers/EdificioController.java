package com.github.websier.sier.app.controllers;

import static com.github.websier.sier.app.utils.FormUtils.tiposDeColeta;
import static com.github.websier.sier.app.utils.PageSettings.of;
import static com.github.websier.sier.app.utils.Templates.EDIFCIO.FORMULARIO;
import static com.github.websier.sier.app.utils.Templates.EDIFCIO.INDEX;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Optional;

import javax.validation.Valid;

import com.github.websier.sier.app.domain.dtos.Alerta;
import com.github.websier.sier.app.domain.enuns.TipoColeta;
import com.github.websier.sier.app.domain.models.Edificio;
import com.github.websier.sier.app.services.EdificioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Edificios controller.
 * 
 * @since 3.0.0
 * @author renato henrique
 */
@Controller
public class EdificioController {

    @Autowired
    private EdificioService service;

    @ModelAttribute
    public void addAttributes(Model model) {
        model.addAttribute("tipos", tiposDeColeta());
        model.addAttribute("edificios", "active");
    }

    private static final String REDIRECT_LISTAGEM = "redirect:/edificios";

    /**
     * Listagem dos edificios.
     * 
     * @param pageable paginador.
     * @param fonteColeta fonte da coleta.
     * @param nome nome conhecido do edificio.
     * @param autor usuario responsavel pelo cadastro do edificio.
     * @param dataColeta data do cadastro do edificio.
     * @return view da listagem dos edificios.
     */
    @RequestMapping("/edificios")
    public String index(
        @RequestParam Optional<Integer> page,
        @RequestParam Optional<Integer> size,
        @RequestParam Optional<TipoColeta> fonteColeta,
        @RequestParam Optional<String> nome,
        @RequestParam Optional<String> autor,
        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Optional<LocalDate> dataColeta,
        Model model
    ) {
        var pageable = of(page, size, Direction.DESC, "createdAt");
        var pagina = service.obterTodos(fonteColeta, nome, autor, dataColeta, model, pageable);
        model.addAttribute("pagina", pagina);
        return INDEX;
    }

    /**
     * Fomrulaio.
     * 
     * Carregar formulario.
     * @return view de formulario.
     */
    @GetMapping("/edificios/formulario")
    public String formulario(Model model) {
        model.addAttribute("edificio", new Edificio());
        return FORMULARIO;
    }

    /**
     * Formulario.
     * 
     * Carregar formulario com edificio.
     * @param id código primaria do edificio desejado.
     * @return view de formulario.
     */
    @GetMapping("/edificios/formulario/{id}")
    public String formulario(@PathVariable Long id, Model model) {
        var edificio = service.obterEdificioPorId(id);
        model.addAttribute("edificio", edificio);
        return FORMULARIO;
    }

    @PostMapping("/edificios/deletar/{id}")
    public String deletar(
        @PathVariable Long id,
        RedirectAttributes redirect
    ) {
        var edificio = service.obterEdificioPorId(id);
        var alerta = new Alerta(edificio.getNomeConhecido(), "Edificio", edificio.getId());
        redirect.addFlashAttribute("deletado", alerta);
        service.deletar(edificio);
        return REDIRECT_LISTAGEM;
    }

    /**
     * Salvar edificio.
     * 
     * @param edificio Edificio.
     * @param result validação.
     * @param redirect dados do redirect.
     * @return view de listagem.
     */
    @PostMapping("/edificios/formulario")
    public String salvar(
        @Valid Edificio edificio,
        BindingResult result,
        RedirectAttributes redirect,
        Model model
    ) {
        if (result.hasErrors()) {
            model.addAttribute("edificio", edificio);
            return FORMULARIO;
        }
        return salvar(edificio, Objects.isNull(edificio.getId()), redirect);
    }

    private String salvar(
        Edificio edificio,
        Boolean novoRegistro,
        RedirectAttributes redirect
    ) {
        if (novoRegistro) {
            var novo = service.persistir(edificio);
            var alerta = new Alerta(novo.getNomeConhecido(), "Edificio", novo.getId());
            redirect.addFlashAttribute("novo", alerta);
            return REDIRECT_LISTAGEM;
        }
        var atualizado = service.atualizar(edificio);
        var alerta = new Alerta(atualizado.getNomeConhecido(), "Edificio", atualizado.getId());
        redirect.addFlashAttribute("atualizado", alerta);
        return REDIRECT_LISTAGEM;
    }
}