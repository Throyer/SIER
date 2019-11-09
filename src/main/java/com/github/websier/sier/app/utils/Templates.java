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

    /**
     * Paginas de erro sistema.
     * 
     * @since 3.0.0.
     * @author Renato Henrique.
     */
    public static class ERROR {

        private static final String ERROR_PATH = "errors/";

        public static final String INDEX = ERROR_PATH + "error";
    }

    /**
     * Paginas comuns do sistema.
     * 
     * @since 3.0.0.
     * @author Renato Henrique.
     */
    public static class MAIN {

        /**
         * Home.
         *
         * @see pagina index do sistema.
         */
        public static final String INDEX = MAIN_PATH + "index";

        /**
         * Contact.
         *
         * @see pagina de contato.
         */
        public static final String CONTATO = MAIN_PATH + "contato";
    }

    /**
     * Paginas relacionadas a sessão do usuario.
     * 
     * @since 3.0.0.
     * @author Renato Henrique.
     */
    public static class SECTION {

        /**
         * Login.
         *
         * @see formulario de login.
         */
        public static final String LOGIN = MAIN_PATH + "login";
    }

    /**
     * Paginas relacionadas a entidade Edifcio.
     * 
     * @since 3.0.0.
     * @author Renato Henrique.
     */
    public static class EDIFCIO {

        private static String BASE_PATH = "edificio/";

        /**
         * @see listagem dos edificios.
         */
        public static final String INDEX = MAIN_PATH + BASE_PATH + "index";

        /**
         * @see formulario de cadastro de edificios.
         */
        public static final String FORMULARIO = MAIN_PATH + BASE_PATH + "formulario";
    }

    /**
     * Paginas ao acervo de edifcios.
     * 
     * @since 3.0.0.
     * @author Renato Henrique.
     */
    public static class ACERVO {

        private static String BASE_PATH = "acervo/";

        /**
         * @see listagem do acervo edificios.
         */
        public static final String INDEX = MAIN_PATH + BASE_PATH + "index";
    }
}
