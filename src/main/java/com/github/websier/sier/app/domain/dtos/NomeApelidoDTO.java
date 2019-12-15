package com.github.websier.sier.app.domain.dtos;

import javax.validation.constraints.NotNull;

/**
 * NomeApelidoDTO
 */
public class NomeApelidoDTO {

    @NotNull
    private String nome;

    @NotNull
    private String apelido;

    public String getApelido() {
        return apelido;
    }

    public void setApelido(String apelido) {
        this.apelido = apelido;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}