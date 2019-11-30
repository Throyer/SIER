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

import org.springframework.validation.ObjectError;

/**
 * Global Constants.
 * 
 * Classe global de contantes do sistema.
 * 
 * @since 3.0.0.
 * @author Renato Henrique.
 */
public class GlobalConstants {
    public static final Integer FORCA_DA_CRIPTOGRAFIA_NA_SENHA = 10;
    public static final String SENHA_FORTE = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$";
    public static final String MENSAGEM_SENHA_FORTE = "No mínimo 8 caracteres, com no mínimo um número, um caractere especial, uma letra maiúscula e uma letra minúscula.";
    public static final ObjectError ERRO_CONFIRMAR_SENHA = new ObjectError("Confirmação da senha", "Por favor, confirme a senha.");
    public static final ObjectError ERRO_EMAIL = new ObjectError("Email", "Email já utilizado por outro usuario. Por favor, utilize um e-mail diferente.");
}