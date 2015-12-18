/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia.jpa;

import java.util.List;
import modelo.Prato;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
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
        
        Prato p = super.getEm().find(Prato.class,id);
        
        super.getEm().remove(p);
        System.out.println("Apagandoooo");
        super.getEm().getTransaction().commit();
    }

    public Prato getById(long pk) {
        return super.getById(pk);
    }
    
    public boolean existePrato(Prato p){
        String query = "select e from Prato e";
        List<Prato> pratos = super.getEm().createQuery(query, Prato.class).getResultList();
        try{
            for(Prato prato : pratos){
                if(prato.equals(p)) return true;
            }
            return false;
        }
        catch(NoResultException e){
            return false;
        }
    }
    
    @Override
    public void save(Prato b) {
        super.getEm().getTransaction().begin();
        if(!existePrato(b)){
            super.getEm().persist(b);
            super.getEm().getTransaction().commit();
        }
    }
    
    
    
}
