/**
 * SAIER 2.0.0.
 */
package br.uel.ceca.cin.saier.services.interfaces;

import br.uel.ceca.cin.saier.persistence.models.Usuario;

/**
 * Responsavel Services.
 * @author Renato
 * @version (02/06/2018)
 */
public interface UsuarioService {

    /**
     * Obter Usuario a partir do atributo email.
     * @param email
     * @return usuario
     */
    public Usuario obterPorEmail(String email);

    /**
     * Salvar um responsavel.
     * @param usuario
     */
    public void salvarUsuario(Usuario usuario);
    
    /**
     * Retorna uma instancia do usuario logado no sistema.
     *
     * @return UsuarioLogado
     */
    public Usuario getUsuarioLogado();

    public Usuario obterPorId(Integer id);

    public void remover(Usuario usuario);
}
