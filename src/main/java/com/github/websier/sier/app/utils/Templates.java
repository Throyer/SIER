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
 * @since 3.0.0.
 * @author Renato Henrique
 */
public class Templates {

    private static final String MAIN_PATH = "views/";

    public static class MAIN {
        public static final String INDEX = MAIN_PATH + "index";
        public static final String CONTATO = MAIN_PATH + "contato";
        public static final String LOGIN = MAIN_PATH + "login";
        public static final String PERFIL = MAIN_PATH + "perfil";
    }

    public static class EDIFCIO {
        private static String BASE_PATH = "edificio/";

        public static final String INDEX = MAIN_PATH + BASE_PATH + "index";
        public static final String FORMULARIO = MAIN_PATH + BASE_PATH + "formulario";
    }

    public static class ALUNO {
        private static String BASE_PATH = "aluno/";

        public static final String INDEX = MAIN_PATH + BASE_PATH + "index";
        public static final String FORMULARIO = MAIN_PATH + BASE_PATH + "formulario";
    }

    public static class USUARIO {
        private static String BASE_PATH = "usuario/";

        public static final String INDEX = MAIN_PATH + BASE_PATH + "index";
        public static final String FORMULARIO = MAIN_PATH + BASE_PATH + "formulario";
    }

    public static class ACERVO {
        private static String BASE_PATH = "acervo/";

        public static final String INDEX = MAIN_PATH + BASE_PATH + "index";
    }
}
