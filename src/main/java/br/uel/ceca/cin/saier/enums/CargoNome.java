/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
