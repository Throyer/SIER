/*
 * Copyright (C) 2019 Renato
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.github.websier.sier.app.domain.models;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.github.websier.sier.app.domain.dtos.Alerta;
import com.github.websier.sier.app.domain.embeddables.Coleta;
import com.github.websier.sier.app.domain.embeddables.Endereco;
import com.github.websier.sier.app.domain.interfaces.Notificavel;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * Classe responsavel por representar um Edificio.
 *
 * @author Renato
 * @since 3.0.0
 */
@Table(name = "residential_building")
@Entity
public class Edificio implements Serializable, Notificavel {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "official_name")
    private String nome;

    @NotEmpty(message = "Por favor, informe um nome fantasia valido.")
    @NotNull(message = "Por favor, informe o nome fantasia.")
    @Column(name = "name")
    private String nomeConhecido;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "foundation_date")
    private LocalDate dataConstrucao;

    @Column(name = "number_of_floors")
    private int numeroAndares;

    @Embedded
    private Endereco endereco;

    @Embedded
    private Coleta coleta;

    @Column(name = "created_at")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDateTime updatedAt;

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Edificio other = (Edificio) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @PrePersist
    private void created() {
        this.createdAt = LocalDateTime.now();
    }

    @PreUpdate
    private void updated() {
        this.updatedAt = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return this.nomeConhecido;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNomeConhecido() {
        return nomeConhecido;
    }

    public void setNomeConhecido(String nomeConhecido) {
        this.nomeConhecido = nomeConhecido;
    }

    public LocalDate getDataConstrucao() {
        return dataConstrucao;
    }

    public void setDataConstrucao(LocalDate dataConstrucao) {
        this.dataConstrucao = dataConstrucao;
    }

    public int getNumeroAndares() {
        return numeroAndares;
    }

    public void setNumeroAndares(int numeroAndares) {
        this.numeroAndares = numeroAndares;
    }

    public Coleta getColeta() {
        return coleta;
    }

    public void setColeta(Coleta coleta) {
        this.coleta = coleta;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public LocalDateTime getCadastradoEm() {
        return this.createdAt;
    }

    public LocalDateTime getAtuaizadoEm() {
        return this.updatedAt;
    }

    @Override
    public Alerta toAlerta() {
        return new Alerta(this.getNomeConhecido(), "edificio", this.getId());
    }
}
