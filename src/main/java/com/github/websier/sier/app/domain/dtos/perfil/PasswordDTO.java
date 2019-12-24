package com.github.websier.sier.app.domain.dtos.perfil;

import javax.validation.constraints.NotNull;

/**
 * TrocaDeSenhaDTO
 */
public class PasswordDTO {

    @NotNull
    private String senhaAtual;
    
    @NotNull
    private String novaSenha;
    
    @NotNull
    private String confirmaSenha;

    public String getSenhaAtual() {
        return senhaAtual;
    }

    public String getConfirmaSenha() {
        return confirmaSenha;
    }

    public void setConfirmaSenha(String confirmaSenha) {
        this.confirmaSenha = confirmaSenha;
    }

    public String getNovaSenha() {
        return novaSenha;
    }

    public void setNovaSenha(String novaSenha) {
        this.novaSenha = novaSenha;
    }

    public void setSenhaAtual(String senhaAtual) {
        this.senhaAtual = senhaAtual;
    }

}