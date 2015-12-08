/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import modelo.Usuario;
import javax.persistence.Query;
import modelo.LoginUsuario;

/**
 *
 * @author Iury
 */


public class UsuarioDAOJPA extends DAOGenericoJPA<Long, Usuario> implements UsuarioDAO{

    public UsuarioDAOJPA() {
        super();
    }
    
    public String retornaSenha(String telefone){
        Query query = super.getEm().createNamedQuery("Login.RetornaSenha",LoginUsuario.class);

        query.setParameter("tel", telefone);
        
        return (String) query.getSingleResult();
    }
    
    @Override
    public Usuario getById(long pk) {
        return super.getById(pk);
    }
    
}
