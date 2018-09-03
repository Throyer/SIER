/**
 * SAIER 2.0.0.
 */
package br.uel.ceca.cin.saier.services;

import br.uel.ceca.cin.saier.services.interfaces.CepService;
import br.uel.ceca.cin.saier.persistence.daos.CepRepository;
import br.uel.ceca.cin.saier.persistence.models.CEP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Renato
 */
@Service
public class CepServiceImpl implements CepService{
    
    @Autowired
    CepRepository cepRepository;

    @Override
    public void salvarCep(CEP cep) {
        cepRepository.save(cep);
    }
    
    @Override
    public CEP obterPorCep(String stringCep) {
        return cepRepository.findByCep(stringCep);
    }
    
     /**
     * Verifica se um CEP existe, caso não exista, salva ele no banco.
     *
     * @param cep
     * @return
     */
    @Override
    public CEP validarCep(CEP cep) {

        /* Se este cep existe no banco. */
        if (cepRepository.findByCep(cep.getCep()) != null) {

            /* Retorna o do banco. */
            return cepRepository.findByCep(cep.getCep());
        } else {

            /* se não, salva um novo cep no banco. */
            cepRepository.save(cep);
            /* e retorna esse cep, agora com um Id.*/
            return cepRepository.findByCep(cep.getCep());
        }
    }
    
}
