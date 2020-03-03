/*
 * Copyright (C) 2019 Renato Henrique
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.github.websier.sier.app.controllers;

import static com.github.websier.sier.app.domain.specifications.EdificioSpecification.where;
import static com.github.websier.sier.app.utils.FormUtils.tiposDeColeta;
import static com.github.websier.sier.app.utils.PageSettings.of;
import static com.github.websier.sier.app.utils.Templates.ACERVO.INDEX;

import java.time.LocalDate;
import java.util.Optional;

import com.github.websier.sier.app.domain.enuns.TipoColeta;
import com.github.websier.sier.app.domain.repositories.EdificioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Classe controller responsavel por listar
 * o acervo de edificios.
 * @since 3.0.0
 * @author Renato Henrique
 */
@Controller
public class AcervoController {

    @Autowired
    private EdificioRepository repository;

    @ModelAttribute
    public void addAttributes(Model model) {
        model.addAttribute("acervo", "active");
        model.addAttribute("tipos", tiposDeColeta());
    }

    /**
     * Listagem do acervo de edificios.
     * @param page numero da pagina
     * @param size quantidade de elementos por pagina
     * @param fonteColeta fonta de coleta desejada
     * @param nome nome do edificio
     * @param autor autor da coleta
     * @param dataColeta data da coleta
     * @return edificios
     */
    @GetMapping("/base")
    public String acervo(
        @RequestParam Optional<Integer> page,
        @RequestParam Optional<Integer> size,
        @RequestParam Optional<TipoColeta> fonteColeta,
        @RequestParam Optional<String> nome,
        @RequestParam Optional<String> autor,
        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Optional<LocalDate> dataColeta,
        Model model
    ) {
        var specification = where(fonteColeta, nome, autor, dataColeta, Optional.of(model));
        var pagina = repository.findAll(specification, of(page, size));
        model.addAttribute("pagina", pagina);
        return INDEX;
    }
    
}