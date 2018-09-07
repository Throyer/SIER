/*
 * S.A.I.E.R 2.0.0 | https://github.com/Throyer/SAIER | 2018.
 */
package br.uel.ceca.cin.saier.web.controllers;

import br.uel.ceca.cin.saier.enums.CargoNome;
import br.uel.ceca.cin.saier.enums.TemplatePath;
import br.uel.ceca.cin.saier.persistence.models.Usuario;
import br.uel.ceca.cin.saier.services.interfaces.CargoService;
import br.uel.ceca.cin.saier.services.interfaces.UsuarioService;

import java.util.Arrays;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Classe controladora das requisições para Aluno.
 *
 * @author Renato
 * @version (07/09/2018)
 */
@Controller
public class AlunoController {

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
    @RequestMapping(value = {"/aluno", "/aluno/gerenciamento"})
    public String lista(Model listagem) {

        /* obtendo todos alunos */
        listagem.addAttribute("alunos", cargoService.ObterCargoPorNome(CargoNome.ALUNO.getCargoNome()).getUsuarios());

        return TemplatePath.LISTA_ALUNO.getPath();

    }

    /**
     * Remover aluno a partir do Id.
     *
     * @param id
     * @return redireciona para gerenciamento de alunos.
     */
    @PostMapping(value = "/aluno/remover")
    public String remover(@RequestParam("id") Integer id) {

        /* Obtendo edificio a partir do Id */
        usuarioService.remover(usuarioService.obterPorId(id));

        return "redirect:" + "/edificio/gerenciamento";
    }

    /**
     * Salvar aluno.
     *
     * Se o id do aluno recibido for nulo, é cadastrado um novo aluno, caso
     * contrario o aluno com o id recebido é atualizado no banco.
     *
     * @param aluno
     * @param result
     * @param formulario
     * @param redirect
     * @param ano
     * @param confirmarSenha
     * @return
     */
    @PostMapping("/aluno/formulario")
    public String salvar(@Valid @ModelAttribute("aluno") Usuario aluno,
            BindingResult result,
            Model formulario,
            RedirectAttributes redirect,
            @RequestParam("turma") Integer ano,
            @RequestParam("confirmarSenha") String confirmarSenha) {

        /* verificando se é uma edição ou um novo registro */
        boolean novo = aluno.getId() == null;

        if (novo && usuarioService.obterPorEmail(aluno.getEmail()) != null) {

            result.rejectValue("email", "error.email", "O email: <strong>"
                    + aluno.getEmail() + "</strong> já é utilizado.<br>Por favor digite um email diferente.");
        }

        /* Caso da senha não for confirmada */
        if (!aluno.getSenha().equals(confirmarSenha)) {

            result.rejectValue("senha", "error.senha", "Senhas não conferem. Por favor digite novamene.");
        }

        if (result.hasErrors()) {

            /* Devolvendo os dados para o formulario */
            formulario.addAttribute("aluno", aluno);

            return TemplatePath.FORMULARIO_ALUNO.getPath();

        } else {

            aluno.setCargos(Arrays.asList(cargoService.ObterCargoPorNome(CargoNome.ALUNO.getCargoNome())));

            if (novo) {

                usuarioService.salvarUsuario(aluno);
                redirect.addFlashAttribute("cadastro", aluno);

                return "redirect:" + "/aluno/formulario";
            } else {

                usuarioService.salvarUsuario(aluno);
                redirect.addFlashAttribute("atualizado", aluno);

                return "redirect:" + "/aluno";
            }
        }
    }

    /**
     * Formulario para cadastro de aluno.
     *
     * @param formulario
     * @return formulario para cadastro de aluno
     */
    @GetMapping("/aluno/formulario")
    public String formulario(Model formulario) {

        /* Novo aluno */
        formulario.addAttribute("aluno", new Usuario());

        return TemplatePath.FORMULARIO_ALUNO.getPath();

    }

    /**
     * Editar aluno a partir do id.
     *
     * @param id
     * @param formulario
     * @return
     */
    @GetMapping("/aluno/editar/{id}")
    public String editar(@PathVariable Integer id, Model formulario) {

        /* aluno */
        formulario.addAttribute("aluno", usuarioService.obterPorId(id));

        return TemplatePath.FORMULARIO_ALUNO.getPath();

    }

    /* Serviços usados */
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private CargoService cargoService;

}
