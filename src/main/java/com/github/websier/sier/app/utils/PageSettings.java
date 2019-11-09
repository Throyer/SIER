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

/**
 * Page Settings. Classe responsavel por toda a configuração de paginas do
 * sistema, como tamanho e outras configurações.
 */
public class PageSettings {

    public static Pageable of(
        Optional<Integer> optionalPage,
        Optional<Integer> optionalSize
    ) {
        Integer page = optionalPage.orElse(0);
        Integer size = optionalSize.orElse(10);
        if (valid(page, size)) {
            return PageRequest.of(page, size);
        }
        return PageRequest.of(0, 10);
    }

    private static Boolean valid(Integer page, Integer size) {
        return ((page >= 0) && (size <= 500));
    }
}