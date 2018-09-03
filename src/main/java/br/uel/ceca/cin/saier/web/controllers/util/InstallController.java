/*
 * S.A.I.E.R 2.0.0 | https://github.com/Throyer/SAIER | 2018.
 */
package br.uel.ceca.cin.saier.web.controllers.util;

import br.uel.ceca.cin.saier.enums.TemplatePath;
import br.uel.ceca.cin.saier.persistence.models.Cargo;
import br.uel.ceca.cin.saier.persistence.models.Usuario;
import br.uel.ceca.cin.saier.services.interfaces.CargoService;
import br.uel.ceca.cin.saier.services.interfaces.SistemaService;
import br.uel.ceca.cin.saier.services.interfaces.UsuarioService;

import java.util.Arrays;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Controlador responsavel por criar as configurações do sistema e o primeiro
 * administrador.
 *
 * @author Renato
 * @version (30/08/2018)
 */
@Controller
public class InstallController {

    /**
     * Formulario de cadastro do administrador. Se o sistema já possui algum
     * administrador, o usuario é redirecionado para a pagina inicial.
     *
     * @param install
     * @param redirect
     * @return pagina de boas vindas ou formulario de administrador.
     */
    @GetMapping(value = "/install")
    public String formulario(Model install, RedirectAttributes redirect) {

        if (sistemaService.isConfigured()) {

            redirect.addFlashAttribute("config", sistemaService.isConfigured());

            return "redirect:/";

        } else {

            install.addAttribute("administrador", new Usuario());

            return TemplatePath.INSTALL.getPath();

        }

    }

    /**
     * Cadastro do primeiro administrador do sistema.
     *
     * @param administrador
     * @param result
     * @param install
     * @param redirect
     * @param confirmarSenha
     * @return pagina de boas vindas.
     */
    @PostMapping(value = "/install")
    public String salvar(@Valid @ModelAttribute("administrador") Usuario administrador,
            BindingResult result,
            Model install,
            RedirectAttributes redirect,
            @RequestParam("confirmarSenha") String confirmarSenha) {

        if (!sistemaService.isConfigured()) {

            /* Caso da senha não for confirmada */
            if (!administrador.getSenha().equals(confirmarSenha)) {

                result.rejectValue("senha", "error.senha", "Senhas não conferem. Por favor digite novamene.");
            }

            /* Caso existam erros no install */
            if (result.hasErrors()) {

                /* Devolvendo os dados para o install */
                install.addAttribute("administrador", administrador);

                return TemplatePath.INSTALL.getPath();

            } else {

                /* Criando o cargo de ALUNO */
                cargoService.salvarCargo(new Cargo(ALUNO));

                /* Criando o cargo de PROFESSOR */
                cargoService.salvarCargo(new Cargo(PROFESSOR));

                /* Criando o cargo de ADMINISTRADOR */
                cargoService.salvarCargo(new Cargo(ADMINISTRADOR));

                /* cargos de adminstrador (administrador tambem pode ser curador) */
                administrador.setCargos(Arrays.asList(
                        cargoService.ObterCargoPorNome(ALUNO),
                        cargoService.ObterCargoPorNome(PROFESSOR),
                        cargoService.ObterCargoPorNome(ADMINISTRADOR)));

                /* Salvando adminstrador */
                usuarioService.salvarUsuario(administrador);

                /* Dados do ADMINISTRADOR */
                redirect.addFlashAttribute("adminstrador", administrador);

                /* Redireciona para a pagina inicial */
                return "redirect:/";
            }

        } else {

            redirect.addFlashAttribute("config", sistemaService.isConfigured());

            return "redirect:/";
        }

    }

    /* Serviços usados */
    @Autowired
    private SistemaService sistemaService;
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private CargoService cargoService;

    /* Nomes dos cargos do sistema */
    private final static String ALUNO = "ALUNO";
    private final static String PROFESSOR = "PROFESSOR";
    private final static String ADMINISTRADOR = "ADMINISTRADOR";

}
