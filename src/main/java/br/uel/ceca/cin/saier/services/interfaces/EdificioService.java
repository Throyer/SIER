/**
 * SAIER 2.0.0.
 */
package br.uel.ceca.cin.saier.services.interfaces;

import br.uel.ceca.cin.saier.persistence.models.CEP;
import br.uel.ceca.cin.saier.persistence.models.Edificio;
import br.uel.ceca.cin.saier.persistence.models.Usuario;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Renato
 */
public interface EdificioService {
    
    /**
     * 
     * @param id
     * @return 
     */
    public Edificio obterPorId(Integer id);
    
    public List<Edificio> obterPorNome(String nome);
    
    public List<Edificio> obterPorNomeConhecido(String nomeConhecido);
    
    public List<Edificio> obterPorUsuario(Usuario usuario);
    
    public List<Edificio> obterPorDataColeta(Date date);
    
    public List<Edificio> obterPorFonteColeta(String fonte);
    
    public boolean numeroNoCepExiste(CEP cep, Integer numero);
    
    public Edificio obterPorNumeroNoCep(CEP cep, Integer numero);

    /**
     * 
     * @return 
     */
    public Iterable<Edificio> obterTodos();
    
    /**
     * 
     * @param edificio 
     * @param tipo 
     */
    public void salvarEdificio(Edificio edificio);
    
    /**
     * 
     * @param edificio 
     */
    public void deletarEdificio(Edificio edificio);
    
    /**
     * 
     * @param edificio
     * @return 
     */
    public String getButtonURL(Edificio edificio);
    
}
