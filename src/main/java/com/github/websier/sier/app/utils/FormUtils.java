package com.github.websier.sier.app.utils;

import static java.util.Optional.ofNullable;

import static com.github.websier.sier.app.utils.GlobalConstants.ERRO_CONFIRMAR_SENHA;
import static com.github.websier.sier.app.utils.GlobalConstants.ERRO_EMAIL;
import static com.github.websier.sier.app.utils.GlobalConstants.FORCA_DA_CRIPTOGRAFIA_NA_SENHA;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.criteria.Predicate;

import com.github.websier.sier.app.domain.dtos.TipoColetaDTO;
import com.github.websier.sier.app.domain.dtos.perfil.EmailDTO;
import com.github.websier.sier.app.domain.enuns.TipoColeta;
import com.github.websier.sier.app.domain.interfaces.Notificavel;
import com.github.websier.sier.app.domain.models.Usuario;
import com.github.websier.sier.app.domain.repositories.UsuarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * FormUtils
 */
@Component
public class FormUtils {

    private static UsuarioRepository repository;

    @Autowired
    public FormUtils(UsuarioRepository repository) {
        FormUtils.repository = repository;
    }

    public static List<TipoColetaDTO> tiposDeColeta() {
        return List.of(TipoColeta.values())
            .stream()
                .map(TipoColetaDTO::new)
                    .collect(Collectors.toList());
    }

    public static void confirmarSenha(
        BindingResult result,
        Optional<String> optionalConfirmacao,
        Optional<String> optionalSenha
    ) {
        var econder = new BCryptPasswordEncoder(FORCA_DA_CRIPTOGRAFIA_NA_SENHA);
        if (optionalSenha.isPresent() && optionalConfirmacao.isPresent()) {
            var confirmacao = optionalConfirmacao.get();
            var senha = optionalSenha.get();

            if (!econder.matches(confirmacao, senha)) {
                result.addError(ERRO_CONFIRMAR_SENHA);
            }
        }
    }

    public static void validarUnicidadeDoEmail(
        BindingResult result,
        EmailDTO emailDTO,
        Usuario usuario
    ) {
        validarUnicidadeDoEmail(
            result,
            ofNullable(emailDTO.getEmail()),
            ofNullable(usuario.getId())
        );
    }

    public static void validarUnicidadeDoEmail(
        BindingResult result,
        Usuario usuario)
    {
        validarUnicidadeDoEmail(
            result,
            ofNullable(usuario.getEmail()),
            ofNullable(usuario.getId())
        );
    }

    public static void validarUnicidadeDoEmail(
        BindingResult result,
        Optional<String> email,
        Optional<Long> id
    ) {
        if (
            id.isEmpty() &&
            isPresent(email) &&
            repository.existsByEmail(email.get())
        ) {
            result.addError(ERRO_EMAIL);
        }

        if (id.isPresent() && email.isPresent()) {
            var usuario = repository.findById(id.get())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

            var novoEmail = email.get();
            var emailAtual = usuario.getEmail();

            var mudouDeEmail = !emailAtual.equals(novoEmail);

            var jaEhUsadoPorOutraPessoa =  repository.existsByEmail(novoEmail);

            if (mudouDeEmail && jaEhUsadoPorOutraPessoa) {
                result.addError(ERRO_EMAIL);
            }
        }
    }

    public static boolean isPresent(Optional<String> field) {
        return field.isPresent() && !field.get().isEmpty() && !field.get().isBlank();
    }

    public static Predicate [] toArray(List<Predicate> predicates) {
        return predicates.toArray(new Predicate[predicates.size()]);
    }

    public static void addNotificacao(RedirectAttributes redirect, TipoAlerta tipo, Notificavel notificavel) {
        redirect.addFlashAttribute(tipo.nome, notificavel.toAlerta());
    }
}