package com.github.websier.sier.app.domain.dtos;

/**
 * Alerta
 */
public class Alerta {

    private Long id;
    private String nome;
    private String entidade;

    public Alerta(String nome, String entidade, Long id) {
        this.id = id;
        this.nome = nome;
        this.entidade = entidade;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEntidade() {
        return entidade;
    }

    public void setEntidade(String entidade) {
        this.entidade = entidade;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}