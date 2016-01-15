/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia.jpa;

import static acesso.Papel.CLIENTE;
import static acesso.Papel.FUNCIONARIO;
import javax.annotation.security.DeclareRoles;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import static javax.ejb.TransactionAttributeType.REQUIRED;
import javax.ejb.TransactionManagement;
import static javax.ejb.TransactionManagementType.CONTAINER;
import javax.validation.executable.ExecutableType;
import javax.validation.executable.ValidateOnExecution;
import modelo.ItemPedido;

@Stateless
@LocalBean
@DeclareRoles({FUNCIONARIO, CLIENTE})
@TransactionManagement(CONTAINER)
@TransactionAttribute(REQUIRED) 
@ValidateOnExecution(type = ExecutableType.NON_GETTER_METHODS)
public class ItemPedidoServico extends ServicoGenerico<Long, ItemPedido>{

    public ItemPedidoServico() {
        super();
    }
    
}
