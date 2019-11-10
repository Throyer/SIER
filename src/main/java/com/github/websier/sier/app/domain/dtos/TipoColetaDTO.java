package com.github.websier.sier.app.domain.dtos;

/**
 * TipoColetaDTO
 */
public class TipoColetaDTO {

    private String nome;
    private String chave;

    public TipoColetaDTO(String nome, String chave) {
        this.nome = nome;
        this.chave = chave;
    }

    public String getNome() {
        return nome;
    }

    public String getChave() {
        return chave;
    }

    public void setChave(String chave) {
        this.chave = chave;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}