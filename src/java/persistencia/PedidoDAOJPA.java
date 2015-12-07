/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import modelo.Pedido;
import javax.persistence.EntityManager;

/**
 *
 * @author Iury
 */
public class PedidoDAOJPA extends DAOGenericoJPA<Long, Pedido> implements PedidoDAO{

    public PedidoDAOJPA() {
        super();
    }
    
    @Override
    public Pedido getById(long pk) {
        return super.getById(pk);
    }
}
