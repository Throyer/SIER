/*
 * S.A.I.E.R 2.0.0 | https://github.com/Throyer/SAIER | 2018.
 */
package br.uel.ceca.cin.saier.web.controllers;

import br.uel.ceca.cin.saier.persistence.models.CEP;
import br.uel.ceca.cin.saier.persistence.models.Edificio;
import br.uel.ceca.cin.saier.services.interfaces.CepService;
import br.uel.ceca.cin.saier.services.interfaces.UsuarioService;
import br.uel.ceca.cin.saier.services.interfaces.EdificioService;
import br.uel.ceca.cin.saier.enums.TemplatePath;

import javax.validation.Valid;

import org.springframework.validation.BindingResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Classe controladora das requisições para Edificio.
 *
 * @author Renato
 * @version (09/07/2018)
 */
@Controller
public class EdificioController {

    @ModelAttribute
    public void addAttributes(Model model) {
        /* Definindo usuario logado */
        model.addAttribute("usuario", usuarioService.getUsuarioLogado());
    }

    /**
     * Listagem dos alunos registrados.
     *
     * @param listagem
     * @return gerenciamento de alunos.
     */
    @RequestMapping(value = {"/edificio", "/edificio/gerenciamento"})
    public String lista(Model listagem) {

        /* Obtendo lista de edificios */
        listagem.addAttribute("edificios", edificioService.obterTodos());

        return TemplatePath.EDIFICIO_LISTA.getPath();

    }

    /**
     * Formulario para cadastro de Edificio.
     *
     * @param formulario
     * @return formulario para cadastro de edificio
     */
    @GetMapping("/edificio/formulario")
    public String formulario(Model formulario) {

        /* Novo edificio */
        formulario.addAttribute("edificio", new Edificio());

        /* CEP vazio */
        formulario.addAttribute("cep", new CEP());

        return TemplatePath.FORMULARIO_EDIFICIO.getPath();

    }

    /**
     * Salvar edificio.
     *
     * Se o id do edificio recibido for nulo, é cadastrado um novo edificio,
     * caso contrario o edificio com o id recebido é atualizado no banco.
     *
     * @param edificio
     * @param result
     * @param formulario
     * @param redirect
     * @return
     */
    @PostMapping(value = "/edificio/formulario")
    public String salvar(@Valid Edificio edificio, BindingResult result, Model formulario, RedirectAttributes redirect) {

        /* verificando se é uma edição ou um novo registro */
        boolean novo = edificio.getId() == null;

        /* Caso existam erros no formulario */
        if (result.hasErrors()) {

            /* Devolvendo os dados para o formulario */
            formulario.addAttribute("edificio", edificio);

            return TemplatePath.FORMULARIO_EDIFICIO.getPath();

        } else {

            /* Novo usuario responsavel */
            edificio.setUsuario(usuarioService.getUsuarioLogado());

            /* Obtendo cep do formulario */
            CEP cep = edificio.getCep();

            /* Validando o cep */
            cep = cepService.validarCep(cep);

            /* Atualizando cep no edificio */
            edificio.setCep(cep);

            /*Se edificio é novo*/
            if (novo) {

                /*Salvando edificio*/
                edificioService.salvarEdificio(edificio);

                /* Passando dados desse novo edificio para o 'redirect' */
                redirect.addFlashAttribute("cadastro", edificio);

                /* Redireciona para formulario novamente, para um novo cadastro */
                return "redirect:/edificio/formulario";
            } else {

                /*Salvando edificio*/
                edificioService.salvarEdificio(edificio);

                /* Passando dados desse edificio para o 'redirect' */
                redirect.addFlashAttribute("atualizado", edificio);

                /* Redireciona para a lista de edificios depois da edição */
                return "redirect:/edificio";
            }
        }

    }

    /**
     * Editar edificio a partir do Id.
     *
     * @param formulario
     * @param id
     * @return
     */
    @GetMapping("/edificio/editar/{id}")
    public String editar(Model formulario, @PathVariable Integer id) {

        /* Obtendo Edificio a partir do Id */
        formulario.addAttribute("edificio", edificioService.obterPorId(id));

        return TemplatePath.FORMULARIO_EDIFICIO.getPath();
    }

    /**
     * Exibir edificio.
     *
     * @param visualizar
     * @param id
     * @return
     */
    @GetMapping("/edificio/visualizar/{id}")
    public String visualizar(Model visualizar, @PathVariable Integer id) {

        Edificio edificio = edificioService.obterPorId(id);

        visualizar.addAttribute("button", edificioService.getButtonURL(edificio));

        visualizar.addAttribute("edificio", edificio);

        return TemplatePath.EDIFICIO.getPath();
    }

    /**
     * Remove Edificio.
     *
     * @param id chave primaria do Edificio a ser removido.
     * @return redireciona o usuario para a pagina de controle
     */
    @PostMapping(value = "/edificio/remover")
    public String remover(@RequestParam("id") Integer id) {

        /* Obtendo edificio a partir do Id */
        edificioService.deletarEdificio(edificioService.obterPorId(id));

        return "redirect:/edificio/gerenciamento";
    }

    /* Serviços usados */
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private EdificioService edificioService;
    @Autowired
    private CepService cepService;

}
