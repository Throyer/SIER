package com.github.websier.sier.app.domain.dtos.perfil;

import javax.validation.constraints.NotNull;

import com.github.websier.sier.app.domain.models.Usuario;

/**
 * NomeApelidoDTO
 */
public class NomeApelidoDTO {

    @NotNull
    private String nome;

    @NotNull
    private String apelido;

    public NomeApelidoDTO() { }

    public NomeApelidoDTO(Usuario usuario) {
        this.nome = usuario.getNome();
        this.apelido = usuario.getApelido();
    }

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