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
package com.github.websier.sier.app.utils;

/**
 * Constantes das configurações de segurança.
 * @since 3.0.0.
 * @author Renato Henrique
 */
public class SecurityConstants {

    public static final String SECRET = "$2a$10$bEe1HPsWbp8YvmCDiJFGEORUFcXtg75BRQ./5t.UFztNSjOg55iYO";

    public static final String USERNAME_PARAMETER = "email";
    public static final String PASSWORD_PARAMETER = "password";

    public static final Integer TOKEN_EXPIRATION = 86400;

    public static final String HOME_URL = "/";
    public static final String LOGIN_URL = "/login";
    public static final String LOGIN_ERROR_URL = LOGIN_URL + "?error=true";
    public static final String ACESSO_NEGADO_URL = "/?negado=true";
    public static final String LOGOUT_URL = "/logout";
    
    public static final String SESSION_COOKIE_NAME = "JSESSIONID";
    
    public static final String[] STATICOS_IGNORADOS = {
        "/robots.txt",
        "/font/**",
        "/css/**",
        "/webjars/**",
        "/webjars/",
        "/js/**",
        "/favicon.ico"
    };
}
