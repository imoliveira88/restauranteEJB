/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia.jpa;

import acesso.Funcionario;
import static acesso.Papel.FUNCIONARIO;
import javax.annotation.security.DeclareRoles;
import javax.annotation.security.PermitAll;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import static javax.ejb.TransactionAttributeType.REQUIRED;
import static javax.ejb.TransactionAttributeType.SUPPORTS;
import javax.ejb.TransactionManagement;
import static javax.ejb.TransactionManagementType.CONTAINER;



@Stateless
@LocalBean
@DeclareRoles({FUNCIONARIO})
@TransactionManagement(CONTAINER)
@TransactionAttribute(REQUIRED) 
public class FuncionarioServico extends UsuarioServico{

    @EJB
    private GrupoServico grupoService;
    
    public FuncionarioServico() {
        super();
    }
    
    @TransactionAttribute(SUPPORTS)
    @PermitAll
    public void save(Funcionario b) {
        if(existeUsuario(b) != 0){
            b.setGrupo(grupoService.getGrupo(FUNCIONARIO));
            entityManager.persist(b);
        }
    }
    
}
