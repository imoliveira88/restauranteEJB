/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia.jpa;

import java.util.List;
import javax.persistence.NoResultException;
import modelo.Endereco;

/**
 *
 * @author Magalhães Oliveira
 */
public class EnderecoServico extends ServicoGenerico<Long, Endereco>{
    public EnderecoServico() {
        super();
    }
    
    public Endereco getById(long pk) {
        return super.getById(pk);
    }
    
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
