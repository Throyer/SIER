/*
 * S.A.I.E.R 2.0.0 | https://github.com/Throyer/SAIER | 2018.
 */
package br.uel.ceca.cin.saier.persistence.models;

import java.io.Serializable;
import java.util.Calendar;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

/**
 * Classe entidade Noticia.
 *
 * @author Renato
 * @version (14/08/2018)
 */
@Entity
public class Noticia implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /* Nome da publicacao */
    @Size(max = 60)
    @Column(nullable = false)
    private String nome;

    /* Descrição */
    @Size(max = 1000)
    private String descicao;

    /* Data da publicação */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Calendar dataPublicacao;

    /* Usuario responsavel pela publicação */
    @ManyToOne
    private Usuario usuario;

    public Noticia() {
        //
    }

    public Noticia(Integer id) {
        this.id = id;
    }

    public Noticia(Integer id, String nome, String descicao, Calendar data) {
        this.id = id;
        this.nome = nome;
        this.descicao = descicao;
        this.dataPublicacao = data;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescicao() {
        return descicao;
    }

    public void setDescicao(String descicao) {
        this.descicao = descicao;
    }

    public Calendar getDataPublicacao() {
        return dataPublicacao;
    }

    public void setDataPublicacao(Calendar data) {
        this.dataPublicacao = data;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public String toString() {
        return "Noticia{" + "id=" + id + ", nome=" + nome + ", descicao=" + descicao + ", dataPublicacao=" + (dataPublicacao == null ? "null" : dataPublicacao.getTime()) + ", usuario=" + usuario + '}';
    }

}
