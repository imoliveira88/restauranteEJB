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
import java.util.List;
import javax.ejb.EJB;
import javax.persistence.NoResultException;


@Stateless
@LocalBean
@DeclareRoles({CLIENTE})
@TransactionManagement(CONTAINER)
@TransactionAttribute(REQUIRED) 
public class ClienteServico extends ServicoGenerico<Cliente,Long>{
    
    @EJB
    private GrupoServico grupoService;

    public ClienteServico() {
        super();
    }
    
    @TransactionAttribute(SUPPORTS)
    @PermitAll
    public long existeCliente(Cliente usu){
        String query = "select e from Cliente e";
        List<Cliente> clientes = entityManager.createQuery(query, Cliente.class).getResultList();
        try{
            for(Cliente cliente : clientes){
                if(cliente.equals(usu)) return cliente.getId();
            }
            return 0;
        }
        catch(NoResultException e){
            return 0;
        }
    }
    
    @TransactionAttribute(SUPPORTS)
    @PermitAll
    public void save(Cliente b) {
        if(existeCliente(b) != 0){
            b.setGrupo(grupoService.getGrupo(CLIENTE));
            entityManager.persist(b);
        }
    }
    
    public Cliente getById(long pk) {
        return (Cliente) entityManager.find(Cliente.class, pk);
    }
    
}