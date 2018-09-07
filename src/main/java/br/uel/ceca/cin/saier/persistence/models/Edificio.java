/*
 * S.A.I.E.R 2.0.0 | https://github.com/Throyer/SAIER | 2018.
 */
package br.uel.ceca.cin.saier.persistence.models;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * Classe entidade Edificio.
 *
 * @author Renato
 * @version (14/08/2018)
 */
@Entity
public class Edificio implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /* Nome real do edificio */
    private String nome;

    /* Nome no qual esse edificio é conhecido */
    @Size(min = 1, max = 255, message = "{edificio.nome-fantasia.tamanho}")
    @Column(nullable = false)
    private String nomeConhecido;

    /* Data de construção */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private Date dataConstrucao;

    /* Numero de andares */
    @Column(nullable = false)
    private int numeroAndares;

    /* Numero (endereço) */
    @Column(nullable = false)
    private int numero;

    /* A fonte da coleta dos dados desse edificio */
    @Column(nullable = false)
    private String fonteColeta;

    /* A data em que os dados desse edificio foram coletados */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Calendar dataColeta;

    /* Informações adicionais sobre esse edificio */
    @Size(max = 100)
    private String informacoes;

    /* CEP do edificio */
    @ManyToOne
    private CEP cep;

    /* Usuario responsavel pelo cadastro desse edificio no sistema */
    @ManyToOne
    private Usuario usuario;

    public Edificio() {
        //
    }

    public Edificio(String nome, String nomeConhecido, Date dataConstrucao, int numeroAndares, int numero, String fonteColeta, Calendar dataColeta, String informacoes) {
        this.nome = nome;
        this.nomeConhecido = nomeConhecido;
        this.dataConstrucao = dataConstrucao;
        this.numeroAndares = numeroAndares;
        this.numero = numero;
        this.fonteColeta = fonteColeta;
        this.dataColeta = dataColeta;
        this.informacoes = informacoes;
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

    public String getNomeConhecido() {
        return nomeConhecido;
    }

    public void setNomeConhecido(String nomeConhecido) {
        this.nomeConhecido = nomeConhecido;
    }

    public Date getDataConstrucao() {
        return dataConstrucao;
    }

    public void setDataConstrucao(Date dataConstrucao) {
        this.dataConstrucao = dataConstrucao;
    }

    public int getNumeroAndares() {
        return numeroAndares;
    }

    public void setNumeroAndares(int numeroAndares) {
        this.numeroAndares = numeroAndares;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getFonteColeta() {
        return fonteColeta;
    }

    public void setFonteColeta(String fonteColeta) {
        this.fonteColeta = fonteColeta;
    }

    public Calendar getDataColeta() {
        return dataColeta;
    }

    public void setDataColeta(Calendar dataColeta) {
        this.dataColeta = dataColeta;
    }

    public String getInformacoes() {
        return informacoes;
    }

    public void setInformacoes(String informacoes) {
        this.informacoes = informacoes;
    }

    public CEP getCep() {
        return cep;
    }

    public void setCep(CEP cep) {
        this.cep = cep;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public String toString() {
        return "Edificio{" + "id=" + id + ", nome=" + nome + ", nomeConhecido=" + nomeConhecido + ", dataConstrucao=" + dataConstrucao + ", numeroAndares=" + numeroAndares + ", numero=" + numero + ", fonteColeta=" + fonteColeta + ", dataColeta=" + dataColeta + ", informacoes=" + informacoes + ", cep=" + cep + '}';
    }

}
