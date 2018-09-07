/*
 * S.A.I.E.R 2.0.0 | https://github.com/Throyer/SAIER | 2018.
 */
package br.uel.ceca.cin.saier.persistence.daos;

import br.uel.ceca.cin.saier.persistence.models.Cargo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositorio de Roles do SpringSercurity.
 *
 * @author Renato
 * @version (02/06/2018)
 */
@Repository
public interface CargoRepository extends JpaRepository<Cargo, Integer> {

    /**
     * Obter Cargo a partir do atributo nome.
     * @param nome
     * @return
     */
    Cargo findByNome(String nome);
    
}
