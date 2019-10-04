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
package com.github.websier.sier.app.configuration.security;

import java.util.Collection;

import com.github.websier.sier.app.domain.models.Usuario;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

/**
 *
 * @author Renato Henrique
 */
public class Autenticado extends User {
    
    private static final long serialVersionUID = 1L;
    
    private String apelido;
    
    public Autenticado(
        String apelido,
        String username,
        String password,
        boolean enabled,
        boolean accountNonExpired,
        boolean credentialsNonExpired,
        boolean accountNonLocked,
        Collection<? extends GrantedAuthority> authorities
    ) {
        super(
            username,
            password,
            enabled,
            accountNonExpired,
            credentialsNonExpired,
            accountNonLocked,
            authorities
        );
        this.apelido = apelido;
    }

    public Autenticado(Usuario usuario) {
        super(
            usuario.getEmail(),
            usuario.getSenha(),
            usuario.isAtivo(),
            true,
            true,
            true,
            usuario.getAuthorities()
        );
        this.apelido = usuario.getApelido();
    }    

    public String getApelido() {
        return apelido;
    }
    
}
