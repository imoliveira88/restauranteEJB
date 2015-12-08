/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import javax.persistence.NoResultException;
import modelo.Usuario;
import javax.persistence.Query;

/**
 *
 * @author Iury
 */


public class UsuarioDAOJPA extends DAOGenericoJPA<Long, Usuario> implements UsuarioDAO{

    public UsuarioDAOJPA() {
        super();
    }
    
    @Override
    public String retornaSenha(String telefone){
        Query query = super.getEm().createNamedQuery("Usuario.RetornaSenha");

        query.setParameter("tel", telefone);
        
        try{
            return (String) query.getSingleResult();
        }
        catch(NoResultException e){
            return "";
        }
    }
    
    @Override
    public Usuario getById(long pk) {
        return super.getById(pk);
    }
    
}
