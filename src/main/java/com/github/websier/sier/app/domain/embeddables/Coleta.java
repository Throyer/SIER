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
package com.github.websier.sier.app.domain.embeddables;

import static javax.persistence.CascadeType.DETACH;
import static javax.persistence.EnumType.STRING;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.github.websier.sier.app.domain.enuns.TipoColeta;
import com.github.websier.sier.app.domain.models.Usuario;

/**
 * Classe reponsavel por representar a coleta dos dados.
 * 
 * @author Renato Henrique.
 * @since 3.0.0
 */
@Embeddable
public class Coleta {

    @NotEmpty(message = "Por favor, informe a fonte da coleta dos dados.")
    @Enumerated(STRING)
    @Column(name = "data_source")
    private TipoColeta fonteColeta;

    @Size(max = 350)
    @Column(name = "additional_information")
    private String informacoes;

    @Column(name = "collected_at")
    private LocalDate collectedAt;

    @ManyToOne(cascade = DETACH)
    @JoinColumn(name = "created_by")
    private Usuario createdBy;
    
    @ManyToOne
    @JoinColumn(name = "updated_by")
    private Usuario updatedBy;

    public TipoColeta getFonteColeta() {
        return fonteColeta;
    }

    public void setFonteColeta(TipoColeta fonteColeta) {
        this.fonteColeta = fonteColeta;
    }

    public String getInformacoes() {
        return informacoes;
    }

    public void setInformacoes(String informacoes) {
        this.informacoes = informacoes;
    }

    public Usuario getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Usuario createdBy) {
        this.createdBy = createdBy;
    }

    public Usuario getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(Usuario updatedBy) {
        this.updatedBy = updatedBy;
    }

    public LocalDate getCollectedAt() {
        return collectedAt;
    }

    public void setCollectedAt(LocalDate collectedAt) {
        this.collectedAt = collectedAt;
    }
}