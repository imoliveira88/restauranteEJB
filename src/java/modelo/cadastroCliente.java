/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import persistencia.*;

/**
 *
 * @author Magalhães Oliveira
 */
@ManagedBean(name = "cadastroC")
@SessionScoped
public class cadastroCliente{
    private String bandeira;
    private String numeroCartao;
    private String validade;
    private String tipologradouro;
    private String logradouro;
    private int numero;
    private String cep;
    private String cidade;
    private String estado;
    private String nome;
    private String telefone;
    private String senha;
    private String mensagem;
    /**
     * Creates a new instance of cadastroCliente
     */
    public cadastroCliente() {
    }

    public String getBandeira() {
        return bandeira;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public void setBandeira(String bandeira) {
        this.bandeira = bandeira;
    }

    public String getNumeroCartao() {
        return numeroCartao;
    }

    public void setNumeroCartao(String numeroCartao) {
        this.numeroCartao = numeroCartao;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getValidade() {
        return validade;
    }

    public void setValidade(String validade) {
        this.validade = validade;
    }

    public String getTipologradouro() {
        return tipologradouro;
    }

    public void setTipologradouro(String tipologradouro) {
        this.tipologradouro = tipologradouro;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
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

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }  
    
    public String cadastraCliente() throws ParseException{
        ClienteDAOJPA cli = new ClienteDAOJPA();
        
        SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");  
        Date data = formatador.parse(validade);
        
        Bandeira band = new Bandeira(this.bandeira);
        Cartao cartao = new Cartao(band,numeroCartao,data);
        Endereco endereco = new Endereco(tipologradouro,logradouro,numero,cep,cidade,estado);
        Cliente cliente = new Cliente(nome,senha,telefone,endereco,cartao);
        

        cli.save(cliente);
        
        this.setMensagem("Cadastro feito com sucesso! Realize login!");
        
        return "index.xhtml?faces-redirect=true";
    }
}
