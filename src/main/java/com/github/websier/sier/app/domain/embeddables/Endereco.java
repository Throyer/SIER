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

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * Classe repensável por representar os endereços
 * dos edifícios.
 * 
 * @author Renato henrique.
 * @since 3.0.0
 */
@Embeddable
public class Endereco {
    @Column(name = "postal_code")
    @NotNull(message = "Por favor, forneça o CEP.")
    private String cep;

    @Column(name = "address_number")
    @NotEmpty(message = "Por favor, forneça o numero do edificio.")
    private String numero;

    @Column(name = "street")
    @NotNull(message = "Por favor, forneça a rua.")
    private String rua;

    @Column(name = "neighborhood")
    @NotNull(message = "Por favor, forneça o bairro.")
    private String bairro;

    @Column(name = "city")
    @NotNull(message = "Por favor, forneça a cidade.")
    private String cidade;

    @Column(name = "state")
    @NotNull(message = "Por favor, forneça o estado.")
    private String estado;

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return this.cep;
    }
}