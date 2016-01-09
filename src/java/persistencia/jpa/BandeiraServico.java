/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia.jpa;

import javax.annotation.security.PermitAll;
import javax.ejb.TransactionAttribute;
import static javax.ejb.TransactionAttributeType.SUPPORTS;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import modelo.Bandeira;

/**
 *
 * @author Iury
 */
public class BandeiraServico extends ServicoGenerico<Long, Bandeira>{

    public BandeiraServico() {
        super();
    }

    @TransactionAttribute(SUPPORTS)
    @PermitAll   
    public Bandeira getById(long pk) {
        return super.getById(pk);
    }
    
    @TransactionAttribute(SUPPORTS)
    @PermitAll   
    public boolean existeBandeira(Bandeira band){
        Bandeira resultado;
        String consulta = "select b from Bandeira b where b.nome = :nome";
        TypedQuery<Bandeira> query = entityManager.createQuery(consulta, Bandeira.class);
        query.setParameter("nome",band.getNome());
        try{
            resultado = query.getSingleResult();
            return true;
        }
        catch(NoResultException e){
            return false;
        }
    }
    
    @Override
    @TransactionAttribute(SUPPORTS)
    @PermitAll 
    public void save(Bandeira b){
        if(!existeBandeira(b)){
            entityManager.persist(b);
        }
    }
}
