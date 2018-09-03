/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uel.ceca.cin.saier.services;

import br.uel.ceca.cin.saier.persistence.models.Cargo;
import br.uel.ceca.cin.saier.services.interfaces.CargoService;
import br.uel.ceca.cin.saier.services.interfaces.SistemaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Renato
 */
@Service
public class SistemaServiceImpl implements SistemaService {

    @Override
    public boolean isConfigured() {
        
        Cargo administrador = cargoService.ObterCargoPorNome("ADMINISTRADOR");

        if (administrador != null) {
            
            if (administrador.getUsuarios() != null) {
                return !(administrador.getUsuarios().isEmpty());
            } else {
                return false;
            }
        } else {
            return false;
        }

    }
    
    /* Serviços usados */
    @Autowired
    CargoService cargoService;

}
