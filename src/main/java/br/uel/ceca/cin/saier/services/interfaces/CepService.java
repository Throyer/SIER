/**
 * SAIER 2.0.0.
 */
package br.uel.ceca.cin.saier.services.interfaces;

import br.uel.ceca.cin.saier.persistence.models.CEP;

/**
 * Classe CepService. Contem os serviços de CEP.
 *
 * @author Renato
 * @version (14/08/2018)
 */
public interface CepService {

    public void salvarCep(CEP cep);

    public CEP obterPorCep(String stringCep);

    public CEP validarCep(CEP cep);
}
