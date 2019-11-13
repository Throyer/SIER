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

import java.util.Optional;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;

/**
 * @since 3.0.0
 * Page Settings. 
 * Classe responsavel por toda a configuração de paginas do
 * sistema, como tamanho e outras configurações.
 */
public class PageSettings {

    private static final Integer DEFAULT_PAGE_NUMBER = 0;
    private static final Integer DEFAULT_PAGE_SIZE = 10;
    private static final Integer MAX_PAGE_SIZE = 500;

    public static Pageable of(
        Optional<Integer> optionalPage,
        Optional<Integer> optionalSize
    ) {
        Integer page = optionalPage.orElse(DEFAULT_PAGE_NUMBER);
        Integer size = optionalSize.orElse(DEFAULT_PAGE_SIZE);
        if (valid(page, size)) {
            return PageRequest.of(page, size);
        }
        return PageRequest.of(DEFAULT_PAGE_NUMBER, DEFAULT_PAGE_SIZE);
    }

    public static Pageable of(
        Optional<Integer> optionalPage,
        Optional<Integer> optionalSize,
        Direction direction,
        String... properties
    ) {
        Integer page = optionalPage.orElse(DEFAULT_PAGE_NUMBER);
        Integer size = optionalSize.orElse(DEFAULT_PAGE_SIZE);
        if (valid(page, size)) {
            return PageRequest.of(page, size, direction, properties);
        }
        return PageRequest.of(
            DEFAULT_PAGE_NUMBER,
            DEFAULT_PAGE_SIZE,
            direction,
            properties
        );
    }

    /**
     * Valida se a pagina não vai
     * ultrapassar o tamanho maximo permitido.
     * @param page numero da pagina.
     * @param size quantidade de elementos por pagina.
     * @return boolean
     */
    private static Boolean valid(Integer page, Integer size) {
        return ((page >= DEFAULT_PAGE_NUMBER) && (size <= MAX_PAGE_SIZE));
    }
}