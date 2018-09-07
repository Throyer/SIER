/*
 * S.A.I.E.R 2.0.0 | https://github.com/Throyer/SAIER | 2018.
 */
package br.uel.ceca.cin.saier.persistence.models;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Size;

/**
 * Classe entidade CEP.
 *
 * @author Renato
 * @version (14/08/2018)
 */
@Entity
public class CEP implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /* Endereço CEP */
    @Size(min = 8, max = 8, message = "forneça um CEP")
    @Column(nullable = false, unique = true)
    private String cep;
    
    @Column(nullable = false)
    private String rua;
    
    @Column(nullable = false)
    private String bairro;
    
    @Column(nullable = false)
    private String cidade;
    
    @Column(nullable = false)
    private String estado;

    /* Edificios nesse CEP */
    @OneToMany(mappedBy = "cep")
    private List<Edificio> edificios;

    public CEP() {
        //
    }

    public CEP(Integer id, String cep, String rua, String bairro, String cidade, String estado) {
        this.id = id;
        this.cep = cep;
        this.rua = rua;
        this.bairro = bairro;
        this.cidade = cidade;
        this.estado = estado;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public List<Edificio> getEdificios() {
        return edificios;
    }

    public void setEdificios(List<Edificio> edificios) {
        this.edificios = edificios;
    }

    @Override
    public String toString() {
        return "Cep{" + "id=" + id + ", cep=" + cep + ", rua=" + rua + ", bairro=" + bairro + ", cidade=" + cidade + ", estado=" + estado + '}';
    }

}
