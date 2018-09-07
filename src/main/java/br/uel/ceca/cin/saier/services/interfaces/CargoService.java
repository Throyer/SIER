/*
 * S.A.I.E.R 2.0.0 | https://github.com/Throyer/SAIER | 2018.
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
