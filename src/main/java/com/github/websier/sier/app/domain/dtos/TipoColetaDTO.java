package com.github.websier.sier.app.domain.dtos;

import com.github.websier.sier.app.domain.enuns.TipoColeta;

/**
 * TipoColetaDTO
 */
public class TipoColetaDTO {

    private String nome;
    private String chave;

    public TipoColetaDTO(TipoColeta tipoColeta) {
        this.nome = tipoColeta.tipo;
        this.chave = tipoColeta.name();
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
    
    @Override
    public String toString() {
        return this.getNome();
    }
}