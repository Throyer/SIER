package com.github.websier.sier.app.controllers;

import static com.github.websier.sier.app.domain.specifications.EdificioSpecification.where;
import static com.github.websier.sier.app.utils.Templates.EDIFCIO.INDEX;

import java.time.LocalDate;
import java.util.Optional;

import com.github.websier.sier.app.domain.enuns.TipoColeta;
import com.github.websier.sier.app.domain.models.Edificio;
import com.github.websier.sier.app.domain.repositories.EdificioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * EdificioController
 */
@Controller
public class EdificioController {

    @Autowired
    private EdificioRepository repository;

    @ModelAttribute
    public void addAttributes(Model model) {
        model.addAttribute("routeEdificios", "active");
    }

    @RequestMapping("/edificios")
    public String Index(
        @PageableDefault(page = 0, size = 10) Pageable pageable,
        @RequestParam Optional<TipoColeta> fonteColeta,
        @RequestParam Optional<String> nome,
        @RequestParam Optional<String> autor,
        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Optional<LocalDate> dataColeta,
        Model model
    ) {
        model.addAttribute("url", "edificios");
        model = setupParameters(model, fonteColeta, nome, autor, dataColeta);
        model.addAttribute("pagina", repository.findAll(where(fonteColeta, nome, autor, dataColeta), pageable));
        return INDEX;
    }

    @RequestMapping("/api/edificios")
    @ResponseBody
    public Page<Edificio> apiIndex(
        @PageableDefault(page = 0, size = 10) Pageable pageable,
        @RequestParam Optional<TipoColeta> fonteColeta,
        @RequestParam Optional<String> nome,
        @RequestParam Optional<String> autor,
        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Optional<LocalDate> dataColeta
    ) {
        return repository.findAll(where(fonteColeta, nome, autor, dataColeta), pageable);
    }

    private Model setupParameters(
        Model model,
        Optional<TipoColeta> fonteColeta,
        Optional<String> nome,
        Optional<String> autor,
        Optional<LocalDate> dataColeta
    ) {
        
        if (fonteColeta.isPresent()) {
            model.addAttribute("fonteColeta", fonteColeta.get());
        }

        if (nome.isPresent()) {
            model.addAttribute("nome", nome.get());
        }

        if (autor.isPresent()) {
            model.addAttribute("autor", autor.get());            
        }

        if (dataColeta.isPresent()) {
            model.addAttribute("dataColeta", dataColeta.get());
        }

        return model;
    }
}