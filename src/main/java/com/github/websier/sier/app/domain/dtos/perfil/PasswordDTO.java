package com.github.websier.sier.app.domain.dtos.perfil;

import java.util.Objects;

import javax.validation.constraints.NotNull;

import com.github.websier.sier.app.domain.models.Usuario;

import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

/**
 * TrocaDeSenhaDTO
 */
public class PasswordDTO {

    @NotNull(message = "Por favor informe a senha atual")
    private String senhaAtual;
    
    @NotNull(message = "Por favor informe uma nova senha")
    private String novaSenha;
    
    @NotNull(message = "Por favor confirme a senha nova")
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

    public void validate(Usuario usuario, BindingResult result) {
        if (Objects.nonNull(senhaAtual) && !usuario.confirmarSenha(senhaAtual)) {
            result.addError(new ObjectError("Senha anterior", "Senha anterior invalida"));
        }
        
        if (Objects.nonNull(novaSenha) && !novaSenha.equals(confirmaSenha)) {
            result.addError(new ObjectError("Confirmação da senha", "Valor informado na confirmação de senha invalido"));
        }
    }
}