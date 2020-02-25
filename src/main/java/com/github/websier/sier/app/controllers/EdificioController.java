package com.github.websier.sier.app.controllers;

import static com.github.websier.sier.app.domain.specifications.EdificioSpecification.where;

import static com.github.websier.sier.app.utils.FormUtils.addNotificacao;
import static com.github.websier.sier.app.utils.FormUtils.tiposDeColeta;
import static com.github.websier.sier.app.utils.PageSettings.of;
import static com.github.websier.sier.app.utils.Templates.EDIFCIO.FORMULARIO;
import static com.github.websier.sier.app.utils.Templates.EDIFCIO.INDEX;

import java.time.LocalDate;
import java.util.Optional;

import javax.validation.Valid;

import com.github.websier.sier.app.domain.enuns.TipoColeta;
import com.github.websier.sier.app.domain.models.Edificio;
import com.github.websier.sier.app.services.EdificioService;
import com.github.websier.sier.app.services.PdfService;
import com.github.websier.sier.app.utils.TipoAlerta;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
 * Edificios controller.
 * 
 * @since 3.0.0
 * @author renato henrique
 */
@Controller
public class EdificioController {

    private static final String REDIRECT_LISTAGEM = "redirect:/edificios";

    private static final String VIEW_ATRIBUTE_PAGINA = "pagina";
    private static final String VIEW_ATRIBUTE_ENTIDADE = "edificio";

    @Autowired
    private EdificioService service;

    @ModelAttribute
    public void addAttributes(Model model) {

        model.addAttribute("tipos", tiposDeColeta());
        model.addAttribute("edificios", "active");
    }

    @GetMapping(value = "/edificios/relatorio", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamResource> relatorioEdificios(
        Optional<TipoColeta> fonteColeta,
        Optional<String> nome,
        Optional<String> autor
    ) {

        var edificios = service.obterTodos(where(fonteColeta, nome, autor, Optional.empty(), Optional.empty()));

        var bytes = PdfService.relatorioEdificios(edificios);

        var headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=relatorioEdificios.pdf");
        
        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(bytes));
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
    @GetMapping("/edificios")
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
        model.addAttribute(VIEW_ATRIBUTE_PAGINA, pagina);
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

        model.addAttribute(VIEW_ATRIBUTE_ENTIDADE, new Edificio());
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

        model.addAttribute(VIEW_ATRIBUTE_ENTIDADE, service.obterPorId(id));
        return FORMULARIO;
    }

    @PostMapping("/edificios/deletar/{id}")
    public String deletar(
        @PathVariable Long id,
        RedirectAttributes redirect
    ) {
    
        var edificio = service.obterPorId(id);
        addNotificacao(redirect, TipoAlerta.DELETADO, edificio);
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
            model.addAttribute(VIEW_ATRIBUTE_ENTIDADE, edificio);
            return FORMULARIO;
        }
        service.salvar(edificio, redirect);
        return REDIRECT_LISTAGEM;
    }
}