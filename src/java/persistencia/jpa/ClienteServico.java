/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia.jpa;

import acesso.Cliente;
import static acesso.Papel.CLIENTE;
import static acesso.Papel.FUNCIONARIO;
import javax.annotation.security.DeclareRoles;
import javax.annotation.security.PermitAll;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import static javax.ejb.TransactionAttributeType.REQUIRED;
import static javax.ejb.TransactionAttributeType.SUPPORTS;
import javax.ejb.TransactionManagement;
import static javax.ejb.TransactionManagementType.CONTAINER;
import javax.validation.executable.ExecutableType;
import javax.validation.executable.ValidateOnExecution;



@Stateless
@LocalBean
@DeclareRoles({FUNCIONARIO, CLIENTE})
@TransactionManagement(CONTAINER)
@TransactionAttribute(REQUIRED) 
@ValidateOnExecution(type = ExecutableType.NON_GETTER_METHODS)
public class ClienteServico extends ServicoGenerico<Long, Cliente>{

    public ClienteServico() {
        super();
    }
    
    @TransactionAttribute(SUPPORTS)
    @PermitAll 
    public Cliente getById(long pk) {
        return super.getById(pk);
    }
    
}
