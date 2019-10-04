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
package com.github.websier.sier.app.domain.forms.usuario;

import static com.github.websier.sier.app.utils.GlobalConstants.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.github.websier.sier.app.domain.annotations.CargoExists;
import com.github.websier.sier.app.domain.annotations.EmailExists;
import com.github.websier.sier.app.domain.models.Usuario;

/**
 * UsuarioFORM
 */
public class UsuarioForm {

    @NotNull(message = "O nome não pode ser NULL.")
    @NotEmpty(message = "Por favor, forneça um nome.")
    private String nome;
    
    @NotNull(message = "O Apelido não pode ser NULL.")
    @NotEmpty(message = "Por favor, forneça um Apelido.")
    private String apelido;
    
    @EmailExists
    @NotNull(message = "O e-mail não pode ser NULL.")
    @Email(message = "Por favor, forneça um e-mail valido.")
    private String email;
    
    @Pattern(regexp = SENHA_FORTE, message = MENSAGEM_SENHA_FORTE)
    private String senha;

    @CargoExists
    private String cargo;

    public String getNome() {
        return nome;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getApelido() {
        return apelido;
    }

    public void setApelido(String apelido) {
        this.apelido = apelido;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

	public Usuario toUsuario() {
		return new Usuario(this);
	}
    
}