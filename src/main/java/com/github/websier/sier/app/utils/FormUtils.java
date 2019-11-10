package com.github.websier.sier.app.utils;

import java.util.List;

import com.github.websier.sier.app.domain.dtos.TipoColetaDTO;

/**
 * FormUtils
 */
public class FormUtils {
    public static List<TipoColetaDTO> tipos() {
        return List.of(
            new TipoColetaDTO("Internet", "INTERNET"),
            new TipoColetaDTO("Portaria", "PORTARIA"),
            new TipoColetaDTO("Jornal", "JORNAL"),
            new TipoColetaDTO("Prefeitura", "PREFEITURA"),
            new TipoColetaDTO("ACIL", "ACIL"),
            new TipoColetaDTO("Sinduscon", "SINDUSCON"),
            new TipoColetaDTO("Lista telefonica", "LISTA_TELEFONICA")
        );
    }
}