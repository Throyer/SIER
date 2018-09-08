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
    EDIFICIO("edificio/edificio"),
    FORMULARIO_EDIFICIO("edificio/formulario"),
    EDIFICIO_LISTA("edificio/index"),
    ALUNO("aluno/aluno"),
    FORMULARIO_ALUNO("aluno/formulario"),
    LISTA_ALUNO("aluno/index"),
    PROFESSOR("professor/professor"),
    FORMULARIO_PROFESSOR("professor/formulario"),
    PROFESSOR_LISTA("professor/index"),
    NOTICIA_FORMULARIO("noticia/formulario"),
    NOTICIA_LISTA("noticia/index"),
    INSTALL("install"),
    SISTEMA("sistema/sistema"),
    ERRO_404("erros/erro404"),
    ERRO_PADRAO("erros/erro");

    private final String PATH;

    TemplatePath(String PATH) {
        this.PATH = PATH;
    }

    public String getPath() {
        return PATH;
    }
}
