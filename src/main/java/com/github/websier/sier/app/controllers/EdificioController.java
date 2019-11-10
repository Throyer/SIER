package com.github.websier.sier.app.controllers;

import static com.github.websier.sier.app.domain.specifications.EdificioSpecification.where;
import static com.github.websier.sier.app.utils.Templates.EDIFCIO.INDEX;
import static com.github.websier.sier.app.utils.Templates.EDIFCIO.FORMULARIO;
import static com.github.websier.sier.app.utils.PageSettings.of;
import static com.github.websier.sier.app.utils.FormUtils.tipos;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Optional;

import javax.validation.Valid;

import com.github.websier.sier.app.domain.enuns.TipoColeta;
import com.github.websier.sier.app.domain.models.Edificio;
import com.github.websier.sier.app.domain.repositories.EdificioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;
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
    private EdificioRepository repository;

    @ModelAttribute
    public void addAttributes(Model model) {
        model.addAttribute("tipos", tipos());
        model.addAttribute("edificios", "active");
    }

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
        var specification = where(fonteColeta, nome, autor, dataColeta, model);
        var pagina = repository.findAll(specification, of(page, size, Direction.DESC, "createdAt"));
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
        var edificio = repository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        model.addAttribute("edificio", edificio);
        return FORMULARIO;
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
        var novo = Objects.isNull(edificio.getId());
        if (novo) {
            persistir(edificio, redirect);
        } else {
            atualizar(edificio, redirect);
        }
        return "redirect:" + "/edificios";
    }

    /**
     * Persistir.
     * 
     * persiste um novo edificio na base.
     * @param edificio Edificio.
     * @param redirect dados do redirect.
     */
    private void persistir(
        Edificio edificio,
        RedirectAttributes redirect
    ) {
        redirect.addAttribute("novo", repository.save(edificio));
    }

    /**
     * Atualizar.
     * 
     * atualiza um edificio na base.
     * @param edificio Edificio.
     * @param redirect dados do rediret.
     */
    private void atualizar(
        Edificio edificio,
        RedirectAttributes redirect
    ) {
        redirect.addAttribute("editado", repository.save(edificio));
    }
}