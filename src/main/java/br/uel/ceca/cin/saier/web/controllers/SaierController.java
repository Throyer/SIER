/**
 * SAIER 2.0.0.
 */
package br.uel.ceca.cin.saier.web.controllers;

import br.uel.ceca.cin.saier.enums.TemplatePath;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import br.uel.ceca.cin.saier.services.interfaces.UsuarioService;
import org.springframework.web.bind.annotation.ModelAttribute;

/**
 * Classe controladora das requisições publicas.
 *
 * @author Renato
 * @version (09/07/2018)
 */
@Controller
public class SaierController {

    @ModelAttribute
    public void addAttributes(Model model) {
        /* Definindo usuario logado */
        model.addAttribute("usuario", usuarioService.getUsuarioLogado());
    }

    /**
     * Home do sistema.
     *
     * @param home pagina inicial.
     * @return view home.
     */
    @RequestMapping(value = "/home")
    public String home(Model home) {
        return TemplatePath.HOME.getPath();
    }

    /**
     * 
     * @param blog
     * @return 
     */
    @RequestMapping(value = "/news")
    public String blog(Model blog) {
        return TemplatePath.NEWS.getPath();
    }

    /**
     * 
     * @param relatorios
     * @return 
     */
    @RequestMapping(value = "/relatorios")
    public String relatorios(Model relatorios) {
        return TemplatePath.RELATORIOS.getPath();
    }

    /**
     * Pagina de contato do sistema.
     *
     * @param contato modelo da pagina contato.
     * @return view home.
     */
    @RequestMapping(value = "/contato")
    public String contato(Model contato) {
        return TemplatePath.CONTATO.getPath();
    }

    /**
     * 
     * @param perfil
     * @return 
     */
    @RequestMapping(value = "/perfil")
    public String perfil(Model perfil) {
        return TemplatePath.CONTA.getPath();
    }

    @Autowired
    private UsuarioService usuarioService;
}
