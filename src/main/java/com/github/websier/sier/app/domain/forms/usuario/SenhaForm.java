package com.github.websier.sier.app.domain.forms.usuario;

import static com.github.websier.sier.app.utils.GlobalConstants.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * SenhaForm
 */
public class SenhaForm {

    @NotEmpty(message = "Por favor, forneça uma senha.")
    @NotNull(message = "A senha não pode ser NULL.")
    @Pattern(regexp = SENHA_FORTE, message = MENSAGEM_SENHA_FORTE)
    private String senha;

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}