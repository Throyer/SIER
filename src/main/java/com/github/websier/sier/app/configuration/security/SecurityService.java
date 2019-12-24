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

import java.util.Optional;

import com.github.websier.sier.app.domain.repositories.UsuarioRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Security Service.
 * 
 * Serviço de autenticação de usuarios.
 * 
 * @author Renato Henrique.
 * @since 3.0.0.
 */
@Service
public class SecurityService implements UserDetailsService {

    @Autowired
    UsuarioRepository repository;

    private Logger logger = LoggerFactory.getLogger(SecurityService.class);

    private static final String NOME_USUARIO_IVALIDO = "Nome de usuario invalido.";
    private static final String NAO_EXISTE_USUARIO_LOGADO = "Não existe um usuario logado.";

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return new Autenticado(repository.findOptionalByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(NOME_USUARIO_IVALIDO)));
    }

    public Optional<Autenticado> getAutenticado() {
        try {
            return Optional.of((Autenticado) getPrincipal());
        } catch (Exception exception) {
            logger.error(NAO_EXISTE_USUARIO_LOGADO, exception);
            return Optional.empty();
        }
    }

    public Object getPrincipal() {
        return SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
