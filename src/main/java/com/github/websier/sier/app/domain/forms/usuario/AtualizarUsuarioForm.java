package com.github.websier.sier.app.domain.forms.usuario;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.github.websier.sier.app.domain.annotations.CargoExists;

/**
 * AtualizarUsuarioForm
 */
public class AtualizarUsuarioForm {

    @NotNull(message = "O nome não pode ser NULL.")
    @NotEmpty(message = "Por favor, forneça um nome.")
    private String nome;

    @NotNull(message = "O Apelido não pode ser NULL.")
    @NotEmpty(message = "Por favor, forneça um Apelido.")
    private String apelido;

    @CargoExists
    private String cargo;

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getApelido() {
        return this.apelido;
    }

    public void setApelido(String apelido) {
        this.apelido = apelido;
    }

    public String getCargo() {
        return this.cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }
}