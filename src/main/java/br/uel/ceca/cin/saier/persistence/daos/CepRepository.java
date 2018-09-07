/*
 * S.A.I.E.R 2.0.0 | https://github.com/Throyer/SAIER | 2018.
 */
package br.uel.ceca.cin.saier.persistence.daos;

import br.uel.ceca.cin.saier.persistence.models.CEP;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositorio de CEP.
 *
 * @author Renato
 * @version (02/06/2018)
 */
@Repository
public interface CepRepository extends CrudRepository<CEP, Integer> {

    /**
     * Obter CEP a partir do atributo cep.
     *
     * @param cep
     * @return
     */
    CEP findByCep(String cep);
}
