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
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import static javax.ejb.TransactionAttributeType.REQUIRED;
import static javax.ejb.TransactionAttributeType.SUPPORTS;
import javax.ejb.TransactionManagement;
import static javax.ejb.TransactionManagementType.CONTAINER;
import javax.persistence.NoResultException;
import javax.validation.executable.ExecutableType;
import javax.validation.executable.ValidateOnExecution;
import modelo.Endereco;

/**
 *
 * @author Magalhães Oliveira
 */

@Stateless
@LocalBean
@DeclareRoles({FUNCIONARIO, CLIENTE})
@TransactionManagement(CONTAINER)
@TransactionAttribute(REQUIRED) 
@ValidateOnExecution(type = ExecutableType.NON_GETTER_METHODS)
public class EnderecoServico extends ServicoGenerico<Long, Endereco>{
    public EnderecoServico() {
        super();
    }
    
    @TransactionAttribute(SUPPORTS)
    @PermitAll
    public Endereco getById(long pk) {
        return super.getById(pk);
    }
    
    @TransactionAttribute(SUPPORTS)
    @PermitAll
    public boolean existeEndereco(Endereco end){
        String query = "select e from Endereco e";
        List<Endereco> enderecos = entityManager.createQuery(query, Endereco.class).getResultList();
        try{
            for(Endereco endereco : enderecos){
                if(endereco.equals(end)) return true;
            }
            return false;
        }
        catch(NoResultException e){
            return false;
        }
    }
    
    @TransactionAttribute(SUPPORTS)
    @PermitAll
    @Override
    public void save(Endereco b) {
        entityManager.getTransaction().begin();
        if(!existeEndereco(b)){
            entityManager.persist(b);
        }
        else entityManager.merge(b);
        entityManager.getTransaction().commit();
    }
}
