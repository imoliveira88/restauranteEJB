/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia.jpa;

import static acesso.Papel.CLIENTE;
import static acesso.Papel.FUNCIONARIO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.security.DeclareRoles;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import static javax.ejb.TransactionAttributeType.REQUIRED;
import static javax.ejb.TransactionAttributeType.SUPPORTS;
import javax.ejb.TransactionManagement;
import static javax.ejb.TransactionManagementType.CONTAINER;
import modelo.Pedido;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.validation.executable.ExecutableType;
import javax.validation.executable.ValidateOnExecution;


@Stateless
@LocalBean
@DeclareRoles({FUNCIONARIO, CLIENTE})
@TransactionManagement(CONTAINER)
@TransactionAttribute(REQUIRED) 
@ValidateOnExecution(type = ExecutableType.NON_GETTER_METHODS)
public class PedidoServico extends ServicoGenerico<Long, Pedido>{

    public PedidoServico() {
        super();
    }
    
    @TransactionAttribute(SUPPORTS)
    @PermitAll
    public Pedido getById(long pk) {
        return super.getById(pk);
    }
    
    @RolesAllowed({FUNCIONARIO})
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
    
    @RolesAllowed({FUNCIONARIO})
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
    
    @RolesAllowed({FUNCIONARIO})
    public void pedidoAtende(Long id)throws NoResultException{
        entityManager.getTransaction().begin();
        Query query = entityManager.createNamedQuery("Pedido.Atende");
        query.setParameter("id", id);
        
        int linhasAfetadas = query.executeUpdate();
        entityManager.getTransaction().commit();
    }
}
