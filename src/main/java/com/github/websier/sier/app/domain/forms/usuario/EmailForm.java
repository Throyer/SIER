package com.github.websier.sier.app.domain.forms.usuario;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.github.websier.sier.app.domain.annotations.EmailExists;

/**
 * EmailForm
 */
public class EmailForm {

    @EmailExists
    @NotEmpty(message = "Por favor, forneça um e-mail.")
    @NotNull(message = "O Email não pode ser NULL.")
    @Email(message = "Por favor, forneça um email valido.")
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}