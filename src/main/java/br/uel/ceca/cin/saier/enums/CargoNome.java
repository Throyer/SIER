/*
 * S.A.I.E.R 2.0.0 | https://github.com/Throyer/SAIER | 2018.
 */
package br.uel.ceca.cin.saier.enums;

/**
 *
 * @author Renato
 */
public enum CargoNome {
    ALUNO("ALUNO"),
    PROFESSOR("PROFESSOR"),
    ADMINISTRADOR("ADMINISTRADOR");
    
    private final String CARGO;

    CargoNome(String CARGO) {
        this.CARGO = CARGO;
    }

    public String getCargoNome() {
        return CARGO;
    }
}
