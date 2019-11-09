package com.github.websier.sier.app.controllers;

import static com.github.websier.sier.app.domain.specifications.EdificioSpecification.where;
import static com.github.websier.sier.app.utils.Templates.EDIFCIO.INDEX;
import static com.github.websier.sier.app.utils.PageSettings.of;

import java.time.LocalDate;
import java.util.Optional;

import com.github.websier.sier.app.domain.enuns.TipoColeta;
import com.github.websier.sier.app.domain.repositories.EdificioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
        var pagina = repository.findAll(specification, of(page, size));
        model.addAttribute("pagina", pagina);
        return INDEX;
    }
}