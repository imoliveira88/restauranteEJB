/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import persistencia.ItemPedidoDAO;
import persistencia.ItemPedidoDAOJPA;
import persistencia.PedidoDAO;
import persistencia.PedidoDAOJPA;

/**
 *
 * @author Magalhães Oliveira
 */
@ManagedBean(name = "pedidoMB")
@SessionScoped
public class PedidoMB {
    private Pedido pedido;
    private PedidoDAO pd;
    private Prato prato;
    private int quantidade;
    
    private String mensagem;

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
    
    public PedidoMB() {
        this.pedido = new Pedido();
        this.pd = new PedidoDAOJPA();
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public Prato getPrato() {
        return prato;
    }

    public void setPrato(Prato prato) {
        this.prato = prato;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
    
    public String adicionaItem(){
        ItemPedido ip = new ItemPedido(this.prato,this.quantidade,pedido);
        ItemPedidoDAO ipd = new ItemPedidoDAOJPA() {};
        ipd.save(ip);
        pedido.addItem(ip);
        setMensagem("Item adicionado ao pedido!");
        return "faces/cliente/pedido.xhtml";
    }
    
}
