/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.validator.constraints.NotBlank;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Magalhães Oliveira
 */
@ManagedBean(name = "prato")
@SessionScoped
@Entity
@Table(name = "TB_PRATO")
public class Prato implements Serializable {
    private static final long serialVersionUID = 1L;
    
    public Prato(){}
    
    public Prato(String nome, double preco, String descricao){
        this.nome = nome;
        this.preco = preco;
        this.descricao = descricao;
    }
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_PRATO")
    private Long id;
    
    @NotBlank
    @Size(min = 3, max = 30)
    @Column(name = "PRATO_NOME")
    private String nome;
    
    @NotNull
    @Min(0)
    @Column(name = "PRATO_PRECO")
    private Double preco;
    
    @NotBlank
    @Size(min = 3, max = 40)
    @Column(name = "PRATO_DESCRICAO")
    private String descricao;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
   
    
    @Override
    public String toString() {
        return "Nome: " + this.nome + " Descrição: " + this.descricao + " Preco: " + this.preco;
    }
    
}
