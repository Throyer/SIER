/*
 * S.A.I.E.R 2.0.0 | https://github.com/Throyer/SAIER | 2018.
 */
package br.uel.ceca.cin.saier.web.controllers;

import br.uel.ceca.cin.saier.enums.TemplatePath;
import br.uel.ceca.cin.saier.persistence.models.Usuario;
import br.uel.ceca.cin.saier.services.interfaces.UsuarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Renato
 */
public class ProfessorController {
    
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
    @RequestMapping(value = {"/professor", "/professor/gerenciamento"})
    public String lista(Model listagem) {

        /* Obtendo lista de alunos */
        //listagem dos alunos aqui.

        return TemplatePath.PROFESSOR_LISTA.getPath();

    }
    
    /**
     * 
     * @param formulario
     * @return 
     */
    @GetMapping("/professor/formulario")
    public String formulario(Model formulario) {

        /* Novo aluno */
        formulario.addAttribute("professor", new Usuario());

        return TemplatePath.FORMULARIO_PROFESSOR.getPath();

    }
    
    /* Serviços usados */
    @Autowired
    private UsuarioService usuarioService;
}
