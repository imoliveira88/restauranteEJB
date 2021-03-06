package beans;

import persistencia.jpa.ClienteServico;
import java.text.ParseException;
import java.util.Date;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import modelo.Bandeira;
import modelo.Cartao;
import acesso.Cliente;
import javax.ejb.EJB;
import modelo.Endereco;
import persistencia.jpa.BandeiraServico;

@ManagedBean(name = "cadastroC")
@RequestScoped
public class ClienteMB{
    private String bandeira;
    private String numeroCartao;
    private Date validade;
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
    
    @EJB
    private BandeiraServico bs;
    
    @EJB
    private ClienteServico cli;
    
    /**
     * Creates a new instance of cadastroCliente
     */
    public ClienteMB() {
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

    public Date getValidade() {
        return validade;
    }

    public void setValidade(Date validade) {
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

        Cartao cartao = new Cartao();
        Bandeira band = new Bandeira(this.bandeira);
        if(bs.existeBandeira(band)) cartao.setBandeira(bs.getByName(band.getNome()));
        else cartao.setBandeira(band);
        
        cartao.setNumero(numeroCartao);
        cartao.setValidade(validade);
        
        Endereco endereco = new Endereco(tipologradouro,logradouro,numero,cep,cidade,estado);
        Cliente cliente = new Cliente(nome,senha,telefone,endereco,cartao);

        cli.save(cliente);
        
        this.setMensagem("Cadastro feito com sucesso! Realize login!");
        
        return "/public/login.xhtml?faces-redirect=true";
    }
}
