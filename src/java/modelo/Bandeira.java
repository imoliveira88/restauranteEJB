/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.validator.constraints.NotBlank;

/**
 *
 * @author Magalhães Oliveira
 */
@ManagedBean(name = "bandeira")
@SessionScoped
@Entity
@Table(name = "TB_BANDEIRA")
public class Bandeira implements Serializable {
    private static final long serialVersionUID = 1L;
    
    public Bandeira(){
        this.cartoes = new ArrayList<>();
    }
    
    public Bandeira(String n){
        this.cartoes = new ArrayList<>();
        this.nome = n;
    }
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_BANDEIRA")
    private Long id;
    
    @OneToMany(mappedBy = "bandeira", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private final List<Cartao> cartoes;
    
    @NotBlank
    @Size(max = 20)
    @Column(name = "BANDEIRA_NOME")
    private String nome;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public void addCartao(Cartao c){
        this.cartoes.add(c);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Bandeira)) {
            return false;
        }
        Bandeira other = (Bandeira) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "corporativo.corporativo3.Bandeira[ id=" + id + " ]";
    }
    
}
