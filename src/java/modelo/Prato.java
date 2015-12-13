/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.validator.constraints.NotBlank;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;
import persistencia.PratoDAO;
import persistencia.PratoDAOJPA;

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
    
    public Prato(){
        this.pratos = new ArrayList<>();
    }
    
    public Prato(String nome, double preco, String descricao){
        this.nome = nome;
        this.preco = preco;
        this.descricao = descricao;
        this.imagem = imagem;
    }
    
    public Prato(String nome, double preco, String descricao, byte[] imagem){
        this.nome = nome;
        this.preco = preco;
        this.descricao = descricao;
        this.imagem = imagem;
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
    
    @Column(name = "PRATO_IMAGEM")
    private byte[] imagem;
    
    @Transient
    private String mensagem;
    
    @Transient
    private ArrayList<Prato> pratos;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
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

    public byte[] getImagem() {
        return imagem;
    }

    public ArrayList<Prato> getPratos() {
        return pratos;
    }

    public void setPratos(ArrayList<Prato> pratos) {
        this.pratos = pratos;
    }

    public void setImagem(byte[] imagem) {
        this.imagem = imagem;
    }
    
    public String cadastraPrato(){
        PratoDAO p = new PratoDAOJPA();
        p.save(this);
        setMensagem("Prato cadastrado com sucesso!");
        pratos.add(this);
        return "/funcionario/cadastro_prato.xhtml?faces-redirect=true";
    }
    
    @Override
    public String toString() {
        return "Nome: " + this.nome + " Descrição: " + this.descricao + " Preco: " + this.preco;
    }
    
    public void fileUpload(FileUploadEvent event) throws IOException {
        try {
            //Cria um arquivo UploadFile, para receber o arquivo do evento
            UploadedFile arq = event.getFile();
            //Transformar a imagem em bytes para salvar em banco de dados
            this.imagem = event.getFile().getContents();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
}
