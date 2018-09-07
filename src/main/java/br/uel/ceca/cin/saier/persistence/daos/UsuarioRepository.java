/*
 * S.A.I.E.R 2.0.0 | https://github.com/Throyer/SAIER | 2018.
 */
package br.uel.ceca.cin.saier.persistence.daos;

import br.uel.ceca.cin.saier.persistence.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositorio de Responsaveis.
 *
 * @author Renato
 * @version (02/06/2018)
 */
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    /**
     * Obter um usuario a partir do atribuito email.
     * @param email
     * @return usuario
     */
    Usuario findByEmail(String email);
}
