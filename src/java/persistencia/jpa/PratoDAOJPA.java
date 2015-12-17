/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia.jpa;

import modelo.Prato;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import persistencia.PratoDAO;

/**
 *
 * @author Iury
 */
public class PratoDAOJPA extends DAOGenericoJPA<Long, Prato> implements PratoDAO{

    public PratoDAOJPA() {
        super();
    }
    
    @Override
    public void delete(Prato prato) {
        super.getEm().getTransaction().begin();
        Query query = super.getEm().createNamedQuery("Prato.RetornaId");
        query.setParameter("nome", prato.getNome());
        
        Long id = (Long) query.getSingleResult();
        
        super.getEm().remove(super.getEm().find(Prato.class,id));
        System.out.println("Apagandoooo");
        super.getEm().getTransaction().commit();
    }

    public Prato getById(long pk) {
        return super.getById(pk);
    }
    
}
