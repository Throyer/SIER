/**
 * SAIER 2.0.0.
 */
package br.uel.ceca.cin.saier.persistence.models;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import javax.validation.constraints.Size;

/**
 * Classe entidade Usuario.
 *
 * @author Renato
 * @version (14/08/2018)
 */
@Entity
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Size(min = 1, max = 255, message = "forneça um nome")
    @Column(nullable = false)
    private String nome;

    @Size(min = 1, max = 255, message = "forneça um sobrenome")
    @Column(nullable = false)
    private String sobrenome;

    @Size(min = 10, max = 255, message = "forneça um email valido")
    @Column(nullable = false, unique = true)
    private String email;

    @Size(min = 1, max = 255)
    @Column(nullable = false)
    private String senha;

    private Integer atividade;

    private String turma;

    @OneToMany(mappedBy = "usuario")
    private List<Edificio> edificios;

    @OneToMany(mappedBy = "usuario")
    private List<Noticia> noticias;

    @ManyToMany(cascade = CascadeType.DETACH)
    @JoinTable(name = "usuario_cargo",
            joinColumns = {
                @JoinColumn(name = "usuario_id")},
            inverseJoinColumns = {
                @JoinColumn(name = "cargo_id")})
    private List<Cargo> cargos;

    @Transient
    private boolean administrador;

    @Transient
    private boolean professor;

    @Transient
    private boolean aluno;

    public Usuario() {
        //
    }

    public Usuario(String nome, String sobrenome, String email, Integer atividade, String senha) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.email = email;
        this.atividade = atividade;
        this.senha = senha;
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

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Integer getAtividade() {
        return atividade;
    }

    public void setAtividade(Integer atividade) {
        this.atividade = atividade;
    }

    public String getTurma() {
        return turma;
    }

    public void setTurma(String turma) {
        this.turma = turma;
    }

    public List<Edificio> getEdificios() {
        return edificios;
    }

    public void setEdificios(List<Edificio> edificios) {
        this.edificios = edificios;
    }

    public List<Noticia> getNoticias() {
        return noticias;
    }

    public void setNoticias(List<Noticia> noticias) {
        this.noticias = noticias;
    }

    public List<Cargo> getCargos() {
        return cargos;
    }

    public void setCargos(List<Cargo> cargos) {
        this.cargos = cargos;
    }

    public boolean isAdministrador() {
        return administrador;
    }

    public void setAdministrador(boolean administrador) {
        this.administrador = administrador;
    }

    public boolean isProfessor() {
        return professor;
    }

    public void setProfessor(boolean professor) {
        this.professor = professor;
    }

    public boolean isAluno() {
        return aluno;
    }

    public void setAluno(boolean aluno) {
        this.aluno = aluno;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Usuario other = (Usuario) obj;
        return Objects.equals(this.id, other.id);
    }

    @Override
    public String toString() {
        return "Usuario{" + "id=" + id + ", nome=" + nome + ", sobrenome=" + sobrenome + ", email=" + email + ", senha=" + senha + ", atividade=" + atividade + ", turma=" + turma + '}';
    }

}
