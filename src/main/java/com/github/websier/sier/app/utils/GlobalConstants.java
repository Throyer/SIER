package com.github.websier.sier.app.utils;

/**
 * GlobalConstants
 */
public class GlobalConstants {

    public static final String SENHA_FORTE = "^.*(?=.{8,})((?=.*[!@#$%^&*()\\-_=+{};:,<.>]){1})(?=.*\\d)((?=.*[a-z]){1})((?=.*[A-Z]){1}).*$";
    public static final String MENSAGEM_SENHA_FORTE = "No mínimo 8 caracteres, com no mínimo um número, um caractere especial, uma letra maiúscula e uma letra minúscula.";
}