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

import java.util.Objects;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.github.websier.sier.app.domain.enuns.TipoColeta;
import com.github.websier.sier.app.domain.models.Usuario;

/**
 * Classe reponsavel por representar a coleta dos dados.
 * 
 * @author Renato Henrique.
 * @since 3.0.0.
 */
@Embeddable
public class Coleta {

    @NotEmpty(message = "Por favor, informe a fonte da coleta dos dados.")
    @Enumerated(EnumType.STRING)
    private TipoColeta fonteColeta;

    @Size(max = 350)
    private String informacoes;

    @ManyToOne
    private Usuario cadastradoPor;
    
    @ManyToOne
    private Usuario atualizadoPor;

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

    public Usuario getCadastradoPor() {
        return cadastradoPor;
    }

    public void setCadastradoPor(Usuario cadastradoPor) {
        if (Objects.isNull(this.cadastradoPor)) {
            this.cadastradoPor = cadastradoPor;
        }
    }

    public Usuario getAtualizadoPor() {
        return atualizadoPor;
    }

    public void setAtualizadoPor(Usuario atualizadoPor) {
        this.atualizadoPor = atualizadoPor;
    }
}