/*
 * S.A.I.E.R 2.0.0 | https://github.com/Throyer/SAIER | 2018.
 */
package br.uel.ceca.cin.saier.enums;

/**
 * Enum TemplatePath. Cotem o path de todos os templates do sistema.
 *
 * @author Renato
 * @version (14/08/2018)
 */
public enum TemplatePath {

    LOGIN("login"),
    HOME("saier/home"),
    NEWS("saier/news"),
    RELATORIOS("saier/relatorios"),
    CONTATO("saier/contato"),
    CONTA("saier/conta"),
    FORMULARIO_EDIFICIO("saier/management/edificios/form-edificio"),
    EDIFICIO_LISTA("saier/management/edificios/list-edificio"),
    FORMULARIO_ALUNO("saier/management/alunos/form-aluno"),
    LISTA_ALUNO("saier/management/alunos/list-aluno"),
    FORMULARIO_PROFESSOR("saier/management/professores/form-professor"),
    PROFESSOR_LISTA("saier/management/professores/list-professor"),
    NOTICIA_FORMULARIO("saier/management/noticias/form-noticia"),
    NOTICIA_LISTA("saier/management/noticias/list-noticia"),
    INSTALL("install"),
    SISTEMA("saier/management/sistema");

    private final String PATH;

    TemplatePath(String PATH) {
        this.PATH = PATH;
    }

    public String getPath() {
        return PATH;
    }
}
