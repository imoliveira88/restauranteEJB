/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia.jpa;

import static acesso.Papel.CLIENTE;
import static acesso.Papel.FUNCIONARIO;
import java.util.List;
import javax.persistence.*;
import acesso.Usuario;
import javax.annotation.security.DeclareRoles;
import javax.annotation.security.PermitAll;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import static javax.ejb.TransactionAttributeType.REQUIRED;
import static javax.ejb.TransactionAttributeType.SUPPORTS;
import javax.ejb.TransactionManagement;
import static javax.ejb.TransactionManagementType.CONTAINER;

@Stateless
@LocalBean
@DeclareRoles({FUNCIONARIO,CLIENTE})
@TransactionManagement(CONTAINER)
@TransactionAttribute(REQUIRED) 
public class UsuarioServico extends ServicoGenerico<Long, Usuario>{

    public UsuarioServico() {
        super();
    }
    
    @TransactionAttribute(SUPPORTS)
    @PermitAll
    public String retornaSenha(String telefone){
        Query query = entityManager.createNamedQuery("Usuario.RetornaSenha");
        
        query.setParameter("tel", telefone);
        
        try{
            return (String) query.getSingleResult();
        }
        catch(NoResultException e){
            return "  ";
        }
    }
    
    @TransactionAttribute(SUPPORTS)
    @PermitAll
    public long retornaId(String telefone){
        Query query = entityManager.createNamedQuery("Usuario.RetornaId");
        
        query.setParameter("tel", telefone);
        
        try{
            return (long) query.getSingleResult();
        }
        catch(NoResultException e){
            return 0;
        }
    }
    
    @TransactionAttribute(SUPPORTS)
    @PermitAll
    public String tipoUsuario(Usuario usu)throws NoResultException{
        Query query = entityManager.createNamedQuery("Usuario.loginCliente");
        
        query.setParameter("telefone", usu.getTelefone());
        
        Object tipoC;
        
        try{
            tipoC = query.getSingleResult();
            if(tipoC == null) return "F";
            else return "C";
        }
        catch(NoResultException e){
            return "F";
        }
    }
    
    @TransactionAttribute(SUPPORTS)
    @PermitAll
    public String tipoUsuario(String telefone, String senha)throws NoResultException{
        Query query = entityManager.createNamedQuery("Usuario.loginCliente");
        
        query.setParameter("telefone", telefone);
        
        Object tipoC;
        
        try{
            tipoC = query.getSingleResult();
            if(tipoC == null) return "F";
            else return "C";
        }
        catch(NoResultException e){
            return "F";
        }
    }
    
    //Retorna a id caso usuário exista e zero, caso não exista
    @TransactionAttribute(SUPPORTS)
    @PermitAll
    public long existeUsuario(Usuario usu){
        String query = "select e from Usuario e";
        List<Usuario> usuarios = entityManager.createQuery(query, Usuario.class).getResultList();
        try{
            for(Usuario usuario : usuarios){
                if(usuario.equals(usu)) return usuario.getId();
            }
            return 0;
        }
        catch(NoResultException e){
            return 0;
        }
    }
    
    @TransactionAttribute(SUPPORTS)
    @PermitAll
    public Usuario getById(long pk) {
        return super.getById(pk);
    }
    
}
