/*
 * Copyright (C) 2019 Renato Henrique
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

import static com.github.websier.sier.app.utils.GlobalConstants.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 *
 * @author Renato Henrique
 */
@Entity
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Por favor forneça um nome.")
    private String nome;

    @NotNull(message = "Por favor forneça um apelido.")
    private String apelido;

    @NotNull(message = "Por favor forneça um email.")
    private String email;

    @JsonIgnore
    @Pattern(regexp = SENHA_FORTE, message = MENSAGEM_SENHA_FORTE)
    private String senha;

    private Boolean ativo = true;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private LocalDateTime lastLogin;

    @ManyToOne
    private Cargo cargo;

    public Usuario() { }

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

    public String getEmail() {
        return email;
    }

    public String getApelido() {
        return apelido;
    }

    public void setApelido(String apelido) {
        this.apelido = apelido;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.setSenha(senha, FORCA_DA_CRIPTOGRAFIA_NA_SENHA);
    }

    public void setSenha(String senha, int força) {
        this.senha = new BCryptPasswordEncoder(força)
            .encode(senha);
    }

    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }

    public Boolean isAtivo() {
        return this.ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public LocalDateTime getUltimoLogin() {
        return lastLogin;
    }

    public void setUltiumoLogin(LocalDateTime lastLogin) {
        this.lastLogin = lastLogin;
    }

    @JsonIgnore
    public List<Cargo> getAuthorities() {
        return Arrays.asList(this.cargo);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.id);
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
        final Usuario other = (Usuario) obj;
        return Objects.equals(this.id, other.id);
    }

    public LocalDateTime getCriadoEm() {
        return createdAt;
    }

    public LocalDateTime getAtuaizadoEm() {
        return updatedAt;
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
        return this.getNome();
    }

}
