/*
 * S.A.I.E.R 2.0.0 | https://github.com/Throyer/SAIER | 2018.
 */
package br.uel.ceca.cin.saier.services;

import br.uel.ceca.cin.saier.persistence.daos.CargoRepository;
import br.uel.ceca.cin.saier.persistence.models.Cargo;
import br.uel.ceca.cin.saier.services.interfaces.CargoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Serviço de Cargos do sistema.
 *
 * @author Renato
 * @version (30/08/2018)
 */
@Service
public class CargoServiceImpl implements CargoService {

    @Override
    public Cargo ObterCargoPorNome(String nome) {
        return cargoDao.findByNome(nome);
    }

    @Override
    public void salvarCargo(Cargo cargo) {
        cargoDao.save(cargo);
    }

    @Autowired
    CargoRepository cargoDao;

}
