/**
 * SAIER 2.0.0.
 */
package br.uel.ceca.cin.saier.web.controllers;

import br.uel.ceca.cin.saier.enums.CargoNome;
import br.uel.ceca.cin.saier.enums.TemplatePath;
import br.uel.ceca.cin.saier.persistence.models.Usuario;
import br.uel.ceca.cin.saier.services.interfaces.CargoService;
import org.springframework.beans.factory.annotation.Autowired;
import br.uel.ceca.cin.saier.services.interfaces.UsuarioService;
import java.util.Arrays;
import javax.validation.Valid;
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
 *
 * @author Renato
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
     * @param listar
     * @return
     */
    @RequestMapping(value = {"/aluno", "/aluno/gerenciamento"})
    public String lista(Model listar) {

        /* obtendo todos alunos */
        listar.addAttribute("alunos", cargoService.ObterCargoPorNome(CargoNome.ALUNO.getCargoNome()).getUsuarios());

        return TemplatePath.LISTA_ALUNO.getPath();

    }

    @PostMapping(value = "/aluno/remover")
    public String remover(@RequestParam("id") Integer id) {

        /* Obtendo edificio a partir do Id */
        usuarioService.remover(usuarioService.obterPorId(id));

        return "redirect:/edificio/gerenciamento";
    }

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

                return "redirect:/aluno/formulario";
            } else {

                usuarioService.salvarUsuario(aluno);
                redirect.addFlashAttribute("atualizado", aluno);

                return "redirect:/aluno";
            }
        }
    }

    /**
     *
     * @param formulario
     * @return
     */
    @GetMapping("/aluno/formulario")
    public String formulario(Model formulario) {

        /* Novo aluno */
        formulario.addAttribute("aluno", new Usuario());

        return TemplatePath.FORMULARIO_ALUNO.getPath();

    }

    @GetMapping("/aluno/editar/{id}")
    public String editar(@PathVariable Integer id, Model formulario) {

        /* aluno */
        formulario.addAttribute("aluno", usuarioService.obterPorId(id));

        return TemplatePath.FORMULARIO_ALUNO.getPath();

    }

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private CargoService cargoService;

}
