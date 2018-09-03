/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uel.ceca.cin.saier.services.interfaces;

import br.uel.ceca.cin.saier.persistence.models.Cargo;

/**
 *
 * @author Renato
 */
public interface CargoService {

    public Cargo ObterCargoPorNome(String ADMINISTRADOR);

    public void salvarCargo(Cargo cargo);
    
}
