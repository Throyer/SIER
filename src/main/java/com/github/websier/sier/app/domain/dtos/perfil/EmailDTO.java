package com.github.websier.sier.app.domain.dtos.perfil;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.github.websier.sier.app.domain.models.Usuario;

/**
 * EmailDTO
 */
public class EmailDTO {

    @Email
    @NotNull
    @NotEmpty
    private String email;

    public EmailDTO() { }

    public EmailDTO(Usuario usuario) {
        this.email = usuario.getEmail();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}