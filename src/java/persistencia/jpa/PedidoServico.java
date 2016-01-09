/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia.jpa;

import java.util.ArrayList;
import java.util.List;
import modelo.Pedido;
import javax.persistence.NoResultException;
import javax.persistence.Query;

/**
 *
 * @author Iury
 */
public class PedidoServico extends ServicoGenerico<Long, Pedido>{

    public PedidoServico() {
        super();
    }
    
 
    public Pedido getById(long pk) {
        return super.getById(pk);
    }
    
    public List<Pedido> pedidosNAtendidos()throws NoResultException{
        Query query = entityManager.createNamedQuery("Pedido.NaoAtendido");
        List<Pedido> pedidos;
        
        try{
            pedidos = query.getResultList();
            return pedidos;
        }
        catch(NoResultException e){
            return new ArrayList<Pedido>();
        }
    }
    
    public List<Pedido> pedidosAtendidos()throws NoResultException{
        Query query = entityManager.createNamedQuery("Pedido.Atendido");
        List<Pedido> pedidos;
        
        try{
            pedidos = query.getResultList();
            return pedidos;
        }
        catch(NoResultException e){
            return new ArrayList<Pedido>();
        }
    }
    
    public void pedidoAtende(Long id)throws NoResultException{
        entityManager.getTransaction().begin();
        Query query = entityManager.createNamedQuery("Pedido.Atende");
        query.setParameter("id", id);
        
        int linhasAfetadas = query.executeUpdate();
        entityManager.getTransaction().commit();
    }
}
