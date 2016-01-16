/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia.jpa;

import static acesso.Papel.CLIENTE;
import static acesso.Papel.FUNCIONARIO;
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
import modelo.Prato;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.validation.executable.ExecutableType;
import javax.validation.executable.ValidateOnExecution;

@Stateless
@LocalBean
@DeclareRoles({FUNCIONARIO, CLIENTE})
@TransactionManagement(CONTAINER)
@TransactionAttribute(REQUIRED) 
public class PratoServico extends ServicoGenerico<Long, Prato>{

    public PratoServico() {
        super();
    }
    
    @Override
    @TransactionAttribute(SUPPORTS)
    @RolesAllowed({FUNCIONARIO})
    public void delete(Prato prato) throws Exception{
        entityManager.getTransaction().begin();
        Query query = entityManager.createNamedQuery("Prato.RetornaId");
        query.setParameter("nome", prato.getNome());
        
        Long id = (Long) query.getSingleResult();
        
        Prato p = entityManager.find(Prato.class,id);
        
        try{
            entityManager.remove(p);
        }catch(Exception e){
            
        }
    }

    @TransactionAttribute(SUPPORTS)
    @PermitAll
    public Prato getById(long pk) {
        return super.getById(pk);
    }
    
    @TransactionAttribute(SUPPORTS)
    @PermitAll
    public boolean existePrato(Prato p){
        String query = "select e from Prato e";
        List<Prato> pratos = entityManager.createQuery(query, Prato.class).getResultList();
        try{
            for(Prato prato : pratos){
                if(prato.equals(p)){
                    System.out.println("RETORNOU VERDADEIROO");
                    return true;
                }
            }
            System.out.println("RETORNOU FALSO");
            return false;
        }
        catch(NoResultException e){
            return false;
        }
    }
    
    @Override
    @TransactionAttribute(SUPPORTS)
    @PermitAll
    public List<Prato> findAll() {
        String query = "SELECT e FROM Prato e ORDER BY e.nome";
        List<Prato> pratos = entityManager.createQuery(query, Prato.class).getResultList();
        return pratos;
    }
    
    @Override
    @RolesAllowed({FUNCIONARIO})
    public void save(Prato b) {
        if(!existePrato(b)){
            entityManager.persist(b);
        }
    }
    
    
    
}
