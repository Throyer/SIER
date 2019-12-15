package com.github.websier.sier.app.utils;

/**
 * TipoEntidade
 */
public enum TipoAlerta {

    NOVO("novo"),
    ATUALIZADO("atualizado"),
    DELETADO("deletado"),
    DESBLOQUEADO("desbloqueado"),
    BLOQUEADO("bloqueado");

    public String nome;
    
    TipoAlerta(String nome) {
        this.nome = nome;
    }
}