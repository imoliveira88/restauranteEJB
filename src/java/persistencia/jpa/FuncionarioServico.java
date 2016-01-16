/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia.jpa;

import acesso.Cliente;
import acesso.Funcionario;
import static acesso.Papel.FUNCIONARIO;
import java.util.List;
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
import javax.persistence.NoResultException;



@Stateless
@LocalBean
@DeclareRoles({FUNCIONARIO})
@TransactionManagement(CONTAINER)
@TransactionAttribute(REQUIRED) 
public class FuncionarioServico extends ServicoGenerico<Cliente,Long>{

    @EJB
    private GrupoServico grupoService;
    
    public FuncionarioServico() {
        super();
    }
    
    @TransactionAttribute(SUPPORTS)
    @PermitAll
    public long existeFuncionario(Funcionario usu){
        String query = "select e from Funcionario e";
        List<Funcionario> funcionarios = entityManager.createQuery(query, Funcionario.class).getResultList();
        try{
            for(Funcionario funcionario : funcionarios){
                if(funcionario.equals(usu)) return funcionario.getId();
            }
            return 0;
        }
        catch(NoResultException e){
            return 0;
        }
    }
    
    @TransactionAttribute(SUPPORTS)
    @PermitAll
    public void save(Funcionario b) {
        if(existeFuncionario(b) != 0){
            b.setGrupo(grupoService.getGrupo(FUNCIONARIO));
            entityManager.persist(b);
        }
    }
    
}
