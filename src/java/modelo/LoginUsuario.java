/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import javax.faces.bean.*;
import javax.faces.context.FacesContext;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import persistencia.UsuarioDAOJPA;

/**
 *
 * @author Magalhães Oliveira
 */

@NamedQueries({
  @NamedQuery(name = " Login.RetornaSenha",
              query= " SELECT u.senha FROM Usuario u " +
                     " WHERE u.telefone = :tel")
})

@ManagedBean(name = "loginusuario")
@SessionScoped
@Entity
public class LoginUsuario implements Serializable{
 
    @Id
    private String telefone;
    private String senha;
    
    public LoginUsuario() {
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
    
    //Compara se o telefone digitado corresponde a um usuário válido, e, correspondendo,
    //compara a senha fornecida, com a senha que há no banco
    public boolean validaUsuario(){
        UsuarioDAOJPA ud = new UsuarioDAOJPA();
        return this.telefone.equals(ud.retornaSenha(this.telefone));
    }
    
    public String doLogin() {
        boolean valido = this.validaUsuario();
         try {   
             if (!valido) {
               FacesContext.getCurrentInstance().validationFailed();
               return "";
             }
             
             return "/index.xhtml?faces-redirect=true";
         } catch (Exception e) {
             FacesContext.getCurrentInstance().validationFailed();
             e.printStackTrace();
             return "";
         }
   
      }
    
}
