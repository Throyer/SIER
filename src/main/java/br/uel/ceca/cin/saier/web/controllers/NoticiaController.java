/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uel.ceca.cin.saier.web.controllers;

import br.uel.ceca.cin.saier.enums.TemplatePath;
import br.uel.ceca.cin.saier.persistence.models.Noticia;
import br.uel.ceca.cin.saier.services.interfaces.UsuarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Renato
 */
@Controller
public class NoticiaController {

    @ModelAttribute
    public void addAttributes(Model model) {
        /* Definindo usuario logado */
        model.addAttribute("usuario", usuarioService.getUsuarioLogado());
    }

    /**
     * Listagem dos alunos registrados.
     *
     * @param listagem
     * @return
     */
    @RequestMapping(value = {"/noticia", "/noticia/gerenciamento"})
    public String lista(Model listagem) {

        /* Obtendo lista de alunos */
        //listagem dos blogs aqui.
        return TemplatePath.NOTICIA_LISTA.getPath();

    }

    /**
     *
     * @param formulario
     * @return
     */
    @GetMapping("/noticia/formulario")
    public String formulario(Model formulario) {

        /* Nova noticia */
        formulario.addAttribute("noticia", new Noticia());

        return TemplatePath.NOTICIA_FORMULARIO.getPath();

    }

    @Autowired
    private UsuarioService usuarioService;
}
