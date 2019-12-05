package com.github.websier.sier.app.utils;

import static com.github.websier.sier.app.utils.GlobalConstants.ERRO_CONFIRMAR_SENHA;
import static com.github.websier.sier.app.utils.GlobalConstants.ERRO_EMAIL;
import static com.github.websier.sier.app.utils.GlobalConstants.FORCA_DA_CRIPTOGRAFIA_NA_SENHA;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.github.websier.sier.app.domain.dtos.TipoColetaDTO;
import com.github.websier.sier.app.domain.enuns.TipoColeta;
import com.github.websier.sier.app.domain.repositories.UsuarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.web.server.ResponseStatusException;

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
        Optional<String> email,
        Optional<Long> id
    ) {
        if (
            id.isEmpty() &&
            email.isPresent() &&
            repository.existsByEmail(email.get())
        ) {
            result.addError(ERRO_EMAIL);
        }

        if (id.isPresent() && email.isPresent()) {
            var usuario = repository.findById(id.get())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

            if (!usuario.getEmail().equals(email.get())) {
                result.addError(ERRO_EMAIL);
            }
        }
    }

    private static String snakeUperCaseToCamelcaseWhithSpaces(String chave) {
        if (chave == null || chave.isEmpty()) {
            return chave;
        }
        
        StringBuilder resultado = new StringBuilder();
     
        boolean converterProximo = true;
        for (char letra : chave.toCharArray()) {
            if (Character.isSpaceChar(letra)) {
                converterProximo = true;
            } else if (converterProximo) {
                letra = Character.toTitleCase(letra);
                converterProximo = false;
            } else {
                letra = Character.toLowerCase(letra);
            }
            resultado.append(letra);
        }
     
        return resultado.toString().replaceAll("_", " ");
    }
}