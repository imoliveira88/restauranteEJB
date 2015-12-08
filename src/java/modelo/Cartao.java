/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import java.util.Date;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.validator.constraints.CreditCardNumber;

/**
 *
 * @author Iury
 */

@ManagedBean(name = "cartao")
@SessionScoped
@Entity
@Table(name = "TB_CARTAO")
public class Cartao implements Serializable{
    private static final long serialVersionUID = 1L;
    
    public Cartao(Bandeira b, String num, Date valid){
        this.bandeira = b;
        this.numero = num;
        this.validade = valid;
    }
    
    public Cartao(){};
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_CARTAO")
    private Long id;
    
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_BANDEIRA", referencedColumnName = "ID_BANDEIRA")
    private Bandeira bandeira;
    
    @NotNull
    @CreditCardNumber
    @Column(name = "CARTAO_NUMERO")
    private String numero;
    
    @NotNull
    @Future
    @Temporal(TemporalType.DATE)
    @Column(name = "CARTAO_VALIDADE")
    private Date validade;

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Bandeira getBandeira() {
        return bandeira;
    }

    public void setBandeira(Bandeira bandeira) {
        this.bandeira = bandeira;
    }

    public Date getValidade() {
        return validade;
    }

    public void setValidade(Date validade) {
        this.validade = validade;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
  
}

