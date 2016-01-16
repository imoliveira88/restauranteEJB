/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia.jpa;

import static acesso.Papel.*;
import javax.annotation.security.DeclareRoles;
import javax.annotation.security.PermitAll;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import static javax.ejb.TransactionAttributeType.REQUIRED;
import static javax.ejb.TransactionAttributeType.SUPPORTS;
import javax.ejb.TransactionManagement;
import static javax.ejb.TransactionManagementType.CONTAINER;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.validation.executable.ExecutableType;
import javax.validation.executable.ValidateOnExecution;
import modelo.Bandeira;

/**
 *
 * @author Iury
 */

@Stateless
@LocalBean
@DeclareRoles({FUNCIONARIO, CLIENTE})
@TransactionManagement(CONTAINER)
@TransactionAttribute(REQUIRED) 
@ValidateOnExecution(type = ExecutableType.NON_GETTER_METHODS)
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
    public Bandeira getByName(String nome) {
        Bandeira resultado;
        String consulta = "select b from Bandeira b where b.nome = :nome";
        TypedQuery<Bandeira> query = entityManager.createQuery(consulta, Bandeira.class);
        query.setParameter("nome",nome);
        
        try{
            resultado = query.getSingleResult();
            return resultado;
        }
        catch(NoResultException e){
            return null;
        }
    }
    
    @TransactionAttribute(SUPPORTS)
    @PermitAll   
    public boolean existeBandeira(Bandeira band){
        try{
            return getByName(band.getNome()) != null;
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
