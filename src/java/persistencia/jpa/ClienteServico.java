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
import acesso.Cliente;
import javax.ejb.EJB;


@Stateless
@LocalBean
@DeclareRoles({CLIENTE})
@TransactionManagement(CONTAINER)
@TransactionAttribute(REQUIRED) 
public class ClienteServico extends UsuarioServico{
    
    @EJB
    private GrupoServico grupoService;

    public ClienteServico() {
        super();
    }
    
    @TransactionAttribute(SUPPORTS)
    @PermitAll
    public void save(Cliente b) {
        if(existeUsuario(b) != 0){
            b.setGrupo(grupoService.getGrupo(CLIENTE));
            entityManager.persist(b);
        }
    }
    
    @Override
    public Cliente getById(long pk) {
        return (Cliente) entityManager.find(Cliente.class, pk);
    }
    
}