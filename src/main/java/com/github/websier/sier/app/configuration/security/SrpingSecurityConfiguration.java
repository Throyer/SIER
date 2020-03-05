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

import static com.github.websier.sier.app.utils.SecurityConstants.ACESSO_NEGADO_URL;
import static com.github.websier.sier.app.utils.SecurityConstants.HOME_URL;
import static com.github.websier.sier.app.utils.SecurityConstants.LOGIN_ERROR_URL;
import static com.github.websier.sier.app.utils.SecurityConstants.LOGIN_URL;
import static com.github.websier.sier.app.utils.SecurityConstants.LOGOUT_URL;
import static com.github.websier.sier.app.utils.SecurityConstants.PASSWORD_PARAMETER;
import static com.github.websier.sier.app.utils.SecurityConstants.SESSION_COOKIE_NAME;
import static com.github.websier.sier.app.utils.SecurityConstants.STATICOS_IGNORADOS;
import static com.github.websier.sier.app.utils.SecurityConstants.TOKEN_EXPIRATION;
import static com.github.websier.sier.app.utils.SecurityConstants.USERNAME_PARAMETER;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 * Security Configuration.
 * 
 * Classe de configurações de segurança. 
 * @author Renato Henrique.
 * @since 3.0.0.
 */
@EnableWebSecurity
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SrpingSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Value("${remember-me.secret}")
    private String SECRET;
    
    @Autowired
    SecurityService service;

    @Autowired
    private BCryptPasswordEncoder encoder;

    /**
     * Serviço de autenticação e Encoder.
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.
            userDetailsService(service)
                .passwordEncoder(encoder);
    }

    /**
     * Configuração.
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.
                /* urls publicas. */
                authorizeRequests()
                    .antMatchers(LOGIN_URL)
                        .permitAll()
                    .antMatchers(HttpMethod.GET, "/base/**", "/")
                        .permitAll()
                
                /* o restante precisa de login. */
                .anyRequest()
                    .authenticated()
                        .and()
                            .csrf()
                                .disable()
                
                /* fomulario de login. */
                .formLogin()
                    .loginPage(LOGIN_URL)
                        .failureUrl(LOGIN_ERROR_URL)
                            .defaultSuccessUrl(HOME_URL)
                                .usernameParameter(USERNAME_PARAMETER)
                                    .passwordParameter(PASSWORD_PARAMETER)
                                        .and()
                
                /* configuração do login permanente. */
                .rememberMe()
                    .key(SECRET)
                        .tokenValiditySeconds(TOKEN_EXPIRATION)
                            .and()
                
                /* configuração do logout. */
                .logout()
                    .deleteCookies(SESSION_COOKIE_NAME)
                        .logoutRequestMatcher(new AntPathRequestMatcher(LOGOUT_URL))
                            .logoutSuccessUrl(LOGIN_URL)
                                .and()
                                    .exceptionHandling()
                    
                /* configuração de acesso negado. */
                .accessDeniedPage(ACESSO_NEGADO_URL);
    }
    
    /**
     * Staticos.
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
            .antMatchers(STATICOS_IGNORADOS);
    }
}
