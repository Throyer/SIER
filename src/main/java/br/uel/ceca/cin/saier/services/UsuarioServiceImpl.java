/**
 * SAIER 2.0.0.
 */
package br.uel.ceca.cin.saier.services;

import br.uel.ceca.cin.saier.services.interfaces.UsuarioService;
import br.uel.ceca.cin.saier.persistence.models.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import br.uel.ceca.cin.saier.persistence.daos.UsuarioRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import br.uel.ceca.cin.saier.persistence.daos.CargoRepository;
import java.util.Collection;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

/**
 * Implementação de UsuarioService.
 *
 * @author Renato
 * @version (02/06/2018)
 */
@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioDAO;
    @Autowired
    private CargoRepository cargoDAO;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public Usuario obterPorEmail(String email) {
        return usuarioDAO.findByEmail(email);
    }

    @Override
    public void salvarUsuario(Usuario usuario) {
        usuario.setSenha(bCryptPasswordEncoder.encode(usuario.getSenha()));
        usuario.setAtividade(1);
        usuarioDAO.save(usuario);
    }

    /**
     * Retorna o usuario logado na sessão.
     *
     * @return UsuarioLogado
     */
    @Override
    public Usuario getUsuarioLogado() {

        /* Obtendo usuario logado */
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return setCargos(usuarioDAO.findByEmail(auth.getName()));
    }

    private Usuario setCargos(Usuario usuario) {

        /* Obtendo Classe de atentificação */
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        /* coleção de permissoes do usuario */
        Collection<? extends GrantedAuthority> authorities = auth.getAuthorities();

        if (usuario != null) {

            /* se for aluno */
            usuario.setAluno(authorities.contains(ALUNO));

            /* se for professor */
            usuario.setProfessor(authorities.contains(PROFESSOR));

            /* se for administrador */
            usuario.setAdministrador(authorities.contains(ADMINISTRADOR));

        }

        return usuario;
    }

    @Override
    public Usuario obterPorId(Integer id) {
        return usuarioDAO.findById(id).get();
    }

    /* Cargos de usuario */
    private static final SimpleGrantedAuthority ALUNO = new SimpleGrantedAuthority("ALUNO");
    private static final SimpleGrantedAuthority PROFESSOR = new SimpleGrantedAuthority("PROFESSOR");
    private static final SimpleGrantedAuthority ADMINISTRADOR = new SimpleGrantedAuthority("ADMINISTRADOR");

    @Override
    public void remover(Usuario usuario) {
        usuarioDAO.delete(usuario);
    }

}
