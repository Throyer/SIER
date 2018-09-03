/**
 * SAIER 2.0.0.
 */
package br.uel.ceca.cin.saier.persistence.models;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

/**
 * Classe entidade Cargo. Representa o cargo do usuario no sistema.
 *
 * Usada no SecurityConfigutration.
 *
 * @author Renato
 * @version (14/08/2018)
 */
@Entity
public class Cargo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String nome;

    @ManyToMany(cascade = CascadeType.DETACH, mappedBy = "cargos")
    private List<Usuario> usuarios;

    public Cargo() {
        //
    }

    public Cargo(String nome) {
        this.nome = nome;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }
    
}
