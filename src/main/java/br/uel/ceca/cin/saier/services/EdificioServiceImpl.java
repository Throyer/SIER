/*
 * S.A.I.E.R 2.0.0 | https://github.com/Throyer/SAIER | 2018.
 */
package br.uel.ceca.cin.saier.services;

import br.uel.ceca.cin.saier.services.interfaces.EdificioService;
import br.uel.ceca.cin.saier.persistence.daos.EdificioRepository;
import br.uel.ceca.cin.saier.persistence.models.Edificio;
import br.uel.ceca.cin.saier.persistence.models.Usuario;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.uel.ceca.cin.saier.persistence.models.CEP;

/**
 *
 * @author Renato
 */
@Service
public class EdificioServiceImpl implements EdificioService {

    @Override
    public Edificio obterPorId(Integer id) {
        return edificioRepository.findById(id).get();
    }

    @Override
    public List<Edificio> obterPorNome(String nome) {
        return edificioRepository.findByNome(nome);
    }

    @Override
    public List<Edificio> obterPorNomeConhecido(String nomeConhecido) {
        return edificioRepository.findByNomeConhecido(nomeConhecido);
    }

    @Override
    public List<Edificio> obterPorUsuario(Usuario usuario) {
        return edificioRepository.findByUsuario(usuario);
    }

    @Override
    public List<Edificio> obterPorDataColeta(Date date) {
        return edificioRepository.findByDataColeta(date);
    }

    @Override
    public List<Edificio> obterPorFonteColeta(String fonte) {
        return edificioRepository.findByFonteColeta(fonte);
    }

    /**
     *
     * @return
     */
    @Override
    public List<Edificio> obterTodos() {
        return edificioRepository.findAll();
    }

    /**
     *
     * @param edificio
     */
    @Override
    public void salvarEdificio(Edificio edificio) {
        edificioRepository.save(edificio);
    }

    /**
     *
     * @param edificio
     */
    @Override
    public void deletarEdificio(Edificio edificio) {
        edificioRepository.delete(edificio);
    }

    /**
     * Retorna o edificio com o numero recebido, onde o CEP é igual ao recebido.
     * @param cep
     * @param numero
     * @return
     */
    @Override
    public Edificio obterPorNumeroNoCep(CEP cep, Integer numero) {
        List<Edificio> edificios = cep.getEdificios();

        for (Edificio edificio : edificios) {
            if (edificio.getNumero() == numero) {
                return edificio;
            }
        }
        return null;
    }

    /**
     * Verifica se já existe um edificio com o numero recebido nesse cep.
     *
     * @param cep
     * @param numero
     * @return
     */
    @Override
    public boolean numeroNoCepExiste(CEP cep, Integer numero) {

        List<Edificio> edificios = cep.getEdificios();

        if (edificios.stream().anyMatch((edificio) -> (edificio.getNumero() == numero))) {
            return true;
        }
        return false;
    }

    @Override
    public String getButtonURL(Edificio edificio) {

        String button
                = "<a class='btn btn-default' href='http://maps.google.com/?q="
                + edificio.getCep().getCep() + " "
                + edificio.getCep().getRua() + " "
                + edificio.getNumero()
                + "' target='_blank' role='button'><i class='fas fa-map-marker-alt'></i> Abrir endereço no maps</a>";

        return button;
    }

    @Autowired
    private EdificioRepository edificioRepository;

}
