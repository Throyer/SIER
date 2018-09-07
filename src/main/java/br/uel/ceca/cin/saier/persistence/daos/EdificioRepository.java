/*
 * S.A.I.E.R 2.0.0 | https://github.com/Throyer/SAIER | 2018.
 */
package br.uel.ceca.cin.saier.persistence.daos;

import br.uel.ceca.cin.saier.persistence.models.CEP;
import br.uel.ceca.cin.saier.persistence.models.Edificio;
import br.uel.ceca.cin.saier.persistence.models.Usuario;
import java.util.Date;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositorio de edificios.
 *
 * @author Renato
 * @version (02/06/2018)
 */
@Repository
public interface EdificioRepository extends JpaRepository<Edificio, Integer> {

    /**
     * Obter uma lista paginavel com todos Edificios.
     *
     * @param pageable
     * @return
     */
    @Override
    Page<Edificio> findAll(Pageable pageable);

    public List<Edificio> findByNome(String nome);

    public List<Edificio> findByNomeConhecido(String nomeConhecido);

    public List<Edificio> findByUsuario(Usuario usuario);

    public List<Edificio> findByDataColeta(Date date);

    public List<Edificio> findByFonteColeta(String fonte);

    public List<Edificio> findByCep(CEP cep);

}
